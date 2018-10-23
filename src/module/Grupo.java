package module;

public class Grupo {
	private int cartel;
	private String nombre;
	
	public Grupo(int cartel, String nombre) {
		this.cartel = cartel;
		this.nombre = nombre;
	}
	
	public Grupo(String nombre) {
		this.cartel = -1;
		this.nombre = nombre;
	}
	
	void anyadirCartel(int cartel) {
		this.cartel = cartel;
	}
	
	public String verNombre() {
		return this.nombre;
	}
	
	public int verCartel() {
		return this.cartel;
	}
}
