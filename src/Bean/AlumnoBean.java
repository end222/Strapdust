package Bean;

import java.sql.Timestamp;

public class AlumnoBean {
	private String nombre;
	private int NIA;
	private Timestamp fecha;
	private String grupo;
	
	public AlumnoBean() {
		this.nombre = "";
		this.NIA = 0;
		this.fecha = new Timestamp(0);
		this.grupo = "";
	}
	
	public AlumnoBean(String nombre, int NIA, Timestamp fecha, String grupo) {
		this.nombre = nombre;
		this.NIA = NIA;
		this.fecha = fecha;
		this.grupo = grupo;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public int getNIA() {
		return this.NIA;
	}
	
	public Timestamp getFecha() {
		return this.fecha;
	}
	
	public String getGrupo() {
		return this.grupo;
	}
	
}