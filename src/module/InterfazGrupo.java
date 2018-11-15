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

public class InterfazGrupo {
	public static boolean obtenerTodosGrupoAlumnos(List<GrupoAlumnos> lista) {
		JDBCTemplate mysql = null;
		boolean correcto = false;
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM GRUPO")) {
				String nombreGrupo = c.getString("NOMBRE");
				List<AlumnoBean> listaAlumnos = new ArrayList<>();
				for(Cursor c2: mysql.executeQueryAndGetCursor("SELECT * FROM ALUMNO WHERE GRUPO_NOMBRE='" + nombreGrupo + "'")) {
					String nombreAlumno = c2.getString("NOMBRE");
					int nia = c2.getInteger("NIA");
					Timestamp fecha = c2.getTimestamp("FECHA_INGRESO");
					String grupo = c2.getString("GRUPO_NOMBRE");
					AlumnoBean al = new AlumnoBean(nombreAlumno, nia, fecha, grupo);
					listaAlumnos.add(al);
				}
				GrupoAlumnos grAl = new GrupoAlumnos(nombreGrupo, listaAlumnos);
				lista.add(grAl);
			}
			correcto = lista.size() != 0;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (mysql != null) mysql.disconnect();
		}
		return correcto;
	}
	
	
	public static boolean anyadirGrupo(Grupo gr) {
		JDBCTemplate mysql = null;
		boolean correcto = false;
		String nombre = "";
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM GRUPO WHERE NOMBRE='" + gr.verNombre() + "'")) {
				nombre = c.getString("NOMBRE");
			}
			if (nombre.equals("")) correcto = true; // No se ha encontrado el grupo en la base de datos
			if (correcto) {
				mysql.executeSentence("INSERT INTO GRUPO(NOMBRE, CARTEL) VALUES (?,?)",gr.verNombre(), gr.verCartel());
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (mysql != null) mysql.disconnect();
		}
		return correcto;
	}
	
	public static boolean anyadirAlumnoGrupo(Alumno al, Grupo gr) {
		JDBCTemplate mysql = null;
		boolean correcto = true;
		int nia = -1;
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM ALUMNO WHERE NIA=" + al.getNIA())) {
				nia = c.getInteger("NIA");
			}
			if (nia == -1) correcto = false; // No se ha encontrado el alumno en la base de datos
			if (correcto) {
				mysql.executeSentence("REPLACE INTO ALUMNO(NOMBRE, NIA, FECHA_INGRESO, PASS, GRUPO_NOMBRE) VALUES (?,?,?,?,?)",al.getNombre(), al.getNIA(), al.getFecha(), al.getPassword(), gr.verNombre());
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (mysql != null) mysql.disconnect();
		}
		return correcto;
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
