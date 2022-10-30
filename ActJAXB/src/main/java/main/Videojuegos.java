package main;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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