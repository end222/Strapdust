package module;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;

import com.mysql.jdbc.Connection;
import jdbc.Cursor;
import jdbc.JDBCTemplate;
import jdbc.MySQLConfiguration;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import module.Alumno;
import Bean.AlumnoBean;

public class InterfazAlumno {
	public static boolean obtenerTodosAlumnos(List<AlumnoBean> lista) {
		JDBCTemplate mysql = null;
		boolean correcto = false;
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM ALUMNO")) {
				String nombre = c.getString("NOMBRE");
				int nia = c.getInteger("NIA");
				Timestamp fecha = c.getTimestamp("FECHA_INGRESO");
				String grupo = c.getString("GRUPO_NOMBRE");
				AlumnoBean al = new AlumnoBean(nombre, nia, fecha, grupo);
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
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM ALUMNO WHERE NIA=" + al.getNIA())) {
				nia = c.getInteger("NIA");
			}
			if (nia == -1) correcto = true; // No se ha encontrado el alumno en la base de datos
			if (correcto) {
				mysql.executeSentence("INSERT INTO ALUMNO(NOMBRE, NIA, FECHA_INGRESO, PASS, GRUPO_NOMBRE) VALUES (?,?,?,?,?)",al.getNombre(), al.getNIA(), al.getFecha(), al.getPassword(), al.getGrupo());
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (mysql != null) mysql.disconnect();
		}
		return correcto;
	}
	
	public static int anyadirListaAlumnos(List<Alumno> lista) {
		JDBCTemplate mysql = null;
		boolean correcto = false;
		int insertados = 0;
		int nia = -1;
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			Connection connection = mysql.getConnection();
			java.sql.PreparedStatement statement = connection.prepareStatement("INSERT INTO ALUMNO(NOMBRE, NIA, FECHA_INGRESO, PASS, GRUPO_NOMBRE) VALUES (?,?,?,?,?)");
			for (Alumno al : lista) {
				for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM ALUMNO WHERE NIA=" + al.getNIA())) {
					nia = c.getInteger("NIA");
				}
				if (nia == -1) correcto = true; // No se ha encontrado el alumno en la base de datos
				if (correcto) {
					statement.setString(1, al.getNombre());
					statement.setInt(2, al.getNIA());
					statement.setTimestamp(3, al.getFecha());
					statement.setString(4, al.getPassword());
					statement.setString(5, al.getGrupo());
					statement.addBatch();
					insertados++;
				}
				if (insertados % 1000 == 0 || insertados == lista.size()) {
	                statement.executeBatch(); // Hacer batch de 1000 alumnos como maximo a la vez
	            }
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (mysql != null) mysql.disconnect();
		}
		return insertados;
	}
	
	public static List<Integer> eliminarListaAlumnos(List<Integer> lista) {
		JDBCTemplate mysql = null;
		int procesados = 0;
		int nia = -1;
		List<Integer> listaInexistentes = new ArrayList<>();
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			Connection connection = mysql.getConnection();
			java.sql.PreparedStatement statement = connection.prepareStatement("DELETE FROM ALUMNO WHERE NIA=?");
			for (Integer al : lista) {
				procesados++;
				for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM ALUMNO WHERE NIA=" + al)) {
					nia = c.getInteger("NIA");
				}
				if (nia == al) {	// El alumno existe en la BBDD
					statement.setInt(1, al);
					statement.addBatch();
				}
				else {
					listaInexistentes.add(al); //Se añade a la lista de los inexistentes
				}
				if (procesados % 1000 == 0 || procesados == lista.size()) {
	                statement.executeBatch(); // Hacer batch de 1000 alumnos como maximo a la vez
	            }
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (mysql != null) mysql.disconnect();
		}
		return listaInexistentes;
	}
	
	public static boolean anyadirPassword(int nia, String password) {
		JDBCTemplate mysql = null;
		boolean correcto = true;
		Timestamp fecha = new Timestamp(System.currentTimeMillis());
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
				MessageDigest md = MessageDigest.getInstance("SHA-256");
		        byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

		        StringBuilder pass256 = new StringBuilder();
		        for (byte b : hashInBytes) {
		            pass256.append(String.format("%02x", b));
		        }
		        mysql.executeSentence ("UPDATE ALUMNO SET PASS = ?, FECHA_INGRESO = ? WHERE (NIA = ?)", pass256.toString(), fecha, nia);
		        mysql.executeSentence("DELETE FROM REGISTRO WHERE ALUMNO_NIA = ?" , nia);
		        
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
			        	correcto = 2; // La contraseña no es correcta
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
	
	public static AlumnoBean obtenerUnAlumno(String niaString) {
		JDBCTemplate mysql = null;
		Properties prop = new Properties();
		String nombre = "";
		int nia = 0;
		Timestamp fecha = new Timestamp(0);
		String grupo = "";
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM ALUMNO WHERE NIA=" + niaString)) {
				nombre = c.getString("NOMBRE");
				nia = c.getInteger("NIA");
				fecha = c.getTimestamp("FECHA_INGRESO");
				grupo = c.getString("GRUPO_NOMBRE");
			}
			AlumnoBean al = new AlumnoBean(nombre, nia, fecha, grupo);
			return al;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return null;
		} finally {
			if (mysql != null) mysql.disconnect();
		}
	}
	
	public static boolean anyadirToken(int NIA, String token) {
		JDBCTemplate mysql = null;
		boolean correcto = false;
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			mysql.executeSentence("REPLACE INTO REGISTRO(ALUMNO_NIA, TOKEN) VALUES (?,?)",NIA, token);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (mysql != null) mysql.disconnect();
			correcto = true;
		}
		return correcto;
	}
	
	
	public static int leerToken(String token) {
		JDBCTemplate mysql = null;
		int NIA = 0;
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM REGISTRO WHERE TOKEN=\"" + token +"\"")) {
				NIA = c.getInteger("ALUMNO_NIA");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (mysql != null) mysql.disconnect();
		}
		return NIA;
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
			Double.parseDouble(str);  
		}  
		catch(NumberFormatException nfe)  
		{  
			return false;  
		}  
		return true;  
	}
	
	public static int obtenerNumeroAlumnos() {
		JDBCTemplate mysql = null;
		int num = -1;
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			Connection connection = mysql.getConnection();
			java.sql.PreparedStatement statement = connection.prepareStatement("select count(*) from ALUMNO");
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				num = rs.getInt("count(*)");
		    }
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (mysql != null) mysql.disconnect();
		}
		return num;
	}
	
}
