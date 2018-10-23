package jdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Iterator;

/**
 * Emulacion de un Cursor de PL/SQL
 *
 */
public class Cursor implements Iterable<Cursor> {

	final ResultSet rs;
	
	public Cursor(ResultSet rs) {
		this.rs = rs;
	}

	public Iterator<Cursor> iterator() {
		return new Iterator<Cursor>() {

			public boolean hasNext() {
				try {
					if (rs.next())
						return true;
					rs.close();
				} catch (SQLException e) {
					try {
						rs.close();
					} catch (SQLException e2) {
					}
				}
				return false;
			}

			public Cursor next() {
				return new Cursor(rs);
			}

			public void remove() {
			}
			
		};
	}

	public String getString(String name) {
		try {
			return rs.getString(name);
		} catch (SQLException e) {
			return "";
		}
	}

	public int getInteger(String name) {
		try {
			return rs.getInt(name);
		} catch (SQLException e) {
			return 0;
		}
	}
	
	public Timestamp getTimestamp(String name) {
		try {
			return rs.getTimestamp(name);
		} catch (SQLException e) {
			Timestamp t = new Timestamp(0);
			return t;
		}
	}
}
