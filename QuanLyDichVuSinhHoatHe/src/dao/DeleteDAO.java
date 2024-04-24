package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import connectDB.ConnectDB;
import connectDB.DatabaseManager;
import utility.InputValidator;

public class DeleteDAO {
	Scanner sc = new Scanner(System.in);
	InputValidator validate = new InputValidator();
	Connection conn = DatabaseManager.getConnectDB();

	public void xoaThongTinDangKyDatabase() {
		 System.out.println("\n=========== Xóa thông tin đăng ký ==========");
	        boolean isValidInput = false;
	        do {
	            try {
	                System.out.println("Nhập MaDK cần xóa: ");
	                int MaDK = Integer.parseInt(sc.nextLine());
	                if (!InputValidator.validateMa(MaDK)) {
	                    System.out.println("mã đăng ký không hợp lệ");
	                    continue;
	                }
	                if (!validate.kiemTraMaDKTonTai(MaDK)) {
	                    System.out.println("Mã đăng ký không tồn tại. Vui lòng nhập lại.");
	                    continue;
	                }

	                String sql = "DELETE FROM DANGKYTRE WHERE MaDK=?";
	                PreparedStatement pst = conn.prepareStatement(sql);
	                pst.setInt(1, MaDK);

	                int hangDuocXoa = pst.executeUpdate();

	                if (hangDuocXoa > 0) {
	                    System.out.println("Thông tin đăng ký đã được xóa thành công");
	                } else {
	                    System.out.println("Không tìm thấy MaDK tương ứng. Không có gì được xóa.");
	                }
	            } catch (NumberFormatException e) {
	                System.out.println("Lỗi: ");
	            } catch (SQLException e) {
	                throw new RuntimeException("Lỗi SQL: ");
	            }
	            isValidInput = true;
	        }while (!isValidInput);
	    }

		
	}

//	public void deleteGiaoVien() {
//		ConnectDB conn = new ConnectDB();
//		Connection con = conn.getConnectDB();
//		PreparedStatement pst = null;
//		
//		try {
//			Scanner sc = new Scanner(System.in);
//			System.out.println("Nhập mã giáo viên cần xóa: ");
//			int maGV = Integer.parseInt(sc.nextLine());
//			String sql = "delete from GiaoVien where MaGV = ?";
//			pst = con.prepareStatement(sql);
//			pst.setLong(1, maGV);
//			if (pst.executeUpdate() > 0) System.out.println("Cập nhật thành công.");
//			else System.out.println("Cập nhật thất bại");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NumberFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				// close connection.
//				con.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//	}
//	
//	public void deleteLopHoc() {
//		String sql = "DELETE FROM LOPHOC WHERE MaLH = ?";
//		Scanner sc = new Scanner(System.in);
//
//        try {
//        	ConnectDB conn = new ConnectDB();
//    		Connection con = conn.getConnectDB();
//    		PreparedStatement pst = null;
//            System.out.println("Nhâp mã Lớp học muốn xóa: ");
//            int lopHocID = Integer.parseInt(sc.nextLine());
//            pst.setInt(1, lopHocID);
//            pst.executeUpdate();
//            System.out.println("Đã xóa thành công");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//	
//	public void deleteMonHoc() {
//		ConnectDB conn = new ConnectDB();
//		Connection con = conn.getConnectDB();
//		PreparedStatement pst = null;
//		
//		try {
//			Scanner sc = new Scanner(System.in);
//			System.out.println("Nhập mã môn học cần xóa: ");
//			int maMH = Integer.parseInt(sc.nextLine());
//			String sql = "delete from MonHoc where MaMH = ?";
//			pst = con.prepareStatement(sql);
//			pst.setLong(1, maMH);
//			if (pst.executeUpdate() > 0) System.out.println("Cập nhật thành công.");
//			else System.out.println("Cập nhật thất bại");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NumberFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				// close connection.
//				con.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}

