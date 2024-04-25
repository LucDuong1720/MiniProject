package utility;

import java.util.regex.Pattern;

public class validate {
	public static boolean isValidHoTenPH(String hoTenPH) {
		if (hoTenPH == null || hoTenPH.trim().isEmpty()) {
            return false;
        }
        String regex = "^[\\p{L}\\s]+$";
        return Pattern.matches(regex, hoTenPH)&& !hoTenPH.matches(".*\\d.*");
    }

		public static boolean isValidDiaChi(String diaChi) {
			 if (diaChi == null || diaChi.trim().isEmpty()) {
		            return false;
		        }
		       String regex = "^[\\w+\\p{L}\\s_\\\\/]+$";
		       return Pattern.matches(regex, diaChi);
		    }
		
		 public static boolean isValidSDT(String sdt) {
			 if (sdt == null || sdt.trim().isEmpty()) {
		            return false;
		        }
		 
		        // Kiểm tra nếu SDT không rỗng và chứa 1 hoặc 2 số điện thoại, ngăn cách bằng dấu ,
		        return sdt.matches("\\d{10}(,\\d{10})?");
		    }
		 public static boolean isValidEmail(String email) {
		        // Biểu thức chính quy để kiểm tra địa chỉ email
		        if (email == null || email.trim().isEmpty()) {
		            return false;
		        }
		        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

		        // Kiểm tra địa chỉ email bằng biểu thức chính quy
		        return email.matches(regex);
		        }
	}