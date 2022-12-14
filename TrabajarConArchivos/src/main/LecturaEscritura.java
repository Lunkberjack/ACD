package main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * Pues eso
 * @author LuciaLM
 * @version 1.0
 */
public class LecturaEscritura {
	public static void main(String[] args) {
		File f = new File("/home/lucia/Escritorio/prueba.txt");
		try {
			escribir();
			escribirAppend();
			leer(f);
			contarPalabras(f);
			quitarEspaciosMayusculas(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * EJ 1: escribir texto en fichero
	 * @throws IOException
	 */
	public static void escribir() throws IOException {
		File f = new File("/home/lucia/Escritorio/prueba.txt");
		System.out.println(f.exists());
		f.createNewFile();
		PrintWriter pw = new PrintWriter(f);
		pw.println("I wanna be the very best\n"
				+ "That no one ever was\n"
				+ "To catch them is my real test\n"
				+ "To train them is my 'cause\n"
				+ "I will travel across that land\n"
				+ "Searching far and wide\n"
				+ "Teach Pokemon to understand\n"
				+ "The power that's inside\n"
				+ "Pokemon, gotta catch 'em all, it's you and me\n"
				+ "I know it's my destiny\n"
				+ "Pokemon, oh, you're my best friend\n"
				+ "In a world we must defend\n"
				+ "Pokemon, gotta catch 'em all, a heart so true\n"
				+ "Our courage will pull us through\n"
				+ "You teach me and I'll teach you, Pokemon\n"
				+ "You gotta catch 'em all\n"
				+ "Pokemon, gotta catch 'em all\n"
				+ "Pikachu\n"
				+ "Pokemon, gotta catch 'em all, it's you and me\n"
				+ "I know it's my destiny\n"
				+ "Pokemon, oh, you're my best friend\n"
				+ "In a world we must defend\n"
				+ "Pokemon, gotta catch 'em all, a heart so true\n"
				+ "Our courage will pull us through\n"
				+ "You teach me and I'll teach you, Pokemon\n"
				+ "You gotta catch 'em all\n"
				+ "Pokemon, gotta catch 'em all\n"
				+ "Pikachu\n"
				+ "Pikachu");
//		pw.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣤⣤⣤⣤⣤⣶⣦⣤⣄⡀⠀⠀⠀⠀⠀⠀⠀\n"
//				+ "⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⡿⠛⠉⠙⠛⠛⠛⠛⠻⢿⣿⣷⣤⡀⠀⠀⠀⠀⠀\n"
//				+ "⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⠋⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⠈⢻⣿⣿⡄⠀⠀⠀⠀\n"
//				+ "⠀⠀⠀⠀⠀⠀⠀⣸⣿⡏⠀⠀⠀⣠⣶⣾⣿⣿⣿⠿⠿⠿⢿⣿⣿⣿⣄⠀⠀⠀\n"
//				+ "⠀⠀⠀⠀⠀⠀⠀⣿⣿⠁⠀⠀⢰⣿⣿⣯⠁⠀⠀⠀⠀⠀⠀⠀⠈⠙⢿⣷⡄⠀\n"
//				+ "⠀⠀⣀⣤⣴⣶⣶⣿⡟⠀⠀⠀⢸⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣷⠀\n"
//				+ "⠀⢰⣿⡟⠋⠉⣹⣿⡇⠀⠀⠀⠘⣿⣿⣿⣿⣷⣦⣤⣤⣤⣶⣶⣶⣶⣿⣿⣿⠀\n"
//				+ "⠀⢸⣿⡇⠀⠀⣿⣿⡇⠀⠀⠀⠀⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃⠀\n"
//				+ "⠀⣸⣿⡇⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠉⠻⠿⣿⣿⣿⣿⡿⠿⠿⠛⢻⣿⡇⠀⠀\n"
//				+ "⠀⣿⣿⠁⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣧⠀⠀\n"
//				+ "⠀⣿⣿⠀⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⠀⠀\n"
//				+ "⠀⣿⣿⠀⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⠀⠀\n"
//				+ "⠀⢿⣿⡆⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡇⠀⠀\n"
//				+ "⠀⠸⣿⣧⡀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠃⠀⠀\n"
//				+ "⠀⠀⠛⢿⣿⣿⣿⣿⣇⠀⠀⠀⠀⠀⣰⣿⣿⣷⣶⣶⣶⣶⠶⠀⢠⣿⣿⠀⠀⠀\n"
//				+ "⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣿⣿⡇⠀⣽⣿⡏⠁⠀⠀⢸⣿⡇⠀⠀⠀\n"
//				+ "⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⣿⣿⡇⠀⢹⣿⡆⠀⠀⠀⣸⣿⠇⠀⠀⠀\n"
//				+ "⠀⠀⠀⠀⠀⠀⠀⢿⣿⣦⣄⣀⣠⣴⣿⣿⠁⠀⠈⠻⣿⣿⣿⣿⡿⠏⠀⠀⠀⠀\n"
//				+ "⠀⠀⠀⠀⠀⠀⠀⠈⠛⠻⠿⠿⠿⠿⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀    ");
		pw.close();
	}
	/**
	 * EJ 2: agregar nuevo texto a texto ya escrito en fichero
	 * @throws IOException
	 */
	public static void escribirAppend() throws IOException {
		File f = new File("/home/lucia/Escritorio/prueba.txt");
		System.out.println(f.exists());
		f.createNewFile();
		FileWriter fw = new FileWriter(f, true);
		fw.write("Hola mundo");
		fw.close();
	}
	/**
	 * EJ 3 y 4: lee un fichero con varias líneas
	 * @param f - el fichero a leer
	 * @throws IOException
	 */
	public static void leer(File f) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(f));
		String linea;
		while((linea = br.readLine()) != null) {
			System.out.println(linea);
		}
		br.close();
	}
	/**
	 * EJ 5: cuenta las palabras de un fichero
	 * @param f - el fichero a contar
	 * @throws IOException 
	 */
	public static void contarPalabras(File f) throws IOException {
		int contadorPalabras = 0;
		BufferedReader br = new BufferedReader(new FileReader(f));
		String linea;
		while((linea = br.readLine()) != null) {
			// En la expresión regular, \\s equivale a cualquier tipo de carácter
			// "blanco", es decir, espacios, tabuladores y retornos. 
			contadorPalabras += linea.split("\\s+").length;
		}
		System.out.println("El documento tiene " + contadorPalabras + " palabras.");
		br.close();
	}
	/**
	 * Copia un archivo en mayúsculas en su directorio con el nombre editado.txt
	 * y quita los espacios en blanco, tabuladores y retornos de carro.
	 * @param f - el fichero a editar
	 * @return editado - el documento procesado
	 * @throws IOException
	 */
	public static File quitarEspaciosMayusculas(File f) throws IOException {
		File editado = new File(f.getParentFile() + "/editado.txt");
		BufferedReader br = new BufferedReader(new FileReader(f));
		PrintWriter pw = new PrintWriter(editado);
		String linea;
		while((linea = br.readLine()) != null) {
			linea = linea.toUpperCase();
			linea = linea.replaceAll("\\s", "");
			pw.println(linea);
		}
		br.close();
		pw.close();
		return editado;
	}
}