package utility;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import connectDB.DatabaseManager;

public class InputValidator {
	static Connection conn = DatabaseManager.getConnectDB();
    public static boolean validateMa(int ma){
        return ma > 0;
    }
    public static boolean validateDate(String dateStr){
        try {
            Date.valueOf(dateStr);
            return true;
        }
        catch (IllegalArgumentException e){
            return false;
        }
    }
    public static boolean validateTrangThai(String TrangThai){
        return TrangThai.equalsIgnoreCase("Đã duyệt") || TrangThai.equalsIgnoreCase("Chưa duyệt");
    }

    public boolean kiemTraMaDKTonTai(int MaDK) throws SQLException {
        String sql = "SELECT 1 FROM DANGKYTRE WHERE MaDK = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, MaDK);
        ResultSet rs = pst.executeQuery();
        return rs.next();
    }

    public boolean kiemTraMaPHTonTai(int MaPH) throws SQLException {
        String sql = "SELECT 1 FROM PHUHUYNH WHERE MaPH = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, MaPH);
        ResultSet rs = pst.executeQuery();
        return rs.next();
    }

    public boolean kiemTraMaTreTonTai(int MaTre) throws SQLException {
        String sql = "SELECT 1 FROM TREEM WHERE MaTre = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, MaTre);
        ResultSet rs = pst.executeQuery();
        return rs.next();
    }

    public boolean kiemTraMaLHTonTai(int MaLH) throws SQLException {
        String sql = "SELECT 1 FROM LOPHOC WHERE MaLH = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, MaLH);
        ResultSet rs = pst.executeQuery();
        return rs.next();
    }
    
    public boolean isValidHoTenPH(String hoTenPH) {
	    // Kiểm tra nếu chuỗi không rỗng và không null
			 if (hoTenPH == null || hoTenPH.isEmpty()) {
		            return false;
		        }

		        // Biểu thức chính quy để kiểm tra chuỗi chỉ chứa tiếng Việt, không chứa số
		        String regex = "^[\\p{L}\\s]+$";

		        return Pattern.matches(regex, hoTenPH)&& !hoTenPH.matches(".*\\d.*");
		    }

		public boolean isValidDiaChi(String diaChi) {
			if (diaChi == null || diaChi.isEmpty()) {
	            return false;
	        }

	        // Biểu thức chính quy để kiểm tra chỉ chứa tiếng Việt
	        String regex = "^[\\w+\\p{L}\\s]+$";

	        return Pattern.matches(regex, diaChi);
	    }
		 public boolean isValidSDT(String sdt) {
			 if (sdt == null || sdt.isEmpty()) {
		            return false;
		        }
		 
		        // Kiểm tra nếu SDT không rỗng và chứa 1 hoặc 2 số điện thoại, ngăn cách bằng dấu ,
		        return sdt.matches("\\d{10}(,\\d{10})?");
		    }
		 public boolean isValidEmail(String email) {
		        // Biểu thức chính quy để kiểm tra địa chỉ email
		        if (email == null || email.isEmpty()) {
		            return false;
		        }
		        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

		        // Kiểm tra địa chỉ email bằng biểu thức chính quy
		        return email.matches(regex);
		        }
}