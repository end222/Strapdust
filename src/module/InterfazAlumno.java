package module;

import java.util.List;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;

import jdbc.Cursor;
import jdbc.JDBCTemplate;
import jdbc.MySQLConfiguration;

import module.Alumno;

public class InterfazAlumno {
	public boolean obtenerTodosAlumnos(List<Alumno> lista) {
		JDBCTemplate mysql = null;
		boolean correcto = false;
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM ADMINISTRADOR")) {
				String nombre = c.getString("NOMBRE");
				int nia = c.getInteger("NIA");
				Timestamp fecha = c.getTimestamp("FECHA_INGRESO");
				String password = c.getString("PASS");
				String grupo = c.getString("GRUPO_NOMBRE");
				Alumno al = new Alumno(nombre, nia, password, fecha, grupo);
				lista.add(al);
			}
			correcto = lista.size() != 0;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (mysql != null) mysql.disconnect();
		}
		return correcto;
	}
	
	public boolean anyadirAlumno(Alumno al) {
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
			if (nia == -1) correcto = true; // No se ha encontrado el alumno en la base de datos
			if (correcto) {
				mysql.executeSentence("INSERT INTO ESTADISTICAS(NOMBRE, NIA, FECHA_INGRESO, PASS, GRUPO_NOMBRE) VALUES (?,?,?,?,?)",al.verNombre(), al.verNIA(), al.verFecha(), al.verPassword(), al.verGrupo());
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
