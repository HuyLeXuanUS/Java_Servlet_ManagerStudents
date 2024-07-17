package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUntil {
	public static Connection getConnection() {
		Connection c = null;
		
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			
			String url = "jdbc:mySQL://localhost:3306/manager_student";	
			String username = "root";
			String password = "151102";
			
			c = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return c;
	}
	
	public static void closeConnection(Connection c) {
		if (c != null) {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

