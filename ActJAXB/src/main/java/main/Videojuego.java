package main;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
/**
 * Clase que representa al elemento <videojuego> del XML.
 * Incluye anotaciones para permitir el mapping de JAXB y 
 * el paso de XML a objeto java y viceversa.
 * 
 * @author Alejandro Reyes, Lucía León
 * @version 1.0
 */
@XmlRootElement(name="videojuego")
@XmlType(propOrder= {"titulo","fechaLanzamiento","genero", "resenia"})
public class Videojuego {
	private int id;
	private String titulo;
	private String fechaLanzamiento;
	private String genero;
	private String resenia;
	
	public Videojuego() {
		
	}
	
	public Videojuego(int id, String titulo, String fecha, String genero, String resenia) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.fechaLanzamiento = fecha;
		this.genero = genero;
		this.resenia = resenia;
	}
	
	@XmlAttribute(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@XmlElement(name="titulo")
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@XmlElement(name="fecha_lanzamiento")
	public String getFechaLanzamiento() {
		return fechaLanzamiento;
	}
	public void setFechaLanzamiento(String fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}

	@XmlElement(name="genero")
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	@XmlElement(name="resenia")
	public String getResenia() {
		return resenia;
	}
	public void setResenia(String resenia) {
		this.resenia = resenia;
	}
}
