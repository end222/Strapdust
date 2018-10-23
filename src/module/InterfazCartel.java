package module;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

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
				Cartel cart = new Cartel(id, titulo, noticia, imagen, pregunta_opinion, reto, publico, fecha);
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