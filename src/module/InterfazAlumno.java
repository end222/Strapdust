package module;

import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;

import jdbc.Cursor;
import jdbc.JDBCTemplate;
import jdbc.MySQLConfiguration;

import module.Alumno;

public class InterfazAlumno {
	public List<Alumno> obtenerTodosAlumnos() {
		JDBCTemplate mysql = null;
		List<Alumno> lista = new ArrayList<Alumno>();
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
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (mysql != null) mysql.disconnect();
		}
		return lista;
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
