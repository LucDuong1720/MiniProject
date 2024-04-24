package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.LopHocDAO;
import data.LopHoc;

public class PhuHuynhController {
    private LopHocDAO lopHocDAO;

    public PhuHuynhController(LopHocDAO lopHocDAO) throws SQLException {
        this.lopHocDAO = lopHocDAO;
    }

    public List<String> layDanhSachLopHoc() throws SQLException {
        // Lấy thông tin về danh sách các lớp học từ nguồn dữ liệu
        List<LopHoc> danhSachLopHoc = lopHocDAO.xemThongTinLopHoc();

        // Tạo một danh sách mới để lưu thông tin về các lớp học dưới dạng chuỗi
        List<String> thongTinLopHoc = new ArrayList<>();

        // Duyệt qua từng lớp học trong danh sách và chuyển đổi thông tin thành chuỗi
        for (LopHoc lopHoc : danhSachLopHoc) {
            String thongTin = lopHoc.toString(); // Chuyển đổi thông tin thành chuỗi
            thongTinLopHoc.add(thongTin); // Thêm chuỗi vào danh sách
        }

        // Trả về danh sách chứa thông tin về các lớp học dưới dạng chuỗi
        return thongTinLopHoc;
    }


    public List<String> timKiemLopHocTheoTenMH(String tenMH) throws SQLException {
        // Tìm kiếm các lớp học dựa trên tên môn học
        List<LopHoc> ketQuaTimKiem = lopHocDAO.timKiemLopHocTheoTenMH(tenMH);

        // Tạo một danh sách mới để lưu thông tin về các lớp học dưới dạng chuỗi
        List<String> thongTinLopHoc = new ArrayList<>();

        // Duyệt qua danh sách kết quả tìm kiếm và chuyển đổi thông tin thành chuỗi
        for (LopHoc lopHoc : ketQuaTimKiem) {
            String thongTin = lopHoc.toString(); // Chuyển đổi thông tin thành chuỗi
            thongTinLopHoc.add(thongTin); // Thêm chuỗi vào danh sách
        }

        // Trả về danh sách chứa thông tin về các lớp học dưới dạng chuỗi
        return thongTinLopHoc;
    }

}
