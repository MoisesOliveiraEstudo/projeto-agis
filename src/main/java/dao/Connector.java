package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

	private static String uri = "jdbc:jtds:sqlserver://localhost:1433/db_agis_teste001";
	
	public static Connection connect() throws ClassNotFoundException, SQLException {
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		Connection connection = DriverManager.getConnection(uri);
		return connection;
	}
	
	public static void main(String[] args) {
		try {
			Connection connection = connect();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
