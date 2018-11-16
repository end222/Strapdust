package module;

import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import jdbc.Cursor;
import jdbc.JDBCTemplate;
import jdbc.MySQLConfiguration;

public class InterfazEstadisticas {
	public static boolean obtenerTodasEstadisticas(List<Estadisticas> lista) {
		JDBCTemplate mysql = null;
		boolean correcto = false;
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM ESTADISTICAS")) {
				int id = c.getInteger("ID");
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
	
	public static void obtenerRespuestasCorrectas(int id, List<Integer> lista) {
		JDBCTemplate mysql = null;
		boolean correcto = false;
		Properties prop = new Properties();
		int correctas1 = 0;
		int correctas2 = 0;
		int correctas3 = 0;
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM ESTADISTICAS WHERE CARTEL=\"" + id + "\"")) {
				int idStats = c.getInteger("ID");
				int acierto1 = c.getInteger("ACIERTO1");
				if(acierto1 == 1) correctas1++;
				int acierto2 = c.getInteger("ACIERTO2");
				if(acierto2 == 1) correctas2++;
				int acierto3 = c.getInteger("ACIERTO3");
				if(acierto3 == 1) correctas3++;
				int edad = c.getInteger("EDAD");
				int unizar = c.getInteger("UNIZAR");
				int cartel = c.getInteger("CARTEL");
				String opinion = c.getString("OPINION");
			}
			lista.add(correctas1);
			lista.add(correctas2);
			lista.add(correctas3);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (mysql != null) mysql.disconnect();
		}
	}
	
	public static Estadisticas obtenerEstadisticas(int ID) {
		JDBCTemplate mysql = null;
		Properties prop = new Properties();
		Estadisticas stats = new Estadisticas();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM ESTADISTICAS")) {
				int id = c.getInteger("ID");
				int acierto1 = c.getInteger("ACIERTO1");
				int acierto2 = c.getInteger("ACIERTO2");
				int acierto3 = c.getInteger("ACIERTO3");
				int edad = c.getInteger("EDAD");
				int unizar = c.getInteger("UNIZAR");
				int cartel = c.getInteger("CARTEL");
				String opinion = c.getString("OPINION");
				stats = new Estadisticas(id, acierto1, acierto2, acierto3, edad, unizar, cartel, opinion);
			}
			return stats;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return null;
		} finally {
			if (mysql != null) mysql.disconnect();
		}
	}
	
	public static boolean anyadirEstadisticas(Estadisticas stats) {
		JDBCTemplate mysql = null;
		boolean correcto = false;
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			mysql.executeSentence("REPLACE INTO ESTADISTICAS(ID,ACIERTO1,ACIERTO2, ACIERTO3, EDAD, UNIZAR, CARTEL, OPINION) VALUES (?,?,?,?,?,?,?,?)",stats.verID(), stats.verAcierto1(), stats.verAcierto2(), stats.verAcierto3(), stats.verEdad(), stats.verUnizar(), stats.verCartel(), stats.verOpinion());
			correcto=true;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (mysql != null) mysql.disconnect();
		}
		return correcto;
	}
	
	public static int obtenerIDMax() {
		int max = 0;
		JDBCTemplate mysql = null;
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM ESTADISTICAS")) {
				int id = c.getInteger("ID");
				if(id > max) {
					max = id;
				}
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (mysql != null) mysql.disconnect();
		}
		return max;
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
