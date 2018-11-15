package module;

import java.sql.Timestamp;

public class Alumno {
	private String nombre;
	private int NIA;
	private String password;
	private Timestamp fecha;
	private String grupo;
	
	public Alumno(String nombre, int NIA) {
		this.nombre = nombre;
		this.NIA = NIA;
	}
	
	public Alumno(String nombre, int NIA, Timestamp fecha) {
		this.nombre = nombre;
		this.NIA = NIA;
		this.fecha = fecha;
	}
	
	public Alumno(String nombre, int NIA, String password, Timestamp fecha, String grupo) {
		this.nombre = nombre;
		this.NIA = NIA;
		this.password = password;
		this.fecha = fecha;
		this.grupo = grupo;
	}
	
	public void anyadirPassword(String password) {
		this.password = password;
	}
	
	public void anyadirGrupo(String grupo) {
		this.grupo = grupo;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public int getNIA() {
		return this.NIA;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public Timestamp getFecha() {
		return this.fecha;
	}
	
	public String getGrupo() {
		return this.grupo;
	}
}
