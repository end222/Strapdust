import java.sql.SQLException;
import java.util.Properties;

import jdbc.Cursor;
import jdbc.JDBCTemplate;
import jdbc.MySQLConfiguration;
import jdbc.OracleConfiguration;

/**
 * Esta clase lee datos de la tabla "title" en MySQL, los procesa y 
 * los inserta en la tabla "peliculas_ejemplo" en BD Oracle.
 */
public class EjemploCargaDatos {

	public static void main(String args[]) {
		JDBCTemplate oracle = null;
		JDBCTemplate mysql = null;
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class
					.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			
			// Esta llamada solo se tendria que hacer 
			// si no existe la tabla de peliculas en Oracle
			crearTablaDePeliculas(oracle);
			
			// Esta llamada solo se tendria que hacer
			// si nos interesa borrar todo el contenido
			// de la tabla de peliculas en Oracle (por ejemplo
			// queremos volver a cargar todos los datos)
			borrarContenidoTablaDePeliculas(oracle);
			
			// Seleccionamos los titulos y el ano de produccion
			// de las peliculas almacenadas en la tabla
			// "title" en la BD MySQL.
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT title, production_year FROM title")) {

				// De cada fila extraemos los datos y los procesamos. 
				// A continuacion los insertamos en la BD Oracle.
				System.out.println("Insertando "+c.getString("title")+" - "+(2013-c.getInteger("production_year")));
				oracle.executeSentence("INSERT INTO PELICULAS_EJEMPLO(NOMBRE,ANTIGUEDAD) VALUES (?,?)", 
						c.getString("title"), 2013-c.getInteger("production_year"));
			}
			
			// Finalmente listamos el contenido resultante
			oracle.executeQuery("SELECT * FROM PELICULAS_EJEMPLO");
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (oracle != null) oracle.disconnect();
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

	private static JDBCTemplate configureOracle(Properties prop)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException {
		JDBCTemplate oracle;
		oracle = new JDBCTemplate(new OracleConfiguration(prop.getProperty("database.oracle.host"),
				prop.getProperty("database.oracle.port"),
				prop.getProperty("database.oracle.sid")),
				prop.getProperty("database.oracle.user"),
				prop.getProperty("database.oracle.password")
				);
		oracle.connect();
		System.out.println("Conectado a " + oracle);
		return oracle;
	}

	private static void borrarContenidoTablaDePeliculas(JDBCTemplate o) {
		StringBuffer sb = new StringBuffer();
		sb.append("TRUNCATE TABLE PELICULAS_EJEMPLO");
		o.executeSentence(sb.toString());
	}

	private static void crearTablaDePeliculas(JDBCTemplate o) {
		StringBuffer sb = new StringBuffer();
		sb.append("CREATE TABLE PELICULAS_EJEMPLO(");
		sb.append("NOMBRE VARCHAR(30) PRIMARY KEY,");
		sb.append("ANTIGUEDAD NUMBER(4)");
		sb.append(")");
		o.executeSentence(sb.toString());
	}
}
