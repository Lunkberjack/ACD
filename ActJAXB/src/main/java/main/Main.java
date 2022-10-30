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
		// Esta primera vez lee el documento plantilla.
		Main.leer(doc);
		// Usamos el documento 2 para que las modificaciones no se sobreescriban.
		Main.anadirVideojuego(doc, new Videojuego(9,"Sonic y el Caballero Negro","3 de marzo de 2009","Hack and slash, rpg, aventura, acción, plataformas", "No es, ni mucho menos, el mejor de todos los Sonics que existen, es uno de los más criticados, pero son los mismos que se quejan de Dark Souls II, dadle un try y dominad las mecánicas, que no os cuesta, tiene una historia tremenda y la banda sonora es maravillosa, viva el rock."));
		Main.anadirVideojuego(doc2, new Videojuego(10,"Sonic Riders: Zero Gravity","8 de enero de 2008","carreras, deportes", "Juego muy infravalorado, para ser un Spin-off, es bastante divertido y consistente, introduce personajes carismáticos y una historia decente, la banda sonora es, una vez más, su punto más fuerte, arte."));
		Main.anadirVideojuego(doc2, new Videojuego(11,"The Legend of Zelda - Twilight Princess","19 de noviembre de 2006","acción-aventura", "El más oscuro de los Zeldas, pero un rayo de luz en tu vida si te decides a jugarlo. Mi infancia entera. Me casaría con sus mecánicas, con su historia."));
		Main.anadirVideojuego(doc2, new Videojuego(12,"Dragon Quest IX - Centinelas del Firmamento","11 de julio de 2009","jrpg", "La razón de que esté estudiando este ciclo. Una inspiración real para querer diseñar videojuegos. ¿Cómo un mundo abierto en Nintendo puede calar tan hondo?"));

		Main.mostrarMenu(doc2);
	}
	
	/**
	 * Muestra un menú que contiene todas las operaciones posibles a realizar
	 * sobre el XML.
	 * 
	 * Copia el archivo "plantilla" en el archivo videojuegos2.xml, donde se pueden
	 * ver las modificaciones tras cada ejecución del programa. El archivo videojuegos.xml
	 * es así "inmutable", y todo vuelve al estado inicial tras una nueva ejecución.
	 * 
	 * @param doc
	 * @throws JAXBException
	 * @throws IOException
	 */
	public static void mostrarMenu(File doc) throws JAXBException, IOException {

		JAXBContext context = JAXBContext.newInstance(Videojuegos.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Videojuegos vjs = (Videojuegos) unmarshaller.unmarshal(doc);
		
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
		File docModificado = new File("videojuegos2.xml");
		marshaller.marshal(vjs, new FileWriter(docModificado, StandardCharsets.UTF_8));
		
		Scanner scan = new Scanner(System.in);
		String respuesta;
		do {
			System.out.println("\nModifique su XML usando JAXB:");
			System.out.println("-----------------------------");
			System.out.println("¿Qué operación quiere realizar?");
			System.out.println("1: Muestre el catálogo de videojuegos.");
			System.out.println("2: Añada un nuevo videojuego.");
			System.out.println("3: Elimine un videojuego del catálogo.");
			System.out.println("4: Modifique alguna característica del videojuego.");
			System.out.println("0: Finalizar.");
			// String para que no haya excepciones al pedir un nextInt e introducir 
			// otro tipo de dato.
			respuesta = scan.next();

			switch(respuesta) {
			case "1":
				Main.leer(docModificado);
				break;
			case "2":
				System.out.println("Introduce el id del juego:");
				int idAux = scan.nextInt();
				scan.nextLine();  // nextInt() no lee el Enter, y hay que leerlo manualmente.
				System.out.println("Introduce el título:");
				String titAux = scan.nextLine();
				System.out.println("Introduce la fecha de lanzamiento:");
				String fechAux = scan.nextLine();
				System.out.println("Introduce el género, o géneros:");
				String genAux = scan.nextLine();
				System.out.println("Introduce la reseña:");
				String resAux = scan.nextLine();
				Main.anadirVideojuego(doc, new Videojuego(idAux, titAux, fechAux, genAux, resAux));
				break;
			case "3":
				System.out.println("Introduce el id del juego a eliminar:");
				Main.borrarVideojuego(doc, scan.nextInt());
				scan.nextLine();
				break;
			case "4":
				System.out.println("Introduce el id del juego:");
				int idMod = scan.nextInt();
				scan.nextLine();  // nextInt() no lee el Enter, y hay que leerlo manualmente.
				System.out.println("Introduce el elemento a modificar (titulo, resenia, genero...):");
				String eleMod = scan.nextLine();
				System.out.println("Introduce el nuevo valor:");
				String valMod = scan.nextLine();
				Main.modificarElemento(docModificado, idMod, eleMod, valMod);
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
					"Videojuego " + v.getId() + ":\n\tTítulo: " + v.getTitulo() + "\n\tFecha lanz.: " + v.getFechaLanzamiento() + "\n\tGénero(s): " + v.getGenero() +
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
	 * Mediante un for que recorre todo el array de videojuegos, encontramos
     * el array con la ID especificada por el usuario, y lo borramos en el acto. 
     *
	 * @param doc
	 * @param id
	 * @throws JAXBException
	 * @throws IOException
	 */
	public static void borrarVideojuego(File doc, int id) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(Videojuegos.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Videojuegos vjs = (Videojuegos) unmarshaller.unmarshal(doc);
        ArrayList<Videojuego> listaVideojuegos = vjs.getVideojuegos();
        
        for (Videojuego s : listaVideojuegos) {
            if (id == s.getId()) {
                listaVideojuegos.remove(s);
                break;
            }    
        }    
        
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
		int cont = 0;
		
		while(encontrado == false && cont < listaVideojuegos.size()) {
			cont++;
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
				case "TÍTULO":
					aux.setTitulo(valor);
					break;
				case "FECHALANZAMIENTO":
				case "FECHA_LANZAMIENTO":
					aux.setFechaLanzamiento(valor);
					break;
				case "GENERO":
				case "GÉNERO":
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