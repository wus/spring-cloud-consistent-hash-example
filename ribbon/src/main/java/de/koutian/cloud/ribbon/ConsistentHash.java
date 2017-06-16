package de.koutian.cloud.ribbon;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.loadbalancer.Server;

public class ConsistentHash {
	
    private static Logger log = LoggerFactory.getLogger(ConsistentHash.class);

	private static final int MAX_VALUE = 360;
	private final SortedMap<Long, Server> circle = new TreeMap<Long, Server>();
	private final Map<Server, Long> servers = new HashMap<Server, Long>();
	
	private Long hash(final Long key) {
		return key % MAX_VALUE;
	}

	public void add(final Set<Server> nodes) {
		final int size = circle.size();
		for (Server node : nodes) {
			for (long n = 0; n < MAX_VALUE; n += MAX_VALUE / (size + nodes.size())) {
				long key = n % MAX_VALUE;
				if (!circle.containsKey(key)) {
					circle.put(key, node);
					servers.put(node, key);
					log.info("new Server has been added with Hash {} Host {}", key, node.getHostPort());
					break;
				}
			}
		}
	}

	public void update(final List<Server> nodes) {
		Set<Server> nodeSet = new HashSet<Server>();
		for (Server node : nodes) {
			nodeSet.add(node);
		}
		// remove down nodes
		for (Server server : servers.keySet()) {
			if (!nodeSet.contains(server)) {
				remove(server);
			} else {
				nodeSet.remove(server);
			}
		}
		// add new nodes
		add(nodeSet);
	}
	
	public boolean remove(final Server node) {
		if (node == circle.remove(servers.get(node))) {
			log.info("Server with Hash {} Host {} has been removed", servers.get(node), node.getHostPort());
			servers.remove(node);
			return true;
		}
		return false;
	}
	
	public boolean exists(final Server node) {
		return servers.containsKey(node);
	}

	public Server get(final Long key) { 
		Long hash = hash(key);
		if (!circle.containsKey(hash)) {
			SortedMap<Long, Server> tailMap = circle.tailMap(hash);
			hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
		}
        final Server server = circle.get(hash);
        log.info("server with Hash {} Host {} has been selected", hash, server.getHostPort());
        return server;
	}
}
