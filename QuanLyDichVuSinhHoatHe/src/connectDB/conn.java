package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conn{
	public static Connection getconnectDB() throws SQLException {
		Connection con = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyDichVuSinhHoatHe;trustServerCertificate=true";
			con = DriverManager.getConnection(url, "lucduong", "rainknight2000");
			//System.out.println(" kết nối thành công !!!!");
		} catch (ClassNotFoundException e) {
			System.out.println(" kết nối thất bại");
			e.getStackTrace();
		}
		return con;
	}
public static void closeConnectDB(Connection con) {
	try { 
		if( con!=null) {
			con.close();
		}
		
	} catch (Exception e) {
		// TODO: handle exception
		e.getStackTrace();
	}
}
}