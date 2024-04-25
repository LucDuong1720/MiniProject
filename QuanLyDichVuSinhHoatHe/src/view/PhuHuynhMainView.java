
package view;

import controller.PhuHuynhController;
import dao.DatabaseConnection;
import dao.LopHocDAO;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.SQLException;

public class PhuHuynhMainView {
    private PhuHuynhView phuHuynhView;
    private DangKyView dangKyView;
    private Scanner scanner;
    MainView mainView = new MainView();
    public PhuHuynhMainView() throws SQLException {
        this.phuHuynhView = new PhuHuynhView(new PhuHuynhController(new LopHocDAO(DatabaseConnection.getConnection())));
        this.dangKyView = new DangKyView(DatabaseConnection.getConnection());
        this.scanner = new Scanner(System.in);
    }

    public void showPhuHuynhMenu() {
        while (true) {
            System.out.println("\n---------------------MENU PHỤ HUYNH-----------------------");
            System.out.println("|	1. Xem thông tin lớp học                         |");
            System.out.println("|	2. Tìm kiếm thông tin lớp học theo tên môn học   |");
            System.out.println("|	3. Đăng ký lớp học cho trẻ                       |");
            System.out.println("|	4. Quay lại                                      |");
            System.out.println("|	5. Thoát                                         |");
            System.out.println("----------------------------------------------------------");
            System.out.print("Lựa chọn của bạn: ");

            try {
                String input = scanner.nextLine().trim(); // Đọc toàn bộ dòng nhập và loại bỏ các khoảng trắng thừa

                // Kiểm tra nếu người dùng không nhập gì
                if (input.isEmpty()) {
                    System.out.println("Vui lòng nhập một lựa chọn.");
                    continue; // Quay lại vòng lặp để yêu cầu nhập lại
                }

                int choice = Integer.parseInt(input); // Chuyển đổi input thành số nguyên

                switch (choice) {
                    case 1:
                        phuHuynhView.layDanhSachLopHoc();
                        break;
                    case 2:
                        phuHuynhView.timKiemLopHocTheoTenMH();
                        break;
                    case 3:
                        dangKyView.nhapThongTinDangKy();
                        break;
                    case 4:
                        mainView.showMenu();
                        break;
                    case 5:
                        System.out.println("Cảm ơn bạn đã sử dụng hệ thống.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số nguyên.");
            } catch (SQLException e) {
                System.out.println("Lỗi khi xử lý dữ liệu từ cơ sở dữ liệu: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Đã xảy ra lỗi không mong muốn: " + e.getMessage());
            }
        }
    }

}



