package main;
import java.io.File;
import java.io.IOException;
/**
 * Trabaja creando estructuras de archivos y directorios, y usa métodos
 * para comprobar su funcionamiento y estado.
 * 
 * 2. Crea un método de Java que dado como entrada el directorio raíz
 * del ejercicio 1, liste los ficheros y directorios del mismo, mostrando
 * la misma información que se ve en el ejercicio 1.
 *
 * 3. Modifica el método del ejercicio 2 para que pueda listar cualquier estructura
 * de ficheros y directorios que contenga el directorio raíz pasado como parámetro.
 * Pruébalo con varias estructuras de directorios y ficheros.
 * 
 * 4. Escribir un método que muestre los nombres de los archivos de un directorio,
 * que se pasará como argumento cuya extensión coincida con la que se pase como
 * segundo argumento.
 *
 * 5. Escribe un método que borre todos los ficheros que tienen la extensión txt
 * dentro de un directorio que se pasará como parámetro.
 *
 * @author LuciaLM
 * @version 1.0
 */
public class Main {
	public static void main(String[] args) {
		try {
			crearEstructura();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void crearEstructura() throws IOException {
		// EJ 1
		File d = new File("/home/lucia/Escritorio", "d");
		d.mkdir();
		File d1 = new File(d, "d1");
		d1.mkdir();
		File d2 = new File(d, "d2");
		d2.mkdir();
		File d3 = new File(d, "d3");
		d3.mkdir();
		// Ficheros d1
		File f11 = new File(d1, "f11.txt");
		f11.createNewFile();
		File f12 = new File(d1, "f12.txt");
		f12.createNewFile();
		// Ficheros d2
		File d21 = new File(d2, "d21");
		d21.mkdir();
		File f21 = new File(d2, "f21.txt");
		f21.createNewFile();
		File d22 = new File(d2, "d22");
		d22.mkdir();
		File f222 = new File(d22, "f222.txt");
		f222.createNewFile();
		// Ficheros d3
		File d31 = new File(d3, "d31");
		d31.mkdir();

		listarRecursivo(d, "\t");
//		listarExtension(d, "txt");
//		listarExtension2(d, "txt");
//		borrarExtension(d1, "txt");
	}

	/**
	 * EJ 2 y 3
	 * Lista recursivamente todos los directorios y ficheros de un directorio
	 * especificado.
	 * @param carp 	- el directorio a listar
	 */
	public static void listarRecursivo(File carp, String sep) {
		File[] carpetas = carp.listFiles();
		// Si y solo si hay algún fichero en el directorio, entra al bucle
	    if (carpetas != null){
	        for (int x = 0; x < carpetas.length; x++) {
	            System.out.println(sep + carpetas[x].getName());
	            if (carpetas[x].isDirectory()) {
	                String nuevoSep = sep + " ";
	                listarRecursivo(carpetas[x], nuevoSep);
	            }
	        }
	    }
	}
//	
//	public static void tabulador(String tab, int) {
//		System.out.println("  ");
//	}

	/**
	 * EJ 4
	 * Lista recursivamente todos los directorios y los archivos con una extensión elegida que
	 * se pasa como parámetro. En esta versión sin el endsWith(), solo soporta extensiones de
	 * hasta 4 caracteres, para evitar confusiones con las extensiones de 2 caracteres. Ejemplo:
	 * archivo1.ab.cd contaría como extensión .ab sin serlo.
	 * @param carp 	- el directorio a listar
	 * @param ext 	- la extensión única a listar
	 */
	public static void listarExtension(File carp, String ext) {
		File[] carpetas = carp.listFiles();
		if(carpetas != null) {
			for (File x : carpetas) {
				// Si es un fichero CON EXTENSIÓN, y no más de 4 caracteres para dicha extensión:
				if(x.getName().contains("." + ext) && (x.getName().lastIndexOf("." + ext) >= x.getName().length() - 5)) {
					System.out.println(x.getName());
				}
				// Si es un directorio y, por tanto, se debe volver a listar:
				if(x.isDirectory()) {
					System.out.println(x.getName());
					listarExtension(x, ext);
				}
			}
		}
	}

	/**
	 * EJ 4 
	 * Uso del método endsWith() para mejorar el ej 4 y hacer que soporte extensiones de más caracteres.
	 * @param carp 	- el directorio a listar
	 * @param ext 	- la extensión única a listar
	 */
	public static void listarExtension2(File carp, String ext) {
		File[] carpetas = carp.listFiles();
		if(carpetas != null) {
			for (File x : carpetas) {
				if(x.getName().endsWith("." + ext)) {
					System.out.println(x.getName());
				}
				// Si es un directorio y, por tanto, se debe volver a listar:
				if(x.isDirectory()) {
					System.out.println(x.getName());
					listarExtension2(x, ext);
				}
			}
		}
	}

	/**
	 * EJ 5
	 * Borra los archivos con una extensión determinada en un directorio que se pasará por parámetro.
	 * @param carp 	- directorio donde se deben borrar los archivos
	 * @ext 		- extensión de los archivos a borrar
	 */
	public static void borrarExtension(File carp, String ext) {
		File[] carpetas = carp.listFiles();
		for (File x : carpetas) {
			if(x.getName().endsWith("." + ext)) {
				if(x.delete()) {
					System.out.println("El archivo " + x.getName() + " se ha borrado con éxito.");
				} else {
					System.out.println("Ha ocurrido un error.");
				}
			}		
		}
	}
}