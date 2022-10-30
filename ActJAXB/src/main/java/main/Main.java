package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Clase con todas las operaciones que se pueden realizar sobre nuestro 
 * XML videojuegos.xml, implementando un menú desde el cual acceder a
 * cada una de las funciones.
 * 
 * @author Alejandro Reyes, Lucía León
 * @version 1.0
 */
public class Main {
	public static void main(String[] args) throws JAXBException, IOException {
		File doc = new File("videojuegos.xml");
		File doc2 = new File("videojuegos2.xml");
		leer(doc);
		// Usamos el documento 2 para que las modificaciones no se sobreescriban
		Main.anadirVideojuego(doc, new Videojuego(7,"Sonic Frontiers","200algo","ola", "ke pasa"));
		Main.modificarElemento(doc2, 2, "titulo", "Dark souls 2 el mejor DS");
		Main.modificarElemento(doc2, 4, "fechalanzamiento", "nomacuerdo cuando salio");
		Main.modificarElemento(doc2, 6, "resenia", "HOY ESTAMOS AQUÍ REUNIDOS PARA");
	}
	
	/**
	 * Muestra un menú que contiene todas las operaciones posibles a realizar
	 * sobre el XML.
	 * 
	 * @param doc
	 * @throws JAXBException
	 * @throws IOException
	 */
	public static void mostrarMenu(File doc) throws JAXBException, IOException {
		Scanner scan = new Scanner(System.in);
		String respuesta;
		do {
			System.out.println("Modifique su XML usando JAXB:\n");
			System.out.println("¿Qué operación quiere realizar?");
			System.out.println("1: Muestre el catálogo de videojuegos.");
			System.out.println("2: Añada un nuevo videojuego.");
			System.out.println("3: Elimine un videojuego del catálogo.");
			System.out.println("4: Modifique alguna característica del videojuego.");
			// String para que no haya excepciones al pedir un nextInt e introducir 
			// otro tipo de dato.
			respuesta = scan.next();

			switch(respuesta) {
			case "1":
				Main.leer(doc);
				break;
			case "2":
				System.out.println("Introduce el id del juego:");
				Main.anadirVideojuego(doc, new Videojuego());
				break;
			case "3":
				break;
			case "4":
				break;
			}
		} while (!respuesta.equals("0"));

		scan.close();
	}

	/**
	 * Lee la información del documento XML que se le indica, con formato
	 * personalizado.
	 * 
	 * @param doc
	 * @throws JAXBException
	 */
	public static void leer(File doc) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Videojuegos.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Videojuegos vjs = (Videojuegos) unmarshaller.unmarshal(doc);
		ArrayList<Videojuego> listaVideojuegos = vjs.getVideojuegos();

		for (Videojuego v : listaVideojuegos) {
			System.out.println(
					"Videojuego " + v.getId() + ":\n\tTítulo: " + v.getTitulo() + "\n\tGénero(s): " + v.getGenero() +
					"\n\tReseña: " + v.getResenia());
		}
	}

	/**
	 * Añade un nuevo elemento <videojuego> al XML.
	 * 
	 * @param doc
	 * @param vj
	 * @throws JAXBException
	 * @throws IOException
	 */
	public static void anadirVideojuego(File doc, Videojuego vj) throws JAXBException, IOException {
		JAXBContext context = JAXBContext.newInstance(Videojuegos.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Videojuegos vjs = (Videojuegos) unmarshaller.unmarshal(doc);
		ArrayList<Videojuego> listaVideojuegos = vjs.getVideojuegos();

		listaVideojuegos.add(vj);

		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);

		marshaller.marshal(vjs, new FileWriter("videojuegos2.xml",StandardCharsets.UTF_8));
	}

	/**
	 * Permite modificar cualquier videojuego en el XML, pasando su id.
	 * También se debe pasar el nombre del elemento a modificar (may. o mín, es indiferente)
	 * y el nuevo valor que se le quiere asignar.
	 * 
	 * @param doc
	 * @param id - El id del videojuego a modificar.
	 * @param elem - El elemento a modificar (título, género, reseña...)
	 * @param valor - El valor que debe tomar el elemento que se está modificando.
	 * @throws JAXBException
	 * @throws IOException
	 */
	public static void modificarElemento(File doc, int id, String elem, String valor) throws JAXBException, IOException {
		JAXBContext context = JAXBContext.newInstance(Videojuegos.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Videojuegos vjs = (Videojuegos) unmarshaller.unmarshal(doc);
		ArrayList<Videojuego> listaVideojuegos = vjs.getVideojuegos();
		Iterator<Videojuego> it = listaVideojuegos.iterator();
		boolean encontrado = false; // Para que, una vez se encuentre el id, se detenga.

		while(encontrado == false) {
			Videojuego aux = it.next();
			if(aux.getId() == id) {
				encontrado = true;
				// Para que ignore las mayúsculas o minúsculas 
				// de los parámetros.
				String auxString = elem.toUpperCase();
				// Actuamos en función de esta nueva String en 
				// mayúsculas.
				switch(auxString) {
				case "TITULO":
					aux.setTitulo(valor);
					break;
				case "FECHALANZAMIENTO":
				case "FECHA_LANZAMIENTO":
					aux.setFechaLanzamiento(valor);
					break;
				case "GENERO":
					aux.setGenero(valor);
					break;
				case "RESENIA":
				case "RESEÑA":
					aux.setResenia(valor);
					break;
				}
			}
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);

			marshaller.marshal(vjs, new FileWriter("videojuegos2.xml",StandardCharsets.UTF_8));
		}
	}
}