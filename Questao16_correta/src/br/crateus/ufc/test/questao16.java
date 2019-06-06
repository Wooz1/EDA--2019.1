package br.crateus.ufc.test;

import java.util.Scanner;

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

		Scanner s1 = new Scanner(System.in);

		while (true) {
			System.out.print("Procure por um filme: ");
			String mv = s1.nextLine();
			if (mv.equals("sair"))
				break;
			
			System.out.println("Sugestões");
			mv = q16.convert(mv);
			System.out.println("Os filmes exatamente com esse nome:");
			Iterable<String> teste = q16.keysThatMatch(mv);
			for (String i : teste) {
				System.out.println(i);
			}
			System.out.println("Os filmes com esse prefixo:");
			teste = q16.keysWithPrefix(mv);
			for (String i : teste) {
				System.out.println(i);
			}
			System.out.println("Os filmes com o prefixo mais longo:");
			String a = q16.longestPrefixOf(mv);
				System.out.println(a);
			
		}

	}
}
///O programa faz a conversão das dos simbolos colocados no scanner para o simbolos elementares pedidos na questão
//além disso, troquei o coringa '.' pelo '?' para ajudar no código. Não fiz arquivos para filmes coloquei apenas strings
//pensadas para o caso e adicionei na RWAYTrie ja que era apenas para teste. Enfim, o programa não é o mais completo mas
//atende as necessidades básicas que foi proposta pela questão.
