package view;

import controller.DangKyController;
import utility.InputUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class DangKyView {
    private DangKyController dangKyController;

    public DangKyView(Connection conn) {
        this.dangKyController = new DangKyController(conn);
    }

    public void nhapThongTinDangKy() throws SQLException {
        System.out.println("Mời bạn nhập vào các thông tin cần để đăng ký!");

        // Nhận thông tin phụ huynh
        String hoTenPH = InputUtils.getStringInput("Nhập họ tên phụ huynh: ");
        String diaChi = InputUtils.getStringInput("Nhập địa chỉ phụ huynh: ");
        String soDT;
        String email;

        // Kiểm tra và yêu cầu nhập lại nếu số điện thoại đã tồn tại
        do {
            soDT = InputUtils.getStringInput("Nhập số điện thoại phụ huynh: ");
            if (dangKyController.kiemTraTonTaiSoDT(soDT)) {
                System.out.println("Số điện thoại đã tồn tại trong hệ thống. Vui lòng nhập lại.");
            }
        } while (dangKyController.kiemTraTonTaiSoDT(soDT));

        // Kiểm tra và yêu cầu nhập lại nếu email đã tồn tại
        do {
            email = InputUtils.getStringInput("Nhập email phụ huynh: ");
            if (dangKyController.kiemTraTonTaiEmail(email)) {
                System.out.println("Email đã tồn tại trong hệ thống. Vui lòng nhập lại.");
            }
        } while (dangKyController.kiemTraTonTaiEmail(email));

        // Nhận thông tin trẻ em
        String hoTenTre = InputUtils.getStringInput("Nhập họ tên trẻ em: ");

        LocalDate ngaySinh = InputUtils.getDateInput("Nhập ngày sinh trẻ em (dd/mm/yyyy): ");
        // Kiểm tra điều kiện nhập học của trẻ
        boolean coDuDieuKienNhapHoc = dangKyController.kiemTraDieuKienNhapHoc(ngaySinh);
        if (!coDuDieuKienNhapHoc) {
            System.out.println("Trẻ không đủ điều kiện nhập học (tuổi từ 5 đến 15). Vui lòng kiểm tra lại ngày sinh của trẻ.");
            return; // Thoát khỏi phương thức nếu không đủ điều kiện
        }

        String gioiTinh = InputUtils.getStringInput("Nhập giới tính trẻ em: ");

        // Nhận thông tin đăng ký lớp học
        int maLH = InputUtils.getIntInput("Nhập mã lớp học mà trẻ đăng ký: ");
        LocalDate ngayDangKy = LocalDate.now();

        // Gọi controller để xử lý đăng ký
        boolean isSuccess = dangKyController.dangKy(hoTenPH, diaChi, soDT, email, hoTenTre, ngaySinh, gioiTinh, maLH, ngayDangKy);
        if (isSuccess) {
            System.out.println("Đăng ký thành công!");
        } else {
            System.out.println("Đăng ký không thành công. Vui lòng thử lại.");
        }

        // Hỏi người dùng có muốn nhập thêm đăng ký khác không
        boolean tiepTuc = InputUtils.getBooleanInput("Bạn có muốn nhập đăng ký khác không? (C/K)");
        while (tiepTuc) {
            nhapThongTinDangKy(); // Gọi lại phương thức này để nhập thông tin đăng ký mới
            tiepTuc = InputUtils.getBooleanInput("Bạn có muốn nhập đăng ký khác không? (C/K)");
        }
    }

    public void hienThiMonHocNhieuNguoiDangKyNhat() {
        dangKyController.hienThiMonHocNhieuNguoiDangKyNhat();
    }
}
