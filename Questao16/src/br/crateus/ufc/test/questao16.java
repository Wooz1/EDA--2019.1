package br.crateus.ufc.test;

import br.ufc.crateus.st.RWayTries;

public class questao16 {

	public static void main(String[] args) {

		RWayTries<Integer> q16 = new RWayTries<Integer>();
		
		q16.put("shells", 0);
		q16.put("sells", 0);
		q16.put("by", 0);
		q16.put("are", 0);
		q16.put("the", 0);
		q16.put("c", 0);
		q16.put("she", 0);
		q16.put("he", 0);
		
		Iterable<String> teste = q16.keysThatMatch("..");
		for(String i : teste) {
			System.out.println(i);
		}
		
	}

}
