package module;

import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import jdbc.Cursor;
import jdbc.JDBCTemplate;
import jdbc.MySQLConfiguration;

public class InterfazEstadisticas {
	public boolean obtenerTodasEstadísticas(List<Estadisticas> lista) {
		JDBCTemplate mysql = null;
		boolean correcto = false;
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM ESTADISTICAS")) {
				int id = c.getInteger("NUM");
				int acierto1 = c.getInteger("ACIERTO1");
				int acierto2 = c.getInteger("ACIERTO2");
				int acierto3 = c.getInteger("ACIERTO3");
				int edad = c.getInteger("EDAD");
				int unizar = c.getInteger("UNIZAR");
				int cartel = c.getInteger("CARTEL");
				String opinion = c.getString("OPINION");
				Estadisticas stats = new Estadisticas(id, acierto1, acierto2, acierto3, edad, unizar, cartel, opinion);
				lista.add(stats);
			}
			correcto = lista.size() != 0;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (mysql != null) mysql.disconnect();
		}
		return correcto;
	}
	
	public boolean anyadirEstadisticas(Estadisticas stats) {
		JDBCTemplate mysql = null;
		boolean correcto = false;
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			mysql.executeSentence("INSERT INTO ESTADISTICAS(NUM,ACIERTO1,ACIERTO2, ACIERTO3, EDAD, UNIZAR, CARTEL, OPINION) VALUES (?,?,?,?,?,?,?,?)",stats.verID(), stats.verAcierto1(), stats.verAcierto2(), stats.verAcierto3(), stats.verEdad(), stats.verUnizar(), stats.verCartel(), stats.verOpinion());
			correcto=true;
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
