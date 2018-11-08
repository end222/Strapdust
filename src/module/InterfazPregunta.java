package module;

import java.sql.SQLException;
import java.util.Properties;
import java.util.List;

import jdbc.Cursor;
import jdbc.JDBCTemplate;
import jdbc.MySQLConfiguration;

public class InterfazPregunta {
	public static boolean obtenerPreguntas(String idCartel, List<Pregunta> lista) {
		JDBCTemplate mysql = null;
		boolean correcto = false;
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM PREGUNTA WHERE CARTEL=" + idCartel)) {
				int num = c.getInteger("NUM");
				int cartel = c.getInteger("CARTEL");
				String enunciado = c.getString("ENUNCIADO");
				String respuesta1 = c.getString("RESPUESTA1");
				String respuesta2 = c.getString("RESPUESTA2");
				String respuesta3 = c.getString("RESPUESTA3");
				int correcta1 = c.getInteger("CORRECTA1");
				int correcta2 = c.getInteger("CORRECTA2");
				int correcta3 = c.getInteger("CORRECTA3");
				String explicacion = c.getString("EXPLICACION");
				Pregunta preg = new Pregunta(num, cartel, enunciado, respuesta1, respuesta2, respuesta3, correcta1, correcta2, correcta3, explicacion);
				lista.add(preg);
			}
			correcto = lista.size() != 0;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (mysql != null) mysql.disconnect();
		}
		return correcto;
	}
	
	public static boolean anyadirPregunta(Pregunta preg) {
		JDBCTemplate mysql = null;
		boolean correcto = false;
		int cartel = -1;
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM PREGUNTA WHERE CARTEL=" + preg.getCartel() + " AND NUM=" + preg.getNum())) {
				cartel = c.getInteger("CARTEL");
			}
			if (cartel == -1) correcto = true; // No se ha encontrado una pregunta con el mismo numero y cartel en la base de datos
			if (correcto) {
				mysql.executeSentence("INSERT INTO PREGUNTA(NUM, CARTEL, ENUNCIADO, RESPUESTA1, RESPUESTA2, RESPUESTA3, CORRECTA1, CORRECTA2, CORRECTA3, EXPLICACION) VALUES (?,?,?,?,?,?,?,?,?,?)",preg.getNum(), preg.getCartel(), preg.getEnunciado(), preg.getRespuesta1(), preg.getRespuesta2(), preg.getRespuesta3(), preg.getCorrecta1(), preg.getCorrecta2(), preg.getCorrecta3(), preg.getExplicacion());
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
