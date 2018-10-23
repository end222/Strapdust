package module;

public class Estadisticas {
	private int ID;
	private int acierto1;
	private int acierto2;
	private int acierto3;
	private int edad;
	private int unizar;
	private int cartel;
	private String opinion;
	
	public Estadisticas(int ID, int acierto1, int acierto2, int acierto3, int edad, int unizar, int cartel, String opinion) {
		this.ID = ID;
		this.acierto1 = acierto1;
		this.acierto2 = acierto2;
		this.acierto3 = acierto3;
		this.edad = edad;
		this.unizar = unizar;
		this.cartel = cartel;
		this.opinion = opinion;
	}
	
	public Estadisticas(int ID, int acierto1, int acierto2, int acierto3, int cartel) {
		this.ID = ID;
		this.acierto1 = acierto1;
		this.acierto2 = acierto2;
		this.acierto3 = acierto3;
		this.cartel = cartel;
	}
	
	public int verID() {
		return this.ID;
	}
	
	public int verAcierto1() {
		return this.acierto1;
	}
	
	public int verAcierto2() {
		return this.acierto2;
	}
	
	public int verAcierto3() {
		return this.acierto3;
	}
	
	public int verEdad() {
		return this.edad;
	}
	
	public int verUnizar() {
		return this.unizar;
	}
	
	public int verCartel() {
		return this.cartel;
	}
	
	public String verOpinion() {
		return this.opinion;
	}
}
