package br.ufc.crateus.st;

import java.util.LinkedList;
import java.util.Queue;

public class RWayTries<V> {
	private static final int R = 256;

	private static class Node {
		Object value;
		Node[] next = new Node[R];
	}

	private Node root = new Node();

	@SuppressWarnings("unchecked")
	private V getValue(Node r) {
		if (r == null)
			return null;
		return (V) r.value;
	}

	private Node put(Node r, String key, int i, V value) {
		if (r == null)
			r = new Node();
		if (key.length() == i) {
			r.value = value;
		} else {
			char ch = key.charAt(i);
			r.next[ch] = put(r.next[ch], key, i + 1, value);
		}
		return r;
	}

	public void put(String key, V value) {
		root = put(root, key, 0, value);
	}

	private Node getNode(Node r, String key, int i) {
		if (r == null)
			return null;
		if (i == key.length()) {
			return r;
		} else {
			char ch = key.charAt(i);
			return getNode(r.next[ch], key, i + 1);
		}
	}

	public V get(String key) {
		Node r = getNode(root, key, 0);
		return r == null ? null : getValue(r);
	}

	private void collect(Queue<String> keys, Node r, String str) {
		if (r != null) {
			if (r.value != null)
				keys.add(str);
			for (char i = 0; i < r.next.length; i++)
				collect(keys, r.next[i], str + i);
		}
	}

	//public Iterable<String> keysWithPrefix(String prefix) {
	//	Node r = getNode(root, prefix, 0);
	//	Queue<String> keys = new LinkedList<String>();
	//	collect(keys, r, prefix);
	//	return keys;
	//}

	private int search(Node r, String query, int d, int lenght) {
		if (r == null)
			return lenght;
		if (r.value != null)
			lenght = d;
		if (query.length() == d)
			return lenght;
		char ch = query.charAt(d);

		return search(r.next[ch], query, d + 1, lenght);
	}

	public String longestPrefixOf(String str) {
		int lenght = search(root, str, 0, 0);

		return str.substring(0, lenght);

	}

	public Iterable<String> keys() {
		Queue<String> keys = new LinkedList<String>();
		collect(keys, root, "");
		return keys;
	}

	public Iterable<String> keysThatMatch(String str) {
		Queue<String> keys = new LinkedList<>();
		KeysThatMatch(root, keys, str, 0, "");
		return keys;

	}


	public String convert(String str) {
		str = str.toLowerCase();
		char[] filme = str.toCharArray();
		for (int i = 0; i < str.length(); i++) {
			if ((filme[i] >= 'a' && filme[i] <= 'z') || filme[i] == ' ' || (filme[i] >= '0' && filme[i] <= '9'))
				continue;
			else if (filme[i] == 'ç')
				filme[i] = 'c';
			else if (filme[i] == 'ñ')
				filme[i] = 'n';
			else if (filme[i] == 'ü' || filme[i] == 'û' || filme[i] == 'ù' || filme[i] == 'Ü' || filme[i] == 'ú'
					|| filme[i] == 230 || (filme[i] >= 'Ú' && filme[i] <= 'Ù'))
				filme[i] = 'u';
			else if (filme[i] == 'é' || (filme[i] >= 'ê' && filme[i] <= 'è'))
				filme[i] = 'e';
			else if ((filme[i] == 'â' && filme[i] <= 134) || filme[i] == 142 || filme[i] == 143 || filme[i] == 'á'
					|| filme[i] == 166 || filme[i] == 'ã')
				filme[i] = 'a';
			else if ((filme[i] == 'ï' && filme[i] <= 141) || filme[i] == 'í')
				filme[i] = 'i';
			else if ((filme[i] == 'ô' && filme[i] <= 'ò') || filme[i] == 'Ö' || filme[i] == 'ó' || filme[i] == 167
					|| filme[i] == 208)
				filme[i] = 'o';
			else if (filme[i] == 'ÿ' || filme[i] == 'ý')
				filme[i] = 'y';
			else if (filme[i] == 158)
				filme[i] = 'x';
			else if (filme[i] == 159)
				filme[i] = 'f';
			else if (filme[i] == 169)
				filme[i] = 'r';
			else if (filme[i] == 184 || filme[i] == 189)
				filme[i] = 'c';
			else if (filme[i] == 251)
				filme[i] = '1';
			else if (filme[i] == 252)
				filme[i] = '2';
			else if (filme[i] == 253)
				filme[i] = '3';
			else
				filme[i] = '?';
		}
		String str2 = "";
		for (int i = 0; i < str.length(); i++) {
			str2 += filme[i];
		}
		return str2;
	}
	
	public Iterable<String> keysWithPrefix(String str) {
		Queue<String> keys = new LinkedList<>();
		keysWithPrefix(root, keys, str, 0, "");
		return keys;
	}
	private void keysWithPrefix(Node r, Queue keys, String str, int k, String acc) {
		if (r != null) {
			
				if (r.value != null) {
					keys.add(acc);
					} 
				else {
				char ch = str.charAt(k);
				if (ch != '?') {
					keysWithPrefix(r.next[ch], keys, str, k + 1, acc + ch);
				} else {
					for (char i = 0; i < r.next.length; i++)
						keysWithPrefix(r.next[i], keys, str, k + 1, acc + i);
				}
			}
		}
	}

	private void KeysThatMatch(Node r, Queue keys, String str, int k, String acc) {
		if (r != null) {
			if (k == str.length()) {
				if (r.value != null) {
					keys.add(acc);
				}
			} else {
				char ch = str.charAt(k);
				if (ch != '?') {
					KeysThatMatch(r.next[ch], keys, str, k + 1, acc + ch);
				} else {
					for (char i = 0; i < r.next.length; i++)
						KeysThatMatch(r.next[i], keys, str, k + 1, acc + i);
				}
			}
		}
	}
}
