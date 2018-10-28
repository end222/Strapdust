package module;

import java.util.List;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;

import jdbc.Cursor;
import jdbc.JDBCTemplate;
import jdbc.MySQLConfiguration;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import module.Alumno;

public class InterfazAlumno {
	public static boolean obtenerTodosAlumnos(List<Alumno> lista) {
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
	
	public static boolean anyadirAlumno(Alumno al) {
		JDBCTemplate mysql = null;
		boolean correcto = false;
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
				mysql.executeSentence("INSERT INTO ALUMNO(NOMBRE, NIA, FECHA_INGRESO, PASS, GRUPO_NOMBRE) VALUES (?,?,?,?,?)",al.verNombre(), al.verNIA(), al.verFecha(), al.verPassword(), al.verGrupo());
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (mysql != null) mysql.disconnect();
		}
		return correcto;
	}
	
	public static boolean anyadirPassword(int nia, String password) {
		JDBCTemplate mysql = null;
		boolean correcto = true;
		int niaCheck = -1;
		String nombre = "";
		Timestamp fecha = new Timestamp(0);
		String grupo = "";
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM ALUMNO WHERE NIA=" + nia)) {
				nombre = c.getString("NOMBRE");
				fecha = c.getTimestamp("FECHA");
				grupo = c.getString("GRUPO");
				niaCheck = c.getInteger("NIA");
			}
			if (niaCheck == nia) correcto = false; // No se ha encontrado el alumno en la base de datos
			if (correcto) {
				MessageDigest md = MessageDigest.getInstance("SHA-256");
		        byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

		        StringBuilder pass256 = new StringBuilder();
		        for (byte b : hashInBytes) {
		            pass256.append(String.format("%02x", b));
		        }
				mysql.executeSentence("REPLACE INTO ALUMNO(NOMBRE, NIA, FECHA_INGRESO, PASS, GRUPO_NOMBRE) VALUES (?,?,?,?,?)", nombre, nia, fecha, pass256.toString(), grupo);
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (mysql != null) mysql.disconnect();
		}
		return correcto;
	}
	
	public static int comprobarPassword(String nia, String password) {
		JDBCTemplate mysql = null;
		int correcto = 0;
		boolean alumno = isNumeric(nia);
		String niaCheck = "";
		String pdiCheck = "";
		String passwordCheck = "";
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			if(alumno) {
				for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM ALUMNO WHERE NIA=" + nia)) {
					niaCheck = c.getString("NIA");
					passwordCheck = c.getString("PASS");
				}
				if (!niaCheck.equals(nia)) correcto = 1; // No se ha encontrado el alumno en la base de datos
				else {
					MessageDigest md = MessageDigest.getInstance("SHA-256");
			        byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
	
			        StringBuilder pass256 = new StringBuilder();
			        for (byte b : hashInBytes) {
			            pass256.append(String.format("%02x", b));
			        }
			        if(!pass256.toString().equals(passwordCheck)) {
			        	correcto = 2; // La contrasaeña no es correcta
			        }
				}
			}
			else {
				for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM ADMINISTRADOR WHERE PDI='" + nia +"'")) {
					pdiCheck = c.getString("PDI");
					passwordCheck = c.getString("PASS");
				}
				if (pdiCheck.equals(nia)) {
					MessageDigest md = MessageDigest.getInstance("SHA-256");
			        byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

			        StringBuilder pass256 = new StringBuilder();
			        for (byte b : hashInBytes) {
			            pass256.append(String.format("%02x", b));
			        }
			        if(!pass256.toString().equals(passwordCheck)) {
			        	correcto = 4; // La contrasaeña no es correcta (siendo admin)
			        }
			        else {
			        	correcto = 3; // La contraseña es correcta y se trata de un administrador 
			        }
				}
				else {
					correcto = 1;
				}
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			correcto = 1;
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
	private static boolean isNumeric(String str){  
		try {  
			double d = Double.parseDouble(str);  
		}  
		catch(NumberFormatException nfe)  
		{  
			return false;  
		}  
		return true;  
	}
}
