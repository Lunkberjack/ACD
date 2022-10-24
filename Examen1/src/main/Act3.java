package main;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Dado el archivo “empresa.xml” subido a Moodle:
3.1. Crea una aplicación que permita modificar el sueldo de un empleado. El usuario dará el
“id” del empleado y el valor del nuevo salario. (1 punto)
3.2. Introduce un nuevo campo para todos los empleados denominado “hijos”. Dicho campo
indicará si un empleado tiene o no hijos(“Sí” o “No”). Introduce el valor “Sí” para los
empleados 1, 5 y 7, y el valor “No” para el resto. (1 punto)
3.3. Crea una serie de métodos que permitan al usuario consultar la siguiente información
sobre la empresa usando XPath y DOM:
a) El nombre y puesto de todos los empleados. (0.4 puntos)
b) El nombre y sueldo de todos los empleados que llevan menos de 10 meses trabajando
en la empresa. (0.4 puntos)
c) Dado el identificador de un empleado(id), toda la información del mismo. (0.4 puntos)
d) El nombre de los empleados que trabajan como “Cocinero/a”. (0.4 puntos)
e) El nombre, puesto y sueldo de todos los empleados mayores de 30 años. (0.4 puntos)
 *
 * @author LuciaLM
 * @version 1.0
 */
public class Act3 {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document doc = dbf.newDocumentBuilder().parse(new File("/home/lucia/Escritorio/empresa.xml"));
		try {
			Act3.aniadirHijo(doc, "empleado", "hijo", "No");
			Act3.modificarAtributo(doc, "empleado", "hijo", "Si", 1);
			Act3.modificarAtributo(doc, "empleado", "hijo", "Si", 5);
			Act3.modificarAtributo(doc, "empleado", "hijo", "Si", 7);
			Act3.modificarSueldo(doc, "empleado", "id", "1", "123");
			Act3.mayorDe(doc, 30);
			Act3.recorrerMostrar(doc, System.out);
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Recorre y muestra el documento con formato
	 * @param doc
	 * @param os
	 * @throws TransformerException
	 */
	private static void recorrerMostrar(Document doc, OutputStream os) throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(os);
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		// Muestra el documento
		transformer.transform(source, result);
	}
	/**
	 * Añade un atributo con valor por defecto
	 * @param doc
	 * @param tagName
	 * @param atr
	 * @param valor
	 */
	private static void aniadirHijo(Document doc, String tagName, String atr, String valor) {
		NodeList nl = doc.getElementsByTagName(tagName);
		for(int i = 0; i < nl.getLength(); i++) {
			Element elem = (Element)nl.item(i);
			// Un valor por defecto que luego se podrá modificar
			elem.setAttribute(atr, valor);
		}
	}
	/**
	 * Permite modificar un atributo, pasando posición
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
	 * Modifica el sueldo de un empleado por id.
	 * @param doc
	 * @param tagName
	 * @param atr
	 * @param valor
	 * @param nuevoSueldo
	 */
	private static void modificarSueldo(Document doc, String tagName, String atr, String valor, String nuevoSueldo) {
		NodeList nl = doc.getElementsByTagName(tagName);
		for(int i = 0; i < nl.getLength(); i++) {
			Element elem = (Element) nl.item(i);
			if(elem.getAttribute(atr).equalsIgnoreCase(valor)) {

				NodeList hijos = elem.getChildNodes();
				for (int j = 0; j < hijos.getLength(); j++) {
					Node hijo = hijos.item(j);
					if (hijo.getNodeType() == Node.ELEMENT_NODE) {
						Element hijoElement = (Element) hijo;

						if (hijoElement.getTagName().equals("sueldo")) {
							hijoElement.setTextContent(nuevoSueldo);
						}
					}
				}
			}
		}
	}
	/**
	 * Crea una XPath para mostrar solo los empleados mayores de una determinada
	 * edad.
	 * @param documento
	 * @param edad
	 * @throws XPathExpressionException
	 */
	private static void mayorDe(Document documento, int edad) throws XPathExpressionException {
		XPath xpath = XPathFactory.newInstance().newXPath();

		String xpathExpression = "/empresa/empleados/empleado[./edad>" + edad + "]";
		NodeList nodos = (NodeList) xpath.evaluate(xpathExpression, documento, XPathConstants.NODESET);

		for (int i = 0; i < nodos.getLength(); i++) {
			Element e = (Element) nodos.item(i);

			NodeList hijos = e.getChildNodes();
			for (int j = 0; j < hijos.getLength(); j++) {
				Node hijo = hijos.item(j);

				if (hijo.getNodeType() == Node.ELEMENT_NODE) {
					Element hijoElement = (Element) hijo;

					if (hijoElement.getTagName().equals("nombre")) {
						System.out.println(hijoElement.getTextContent());
					}
				}
			}
		}
	}
}