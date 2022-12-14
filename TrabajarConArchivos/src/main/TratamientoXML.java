package main;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
/**
 * 	Clase con las soluciones a la relación de ejercicios XML.
 * 	Se prueban los distintos métodos en la clase main.
 * 	Se recomienda comentar las llamadas a estos métodos para no
 * sobreescribirlos con métodos posteriores.
 * 
 * @author LuciaLM
 * @version 1.0
 */
public class TratamientoXML {

	public static void main(String[] args) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;

		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
			Element raiz = doc.createElement("libreria");
			doc.appendChild(raiz);
			Element nombre = doc.createElement("nombre");
			nombre.setTextContent("Librería Pepe");
			raiz.appendChild(nombre);
			Element libros = doc.createElement("libros");
			raiz.appendChild(libros);
			Element libro1 = TratamientoXML.crearLibro(doc, "1234567890", "Don Quijote de la Mancha", "Miguel de Cervantes");
			libros.appendChild(libro1);
			Element libro2 = TratamientoXML.crearLibro(doc, "2345678901", "Lazarillo de Tormes", "Anónimo");
			libros.appendChild(libro2);
			Element libro3 = TratamientoXML.crearLibro(doc, "4567890123", "La vida es sueño", "Pedro Calderón de la Barca");
			libros.appendChild(libro3);
			
			// 4. Añadir un nuevo libro
			Element libro4 = TratamientoXML.crearLibro(doc, "890123457", "Cien años de soledad", "Gabriel García Márquez");
			libros.appendChild(libro4);
			
			// 5. Nueva etiqueta dirección (antes de <libros>)
			Element direccion = doc.createElement("direccion");
			direccion.setTextContent("C/Amiel 12");
			raiz.insertBefore(direccion, libros);
			
			// 6. Eliminar el segundo libro
			TratamientoXML.eliminarHijo(doc, "libro", 2);
			
			// 7. Cambiar el texto de la etiqueta <direccion>
			direccion.setTextContent("C/Amiel 26");
			
			// 8. Añadir atributo estado
			TratamientoXML.aniadirAtributoTodos(doc, "libro", "estado", "bien");
			
			// Modificar estado del libro 2 (probar método)
			TratamientoXML.modificarAtributo(doc, "libro", "estado", "aceptable", 2);
			
			// 9. Eliminar estado del primer y tercer libros
			TratamientoXML.eliminarAtributo(doc, "libro", "estado", 1);
			TratamientoXML.eliminarAtributo(doc, "libro", "estado", 3);
	
			// 2. Recorrer y mostrar
			TratamientoXML.recorrerMostrar(doc, System.out);
			
			// 3. Crear un archivo XML a partir del DOM
			TratamientoXML.crearXMLAPartirDOC(doc);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crea un elemento para el doc.
	 * @param doc
	 * @param isbn
	 * @param autor
	 * @param titulo
	 * @return
	 */
	private static Element crearLibro(Document doc, String isbn, String tit, String aut) {
		Element lib = doc.createElement("libro");
		lib.setAttribute("isbn", isbn);

		Element autor = doc.createElement("autor");
		autor.setTextContent(aut);
		lib.appendChild(autor);

		Element titulo = doc.createElement("titulo");
		titulo.setTextContent(tit);
		lib.appendChild(titulo);

		return lib;
	}

	/**
	 * 2. Recorre el documento y muestra su contenido.
	 * @param doc
	 * @param os
	 * @throws TransformerException
	 */
	private static void recorrerMostrar(Document doc, OutputStream os) throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer suletta = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(os);
		// Pa poner bonita la salida
		suletta.setOutputProperty(OutputKeys.INDENT, "yes");
		suletta.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		// Muestra el documento
		suletta.transform(source, result);
	}
	/**
	 * Crea un archivo XML en el escritorio a partir del árbol DOM.
	 * @param doc
	 * @throws TransformerException
	 */
	private static void crearXMLAPartirDOC(Document doc) throws TransformerException {
		File f = new File("/home/lucia/Escritorio/TratamientoXML.xml"); 
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer suletta = transformerFactory.newTransformer();
		// Formato salida
		suletta.setOutputProperty(OutputKeys.INDENT, "yes");
		suletta.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		// Creamos un StreamResult, intermediario entre el Transformer y el File de destino.
		StreamResult result = new StreamResult(f);
		// Creamos un DOMSource, intermediario entre el Transformer y el árbol.
		DOMSource source = new DOMSource(doc);
		// Transformamos el DOM a archivo XML.
		suletta.transform(source, result);
	}
	/**
	 * Elimina el hijo del elemento especificado en la posición elegida
	 * @param doc
	 * @param pos
	 */
	private static void eliminarHijo(Document doc, String tagName, int pos) {
		NodeList nl = doc.getElementsByTagName(tagName);
		// Se busca al elemento en la posición deseada
		Element e = (Element)nl.item(pos - 1);
		Element padre = (Element)e.getParentNode();
		// Y se elimina del padre
		padre.removeChild(e);
	}
	/**
	 * Añade un atributo a todos los elementos con una etiqueta, y le da un valor
	 * por defecto.
	 * @param doc
	 * @param tagName
	 * @param atr
	 * @param valor
	 */
	private static void aniadirAtributoTodos(Document doc, String tagName, String atr, String valor) {
		NodeList nl = doc.getElementsByTagName(tagName);
		for(int i = 0; i < nl.getLength(); i++) {
			Element elem = (Element)nl.item(i);
			// Un valor por defecto que luego se podrá modificar
			elem.setAttribute(atr, valor);
		}
	}
	/**
	 * Permite modificar un atributo elegido de un elemento, pasando posición.
	 * @param doc
	 * @param tagName
	 * @param atr
	 * @param valor
	 * @param pos
	 */
	private static void modificarAtributo(Document doc, String tagName, String atr, String valor, int pos) {
		NodeList nl = doc.getElementsByTagName(tagName);
		// Se busca al elemento en la posición deseada
		Element elem = (Element)nl.item(pos - 1);
		// Se borra su atributo elegido
		elem.setAttribute(atr, valor);
	}
	/**
	 * Permite eliminar un atributo deseado en una posición específica.
	 * @param doc
	 * @param tagName
	 * @param atr
	 * @param pos
	 */
	private static void eliminarAtributo(Document doc, String tagName, String atr, int pos) {
		NodeList nl = doc.getElementsByTagName(tagName);
		// Se busca al elemento en la posición deseada
		Element elem = (Element)nl.item(pos - 1);
		// Se borra su atributo elegido
		elem.removeAttribute(atr);
	}
}
