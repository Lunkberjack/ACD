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

public class Main {
	public static void main(String[] args) throws JAXBException, IOException {
		File doc = new File("videojuegos.xml");
		leer(doc);
		Videojuego videojuego = new Videojuego(7,"si","12-6-12","ola", "ke pasa");
		anadirVideojuego(doc, videojuego);
		Main.modificarElemento(doc, 2, "titulo", "Dark souls 2 el mejor DS");
		Main.modificarElemento(doc, 4, "fechalanzamiento", "nomacuerdo cuando salio");
	}

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


	public static void leer(File doc) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Videojuegos.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Videojuegos vjs = (Videojuegos) unmarshaller.unmarshal(doc);
		ArrayList<Videojuego> listaVideojuegos = vjs.getVideojuegos();

		for (Videojuego v : listaVideojuegos) {
			System.out.println(
					"El gueim " + v.getId() + " se llama " + v.getTitulo() + " y es de género " + v.getGenero());
		}
	}

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

	public static void modificarElemento(File doc, int id, String elem, String valor) throws JAXBException, IOException {
		JAXBContext context = JAXBContext.newInstance(Videojuegos.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Videojuegos vjs = (Videojuegos) unmarshaller.unmarshal(doc);
		ArrayList<Videojuego> listaVideojuegos = vjs.getVideojuegos();
		Iterator<Videojuego> it = listaVideojuegos.iterator();
		boolean encontrado = false;

		while(encontrado == false) {
			Videojuego aux = it.next();
			if(aux.getId() == id) {
				encontrado = true;
				// Para que ignore las mayúsculas o minúsculas 
				// de los parámetros.
				String auxString = elem.toUpperCase();
				
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