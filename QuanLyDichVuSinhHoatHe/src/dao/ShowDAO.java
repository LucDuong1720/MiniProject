package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import connectDB.ConnectDB;
import connectDB.DatabaseManager;
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
        boolean isValidInput = false;
        do {
            try {
                System.out.println("Nhập MaDK cần tìm: ");
                int MaDK = Integer.parseInt(sc.nextLine());
                if (!InputValidator.validateMa(MaDK)) {
                    System.out.println("mã đăng ký không hợp lệ");
                    continue;
                }
                if (!check.kiemTraMaDKTonTai(MaDK)) {
                    System.out.println("Mã đăng ký không tồn tại. Vui lòng nhập lại.");
                    continue;
                }
                System.out.println("+----------+----------+------------------+---------------------+-------------------------+--------------------------+----------+------------------+----------------+----------+----------+--------------+-----------+-------------------+------------+--------------+--------------+");
                System.out.println("|   MaDK   |   MaPH   |     HoTenPH      |        DiaChi       |          SoDT           |            Email         |  MaTre   |     HoTenTre     |     NgaySinh   | GioiTinh |  MaLH    |   PhongHoc   |   SoBuoi  |    NgayKhaiGiang  |   HocPhi   |  NgayDangKy  |   TrangThai  |");
                System.out.println("+----------+----------+------------------+---------------------+-------------------------+--------------------------+----------+------------------+----------------+----------+----------+--------------+-----------+-------------------+------------+--------------+--------------+");


                String sql = "SELECT DK.MaDK, PH.MaPH, PH.HoTenPH, PH.DiaChi, PH.SoDT, PH.Email, T.MaTre, T.HoTenTre, T.NgaySinh, T.GioiTinh, LH.MaLH, LH.PhongHoc, LH.SoBuoi, LH.NgayKhaiGiang, LH.HocPhi, DK.NgayDangKy, DK.TrangThai\n" +
                        "FROM DANGKYTRE DK\n" +
                        "JOIN PHUHUYNH PH ON DK.MaPH = PH.MaPH\n" +
                        "JOIN TREEM T ON DK.MaTre = T.MaTre\n" +
                        "JOIN LOPHOC LH ON DK.MaLH = LH.MaLH\n" +
                        "WHERE DK.MaDK=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, MaDK);
                ResultSet rs = pst.executeQuery();
                if (rs.next()){
                    System.out.printf("| %-8s | %-8s | %-16s | %-19s | %-23s | %-23s | %-8s | %-16s | %-14s | %-8s | %-8s | %-12s | %-9s | %-17s | %-10s | %-12s | %-12s |\n",
                        rs.getInt("MaDK"),
                        rs.getInt("MaPH"),
                        rs.getString("HoTenPH"),
                        rs.getString("DiaChi"),
                        rs.getString("SoDT"),
                        rs.getString("Email"),
                        rs.getInt("MaTre"),
                        rs.getString("HoTenTre"),
                        rs.getDate("NgaySinh"),
                        rs.getString("GioiTinh"),
                        rs.getInt("MaLH"),
                        rs.getString("PhongHoc"),
                        rs.getInt("SoBuoi"),
                        rs.getDate("NgayKhaiGiang"),
                        rs.getDouble("HocPhi"),
                        rs.getDate("NgayDangKy"),
                        rs.getString("TrangThai"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            isValidInput = true;
        }while (!isValidInput);
        System.out.println("+----------+----------+------------------+---------------------+-------------------------+--------------------------+----------+------------------+----------------+----------+----------+--------------+-----------+-------------------+------------+--------------+--------------+");
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

}
