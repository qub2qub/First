package ru.mars.Main;

import java.util.LinkedHashMap;
import java.util.Map;

public class MyCache<K, V> extends LinkedHashMap<K, V> {
	
	private final int cap;

	public MyCache(int initialCapacity) {
		super(initialCapacity+1, 2.0f, true);
		cap=initialCapacity;
	}
	
	@Override
	protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
		return this.size() > cap;
	}
	
}
