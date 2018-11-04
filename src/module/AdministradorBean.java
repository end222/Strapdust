package module;

public class AdministradorBean {
	private String nombre;
	private String PDI;
	
	public AdministradorBean(String nombre, String PDI) {
		this.nombre = nombre;
		this.PDI = PDI;
	}
	
	public String verNombre() {
		return this.nombre;
	}
	
	public String verPDI() {
		return this.PDI;
	}
}
