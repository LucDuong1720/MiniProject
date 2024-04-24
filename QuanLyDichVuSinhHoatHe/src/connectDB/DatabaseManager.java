package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
	private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyDichVuSinhHoatHe;trustServerCertificate=true";
    private static final String USER = "lucduong";
    private static final String PASS = "rainknight2000";
    private static Connection conn = null;

    // Method to establish connection
    public static Connection getConnectDB() {
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName(JDBC_DRIVER);
                System.out.println("Đang kết nối đến cơ sở dữ liệu...");
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Kết nối thành công!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    // Method to close connection
    public static void closeConnectDB() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Đã đóng kết nối đến cơ sở dữ liệu.");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}