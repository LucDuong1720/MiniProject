package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import connectDB.ConnectDB;
import connectDB.DatabaseManager;

public class FindDAO {
	Connection conn = DatabaseManager.getConnectDB();
	public void find5() {
		ConnectDB conn = new ConnectDB();
		Connection con = conn.getConnectDB();
		Statement st = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT TOP 1 WITH TIES LH.MaLH, LH.MaMH, LH.MaGV, LH.MaTGH, LH.PhongHoc, " +
                    "LH.SoBuoi, LH.NgayKhaiGiang, LH.HocPhi, COUNT(DK.MaLH) AS SoLuongDangKy " +
                    "FROM LOPHOC LH " +
                    "LEFT JOIN DANGKYTRE DK ON LH.MaLH = DK.MaLH " +
                    "WHERE LH.NgayKhaiGiang >= '2023-12-01' AND LH.NgayKhaiGiang <= GETDATE()" +
                    "GROUP BY LH.MaLH, LH.MaMH, LH.MaGV, LH.MaTGH, LH.PhongHoc, " +
                    "LH.SoBuoi, LH.NgayKhaiGiang, LH.HocPhi " +
                    "ORDER BY SoLuongDangKy ASC";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
	        System.out.printf("| %-10s | %-10s | %-12s | %-17s | %-12s | %-8s | %-15s | %-8s | %-18s |\n",
	                "Mã lớp học", "Mã môn học", "Mã giáo viên", "Mã thời gian học", "Phòng học",
	                "Số buổi", "Ngày khai giảng", "Học phí", "Số lượng đăng ký");
	        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
	        
	        while(rs.next()) {
	            // Print data rows
	            System.out.printf("| %-10s | %-10s | %-12s | %-17s | %-12s | %-8s | %-15s | %-8s | %-18s |\n",
	                    rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5),
	                    rs.getInt(6), rs.getDate(7), rs.getDouble(8), rs.getInt(9));
	        }
	        // Print decorative border
	        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				// close connection.
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void lietKeThoiGianHoc() {
		System.out.println("\n==============Liệt kê thời gian học==============");
        System.out.println("+-----------+---------------+-------------------+");
        System.out.println("|  Ngày học |    Giờ học    | Số lượng đăng ký  |");
        System.out.println("+-----------+---------------+-------------------+");
        try {
            String sql = "SELECT TH.NgayHoc, TH.GioHoc, COUNT(DKT.MaDK) AS SoLuongDangKy\n" +
                    "FROM THOIGIANHOC TH\n" +
                    "INNER JOIN LOPHOC LH ON TH.MaTGH = LH.MaTGH\n" +
                    "INNER JOIN DANGKYTRE DKT ON LH.MaLH = DKT.MaLH\n" +
                    "WHERE DKT.NgayDangKy BETWEEN '2024-01-01' AND '2024-03-31'\n" +
                    "GROUP BY TH.NgayHoc, TH.GioHoc\n" +
                    "ORDER BY SoLuongDangKy DESC;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                String ngayHoc = rs.getString("NgayHoc");
                String gioHoc = rs.getString("GioHoc");
                int soLuongDangKy = rs.getInt("SoLuongDangKy");

                System.out.printf("| %-9s | %-13s | %-17s |\n",ngayHoc, gioHoc, soLuongDangKy);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("+-----------+---------------+-------------------+");
    }

	public void timKiem(Scanner sc) {
		System.out.println(" nhập họ tên phụ huynh cần tìm kiếm ");
		String hoTen = sc.nextLine();
		System.out.println("\n===========Danh sách tìm kiếm phụ huynh===========");
		System.out.println(
				"+---------+-------------------+---------------------+-------------------------+----------------------------+");
		System.out.println(
				"|   MaPH  |      HoTenPH      |        DiaChi       |           SoDT          |            Email           |");
		System.out.println(
				"+---------+-------------------+---------------------+-------------------------+----------------------------+");

		try {
			Connection con = connectDB.conn.getconnectDB();

			String sql = "SELECT * FROM PHUHUYNH WHERE HoTenPH Like ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, "%" + hoTen + "%");

			ResultSet rs = pst.executeQuery();

			int count = 0;
			while (rs.next()) {
				int maPH = rs.getInt("MaPH");
				String tenPH = rs.getString("HoTenPH");
				String diaChi = rs.getString("DiaChi");
				String soDT = rs.getString("SoDT");
				String email = rs.getString("Email");
				count++;
				System.out.printf("| %-7s | %-17s | %-19s | %-23s | %-26s |\n", maPH, tenPH, diaChi, soDT, email);

			}
			if (count == 0) {
				System.out.println(" không có dữ  liệu về " + hoTen);
			}

			con.close();
		} catch (SQLException e) {
			e.getStackTrace();
			System.out.println(" lỗi " + e.getMessage());

		}
		System.out.println(
				"+---------+-------------------+---------------------+-------------------------+----------------------------+");

	}

	public void top3() {
		System.out.println("\n===========Tim kiếm top 3 phụ huynh đăng ký===========");
		System.out.println("+---------+-------------------+----------------+---------+");
		System.out.println("|   MaPH  |      HoTenPH      |    TenMH       |  TongDK |");
		System.out.println("+---------+-------------------+----------------+---------+");

		try {
			Connection con = connectDB.conn.getconnectDB();

			String sql = "SELECT PHUHUYNH.MaPH,HoTenPH,TenMH,TongDK "
					+ "FROM ( SELECT TOP (3) COUNT(DANGKYTRE.MaPH) TongDK,DANGKYTRE.MaPH,MaLH,NgayDangKy "
					+ "FROM DANGKYTRE " + "GROUP BY DANGKYTRE.MaPH,MaLH,NgayDangKy  " + "ORDER BY TongDK DESC ) "
					+ "AS DANGKYTRE " + "JOIN PHUHUYNH ON DANGKYTRE.MaPH = PHUHUYNH.MaPH "
					+ "JOIN LOPHOC ON DANGKYTRE.MaLH = LOPHOC.MaLH " + "JOIN MONHOC ON LOPHOC.MaMH= MONHOC.MaMH "
					+ "WHERE YEAR(DANGKYTRE.NgayDangKy) = 2023;";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int maPH = rs.getInt("MaPH");
				String tenPH = rs.getString("HoTenPH");
				String tenMH = rs.getString("TenMH");
				int tong = rs.getInt("TongDK");

				System.out.printf("| %-7s | %-17s | %-14s | %-7s |\n", maPH, tenPH, tenMH, tong);

			}

			con.close();
		} catch (SQLException e) {
			System.out.println("lỗi " + e.getMessage());

		}
		System.out.println("+---------+-------------------+----------------+---------+");
	}

	public void lietKeMonHocChuaDK() {
		String sql = "SELECT DISTINCT TenMH FROM MONHOC as MH, LOPHOC as LH, DANGKYTRE as DKT	 WHERE MH.MaMH = LH.MaMH AND LH.MaLH = DKT.MaLH AND NOT  DKT.NgayDangKy BETWEEN '2024-03-01' AND '2024-03-31'";
		Connection con = null;

		try {
			con = ConnectDB.getConnection();
			PreparedStatement pst = con.prepareStatement(sql);

			ResultSet rs = pst.executeQuery();
			System.out.println("Thông tin Lớp học chưa được PH đăng ký trong tháng 3/2024: ");
			System.out.println(" ----------------");
			System.out.printf("|%15s|", "Tên Môn học");
			System.out.println();
			System.out.println(" ----------------");
			while (rs.next()) {
				System.out.printf("|%15s|", rs.getString("TenMH"));
				System.out.println();
			}
			System.out.println(" ----------------");
		} catch (SQLException e) {
			System.out.println("Liệt kê thất bại");
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
}
