package module;

public class Administrador {
	private String nombre;
	private String PDI;
	private String password;
	
	public Administrador(String nombre, String PDI, String password) {
		this.nombre = nombre;
		this.PDI = PDI;
		this.password = password;
	}
	
	public String verNombre() {
		return this.nombre;
	}
	
	public String verPDI() {
		return this.PDI;
	}
	
	public String verPassword() {
		return this.password;
	}
}
