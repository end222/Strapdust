package module;

public class Administrador {
	private String nombre;
	private String PDI;
	private String password;
	private String token;
	
	public Administrador(String nombre, String PDI) {
		this.nombre = nombre;
		this.PDI = PDI;
	}
	
	public Administrador(String nombre, String PDI, String password, String token) {
		this.nombre = nombre;
		this.PDI = PDI;
		this.password = password;
		this.token = token;
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
	
	public String verToken() {
		return this.token;
	}
}
