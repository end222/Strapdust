package module;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.mysql.jdbc.Connection;

import Bean.AlumnoBean;
import jdbc.Cursor;
import jdbc.JDBCTemplate;
import jdbc.MySQLConfiguration;

public class InterfazGrupo {
	public static boolean obtenerTodosGrupoAlumnos(List<GrupoAlumnos> lista) {
		JDBCTemplate mysql = null;
		boolean correcto = false;
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM GRUPO")) {
				String nombreGrupo = c.getString("NOMBRE");
				List<AlumnoBean> listaAlumnos = new ArrayList<>();
				for(Cursor c2: mysql.executeQueryAndGetCursor("SELECT * FROM ALUMNO WHERE GRUPO_NOMBRE='" + nombreGrupo + "'")) {
					String nombreAlumno = c2.getString("NOMBRE");
					int nia = c2.getInteger("NIA");
					Timestamp fecha = c2.getTimestamp("FECHA_INGRESO");
					String grupo = c2.getString("GRUPO_NOMBRE");
					AlumnoBean al = new AlumnoBean(nombreAlumno, nia, fecha, grupo);
					listaAlumnos.add(al);
				}
				GrupoAlumnos grAl = new GrupoAlumnos(nombreGrupo, listaAlumnos);
				lista.add(grAl);
			}
			correcto = lista.size() != 0;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (mysql != null) mysql.disconnect();
		}
		return correcto;
	}
	
	public static GrupoAlumnos obtenerGrupoAlumnos(String nombreGrupo, int NIApropio) {
		JDBCTemplate mysql = null;
		Properties prop = new Properties();
		GrupoAlumnos grAl = null;
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			List<AlumnoBean> listaAlumnos = new ArrayList<>();
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM ALUMNO WHERE GRUPO_NOMBRE='" + nombreGrupo + "'")) {
				String nombreAlumno = c.getString("NOMBRE");
				int nia = c.getInteger("NIA");
				Timestamp fecha = c.getTimestamp("FECHA_INGRESO");
				String grupo = c.getString("GRUPO_NOMBRE");
				AlumnoBean al = new AlumnoBean(nombreAlumno, nia, fecha, grupo);
				listaAlumnos.add(al);
			}
			grAl = new GrupoAlumnos(nombreGrupo, listaAlumnos);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (mysql != null) mysql.disconnect();
		}
		return grAl;
	}
	
	
	public static boolean anyadirGrupo(Grupo gr) {
		JDBCTemplate mysql = null;
		boolean correcto = false;
		String nombre = "";
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM GRUPO WHERE NOMBRE='" + gr.verNombre() + "'")) {
				nombre = c.getString("NOMBRE");
			}
			if (nombre.equals("")) correcto = true; // No se ha encontrado el grupo en la base de datos
			if (correcto) {
				mysql.executeSentence("SET FOREIGN_KEY_CHECKS = 0");
				mysql.executeSentence("REPLACE INTO GRUPO(NOMBRE, CARTEL) VALUES (?,?)",gr.verNombre(), gr.verCartel());
				mysql.executeSentence("SET FOREIGN_KEY_CHECKS = 1");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (mysql != null) mysql.disconnect();
		}
		return correcto;
	}
	
	public static boolean anyadirAlumnoGrupo(Alumno al, Grupo gr) {
		JDBCTemplate mysql = null;
		boolean correcto = true;
		int nia = -1;
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM ALUMNO WHERE NIA=" + al.getNIA())) {
				nia = c.getInteger("NIA");
			}
			if (nia == -1) correcto = false; // No se ha encontrado el alumno en la base de datos
			if (correcto) {
				mysql.executeSentence("REPLACE INTO ALUMNO(NOMBRE, NIA, FECHA_INGRESO, PASS, GRUPO_NOMBRE) VALUES (?,?,?,?,?)",al.getNombre(), al.getNIA(), al.getFecha(), al.getPassword(), gr.verNombre());
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



	public static List<String> crearNuevoGrupo(List<String> lista) {
		JDBCTemplate mysql = null;
		List<String> listaErrores = new ArrayList<>();
		boolean correcto = true;
		int nia = -1;
		String nombre = "";
		String pass = "";
		Timestamp fecha = null;
		String gr = "";
		String grupo = lista.get(0);
		lista.remove(0);	//lista pasa a contener los NIAs en forma de String
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			Connection connection = mysql.getConnection();
			java.sql.PreparedStatement statement = connection.prepareStatement("REPLACE INTO ALUMNO(NOMBRE, NIA, FECHA_INGRESO, PASS, GRUPO_NOMBRE) VALUES (?,?,?,?,?)");
			for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM GRUPO WHERE NOMBRE='" + grupo + "'")) {
				gr = c.getString("NOMBRE");
			}
			if(!grupo.equals(gr)) {	//si el grupo no existe
				for (String al : lista) {
						for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM ALUMNO WHERE NIA=" + Integer.parseInt(al))) {
							nombre = c.getString("NOMBRE");
							nia = c.getInteger("NIA");
							fecha = c.getTimestamp("FECHA_INGRESO");
							pass = c.getString("PASS");
						}
						if (nia == Integer.parseInt(al)) {	
							statement.setString(1, nombre);
							statement.setInt(2, nia);
							statement.setTimestamp(3, fecha);
							statement.setString(4, pass);
							statement.setString(5, grupo);
							statement.addBatch();
						}
						else {
							correcto = false; // No se ha encontrado el alumno en la base de datos
							listaErrores.add(al);
						}
				}
				if(correcto) {
					Grupo nuevoGrupo = new Grupo(grupo);
					mysql.executeSentence("SET FOREIGN_KEY_CHECKS = 0");
					mysql.executeSentence("INSERT INTO GRUPO(NOMBRE, CARTEL) VALUES (?,?)",nuevoGrupo.verNombre(), nuevoGrupo.verCartel());
					statement.executeBatch();
					mysql.executeSentence("SET FOREIGN_KEY_CHECKS = 1");
				}
			}
			else {
				listaErrores.add("grupo_existente");
			}
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (mysql != null) mysql.disconnect();
		}
		return listaErrores;
	}


	public static List<String> eliminarListaGrupo(List<String> lista) {
		JDBCTemplate mysql = null;
		int procesadosTotal = 0;
		int gruposProcesados = 0;
		String nombreGrupo = "";
		List<String> listaInexistentes = new ArrayList<>();
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			Connection connection = mysql.getConnection();
			java.sql.PreparedStatement statement = connection.prepareStatement("DELETE FROM GRUPO WHERE NOMBRE=?");
			java.sql.PreparedStatement statementAlumno = connection.prepareStatement("REPLACE INTO ALUMNO(NOMBRE, NIA, FECHA_INGRESO, PASS, GRUPO_NOMBRE) VALUES (?,?,?,?,?)");
			for (String grupo : lista) {
				for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM GRUPO WHERE NOMBRE='" + grupo  + "'")) {
					nombreGrupo = c.getString("NOMBRE");
				}
				gruposProcesados++;
				if (nombreGrupo.equals(grupo)) {	// El grupo existe en la BBDD
					//Eliminar primero el campo grupo de los alumnos implicados
					for(Cursor c: mysql.executeQueryAndGetCursor("SELECT * FROM ALUMNO WHERE GRUPO_NOMBRE='" + grupo  + "'")) {
						procesadosTotal++;
						String nombre = c.getString("NOMBRE");
						int nia = c.getInteger("NIA");
						Timestamp fecha = c.getTimestamp("FECHA_INGRESO");
						String pass = c.getString("PASS");
						statementAlumno.setString(1, nombre);
						statementAlumno.setInt(2, nia);
						statementAlumno.setTimestamp(3, fecha);
						statementAlumno.setString(4, pass);
						statementAlumno.setString(5, null);
						statementAlumno.addBatch();
					}
					procesadosTotal++;
					statement.setString(1, grupo);
					statement.addBatch();
				}
				else {
					listaInexistentes.add(grupo); //Se añade a la lista de los inexistentes
				}
				if (procesadosTotal % 1000 == 0 || gruposProcesados == lista.size()) {
					statementAlumno.executeBatch(); // Hacer batch de 1000 sentencias como maximo a la vez
	                statement.executeBatch();
	            }
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			if (mysql != null) mysql.disconnect();
		}
		return listaInexistentes;
	}
	
	public static int obtenerNumeroGrupos() {
		JDBCTemplate mysql = null;
		int num = -1;
		Properties prop = new Properties();
		try {
			prop.load(EjemploCargaDatos.class.getResourceAsStream("sistemas.properties"));
			mysql = configureMySQL(prop);
			Connection connection = mysql.getConnection();
			java.sql.PreparedStatement statement = connection.prepareStatement("select count(*) from GRUPO");
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