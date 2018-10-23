package module;

import java.sql.SQLException;
import java.util.Properties;

import jdbc.Cursor;
import jdbc.JDBCTemplate;
import jdbc.MySQLConfiguration;

public class InterfazGrupo {
	public boolean anyadirGrupo(Grupo gr) {
		JDBCTemplate mysql = null;
		boolean correcto = true;
		String nombre = "";
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM GRUPO WHERE NOMRBRE=" + gr.verNombre())) {
				nombre = c.getString("NOMBRE");
			}
			if (nombre == "") correcto = true; // No se ha encontrado el grupo en la base de datos
			if (correcto) {
				mysql.executeSentence("INSERT INTO GRUPO(NOMBRE, NIA, FECHA_INGRESO, PASS, GRUPO_NOMBRE) VALUES (?,?,?,?,?)",al.verNombre(), al.verNIA(), al.verFecha(), al.verPassword(), al.verGrupo());
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
