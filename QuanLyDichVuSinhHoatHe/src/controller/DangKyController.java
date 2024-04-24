package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import dao.DangKyDAO;
import data.MonHocDangKy;
import data.PhuHuynh;
import data.TreEm;

public class DangKyController {
    private DangKyDAO dangKyDAO;

    public DangKyController(Connection conn) {
        this.dangKyDAO = new DangKyDAO(conn);
    }

    // Phương thức để xử lý đăng ký
    public boolean dangKy(String hoTenPH, String diaChi, String soDT, String email,
                          String hoTenTre, LocalDate ngaySinh, String gioiTinh, int maLH, LocalDate ngayDangKy) {
        try {
            // Tạo đối tượng PhuHuynh từ các thông tin nhập vào và thêm vào cơ sở dữ liệu
            int maPH = dangKyDAO.themPhuHuynh(new PhuHuynh(hoTenPH, diaChi, soDT, email));

            // Kiểm tra nếu mã phụ huynh không hợp lệ
            if (maPH < 0) {
                return false;
            }

            // Tạo đối tượng TreEm từ các thông tin nhập vào và thêm vào cơ sở dữ liệu
            TreEm treEm = new TreEm(hoTenTre, ngaySinh, gioiTinh, maPH);
            int maTre = dangKyDAO.themTreEm(treEm);

            // Kiểm tra nếu mã trẻ em không hợp lệ
            if (maTre < 0) {
                return false;
            }

            // Thêm đăng ký lớp học vào cơ sở dữ liệu
            return dangKyDAO.themDangKyLopHoc(maPH, maTre, maLH, ngayDangKy);
        } catch (SQLException e) {
            System.err.println("Lỗi đăng ký: " + e.getMessage());
            return false;
        }
    }

    // Phương thức để kiểm tra điều kiện nhập học của trẻ
    public boolean kiemTraDieuKienNhapHoc(LocalDate ngaySinh) {
        LocalDate currentDate = LocalDate.now();
        LocalDate fiveYearsAgo = currentDate.minusYears(5);
        LocalDate fifteenYearsAgo = currentDate.minusYears(15);

        return ngaySinh.isBefore(fiveYearsAgo) && ngaySinh.isAfter(fifteenYearsAgo);
    }

    public void hienThiMonHocNhieuNguoiDangKyNhat() {
        try {
            List<MonHocDangKy> monHocDangKyList = dangKyDAO.getMonHocNhieuNguoiDangKyNhat();
            if (monHocDangKyList.isEmpty()) {
                System.out.println("Không có dữ liệu về môn học được nhiều người đăng ký nhất.");
            } else {
//                System.out.println("Danh sách môn học được nhiều người đăng ký nhất:");
//                for (MonHocDangKy monHocDangKy : monHocDangKyList) {
//                    System.out.println("Môn học: " + monHocDangKy.getTenMonHoc() +
//                            ", Số phụ huynh đăng ký: " + monHocDangKy.getSoPhuHuynh());
//                }
                System.out.println("Danh sách môn học được nhiều người đăng ký nhất:");
                System.out.printf("%-10s | %-30s%n", "Môn học", "Số phụ huynh đăng ký");
                for (MonHocDangKy monHocDangKy : monHocDangKyList) {
                    System.out.printf("%-10s | %-30d%n", monHocDangKy.getTenMonHoc(), monHocDangKy.getSoPhuHuynh());
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn cơ sở dữ liệu: " + e.getMessage());
        }
    }
    public boolean kiemTraTonTaiSoDT(String soDT) throws SQLException {
        return dangKyDAO.kiemTraTonTaiSoDT(soDT);
    }

    public boolean kiemTraTonTaiEmail(String email) throws SQLException {
        return dangKyDAO.kiemTraTonTaiEmail(email);
    }

}