package module;

import java.sql.Timestamp;

public class Cartel {
	private int ID;
	private String titulo;
	private String noticia;
	private String enlace;
	private String imagen;
	private String pregunta;
	private String reto;
	private int publico;
	private Timestamp fecha;
	
	public Cartel(int ID, String titulo, String noticia, String imagen, String pregunta, String reto, int publico, Timestamp fecha, String enlace) {
		this.ID = ID;
		this.titulo = titulo;
		this.noticia = noticia;
		this.imagen = imagen;
		this.pregunta = pregunta;
		this.reto = reto;
		this.publico = publico;
		this.fecha = fecha;
		this.enlace = enlace;
	}
	
	public Cartel(int ID, String titulo, String noticia, String imagen, String pregunta, String reto, int publico, String enlace) {
		this.ID = ID;
		this.titulo = titulo;
		this.noticia = noticia;
		this.imagen = imagen;
		this.pregunta = pregunta;
		this.reto = reto;
		this.publico = publico;
		this.enlace = enlace;
	}
	
	public Cartel() {
		this.ID = -1;
		this.titulo = "";
		this.noticia = "";
		this.imagen = "";
		this.pregunta = "";
		this.reto = "";
		this.publico = -1;
	}
	
	public void poblar(int ID, String titulo, String noticia, String imagen, String pregunta, String reto, int publico, Timestamp fecha, String enlace) {
		this.ID = ID;
		this.titulo = titulo;
		this.noticia = noticia;
		this.imagen = imagen;
		this.pregunta = pregunta;
		this.reto = reto;
		this.publico = publico;
		this.fecha = fecha;
		this.enlace = enlace;
	}
	
	public void anyadirFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	
	public int getID() {
		return this.ID;
	}
	
	public String getTitulo() {
		return this.titulo;
	}
	
	public String getNoticia() {
		return this.noticia;
	}
	
	public String getImagen() {
		return this.imagen;
	}
	
	public String getPregunta() {
		return this.pregunta;
	}
	
	public String getReto() {
		return this.reto;
	}
	
	public int getPublico() {
		return this.publico;
	}
	
	public Timestamp getFecha() {
		return this.fecha;
	}
	
	public String getEnlace() {
		return this.enlace;
	}
}
