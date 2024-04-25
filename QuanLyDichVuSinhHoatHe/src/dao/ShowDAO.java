package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import connectDB.ConnectDB;
import connectDB.DatabaseManager;
import connectDB.ketNoi;
import controller.PhuHuynhController;
import utility.InputValidator;

public class ShowDAO {
	Connection conn = DatabaseManager.getConnectDB();
	Scanner sc = new Scanner(System.in);
	InputValidator check = new InputValidator();
	private PhuHuynhController phuHuynhController;
	public void showGiaoVien() {
		ConnectDB conn = new ConnectDB();
		Connection con = conn.getConnectDB();
		Statement st = null;
		try {
			String sql = "select * from GiaoVIen";
			// 3. create statement
			st = con.createStatement();
			// 4. execute sql query
			ResultSet rs = st.executeQuery(sql);
			// 5. display information from rs.
			System.out.println("--------------------------------------------------------------");
	        System.out.printf("| %-15s | %-40s |\n", "Mã giáo viên", "Họ và tên giáo viên");
	        System.out.println("|-----------------+------------------------------------------|");
	        
	        while(rs.next()) {
	            // Print data rows
	            System.out.printf("| %-15s | %-40s |\n", rs.getString(1), rs.getString(2));
	        }
	        // Print decorative border
	        System.out.println("--------------------------------------------------------------");

		} catch (SQLException e) {
			System.out.println("Lỗi");
		} finally {
			try {
				// close connection.
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void showMonHoc() {
		ConnectDB conn = new ConnectDB();
		Connection con = conn.getConnectDB();
		Statement st = null;
		try {
			String sql = "select * from MonHoc";
			// 3. create statement
			st = con.createStatement();
			// 4. execute sql query
			ResultSet rs = st.executeQuery(sql);
			// 5. display information from rs.
			System.out.println("--------------------------------------------------------------");
	        System.out.printf("| %-15s | %-40s |\n", "Mã môn học", "Tên môn học");
	        System.out.println("|-----------------+------------------------------------------|");
	        
	        while(rs.next()) {
	            // Print data rows
	            System.out.printf("| %-15s | %-40s |\n", rs.getString(1), rs.getString(2));
	        }
	        // Print decorative border
	        System.out.println("--------------------------------------------------------------");

		} catch (SQLException e) {
			System.out.println("Lỗi");
		} finally {
			try {
				// close connection.
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public List<String> layDanhSachLopHoc() throws SQLException {
        List<String> danhSachLopHoc = phuHuynhController.layDanhSachLopHoc();
        if (danhSachLopHoc.isEmpty()) {
            System.out.println("Không có thông tin lớp học để hiển thị.");
        } else {
            // Hiển thị tiêu đề cột
            System.out.println(String.format("%-5s %-15s %-20s %-15s %-20s %-10s %-10s %-15s %-20s",
                    "MaLH", "TenMH", "HoTenGV", "NgayHoc", "GioHoc", "PhongHoc", "SoBuoi", "NgayKhaiGiang", "HocPhi"));
            // Hiển thị mỗi hàng
            for (String lopHoc : danhSachLopHoc) {
                System.out.println(lopHoc);
            }
        }
        return danhSachLopHoc;
    }

	public void timKiemThongTinDangKyDatabase(){
		Scanner sc = new Scanner(System.in);
		System.out.println("\n========================Tìm Kiếm thông tin đăng ký=========================");
        try {
            System.out.println("Nhập MaDK cần tìm: ");
            int MaDK = Integer.parseInt(sc.nextLine());

            System.out.println("+----------+----------+----------+----------+--------------+--------------+");
            System.out.println("|   MaDK   |   MaPH   |   MaTre  |   MaLH   |  NgayDangKy  |   TrangThai  |");
            System.out.println("+----------+----------+----------+----------+--------------+--------------+");
            String sql = "SELECT * FROM DANGKYTRE WHERE MaDK=?";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, MaDK);
            ResultSet rs = pst.executeQuery();

            if (rs.next()){
                System.out.printf("| %-8s | %-8s | %-8s | %-8s | %-12s | %-12s |\n",
                                    rs.getInt("MaDK"),
                                    rs.getInt("MaPH"),
                                    rs.getInt("MaTre"),
                                    rs.getInt("MaLH"),
                                    rs.getDate("NgayDangKy"),
                                    rs.getString("TrangThai"));
            }
            else {
                System.out.println("không tìm thấy thông tin đăng ký");
            }
            rs.close();
        }
        catch (NumberFormatException | SQLException e){
            System.out.println("Lỗi" + e);
        }
        System.out.println("+----------+----------+----------+----------+--------------+--------------+");
    }

	public void hienThi() {
		System.out.println("\n==========Danh sách phụ huynh==========");
		System.out.println(
				"+---------+-------------------+---------------------+-------------------------+----------------------------+");
		System.out.println(
				"|   MaPH  |      HoTenPH      |        DiaChi       |           SoDT          |            Email           |");
		System.out.println(
				"+---------+-------------------+---------------------+-------------------------+----------------------------+");
		try {
			Connection con = connectDB.conn.getconnectDB();
			String sql = "SELECT* FROM PHUHUYNH";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			int count = 0;
			while (rs.next()) {
				int maPH = rs.getInt("MaPH");
				String hoTenPH = rs.getString("HoTenPH");
				String diaChi = rs.getString("DiaChi");
				String soDT = rs.getString("SoDT");
				String email = rs.getString("Email");			
				count++;
				System.out.printf("| %-7s | %-17s | %-19s | %-23s | %-26s |\n", maPH, hoTenPH, diaChi, soDT, email);
			}
			if (count == 0) {
				System.out.println(" Chưa có dữ liệu ");
			}

			con.close();
		} catch (SQLException e) {
			System.out.println(" lỗi " + e.getMessage());

		}
		System.out.println(
				"+---------+-------------------+---------------------+-------------------------+----------------------------+");
	

	}

	public void hienThiToanBoThongTin() {
	    System.out.println("\n==========Hiển thị toàn bộ thông tin============");
	    try {
	        System.out.println("+----------+----------+----------+----------+----------------+--------------+");
	        System.out.println("|   MaDK   |   MaPH   |  MaTre   |  MaLH    |    NgayDangKy  |   TrangThai  |");
	        System.out.println("+----------+----------+----------+----------+----------------+--------------+");

	        String sql = "SELECT * FROM DANGKYTRE";
	        PreparedStatement pst = conn.prepareStatement(sql);
	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	            System.out.printf("| %-8s | %-8s | %-8s | %-8s | %-14s | %-12s |\n",
	                    rs.getInt("MaDK"),
	                    rs.getInt("MaPH"),
	                    rs.getInt("MaTre"),
	                    rs.getInt("MaLH"),
	                    rs.getDate("NgayDangKy"),
	                    rs.getString("TrangThai"));
	        }
	        System.out.println("+----------+----------+----------+----------+----------------+--------------+");
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}


	public void hienThiThongTinLop() {
		String sql = "SELECT MaLH, TenMH, HoTenGV, NgayHoc, GioHoc, PhongHoc,SoBuoi,NgayKhaiGiang,HocPhi FROM LOPHOC as LH , MONHOC as MH, GIAOVIEN as GV, THOIGIANHOC as TGH Where MH.MaMH = LH.MaMH AND GV.MaGV = LH.MaGV AND TGH.MaTGH = LH.MaTGH";

		Connection con = null;

		try {
			con = ConnectDB.getConnection();
			PreparedStatement pst = con.prepareStatement(sql);

			ResultSet rs = pst.executeQuery();
			System.out.println(
					">++=========================================================++@>THÔNG TIN LỚP HỌC<@++=========================================================++< ");
			System.out.println(
					" ------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("|%10s||%15s||%25s||%10s||%15s||%10s||%8s||%15s||%10s           |", "Mã Lớp học",
					"Tên Môn học", "Tên Giáo viên", "Ngày học", "Giờ học", "Phòng học", "Số buổi", "Ngày khai khảng",
					"Học phí");
			System.out.println();
			System.out.println(
					" ------------------------------------------------------------------------------------------------------------------------------------------------");

			while (rs.next()) {

				System.out.printf("|%10d||%15s||%25s||%10s||%15s||%10s||%8d||%15tD||%10d%10s|", rs.getInt("MaLH"),
						rs.getString("TenMH"), rs.getString("HoTenGV"), rs.getString("NgayHoc"), rs.getString("GioHoc"),
						rs.getString("PhongHoc"), rs.getInt("SoBuoi"), rs.getDate("NgayKhaiGiang"), rs.getInt("HocPhi"),
						" Nghìn đồng");
				System.out.println();

			}
			System.out.println(
					" ------------------------------------------------------------------------------------------------------------------------------------------------");

		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println("Không hiển thị");
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

	public void xemTreEm() {
		try {
			Connection con = ketNoi.getConnection();
			String sql = "SELECT * FROM TREEM";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			System.out.printf("%-10s %-20s %-20s %-15s %-10s\n", "Mã Trẻ", "Họ Tên Trẻ", "Ngày Sinh", " Giới Tính",
					"Mã Phụ Huynh");
			while (rs.next()) {
				int MaTre = rs.getInt("MaTre");
				String HoTenTre = rs.getString("HoTenTre");
				Date NgaySinh = rs.getDate("NgaySinh");
				String GioiTinh = rs.getString("GioiTinh");
				int MaPH = rs.getInt("MaPH");
				System.out.printf("%-10d %-20s %-20s %-15s %-10d\n", MaTre, HoTenTre,
						new SimpleDateFormat("yyyy-MM-dd").format(NgaySinh), GioiTinh, MaPH);
			}
			System.out.println(sql);
			ketNoi.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Xem thông tin trẻ em không thành công: " + e.getMessage());
		}
	}

	public static void lietKeGiaoVien() {
		try {
			Connection con = ketNoi.getConnection();
			String sql = "SELECT gv.MaGV, gv.HoTenGV, COUNT(lh.MaLH) AS SoLuongLop\n"
					+ "FROM GIAOVIEN gv JOIN LOPHOC lh ON gv.MaGV = lh.MaGV\n"
					+ "WHERE lh.NgayKhaiGiang BETWEEN '2024-01-01' AND '2024-02-28'\n"
					+ "GROUP BY gv.MaGV, gv.HoTenGV\n" + "ORDER BY COUNT(lh.MaLH) DESC";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			System.out.println("Thông tin các giáo viên dạy nhiều lớp học nhất trong khoảng thời gian 1/2024 - 2/2024");
			System.out.printf("%-20s %-30s %-10s\n", "Mã Giáo Viên", "Tên Giáo Viên", "Số Lượng Lớp");
			while (rs.next()) {
				int MaGV = rs.getInt("MaGV");
				String HoTenGV = rs.getString("HoTenGV");
				int soLuongLop = rs.getInt("SoLuongLop");
				System.out.printf("%-20d %-30s %-10d\n", MaGV, HoTenGV, soLuongLop);
			}
			ketNoi.closeConnection(con);
		} catch (SQLException e) {
			System.out.println("Liệt kê thông tin giáo viên không thành công: " + e.getMessage());
		}
	}

	public static boolean kiemTraMa(String tenBang, String tenCot, String maBang) {
		boolean kiemTra = false;
		try {
			Connection con = ketNoi.getConnection();
			String sql = "SELECT * FROM " + tenBang + " WHERE " + tenCot + " = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, maBang);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				kiemTra = true;
			}
			ketNoi.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kiemTra;
	}

	public void hienThiTGHoc() {
		ConnectDB conn = new ConnectDB();
		Connection con = conn.getConnectDB();
		PreparedStatement pst = null;
		System.out.println("\n============Hiển thị thơi gian học============");
	        try {
	            String sql = "SELECT * FROM THOIGIANHOC";
	            pst = con.prepareStatement(sql);
	            ResultSet rs = pst.executeQuery();

	            while (rs.next()){
	                System.out.println("MaTGH: " + rs.getInt("MaTGH"));
	                System.out.println("NgayHoc: " + rs.getString("NgayHoc"));
	                System.out.println("GioHoc: " + rs.getString("GioHoc"));
	            }
	            rs.close();
	            con.close();
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }

}
