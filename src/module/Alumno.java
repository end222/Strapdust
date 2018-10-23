package module;

import java.sql.Timestamp;

public class Alumno {
	private String nombre;
	private int NIA;
	private String password;
	private Timestamp fecha_creacion;
	private String grupo_nombre;
	
	public Alumno(String nombre, int NIA, String password, Timestamp fecha) {
		this.nombre = nombre;
		this.NIA = NIA;
		this.fecha_creacion = fecha;
	}
	
	public Alumno(String nombre, int NIA, String password, Timestamp fecha, String grupo) {
		this.nombre = nombre;
		this.NIA = NIA;
		this.password = password;
		this.fecha_creacion = fecha;
		this.grupo_nombre = grupo;
	}
	
	public void anyadirPassword(String password) {
		this.password = password;
	}
	
	public void anyadirGrupo(String grupo) {
		this.grupo_nombre = grupo;
	}
	
	public String verNombre() {
		return this.nombre;
	}
	
	public int verNIA() {
		return this.NIA;
	}
	
	public String verPassword() {
		return this.password;
	}
	
	public Timestamp verFecha() {
		return this.fecha_creacion;
	}
	
	public String verGrupo() {
		return this.grupo_nombre;
	}
}
