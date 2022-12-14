package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
		File f = new File ("/home/lucia/Escritorio/numeros.txt");
		Scanner scan = new Scanner(System.in);
		DataOutputStream out;
		DataInputStream in;
		String[] numeros;
		try {
			numeros = new String[2];
			out = new DataOutputStream(new FileOutputStream(f));
			in = new DataInputStream(new FileInputStream(f));
			// int var1, var2;
			do {
				System.out.println("Introduce dos números, separados por intro: ");
				numeros[0] = scan.nextLine();
				numeros[1] = scan.nextLine();
				if(numeros[0].equals("") || numeros[1].equals("")) {
					break;
				}
				//var1 = Integer.valueOf(numeros[0]);
				//var2 = Integer.valueOf(numeros[1]);
				out.writeChars(numeros[0]);
				out.writeChar(' ');
				out.writeChars(numeros[1]);
				out.writeChar('\n');
				} while (true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		scan.close();
	}
}