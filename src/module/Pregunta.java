package module;

public class Pregunta {
	private int num;
	private int cartel;
	private String enunciado;
	private String respuesta1;
	private String respuesta2;
	private String respuesta3;
	private int correcta1;
	private int correcta2;
	private int correcta3;
	private String explicacion;
	
	public Pregunta(int num, int cartel, String enunciado, String respuesta1, String respuesta2, String respuesta3, int correcta1, int correcta2, int correcta3, String explicacion) {
		this.num = num;
		this.cartel = cartel;
		this.enunciado = enunciado;
		this.respuesta1 = respuesta1;
		this.respuesta2 = respuesta2;
		this.respuesta3 = respuesta3;
		this.correcta1 = correcta1;
		this.correcta2 = correcta2;
		this.correcta3 = correcta3;
		this.explicacion = explicacion;
	}
	
	public int getNum() {
		return this.num;
	}
	
	public int getCartel() {
		return this.cartel;
	}
	
	public String getEnunciado() {
		return this.enunciado;
	}
	
	public String getRespuesta1() {
		return this.respuesta1;
	}
	
	public String getRespuesta2() {
		return this.respuesta2;
	}
	
	public String getRespuesta3() {
		return this.respuesta3;
	}
	
	public int getCorrecta1() {
		return this.correcta1;
	}
	
	public int getCorrecta2() {
		return this.correcta2;
	}
	
	public int getCorrecta3() {
		return this.correcta3;
	}
	
	public String getExplicacion() {
		return this.explicacion;
	}
}
