package jdbc;

/**
 * Configuracion de una BD Oracle.
 */

public class OracleConfiguration implements Configuration {

	private String host;
	private String port;
	private String sid;

	public OracleConfiguration(String host, String port, String sid) {
		this.host = host;
		this.port = port;
		this.sid = sid;
	}
	
	public String getDriver() {
		return "oracle.jdbc.driver.OracleDriver";
	}

	public String getURL() {
		return "jdbc:oracle:thin:@" + getHost() + ":" + getPort() +":" + getSid();
	}

	private String getSid() {
		return sid;
	}

	private String getPort() {
		return port == null?"1521":port;
	}

	private String getHost() {
		return host;
	}

}