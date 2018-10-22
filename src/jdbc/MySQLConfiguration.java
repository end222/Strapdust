package jdbc;

/**
 * Configuracion de una BD Oracle.
 */
/**
 * Configuracion de una BD MySQL.
 */

public class MySQLConfiguration implements Configuration {

	private String host;
	private String port;
	private String dbName;

	public MySQLConfiguration(String host, String port, String dbName) {
		this.host = host;
		this.port = port;
		this.dbName = dbName;
	}
	
	public String getDriver() {
		return "com.mysql.jdbc.Driver";
	}

	public String getURL() {
		return "jdbc:mysql://" + getHost() + ":" + getPort() +"/" + getDBName();
	}

	private String getDBName() {
		return dbName;
	}

	private String getPort() {
		return port == null?"3306":port;
	}

	private String getHost() {
		return host;
	}

}