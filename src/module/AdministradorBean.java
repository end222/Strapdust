package module;

public class AdministradorBean {
	private String nombre;
	private String PDI;
	
	public AdministradorBean(String nombre, String PDI) {
		this.nombre = nombre;
		this.PDI = PDI;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getPDI() {
		return this.PDI;
	}
}
