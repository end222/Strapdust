package module;

import java.util.List;

import Bean.AlumnoBean;

public class GrupoAlumnos {
	private String grupo;
	private List<AlumnoBean> lista;
	
	public GrupoAlumnos(String grupo, List<AlumnoBean> listaAlumnos) {
		this.grupo = grupo;
		this.lista = listaAlumnos;
	}
	
	public String getGrupo() {
		return this.grupo;
	}
	
	public List<AlumnoBean> getLista() {
		return this.lista;
	}
	
	public AlumnoBean getLista(int i) {
		return this.lista.get(i);
	}
	
}
