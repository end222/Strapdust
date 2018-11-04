package Bean;

import java.sql.Timestamp;

public class AlumnoBean {
	private String nombre;
	private int NIA;
	private Timestamp fecha_creacion;
	private String grupo;
	
	public AlumnoBean() {
		this.nombre = "";
		this.NIA = 0;
		this.fecha_creacion = new Timestamp(0);
		this.grupo = "";
	}
	
	public AlumnoBean(String nombre, int NIA, Timestamp fecha, String grupo) {
		this.nombre = nombre;
		this.NIA = NIA;
		this.fecha_creacion = fecha;
		this.grupo = grupo;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public int getNIA() {
		return this.NIA;
	}
	
	public Timestamp getFecha() {
		return this.fecha_creacion;
	}
	
	public String getGrupo() {
		return this.grupo;
	}
}