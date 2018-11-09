package module;

import java.util.List;

public class GrupoAlumnos {
	private Grupo grupo;
	private List<Alumno> listaAlumnos;
	
	public GrupoAlumnos(Grupo grupo, List<Alumno> listaAlumnos) {
		this.grupo = grupo;
		this.listaAlumnos = listaAlumnos;
	}
	
	public Grupo verGrupo() {
		return this.grupo;
	}
	
	public List<Alumno> verAlumnos() {
		return this.listaAlumnos;
	}
	
}
