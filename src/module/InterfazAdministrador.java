package module;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import jdbc.Cursor;
import jdbc.JDBCTemplate;
import jdbc.MySQLConfiguration;

public class InterfazAdministrador {
	public static boolean obtenerTodosAdministradores(List<Administrador> lista) {
		JDBCTemplate mysql = null;
		boolean correcto = false;
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM ADMINISTRADOR")) {
				String nombre = c.getString("NOMBRE");
				String pdi = c.getString("PDI");
				String password = c.getString("PASS");
				Administrador admin = new Administrador(nombre, pdi, password);
				lista.add(admin);
			}
			correcto = lista.size() != 0;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (mysql != null) mysql.disconnect();
		}
		return correcto;
	}
	
	public static boolean anyadirAdministrador(Administrador admin) {
		JDBCTemplate mysql = null;
		boolean correcto = false;
		String pdi = "";
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM ADMINISTRADOR WHERE PDI=" + admin.verPDI())) {
				pdi = c.getString("PDI");
			}
			if (pdi == "") correcto = true; // No se ha encontrado el administrador en la base de datos
			if (correcto) {
				mysql.executeSentence("INSERT INTO ADMINISTRADOR(NOMBRE, PDI, PASS) VALUES (?,?,?)",admin.verNombre(), admin.verPDI(), admin.verPassword());
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (mysql != null) mysql.disconnect();
		}
		return correcto;
	}
	
	public static AdministradorBean obtenerUnAdmin(String pdiString) {
		JDBCTemplate mysql = null;
		Properties prop = new Properties();
		String nombre = "";
		String pdi = "";
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM ADMINISTRADOR WHERE NIA=" + pdiString)) {
				nombre = c.getString("NOMBRE");
				pdi = c.getString("NIA");
			}
			AdministradorBean admin = new AdministradorBean(nombre, pdi);
			return admin;
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
