package de.koutian.cloud.ribbon;

import org.springframework.stereotype.Service;

@Service
public class KeyHolder {

	private ThreadLocal<String> keys = new ThreadLocal<String>();

	public void setKey(final String key) {
		keys.set(key);
	}

	public String getKey() {
		return keys.get();
	}

	public void removeKey() {
		keys.remove();
	}

}
