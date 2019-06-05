package br.ufc.crateus.st;

public interface Map<K, V> {
	void put(K key, V value);
	V get(K key);
	void remove(K key);
	Iterable<K> keys();
	boolean contains(K key);
}
