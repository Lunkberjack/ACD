package main;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Realiza un método que guarde en un documento un mensaje que ha sido cifrado
 * usando el cifrado. El mensaje original estará en un documento de texto llamado
 * mensaje.txt que se encontrará en el espacio de trabajo del proyecto. El
 * método deberá tener tres argumentos, el directorio donde se guardará el
 * documento codificado, el nombre del documento y un entero número (N), que
 * indicará el desplazamiento de los caracteres del mensaje original.
 * 
 * @author LuciaLM
 * @version 1.0
 */
public class Act1 {
	public static String ALFABETO = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public static void main(String[] args) {
		File directorio = new File("/home/lucia/Escritorio/mensaje.txt");
		try {
			Act1.cifrar(directorio, 3);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void cifrar(File dir, int desp) throws IOException {
		String texto, caracterStr;
		RandomAccessFile inOut = new RandomAccessFile(dir, "rw");
		inOut.seek(0);
		// Crea un array de bytes con la longitud del archivo
        byte[] arr = new byte[(int) inOut.length()];
        // Lo lee completamente
		inOut.readFully(arr);
        inOut.seek(0);
        // Mete el contenido en un String
		texto = new String(arr);
		// Vuelve a mover el puntero a la primera posición
		inOut.seek(0);
		for(int i = 0; i < texto.length(); i++) {
			// Si es una letra, se cifra
			if (Character.isAlphabetic(texto.charAt(i))) {
				for(int j = 0; j < ALFABETO.length(); j++) {
					// Se busca en el alfabeto
					if(String.valueOf(texto.charAt(i)).equalsIgnoreCase(
							String.valueOf(ALFABETO.charAt(j)))) {
						// Si está, y es mayúscula
						if(Character.isUpperCase(texto.charAt(i))) {
							inOut.writeChar(ALFABETO.charAt(j+desp));
						// Si está, y es minúscula
						} else {
							caracterStr = String.valueOf(ALFABETO.charAt(j+desp)).toLowerCase();
							inOut.writeChars(caracterStr);
						}
					}
				}
			// Si es un símbolo o dígito, se ignora
			} else {
				inOut.writeChars(String.valueOf(texto.charAt(i)));
			}
		}
		inOut.close();
	}
}