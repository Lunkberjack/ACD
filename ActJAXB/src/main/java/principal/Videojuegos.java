package principal;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase que contiene el elemento raíz del XML, <videojuegos>,
 * y que implementa una ArrayList de objetos Videojuego.
 * 
 * @author Alejandro Reyes, Lucía León
 * @version 1.0
 */
@XmlRootElement(name="videojuegos")
public class Videojuegos {
	ArrayList<Videojuego> videojuegos = new ArrayList<Videojuego>();

	public Videojuegos(ArrayList<Videojuego> videojuegos) {
		super();
		this.videojuegos = videojuegos;
	}
	
	public Videojuegos() {
		super();
	}

	@XmlElement(name="videojuego")
	public ArrayList<Videojuego> getVideojuegos() {
		return this.videojuegos;
	}
	public void setVideojuegos(ArrayList<Videojuego> videojuegos) {
		this.videojuegos = videojuegos;
	}
}