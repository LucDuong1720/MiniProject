package dao;
import data.MonHocDangKy;
import data.PhuHuynh;
import data.TreEm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DangKyDAO {
    private Connection conn;

    public DangKyDAO(Connection conn) {
        this.conn = conn;
    }

    public int themPhuHuynh(PhuHuynh phuHuynh) throws SQLException {
        String sql = "INSERT INTO PHUHUYNH (HoTenPH, DiaChi, SoDT, Email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, phuHuynh.getHoTenPH());
            pstmt.setString(2, phuHuynh.getDiaChi());
            pstmt.setString(3, phuHuynh.getSoDT());
            pstmt.setString(4, phuHuynh.getEmail());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return layMaPhuHuynhMoi(phuHuynh);
            }
        }
        return -1;
    }

    private int layMaPhuHuynhMoi(PhuHuynh phuHuynh) throws SQLException {
        String sql = "SELECT MaPH FROM PHUHUYNH WHERE HoTenPH = ? AND DiaChi = ? AND SoDT = ? AND Email = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, phuHuynh.getHoTenPH());
            pstmt.setString(2, phuHuynh.getDiaChi());
            pstmt.setString(3, phuHuynh.getSoDT());
            pstmt.setString(4, phuHuynh.getEmail());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("MaPH");
                }
            }
        }
        return -1;
    }

    public int themTreEm(TreEm treEm) throws SQLException {
        String sql = "INSERT INTO TREEM (HoTenTre, NgaySinh, GioiTinh, MaPH) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, treEm.getHoTenTre());
            pstmt.setDate(2, java.sql.Date.valueOf(treEm.getNgaySinh()));
            pstmt.setString(3, treEm.getGioiTinh());
            pstmt.setInt(4, treEm.getMaPH());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return layMaTreEmMoi(treEm);
            }
        }
        return -1;
    }

    private int layMaTreEmMoi(TreEm treEm) throws SQLException {
        String sql = "SELECT MaTre FROM TREEM WHERE HoTenTre = ? AND NgaySinh = ? AND GioiTinh = ? AND MaPH = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, treEm.getHoTenTre());
            pstmt.setDate(2, java.sql.Date.valueOf(treEm.getNgaySinh()));
            pstmt.setString(3, treEm.getGioiTinh());
            pstmt.setInt(4, treEm.getMaPH());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("MaTre");
                }
            }
        }
        return -1;
    }

    public boolean themDangKyLopHoc(int maPH, int maTre, int maLH, LocalDate ngayDangKy) throws SQLException {
        String sql = "INSERT INTO DANGKYTRE (maPH, MaTre, MaLH, NgayDangKy, TrangThai) VALUES (?, ?, ?, ?, N'Chưa duyệt')";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, maPH);
            pstmt.setInt(2, maTre);
            pstmt.setInt(3, maLH);
            pstmt.setDate(4, java.sql.Date.valueOf(ngayDangKy));
            return pstmt.executeUpdate() > 0;
        }
    }

    public List<MonHocDangKy> getMonHocNhieuNguoiDangKyNhat() throws SQLException {
        List<MonHocDangKy> monHocDangKyList = new ArrayList<>();

        String query = "SELECT MONHOC.TenMH, COUNT(DANGKYTRE.MaLH) AS SoPhuHuynh " +
                "FROM DANGKYTRE " +
                "JOIN LOPHOC ON DANGKYTRE.MaLH = LOPHOC.MaLH " +
                "JOIN MONHOC ON LOPHOC.MaMH = MONHOC.MaMH " +
                "WHERE DANGKYTRE.NgayDangKy BETWEEN '2024-01-01' AND '2024-03-31' " +
                "GROUP BY MONHOC.TenMH " +
                "ORDER BY SoPhuHuynh DESC";

        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String tenMonHoc = resultSet.getString("TenMH");
                int soPhuHuynh = resultSet.getInt("SoPhuHuynh");
                MonHocDangKy monHocDangKy = new MonHocDangKy(tenMonHoc, soPhuHuynh);
                monHocDangKyList.add(monHocDangKy);
            }
        }

        return monHocDangKyList;
    }
    public boolean kiemTraTonTaiSoDT(String soDT) throws SQLException {
        return kiemTraTonTai("PHUHUYNH", "SoDT", soDT);
    }

    public boolean kiemTraTonTaiEmail(String email) throws SQLException {
        return kiemTraTonTai("PHUHUYNH", "Email", email);
    }

    private boolean kiemTraTonTai(String table, String column, String value) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + table + " WHERE " + column + " = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, value);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}
