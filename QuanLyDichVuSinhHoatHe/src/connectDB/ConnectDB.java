package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	public Connection getConnectDB() {
		Connection con = null;
 
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//Port username password
			String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyDichVuSinhHoatHe;trustServerCertificate=true";
			con =  DriverManager.getConnection(connectionUrl, "lucduong", "rainknight2000");
//			System.out.println("success");
		} catch (ClassNotFoundException e) {
			System.out.println("forName is wrong " + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("getConnection fail" + e.getMessage());
			e.printStackTrace();
		}
		return con;
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QuanLyDichVuSinhHoatHe;trustServerCertificate=true", "lucduong", "rainknight2000");
	}
	
}