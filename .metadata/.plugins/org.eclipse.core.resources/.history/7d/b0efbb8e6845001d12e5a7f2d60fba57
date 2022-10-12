package main;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class AccesoRandomEj1 {

	public static void main(String[] args) {
		try {
			File f = new File("/home/lucia/Escritorio/fichero.dat");
			RandomAccessFile inOut = new RandomAccessFile(f, "rw");
			String texto, caracterStr;
			
			// Mueve el puntero a la primera posición y lee un
			// array de bytes con la longitud del texto.
	        inOut.seek(0);
	        byte[] arr = new byte[(int) inOut.length()];
	        // Lee el texto completamente en el array
			inOut.readFully(arr);
			// Vuelve a mover el puntero a primera posición
	        inOut.seek(0);
	        // Crea un nuevo texto a partir de ese array
			texto = new String(arr);
			
			inOut.seek(0);
			// Lee cada carácter del texto, lo pasa a String y lo escribe,
			// teniendo en cuenta que no sea un espacio. Esto lo hace un nº
			// de veces igual a la longitud inicial del texto.
			for(int i = 0; i < texto.length(); i++) {
				if (texto.charAt(i) != ' ') {
					caracterStr = String.valueOf(texto.charAt(i)).toUpperCase();
					inOut.writeChars(caracterStr);
				}
			}
			inOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}