package module;

import java.sql.SQLException;
import java.util.Properties;

import jdbc.Cursor;
import jdbc.JDBCTemplate;
import jdbc.MySQLConfiguration;

public class InterfazGrupo {
	public static boolean anyadirGrupo(Grupo gr) {
		JDBCTemplate mysql = null;
		boolean correcto = false;
		String nombre = "";
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM GRUPO WHERE NOMRBRE=" + gr.verNombre())) {
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
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM ALUMNO WHERE NIA=" + al.verNIA())) {
				nia = c.getInteger("NIA");
			}
			if (nia == -1) correcto = false; // No se ha encontrado el alumno en la base de datos
			if (correcto) {
				mysql.executeSentence("REPLACE INTO ALUMNO(NOMBRE, NIA, FECHA_INGRESO, PASS, GRUPO_NOMBRE) VALUES (?,?,?,?,?)",al.verNombre(), al.verNIA(), al.verFecha(), al.verPassword(), gr.verNombre());
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
