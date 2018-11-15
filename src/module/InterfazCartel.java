package module;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import Bean.AlumnoBean;
import jdbc.Cursor;
import jdbc.JDBCTemplate;
import jdbc.MySQLConfiguration;

public class InterfazCartel {
	public static boolean obtenerTodosCarteles(List<Cartel> lista) {
		JDBCTemplate mysql = null;
		boolean correcto = false;
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM CARTEL")) {
				String titulo = c.getString("TITULO");
				int id = c.getInteger("ID");
				String noticia = c.getString("NOTICIA");
				String imagen = c.getString("IMAGEN");
				String pregunta_opinion = c.getString("PREGUNTA_OPINION");
				String reto = c.getString("RETO");
				int publico = c.getInteger("PUBLICO");
				Timestamp fecha = c.getTimestamp("FECHA");
				String enlace = c.getString("ENLACE");
				Cartel cart = new Cartel(id, titulo, noticia, imagen, pregunta_opinion, reto, publico, fecha, enlace);
				lista.add(cart);
			}
			correcto = lista.size() != 0;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (mysql != null) mysql.disconnect();
		}
		return correcto;
	}
	
	public static boolean obtenerTodosCartelGrupoAlumnos(List<CartelGrupoAlumnos> lista) {
		JDBCTemplate mysql = null;
		boolean correcto = false;
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM CARTEL, GRUPO WHERE CARTEL.ID = GRUPO.CARTEL")) {
				String titulo = c.getString("TITULO");
				int id = c.getInteger("ID");
				String noticia = c.getString("NOTICIA");
				String imagen = c.getString("IMAGEN");
				String pregunta_opinion = c.getString("PREGUNTA_OPINION");
				String reto = c.getString("RETO");
				int publico = c.getInteger("PUBLICO");
				Timestamp fecha = c.getTimestamp("FECHA");
				String enlace = c.getString("ENLACE");
				String nombreGrupo = c.getString("NOMBRE");
				Cartel cart = new Cartel(id, titulo, noticia, imagen, pregunta_opinion, reto, publico, fecha, enlace);
				
				List<AlumnoBean> listaAlumnos = new ArrayList<>();
				for(Cursor c2: mysql.executeQueryAndGetCursor("SELECT * FROM GRUPO JOIN ALUMNO  WHERE ALUMNO.GRUPO_NOMBRE = '" + nombreGrupo + "' AND GRUPO.CARTEL = " + id)) {
					String nombreAlumno = c2.getString("ALUMNO.NOMBRE");
					int nia = c2.getInteger("NIA");
					Timestamp fechaAlumno = c2.getTimestamp("FECHA_INGRESO");
					AlumnoBean al = new AlumnoBean(nombreAlumno, nia, fechaAlumno, nombreGrupo);
					listaAlumnos.add(al);
				}
				CartelGrupoAlumnos elemento = new CartelGrupoAlumnos(cart, nombreGrupo, listaAlumnos);
				lista.add(elemento);
			}
			
			correcto = lista.size() != 0;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (mysql != null) mysql.disconnect();
		}
		return correcto;
	}
	
	public static Cartel obtenerCartel(int cartel) {
		JDBCTemplate mysql = null;
		Properties prop = new Properties();
		Cartel cart = new Cartel();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM CARTEL WHERE ID=" + cartel)) {
				String titulo = c.getString("TITULO");
				int id = c.getInteger("ID");
				String noticia = c.getString("NOTICIA");
				String imagen = c.getString("IMAGEN");
				String pregunta_opinion = c.getString("PREGUNTA_OPINION");
				String reto = c.getString("RETO");
				int publico = c.getInteger("PUBLICO");
				Timestamp fecha = c.getTimestamp("FECHA");
				String enlace = c.getString("ENLACE");
				cart = new Cartel(id, titulo, noticia, imagen, pregunta_opinion, reto, publico, fecha, enlace);
			}
			return cart;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return null;
		} finally {
			if (mysql != null) mysql.disconnect();
		}
	}
	
	public static int subirCartel(Cartel cartel, Pregunta pregunta1, Pregunta pregunta2, Pregunta pregunta3, int idCartel, String grupo) {
		JDBCTemplate mysql = null;
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			if(idCartel == 0) {
				int max = 0;
				for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM CARTEL")) {
					int id = c.getInteger("ID");
					if(id > max) {
						max = id;
					}
				}
				idCartel = max + 1;
				InterfazGrupo.anyadirGrupo(new Grupo(idCartel, grupo));
			}
			mysql.executeSentence("SET FOREIGN_KEY_CHECKS = 0");
			mysql.executeSentence("REPLACE INTO CARTEL(ID, TITULO, NOTICIA, IMAGEN, PREGUNTA_OPINION, RETO, PUBLICO, FECHA, ENLACE) VALUES (?,?,?,?,?,?,?,?,?)",idCartel, cartel.getTitulo(), cartel.getNoticia(), "images/" + String.valueOf(idCartel) + ".png", cartel.getPregunta(), cartel.getReto(), cartel.getPublico(), new Timestamp(System.currentTimeMillis()), cartel.getEnlace());
			mysql.executeSentence("SET FOREIGN_KEY_CHECKS = 1");
			mysql.executeSentence("REPLACE INTO PREGUNTA(NUM, CARTEL, ENUNCIADO, RESPUESTA1, RESPUESTA2, RESPUESTA3, CORRECTA1, CORRECTA2, CORRECTA3, EXPLICACION) VALUES (?,?,?,?,?,?,?,?,?,?)",pregunta1.getNum(), idCartel, pregunta1.getEnunciado(), pregunta1.getRespuesta1(), pregunta1.getRespuesta2(), pregunta1.getRespuesta3(), pregunta1.getCorrecta1(), pregunta1.getCorrecta2(), pregunta1.getCorrecta3(), pregunta1.getExplicacion());
			mysql.executeSentence("REPLACE INTO PREGUNTA(NUM, CARTEL, ENUNCIADO, RESPUESTA1, RESPUESTA2, RESPUESTA3, CORRECTA1, CORRECTA2, CORRECTA3, EXPLICACION) VALUES (?,?,?,?,?,?,?,?,?,?)",pregunta2.getNum(), idCartel, pregunta2.getEnunciado(), pregunta2.getRespuesta1(), pregunta2.getRespuesta2(), pregunta2.getRespuesta3(), pregunta2.getCorrecta1(), pregunta2.getCorrecta2(), pregunta2.getCorrecta3(), pregunta2.getExplicacion());
			mysql.executeSentence("REPLACE INTO PREGUNTA(NUM, CARTEL, ENUNCIADO, RESPUESTA1, RESPUESTA2, RESPUESTA3, CORRECTA1, CORRECTA2, CORRECTA3, EXPLICACION) VALUES (?,?,?,?,?,?,?,?,?,?)",pregunta3.getNum(), idCartel, pregunta3.getEnunciado(), pregunta3.getRespuesta1(), pregunta3.getRespuesta2(), pregunta3.getRespuesta3(), pregunta3.getCorrecta1(), pregunta3.getCorrecta2(), pregunta3.getCorrecta3(), pregunta3.getExplicacion());
			return idCartel;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return 0;
		} finally {
			if (mysql != null) mysql.disconnect();
		}
	}
	
	public static int obtenerCartelId(String grupo) {
		JDBCTemplate mysql = null;
		int id = 0; // Si sigue siendo 0 cuando llegue al return el grupo no ha subido ningun cartel
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM GRUPO WHERE NOMBRE=\"" + grupo + "\"")) {
				id = c.getInteger("CARTEL");
			}
			return id;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return id;
		} finally {
			if (mysql != null) mysql.disconnect();
		}
	}
	
	public static int obtenerCartelByGrupo(String grupo, Cartel cartel, List<Pregunta> lista) {
		JDBCTemplate mysql = null;
		int id = 0; // Si sigue siendo 0 cuando llegue al return el grupo no ha subido ningun cartel
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM GRUPO WHERE NOMBRE=\"" + grupo + "\"")) {
				id = c.getInteger("CARTEL");
			}
			if(id != 0) {
				for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM CARTEL WHERE ID=" + id)) {
					String titulo = c.getString("TITULO");
					String noticia = c.getString("NOTICIA");
					String imagen = c.getString("IMAGEN");
					String pregunta_opinion = c.getString("PREGUNTA_OPINION");
					String reto = c.getString("RETO");
					int publico = c.getInteger("PUBLICO");
					Timestamp fecha = c.getTimestamp("FECHA");
					String enlace = c.getString("ENLACE");
					cartel.poblar(id, titulo, noticia, imagen, pregunta_opinion, reto, publico, fecha, enlace);
				}
				for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM PREGUNTA WHERE CARTEL=" + id)) {
					int num = c.getInteger("NUM");
					String enunciado = c.getString("ENUNCIADO");
					String respuesta1 = c.getString("RESPUESTA1");
					String respuesta2 = c.getString("RESPUESTA2");
					String respuesta3 = c.getString("RESPUESTA3");
					int correcta1 = c.getInteger("CORRECTA1");
					int correcta2 = c.getInteger("CORRECTA2");
					int correcta3 = c.getInteger("CORRECTA3");
					String explicacion = c.getString("EXPLICACION");
					Pregunta preg = new Pregunta(num, id, enunciado, respuesta1, respuesta2, respuesta3, correcta1, correcta2, correcta3, explicacion);
					lista.add(preg);
				}
			}
			return id;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return id;
		} finally {
			if (mysql != null) mysql.disconnect();
		}
	}
	
	private static JDBCTemplate configureMySQL(Properties prop)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException {
		JDBCTemplate mysql;
		mysql = new JDBCTemplate(new MySQLConfiguration(prop.getProperty("database.mysql.host"),
				prop.getProperty("database.mysql.port"),
				prop.getProperty("database.mysql.dbname")),
				prop.getProperty("database.mysql.user"),
				prop.getProperty("database.mysql.password")
				);
		mysql.connect();
		System.out.println("Conectado a " + mysql);
		return mysql;
	}
}