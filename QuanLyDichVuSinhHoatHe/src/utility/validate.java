package utility;

import java.util.regex.Pattern;

public class validate {
	public static boolean isValidHoTenPH(String hoTenPH) {
	    // Kiểm tra nếu chuỗi không rỗng và không null
			 if (hoTenPH == null || hoTenPH.isEmpty()) {
		            return false;
		        }

		        // Biểu thức chính quy để kiểm tra chuỗi chỉ chứa tiếng Việt, không chứa số
		        String regex = "^[\\p{L}\\s]+$";

		        return Pattern.matches(regex, hoTenPH)&& !hoTenPH.matches(".*\\d.*");
		    }

		public static boolean isValidDiaChi(String diaChi) {
			if (diaChi == null || diaChi.isEmpty()) {
	            return false;
	        }

	        // Biểu thức chính quy để kiểm tra chỉ chứa tiếng Việt
	        String regex = "^[\\w+\\p{L}\\s]+$";

	        return Pattern.matches(regex, diaChi);
	    }
		 public static boolean isValidSDT(String sdt) {
			 if (sdt == null || sdt.isEmpty()) {
		            return false;
		        }
		 
		        // Kiểm tra nếu SDT không rỗng và chứa 1 hoặc 2 số điện thoại, ngăn cách bằng dấu ,
		        return sdt.matches("\\d{10}(,\\d{10})?");
		    }
		 public static boolean isValidEmail(String email) {
		        // Biểu thức chính quy để kiểm tra địa chỉ email
		        if (email == null || email.isEmpty()) {
		            return false;
		        }
		        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

		        // Kiểm tra địa chỉ email bằng biểu thức chính quy
		        return email.matches(regex);
		        }
	}