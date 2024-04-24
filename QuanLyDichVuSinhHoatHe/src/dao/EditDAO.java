package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

import connectDB.ConnectDB;
import connectDB.DatabaseManager;
import connectDB.ketNoi;
import utility.InputUtil;
import utility.InputUtils;
import utility.InputValidator;

public class EditDAO {
	InputUtils check = new InputUtils();
	static InputValidator validate = new InputValidator();
	Connection conn = DatabaseManager.getConnectDB();
	Scanner sc = new Scanner(System.in);
	public void editGiaoVien() {
		// TODO Auto-generated method stub
		ConnectDB conn = new ConnectDB();
		Connection con = conn.getConnectDB();
		PreparedStatement pst = null;
		try {
	        
	        int maGV = check.getValidMa(sc);
	        
	        if (check.isExists("GiaoVien", "MaGV", String.valueOf(maGV)) == true) {
	        	 String tenGV = check.getValidName(sc);
	 	        
	 	        String sql = "update GiaoVien set HoTenGV = ? where MaGV = ?";
	 	        pst = con.prepareStatement(sql);      
	 	        pst.setString(1, tenGV);
	 	        pst.setInt(2, maGV);
	 	        int rowsAffected = pst.executeUpdate();
	 	        if (rowsAffected > 0) {
	 	            System.out.println("Cập nhật giáo viên thành công!");
	 	        } else {
	 	            System.out.println("Không tìm thấy mã giáo viên!");
	 	        }
	        } else {
	        	System.out.println("Không tìm thấy mã giáo viên!");
	        	editGiaoVien();
	        }
	        
	       
	    } catch (SQLException e) {
	       // e.printStackTrace();
	        System.out.println("Cập nhật giáo viên thất bại!");
	    }
	}

	public void editMonHoc() {
		ConnectDB conn = new ConnectDB();
		Connection con = conn.getConnectDB();
		PreparedStatement pst = null;
		try {
//			Scanner sc = new Scanner(System.in);
			
			int maMH = check.getValidMa(sc);
//			check.isExists("MonHoc", "MaMH", String.valueOf(maMH));
			
			if (check.isExists("MonHoc", "MaMH", String.valueOf(maMH)) == true) {
				String tenMH = check.getValidName(sc);
				
				String sql = "update MonHoc set TenMH = ? where MaMH = ?";
				pst = con.prepareStatement(sql);		
				pst.setString(1, tenMH);
				pst.setInt(2, maMH);
				pst.executeUpdate();
				
				if (pst.executeUpdate() > 0) {
			            System.out.println("Cập nhật môn học thành công!");
			        } else {
			            System.out.println("Không tìm thấy mã môn học!");
			        }
			} else {
				System.out.println("Không tìm thấy mã môn học!");
				editMonHoc();
			}
			
			
		    } catch (SQLException e) {
		        //e.printStackTrace();
		        System.out.println("Cập nhật môn học thất bại!");
		    } finally {
		        try {
		            con.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}

	public void suaThongTinDangKyDatabase() {
		System.out.println("\n===========Sửa thông tin đăng ký==========");
        boolean isValidInput = false;
        do {
            try {
                System.out.println("Nhập MaDK cần sửa: ");
                int MaDK = Integer.parseInt(sc.nextLine());
                if (!InputValidator.validateMa(MaDK)){
                    System.out.println("mã đăng ký không hợp lệ");
                    continue;
                }
                if (!validate.kiemTraMaDKTonTai(MaDK)) {
                    System.out.println("Mã đăng ký không tồn tại. Vui lòng nhập lại.");
                    continue;
                }


                System.out.println("Nhập MaPH mới: ");
                int MaPH = Integer.parseInt(sc.nextLine());
                if (!InputValidator.validateMa(MaPH)){
                    System.out.println("mã phụ huynh không hợp lệ");
                    continue;
                }
                if (!validate.kiemTraMaPHTonTai(MaPH)) {
                    System.out.println("Mã Phụ huynh không tồn tại. Vui lòng nhập lại.");
                    continue;
                }

                System.out.println("Nhập MaTre mới: ");
                int MaTre = Integer.parseInt(sc.nextLine());
                if (!InputValidator.validateMa(MaTre)){
                    System.out.println("mã trẻ em không hợp lệ");
                    continue;
                }
                if (!validate.kiemTraMaTreTonTai(MaTre)){
                    System.out.println("Mã trẻ em không tồn tại. Vui lòng nhập lại.");
                    continue;
                }


                System.out.println("nhập MaLH mới: ");
                int MaLH = Integer.parseInt(sc.nextLine());
                if (!InputValidator.validateMa(MaLH)){
                    System.out.println("mã lớp học không hợp lệ");
                    continue;
                }
                if (!validate.kiemTraMaLHTonTai(MaLH)){
                    System.out.println("Mã lớp học không tồn tại. Vui lòng nhập lại.");
                    continue;
                }

                System.out.println("nhập NgayDangKy mới (yyyy-MM-dd): ");
                String NgayDangKyStr = sc.nextLine();
                if (!InputValidator.validateDate(NgayDangKyStr)) {
                    System.out.println("Ngày đăng ký không hợp lệ.");
                    continue;
                }

                System.out.println("nhập TrangThai mới: ");
                String TrangThai = sc.nextLine();
                if (!InputValidator.validateTrangThai(TrangThai)) {
                    System.out.println("Trạng thái đăng ký không hợp lệ.");
                    continue;
                }

                String sql = "UPDATE DANGKYTRE SET MaPH=?, MaTre=?, MaLH=?, NgayDangKy=?, TrangThai=? WHERE MaDK=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, MaPH);
                pst.setInt(2, MaTre);
                pst.setInt(3, MaLH);
                pst.setDate(4, Date.valueOf(NgayDangKyStr));
                pst.setString(5, TrangThai);
                pst.setInt(6, MaDK);
                pst.executeUpdate();

                System.out.println("Thông tin đăng ký đã sửa thành công");
            } catch (NumberFormatException e) {
                System.out.println("lỗi: " );
            } catch (SQLException e) {
                System.out.println("vui lòng nhập lại: ");
            }
            isValidInput = true;
        }while (!isValidInput);
    }

	public static void update() {
		Scanner sc = new Scanner(System.in);
		System.out.println(" nhập mã cần sửa ");
		int maPH = 0;
		boolean t = true;
		while (t) {
			maPH = Integer.parseInt(sc.nextLine());
			try {
				if (check(maPH)) {
					t = false;
				} else {
					System.out.println(" nhập  lại mã cần sửa ");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String hoTenPH = null;
		boolean isValid;

		do {
			System.out.println("Họ và tên phụ huynh:");
			hoTenPH = sc.nextLine();
			isValid = validate.isValidHoTenPH(hoTenPH);

			if (!isValid) {
				System.out.println("Họ tên phụ huynh không hợp lệ. Vui lòng nhập lại.");
			}
		} while (!isValid);

		String diaChi = null;

		do {
			System.out.println(" Địa chỉ");
			diaChi = sc.nextLine();
			isValid = validate.isValidDiaChi(diaChi);

			if (!isValid) {
				System.out.println("Địa chỉ không hợp lệ. Vui lòng nhập lại.");
			}
		} while (!isValid);
		String soDT = null;
		do {
			System.out.println("Số điện thoại");
			soDT = sc.nextLine();
			isValid = validate.isValidSDT(soDT);

			if (!isValid) {
				System.out.println("Số điện thoại không hợp lệ. Vui lòng nhập lại.");
			}
		} while (!isValid);

		String email = null;
		do {
			System.out.println("Nhập địa chỉ email:");
			email = sc.nextLine();
			isValid = validate.isValidEmail(email);

			if (!isValid) {
				System.out.println("Email không hợp lệ. Vui lòng nhập lại.");
			}
		} while (!isValid);

		try {
			Connection con = connectDB.conn.getconnectDB();

			String sql = "UPDATE  PHUHUYNH SET HoTenPH = ?,DiaChi = ?,SoDT =?,Email = ? WHERE MaPH=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, hoTenPH);
			pst.setString(2, diaChi);
			pst.setString(3, soDT);
			pst.setString(4, email);
			pst.setInt(5, maPH);
			int a = pst.executeUpdate();
			System.out.println(a + " phụ huynh đã được sửa vào thành công ");

			con.close();
		} catch (SQLException e) {
			System.out.println(" lỗi " + e.getMessage());
		}

	}
		
	public static boolean check(int maPH) throws SQLException {
		boolean ct = false;
		try {
			Connection con = connectDB.conn.getconnectDB();
			String sql = "SELECT * FROM PHUHUYNH WHERE MaPH = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, maPH);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				ct = true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return ct;
	}

	public void suaThongTinLop() {
		String sql = "UPDATE LOPHOC SET MaMH = ?, MaGV = ?, MaTGH = ?, PhongHoc = ?,"
				+ "SoBuoi = ?, NgayKhaiGiang = ?, HocPhi = ? WHERE MaLH = ?";
		Connection con = null;
		int lopHocID = 0;
		int monHocID = 0;
		int giaoVienID = 0;
		int thoiGianHocID = 0;
		String phongHoc = "";
		int soBuoi = 0;
		String ngayKhaiGiangString = "";
		int hocPhi = 0;
		try {
			con = ConnectDB.getConnection();
			PreparedStatement pst = con.prepareStatement(sql);

			System.out.println("Nhâp thông tin cần sửa: ");
			lopHocID = InputUtil.getCheckLopHocID(lopHocID);
			monHocID = InputUtil.getCheckMonHocID(monHocID);
			giaoVienID = InputUtil.getCheckGiaoVienID(giaoVienID);
			thoiGianHocID = InputUtil.getCheckThoiGianHocID(thoiGianHocID);
			phongHoc = InputUtil.getCheckPhongHoc();
			soBuoi = InputUtil.getCheckSoBuoi(soBuoi);
			ngayKhaiGiangString = InputUtil.getCheckNgayKhaiGiang();
			hocPhi = InputUtil.getCheckHocPhi(hocPhi);
			
			pst.setInt(1, monHocID);
			pst.setInt(2, giaoVienID);
			pst.setInt(3, thoiGianHocID);
			pst.setString(4, phongHoc);
			pst.setInt(5, soBuoi);
			pst.setString(6, ngayKhaiGiangString);
			pst.setInt(7, hocPhi);
			pst.setInt(8, lopHocID);
			// Thực thi câu truy vấn
			pst.executeUpdate();
			System.out.println("Đã sửa thành công");
		} catch (SQLException e) {
			System.out.println("Vui lòng nhập lại");
		} finally {
			// Đóng kết nối sau khi hoàn thành hoặc gặp lỗi
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				System.out.println("Đống kết nối thất bại");
			}
		}
	}

	public void suaTreEm(Scanner sc2) {
		try {
			Connection con = ketNoi.getConnection();
			int maSua = 0;
			boolean kt = false;
			while (!kt) {
				System.out.println("Nhập vào mã của trẻ cần sửa");
				maSua = Integer.parseInt(sc.nextLine());
				if (check.isExists("TREEM", "MaTre", String.valueOf(maSua)) == true) {
					kt = true;
				} else {
					System.out.println("Không tìm thấy mã trẻ!");
				}
			}
			System.out.println("Nhập vào họ tên của trẻ");
			String tenSua = check.nhapHoTenTre(sc);
			java.sql.Date ngaySinhSua = check.nhapNgaySinh(sc);
			String gioiTinhSua = check.nhapGioiTinh(sc);
			int maPHSua = check.nhapMaPH(sc);
			String sql = "UPDATE TREEM SET HoTenTre = ?, NgaySinh = ?, GioiTinh = ?, MaPH = ? WHERE MaTre = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, tenSua);
			pst.setDate(2, ngaySinhSua);
			pst.setString(3, gioiTinhSua);
			pst.setInt(4, maPHSua);
			pst.setInt(5, maSua);
			pst.executeUpdate();
			System.out.println("Đã sửa thông tin trẻ em thành công");
			System.out.println(sql);
			ketNoi.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Sửa thông tin trẻ em không thành công: " + e.getMessage());
		}
	}
		
}
