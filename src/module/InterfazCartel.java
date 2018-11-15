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