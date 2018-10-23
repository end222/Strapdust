package module;

import java.sql.Timestamp;

public class Cartel {
	private int ID;
	private String titulo;
	private String noticia;
	private String imagen;
	private String pregunta_opinion;
	private String reto;
	private int publico;
	private Timestamp fecha;
	
	public Cartel(int ID, String titulo, String noticia, String imagen, String pregunta_opinion, String reto, int publico, Timestamp fecha) {
		this.ID = ID;
		this.titulo = titulo;
		this.noticia = noticia;
		this.imagen = imagen;
		this.pregunta_opinion = pregunta_opinion;
		this.reto = reto;
		this.publico = publico;
		this.fecha = fecha;
	}
	
	public Cartel(int ID, String titulo, String noticia, String imagen, String pregunta_opinion, String reto, int publico) {
		this.ID = ID;
		this.titulo = titulo;
		this.noticia = noticia;
		this.imagen = imagen;
		this.pregunta_opinion = pregunta_opinion;
		this.reto = reto;
		this.publico = publico;
	}
	
	public void anyadirFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	
	public int verID() {
		return this.ID;
	}
	
	public String verTitulo() {
		return this.titulo;
	}
	
	public String verNoticia() {
		return this.noticia;
	}
	
	public String verImagen() {
		return this.imagen;
	}
	
	public String verPregunta() {
		return this.pregunta_opinion;
	}
	
	public String verReto() {
		return this.reto;
	}
	
	public int verPublico() {
		return this.publico;
	}
	
	public Timestamp verFecha() {
		return this.fecha;
	}
}
