package module;

import java.sql.Timestamp;

public class AlumnoBean {
	private String nombre;
	private int NIA;
	private Timestamp fecha_creacion;
	private String grupo_nombre;
	
	public AlumnoBean(String nombre, int NIA, Timestamp fecha, String grupo) {
		this.nombre = nombre;
		this.NIA = NIA;
		this.fecha_creacion = fecha;
		this.grupo_nombre = grupo;
	}
	
	public String verNombre() {
		return this.nombre;
	}
	
	public int verNIA() {
		return this.NIA;
	}
	
	public Timestamp verFecha() {
		return this.fecha_creacion;
	}
	
	public String verGrupo() {
		return this.grupo_nombre;
	}
}
