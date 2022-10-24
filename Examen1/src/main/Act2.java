package main;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * Se dispone de dos ficheros de texto que contienen una lista de números
 * enteros (tipo primitivo de Java) cada uno, ordenados de menor a mayor,
 * a razón de un número por línea. Se pide un programa en Java que a partir
 * de estos dos ficheros, genere un tercer fichero que contenga todos y
 * cada uno de los datos de los dos ficheros iniciales y esté también ordenado 
 * de menor a mayor.
 * 
 * @author LuciaLM
 * @version 1.0
 */
public class Act2 {
	public static int[] numerosFichero1;
	public static int[] numerosFichero2;
	public static int[] numerosCompletos;
	
	public static void main(String[] args) {
		File fichero1 = new File("/home/lucia/Escritorio/fichero1.txt");
		File fichero2 = new File("/home/lucia/Escritorio/fichero2.txt");
		File resultado = new File("/home/lucia/Escritorio/resultado.txt");
		try {
			ordenarMostrarNumeros(fichero1, fichero2, resultado);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void ordenarMostrarNumeros(File f1, File f2, File res) throws IOException {
		Act2.leerFichero(f1, numerosFichero1);
		Act2.leerFichero(f2, numerosFichero1);
		RandomAccessFile raf = new RandomAccessFile(res, "rw");
		Act2.numerosCompletos = 
				new int[numerosFichero1.length + numerosFichero2.length];
		
		for(int i = 0; i < numerosFichero1.length; i++) {
			numerosCompletos[i] = numerosFichero1[i];
		}
		for(int i = numerosFichero1.length; i < numerosFichero2.length; i++) {
			numerosCompletos[i] = numerosFichero2[i];
		}
		
		// Los dos arrays deberían unirse en uno, que se ordenaría mediante
		// la librería Arrays.
		Arrays.sort(numerosCompletos);

		// Después se escribiría en el archivo resultado.
		raf.seek(0);
		for (int i : numerosCompletos) {
			System.out.print(i);
			raf.write(i);
		}
		raf.close();
	}

	private static void leerFichero(File fichero, int[] array) throws IOException {
		int numero;
		// Se leen las líneas en formato String antes de convertirlas a int
		String numStr, texto;

		RandomAccessFile raf = new RandomAccessFile(fichero, "rw");
		raf.seek(0);
		byte[] arr = new byte[(int) raf.length()];
		raf.readFully(arr);
		raf.seek(0);
		// Crea un nuevo texto a partir del array
		texto = new String(arr);
		
		// Creamos un array con la longitud del texto
		array = new int[texto.length()];
		for(int i = 0; i < texto.length(); i++) {
			// Se leen los números línea por línea
			numStr = raf.readLine();
			if(numStr != null) {
				numero = Integer.valueOf(numStr);
				Act2.numerosCompletos[i] = numero;
			}
		}
	}
}