package view;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import dao.DatabaseConnection;
import utility.InputUtils;

public class MainView {
	InputUtils check = new InputUtils();
	public void showMenu() {
		Scanner sc = new Scanner(System.in);
		BGVView bgv = new BGVView();
		PhuHuynhMainView phuHuynhMainView = null;
		try {
			phuHuynhMainView = new PhuHuynhMainView();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int choice = 0;
		do {
			System.out.println("");
			System.out.println("Chao mung ban den voi chuong trinh quan ly dich vu sinh hoat he, ban la ai?");
			System.out.println("1. Ban giao vu");
			System.out.println("2. Phu huynh");
			System.out.println("3. Thoat khoi chuong trinh");
//			System.out.println("Nhap vao so cua chuong trinh: ");
			try {
				choice = check.getValidChoice(sc);
				switch (choice) {
				case (1) : 
					bgv.view();
					break;
				case (2):
					phuHuynhMainView.showPhuHuynhMenu();
					break;
				case (3):
					System.exit(0);
					break;
					default: 
						System.out.println("Nhap sai");
						break;
				}
			} catch (Exception e) {
				System.out.println("Khong hop le vui long nhap lai");
				sc.next();
			}
		} while (choice != 3);
//		try (Connection conn = DatabaseConnection.getConnection()) {
//
//            // Tạo đối tượng MainView
//            PhuHuynhMainView phuHuynhMainView = new PhuHuynhMainView();
//
//            // Sử dụng Scanner để quản lý các tùy chọn menu
//            try (Scanner scanner = new Scanner(System.in)) {
////                while (true) {
//            	do {
//                    System.out.println("\nChọn vai trò của bạn:");
//                    System.out.println("1. Phụ huynh");
//                    System.out.println("2. Ban giáo vụ");
//                    System.out.println("3. Thoát");
//                    System.out.print("Lựa chọn của bạn: ");
//
//                    int role = scanner.nextInt();
//
//                    switch (role) {
//                        case 1:
//                            phuHuynhMainView.showPhuHuynhMenu();
//                            break;
//                        case 2:
//                        	bgv.view();
//        					break;
//                        case 3:
//                            System.out.println("Cảm ơn bạn đã sử dụng hệ thống.");
//                            System.exit(0);
//                            break; // Thoát khỏi ứng dụng
//                        default:
//                            System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
//                            break;
//                    }
//                } 
//            }
//        } catch (SQLException e) {
//            System.err.println("Lỗi kết nối cơ sở dữ liệu: ");
//            e.printStackTrace();
//        } catch (Exception e) {
//        	System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
//        }
	}
}
