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
			System.out.println("Chào mừng đến với chương trình quản lý sinh hoạt hè, bạn là ai?");
			System.out.println("-------------------------------------");
			System.out.println("|	1. Ban giáo vụ	    	    |");
			System.out.println("|	2. Phụ huynh	    	    |");
			System.out.println("|	3. Thoát khỏi chương trình  |");
			System.out.println("-------------------------------------");
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
	}
}
