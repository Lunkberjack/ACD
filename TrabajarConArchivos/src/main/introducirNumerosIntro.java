package main;

import java.util.Scanner;

/**
 * 2. Escribe un programa que cree un archivo que contiene parejas de números
	enteros separados por blanco en cada línea. La introducción de datos finaliza si
	escribimos INTRO al comienzo de una línea.
 * @author LuciaLM
 * @version 1.0
 */
public class introducirNumerosIntro {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce algo: ");
		while((int)(sc.next().charAt(0)) != 13) {
				System.out.print(sc.next().charAt(0));
				System.out.println(sc.next().charAt(0));
			sc.close();
		}
	}
}
