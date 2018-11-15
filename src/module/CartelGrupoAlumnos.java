package module;

import java.util.List;

import Bean.AlumnoBean;

public class CartelGrupoAlumnos {
	private Cartel cartel;
	private String grupo;
	private List<AlumnoBean> lista;
	
	public CartelGrupoAlumnos(Cartel cartel, String grupo, List<AlumnoBean> listaAlumnos) {
		this.cartel = cartel;
		this.grupo = grupo;
		this.lista = listaAlumnos;
	}
	
	public Cartel getCartel() {
		return this.cartel;
	}
	
	
	public String getGrupo() {
		return this.grupo;
	}
	
	public List<AlumnoBean> getLista() {
		return this.lista;
	}
	
}
