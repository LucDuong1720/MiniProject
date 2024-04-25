package utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import connectDB.ConnectDB;

public class InputUtil {
	static Scanner sc = new Scanner(System.in);

	// Kiểm tra tồn tại MaLH
	public static boolean kiemTraMaLHTonTai(int lopHocID) throws SQLException {
		String sql = "SELECT 1 FROM LOPHOC WHERE MaLH = ?";
		Connection conn = ConnectDB.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, lopHocID);
		ResultSet rs = pst.executeQuery();
		return rs.next();
	} // Kiểm tra tồn tại MaMH

	public static boolean kiemTraMaMHTonTai(int monHocID) throws SQLException {
		String sql = "SELECT 1 FROM MONHOC WHERE MaMH = ?";
		Connection conn = ConnectDB.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, monHocID);
		ResultSet rs = pst.executeQuery();
		return rs.next();
	} // Kiểm tra tồn tại MaGV

	public static boolean kiemTraMaGVTonTai(int giaoVienID) throws SQLException {
		String sql = "SELECT 1 FROM GIAOVIEN WHERE MaGV = ?";
		Connection conn = ConnectDB.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, giaoVienID);
		ResultSet rs = pst.executeQuery();
		return rs.next();
	} // Kiểm tra tồn tại MaGV

	public static boolean kiemTraMaTGHTonTai(int thoiGianHocID) throws SQLException {
		String sql = "SELECT 1 FROM THOIGIANHOC WHERE MaTGH = ?";
		Connection conn = ConnectDB.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, thoiGianHocID);
		ResultSet rs = pst.executeQuery();
		return rs.next();
	}

	/// Kiểm tra ID Lớp học
	public static int getCheckLopHocID(int lopHocID) {
		boolean validInput1 = true;
		lopHocID = 0;
		while (validInput1) {
			try {
				System.out.println("Nhập Mã lớp: ");
				String input = sc.nextLine();

				// Kiểm tra rỗng
				if (input.isEmpty()) {
					throw new IllegalArgumentException("Mã lớp không được để trống");
				}

				// Kiểm tra chiều dài không quá 5 ký tự
				if (input.length() > 5) {
					throw new IllegalArgumentException("Mã lớp không được dài quá 5 ký tự");
				}

				// Kiểm tra ký tự đặc biệt
				if (!input.matches("[a-zA-Z0-9]+")) {
					throw new IllegalArgumentException("Mã lớp không được chứa ký tự đặc biệt");
				}

				// pass thì chuyển đổi sang số nguyên
				lopHocID = Integer.parseInt(input);

				try {
					if (!kiemTraMaLHTonTai(lopHocID)) {
						System.out.println("Mã lớp học không tồn tại. Vui lòng nhập lại.");
						continue;
					}
				} catch (SQLException e) { // TODO Auto-generated catch block
					e.printStackTrace();
				}

				validInput1 = false;
			} catch (NumberFormatException e) {
				System.out.println("Vui lòng nhập lại");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}

		}
		return lopHocID;
	}

	// Kiểm tra ID Môn học
	public static int getCheckMonHocID(int monHocID) {
		boolean validInput1 = true;
		while (validInput1) {
			try {
				System.out.println("Nhập Mã môn học: ");
				String input = sc.nextLine();

				// Kiểm tra rỗng
				if (input.isEmpty()) {
					throw new IllegalArgumentException("Mã môn học không được để trống");
				}

				// Kiểm tra chiều dài không quá 5 ký tự
				if (input.length() > 5) {
					throw new IllegalArgumentException("Mã môn học không được dài quá 5 ký tự");
				}

				// Kiểm tra ký tự đặc biệt
				if (!input.matches("[a-zA-Z0-9]+")) {
					throw new IllegalArgumentException("Mã môn học không được chứa ký tự đặc biệt");
				}

				// pass thì chuyển đổi sang số nguyên
				monHocID = Integer.parseInt(input);
				try {
					if (!kiemTraMaMHTonTai(monHocID)) {
						System.out.println("Mã môn học không tồn tại. Vui lòng nhập lại.");
						continue;
					}
				} catch (SQLException e) { // TODO Auto-generated catch block
					e.printStackTrace();
				}
				validInput1 = false;
			} catch (NumberFormatException e) {
				System.out.println("Vui lòng nhập lại");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}

		}
		return monHocID;
	}

	// Kiểm tra mã ID Giáo viên
	public static int getCheckGiaoVienID(int giaoVienID) {
		boolean validInput1 = true;
		// giaoVienID = 0;
		while (validInput1) {
			try {
				System.out.println("Nhập Mã giáo viên: ");
				String input = sc.nextLine();

				// Kiểm tra rỗng
				if (input.isEmpty()) {
					throw new IllegalArgumentException("Mã giáo viên không được để trống");
				}

				// Kiểm tra chiều dài không quá 5 ký tự
				if (input.length() > 5) {
					throw new IllegalArgumentException("Mã giáo viên không được dài quá 5 ký tự");
				}

				// Kiểm tra ký tự đặc biệt
				if (!input.matches("[a-zA-Z0-9]+")) {
					throw new IllegalArgumentException("Mã giáo viên không được chứa ký tự đặc biệt");
				}

				// pass thì chuyển đổi sang số nguyên
				giaoVienID = Integer.parseInt(input);

				try {
					if (!kiemTraMaGVTonTai(giaoVienID)) {
						System.out.println("Mã giáo viên không tồn tại. Vui lòng nhập lại.");
						continue;
					}
				} catch (SQLException e) { // TODO Auto-generated catch block
					e.printStackTrace();
				}

				validInput1 = false;
			} catch (NumberFormatException e) {
				System.out.println("Vui lòng nhập lại");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}

		}
		return giaoVienID;
	}

	// Kiểm tra ID thời gian học
	public static int getCheckThoiGianHocID(int thoiGianHocID) {
		boolean validInput1 = true;
		thoiGianHocID = 0;
		while (validInput1) {
			try {
				System.out.println("Nhập Mã thời gian học: ");
				String input = sc.nextLine();

				// Kiểm tra rỗng
				if (input.isEmpty()) {
					throw new IllegalArgumentException("Mã thời gian học không được để trống");
				}

				// Kiểm tra chiều dài không quá 5 ký tự
				if (input.length() > 5) {
					throw new IllegalArgumentException("Mã thời gian học không được dài quá 5 ký tự");
				}

				// Kiểm tra ký tự đặc biệt
				if (!input.matches("[a-zA-Z0-9]+")) {
					throw new IllegalArgumentException("Mã thời gian học không được chứa ký tự đặc biệt");
				}

				// pass thì chuyển đổi sang số nguyên
				thoiGianHocID = Integer.parseInt(input);

				try {
					if (!kiemTraMaTGHTonTai(thoiGianHocID)) {
						System.out.println("Mã thời gian học không tồn tại. Vui lòng nhập lại.");
						continue;
					}
				} catch (SQLException e) { // TODO Auto-generated catch block
					e.printStackTrace();
				}

				validInput1 = false;
			} catch (NumberFormatException e) {
				System.out.println("Vui lòng nhập lại");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}

		}
		return thoiGianHocID;
	}

	/// Kiểm tra Phòng học
	public static String getCheckPhongHoc() {
		String phongHoc = "";
		boolean validInput1 = true;
		while (validInput1) {
			try {
				System.out.println("Nhập Phòng học: ");
				phongHoc = sc.nextLine();

				// Kiểm tra rỗng
				if (phongHoc.isEmpty()) {
					throw new IllegalArgumentException("Phòng học không được để trống");
				}

				// Kiểm tra chiều dài không quá 5 ký tự
				if (phongHoc.length() > 5) {
					throw new IllegalArgumentException("Phòng học không được dài quá 5 ký tự");
				}

				// Kiểm tra ký tự đặc biệt
				if (!phongHoc.matches("[a-zA-Z0-9]+")) {
					throw new IllegalArgumentException("Phòng học không được chứa ký tự đặc biệt");
				}

				// pass thì chuyển đổi sang số nguyên

				validInput1 = false;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}

		}
		return phongHoc;
	}

	// Kiểm tra số buổi học
	public static int getCheckSoBuoi(int soBuoi) {
		boolean validInput1 = true;
		while (validInput1) {
			try {
				System.out.println("Nhập Số buổi học: ");
				String input = sc.nextLine();

				// Kiểm tra rỗng
				if (input.isEmpty()) {
					throw new IllegalArgumentException("Số buổi học không được để trống");
				}

				// Kiểm tra chiều dài không quá 5 ký tự
				if (input.length() > 5) {
					throw new IllegalArgumentException("Số buổi học không được dài quá 5 ký tự");
				}

				// Kiểm tra ký tự đặc biệt
				if (!input.matches("[a-zA-Z0-9]+")) {
					throw new IllegalArgumentException("Số buổi học không được chứa ký tự đặc biệt");
				}

				// pass thì chuyển đổi sang số nguyên
				soBuoi = Integer.parseInt(input);
				validInput1 = false;
			} catch (NumberFormatException e) {
				System.out.println("Vui lòng nhập lại");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}

		}
		return soBuoi;
	}

	// Kiểm tra Học phí
	public static int getCheckHocPhi(int hocPhi) {
		boolean validInput1 = true;
		while (validInput1) {
			try {
				System.out.println("Nhập Học phí: ");
				String input = sc.nextLine();

				// Kiểm tra rỗng
				if (input.isEmpty()) {
					throw new IllegalArgumentException("Học phí không được để trống");
				}

				// Kiểm tra chiều dài không quá 5 ký tự
				if (input.length() > 5) {
					throw new IllegalArgumentException("Học phí không được dài quá 5 ký tự");
				}

				// Kiểm tra ký tự đặc biệt
				if (!input.matches("[a-zA-Z0-9]+")) {
					throw new IllegalArgumentException("Học phí không được chứa ký tự đặc biệt");
				}

				// pass thì chuyển đổi sang số nguyên
				hocPhi = Integer.parseInt(input);
				validInput1 = false;
			} catch (NumberFormatException e) {
				System.out.println("Vui lòng nhập lại");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}

		}
		return hocPhi;
	}

	// Kiểm tra ngày khai giảng
	public static String getCheckNgayKhaiGiang() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
           while (true) {
               try {
                   System.out.println("Nhập ngày Khai giảng (định dạng yyyy-MM-dd): ");
                   String ngayKhaiGiangString = sc.nextLine();


                   // Kiểm tra tính hợp lệ của ngày tháng trước khi phân tích cú pháp
                   if (!isValidDate(ngayKhaiGiangString)) {
                       System.out.println("Ngày không hợp lệ. Vui lòng nhập lại đúng định dạng.");
                       continue; // Tiếp tục vòng lặp để yêu cầu nhập lại
                   }


                   Date ngayKhaiGiang = formatter.parse(ngayKhaiGiangString);
                   return ngayKhaiGiangString;
               } catch (ParseException e) {
                   System.out.println("Ngày không hợp lệ. Vui lòng nhập lại đúng định dạng.");
               }
           }
       }


   // Kiểm tra ngày khai giang
    public static boolean isValidDate(String ngayKhaiGiangString) {
           try {
               SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
               formatter.setLenient(false); // Không cho phép chấp nhận các ngày không hợp lệ (ví dụ: 02-30)


               // Parse ngày từ chuỗi
               Date ngayKhaiGiang = formatter.parse(ngayKhaiGiangString);


               // Kiểm tra xem ngày có đúng không
               return true;
           } catch (ParseException e) {
               return false; // Nếu có lỗi xảy ra trong quá trình kiểm tra, trả về false
           }
       }
 
	 public static int getCheckMenu() {
	        int choice = -1;
	        boolean validInput = false;
	        while (!validInput) {
	            try {
	                choice = Integer.parseInt(sc.nextLine());
	                if (choice < 0 || choice > 5) {
	                    throw new IllegalArgumentException("Lựa chọn không hợp lệ. Vui lòng nhập lại");
	                }
	                validInput = true;
	            } catch (NumberFormatException e) {
	                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại");
	            } catch (IllegalArgumentException e) {
	                System.out.println(e.getMessage());
	            }
	        }
	        return choice;
	    }
}

