package view;

import java.util.Scanner;

import connectDB.DatabaseManager;
import dao.AddDAO;
import dao.DeleteDAO;
import dao.EditDAO;
import dao.FindDAO;
import dao.ShowDAO;
import utility.InputUtils;

public class BGVView {
	private DangKyView dangKyView;
	ShowDAO showDAO = new ShowDAO();
	EditDAO editDAO = new EditDAO();
	AddDAO addDAO = new AddDAO();
	ShowDAO showDao = new ShowDAO();
	InputUtils check = new InputUtils();
	DeleteDAO deleteDAO = new DeleteDAO();
	FindDAO findDAO = new FindDAO();
	Scanner sc = new Scanner(System.in);
	MainView mainView = new MainView();
	public void view() {
		
		
		int choice = 0;
		do {
			System.out.println("");
			System.out.println("===================BAN GIAO VU===================");
			System.out.println("|1. Thông tin lớp học");
			System.out.println("|2. Thông tin phụ huynh");
			System.out.println("|3. Xóa thông tin đăng ký lớp học");
			System.out.println("|4. Thông tin giáo viên");
			System.out.println("|5. Thông tin môn học");
			System.out.println("|6. Thông tin đăng ký trẻ");
			System.out.println("|7. Thống kê");
			System.out.println("|8. Quay lại");
			System.out.println("|9. Thoát chương trình");
			System.out.println("=================================================");
			try {
				choice = check.getValidChoice(sc);
				switch (choice) {
				case (1) : 
					lopHocView();
					break;
				case (2):
					phuHuynhView();
					break;
				case (3):
//					deleteView();
					break;
				case (4):
					giaoVienView();
					break;
				case (5):
					monHocView();
				case (6):
					dangKyTreView();
					break;
				case (7):
					findView();
					break;
				case (8):
					mainView.showMenu();
					break;
				case (9):
					System.out.println("Cảm ơn bạn đã sử dụng hệ thống.");
				System.exit(0);
				default: 
					System.out.println("Nhập sai");
					break;
				}
			} catch (Exception e) {
				System.out.println("Khong hop le vui long nhap lai");
				sc.next();
			}
		} while (choice != 9);
	}

	private void lopHocView() {
		int n;
		do {
			displayMenu();
			System.out.println("Nhập lựa chọn của bạn:");
			Scanner sc = new Scanner(System.in);
			n = Integer.parseInt(sc.nextLine());
			switch (n) {
			case 1:
				showDAO.hienThiThongTinLop();
				break;
			case 2:
				addDAO.themThongTinLop();
				break;
			case 3:
				editDAO.suaThongTinLop();
				break;
			case 4:
				view();
				break;
			case 0:
				System.out.println("Kết thúc chương trình.");
				System.exit(0);
				break;
			default:
				System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại!!!");
				System.out.println();
				break;
			}
		} while (n != 0);
	}
	
	public void displayMenu() {
		System.out.println("1. Hiển thị thông tin Lớp");
		System.out.println("2. Thêm thông tin Lớp");
		System.out.println("3. Sửa thông tin Lớp");
		System.out.println("4. Quay lại");
		System.out.println("0. Thoát chương trình");
	}


	public void phuHuynhView() {
		boolean ct = true;
		while (ct) {
			System.out.println(" \n===============Menu phụ huynh===================");
			System.out.println("| 1. Thêm phụ huynh                            |");
			System.out.println("| 2. Hiện thị danh sách phụ huynh đăng kí      |");
			System.out.println("| 3. Tìm kiếm theo tên phụ huynh               |");
			System.out.println("| 4. Sửa thông tin phụ huynh theo mã phụ huynh |");
			System.out.println("| 5. Quay lại                                  |");
			System.out.println("| 0. Thoát chương trình                        |");
			System.out.println("================================================");
			int a = 0;
			boolean ct1 = true;
			while (ct1) {
				try {
					System.out.println("nhập vào lựa chọn");
					a = Integer.parseInt(sc.nextLine());
					if (a < 0 || a > 6) {
						System.out.println("Lựa chọn chưa được thiết lập vui lòng chọn lại ");
					} else {
						ct1 = false;
					}
				} catch (NumberFormatException e) {
					System.out.println(" Lựa chọn không hợp lệ vui lòng nhập lại");
				}
			}
			switch (a) {
			case 1:
				addDAO.them(sc);
				break;
			case 2:
				showDAO.hienThi();
				break;
			case 3:
				findDAO.timKiem(sc);
				break;
			case 4:
				EditDAO.update();
				break;
			case 5:
				view();
				break;
			case 0:
				ct = false;
				break;
			default:
				break;

			}

		}
	}

	private void dangKyTreView() {
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
        while (!exit){
            try {
                System.out.println("\n==================MENU==================");
                System.out.println("|       1.Thêm thông tin đăng ký       |");
                System.out.println("|       2.Hiện thị thông tin đăng ký   |");
                System.out.println("|       3.Sửa thông tin đăng ký        |");
                System.out.println("|       4.Xóa thông tin đăng ký        |");
                System.out.println("|       5.Tìm kiếm thông tin đăng ký   |");
                System.out.println("|       6.Quay lại                     |");
                System.out.println("========================================");
                System.out.println("Chọn chức năng: ");
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice){
                    case 1:
                        addDAO.themThongTinDangKy();
                        break;
                    case 2:
                    	showDAO.hienThiToanBoThongTin();
                    case 3:
                        editDAO.suaThongTinDangKyDatabase();
                        break;
                    case 4:
                        deleteDAO.xoaThongTinDangKyDatabase();
                        break;
                    case 5:
                        showDAO.timKiemThongTinDangKyDatabase();
                        break;
                    case 6:
    					view();
                        break;
                    case 0:
                        exit = true;
                        break;
                    default:
                        System.out.println("lựa chọn không hợp lệ vui lòng chọn lại");
                }
            }
            catch (NumberFormatException e){
                System.out.println("lỗi " + e);
            }
            catch (Exception e){
                System.out.println("Đã xảy ra lỗi: " + e.getMessage());
            }
        }
        DatabaseManager.closeConnectDB();
    }
		

	private void giaoVienView() {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		do {
			System.out.println("");
			System.out.println("====================GIÁO VIÊN======================");
			System.out.println("|	1. Xem thông tin                           |");
			System.out.println("|	2. Sửa thông tin                           |");
			System.out.println("|	3. Thêm thông tin                          |");
			System.out.println("|	4. Quay lại                                |");
			System.out.println("|	5. Thoát chương trình                      |");
			System.out.println("===================================================");
//			System.out.println("Nhap vao so cua chuong trinh: ");
			try {
				choice = check.getValidChoice(sc);
				switch (choice) {
				case (1) : 
					showDAO.showGiaoVien();
					break;
				case (2):
					showDAO.showGiaoVien();
					editDAO.editGiaoVien();
					break;
				case (3):
					addDAO.addGV();
					break;
				case (4):
					view();
					break;
				case (5):
					System.out.println("Cảm ơn bạn đã sử dụng hệ thống.");
					System.exit(0);
				default: 
					System.out.println("Nhập sai");
					break;
				}
			} catch (Exception e) {
				System.out.println("Khong hop le vui long nhap lai");
				sc.next();
			}
		} while (choice != 6);
	}

	public void monHocView() {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		do {
			System.out.println("");
			System.out.println("===================MÔN HỌC====================");
			System.out.println("|	1. Xem thông tin                           |");
			System.out.println("|	2. Sửa thông tin                           |");
			System.out.println("|	3. Thêm thông tin                          |");
			System.out.println("|	4. Quay lại                                |");
			System.out.println("|	5. Thoát chương trình                      |");
			System.out.println("================================================");
//				System.out.println("Nhap vao so cua chuong trinh: ");
			try {
				choice = check.getValidChoice(sc);
				switch (choice) {
				case (1) : 
					showDAO.showMonHoc();
					break;
				case (2):
					showDAO.showMonHoc();
					editDAO.editMonHoc();
					break;
				case (3):
					addDAO.addMonHoc();
					break;
				case (4):
					view();
					break;
				case (5):
					System.out.println("Cảm ơn bạn đã sử dụng hệ thống.");
					System.exit(0);
				default: 
					System.out.println("Nhập sai");
					break;
				}
			} catch (Exception e) {
				System.out.println("Khong hop le vui long nhap lai");
				sc.next();
			}
		} while (choice != 6);
	}
		
	private void findView() {
		FindDAO findDAO = new FindDAO();
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		do {
			System.out.println("");
			System.out.println("===========================================TÌM KIẾM===========================================");
			System.out.println("1. Liệt kê môn học được nhiều PH đăng ký nhất trong khoảng thời gian 1/2024 ~ 3/2024");
			System.out.println("2. Thêm thông tin phụ huynh");
			System.out.println("3. Liệt kê top 3 PH đăng kí nhiều môn học nhất trong năm 2023");
			System.out.println("4. Thêm thông tin giáo viên");
			System.out.println("5. Liệt kê lớp học có số lượng đăng ký học thấp nhất từ tháng 12/2023 đến hiện tại");
			System.out.println("6. Liệt kê những thời gian học có nhiều người đăng kí học từ 1/1/2024 đến 31/3/2024");
			System.out.println("7. Quay lại");
			System.out.println("Nhap vao so cua chuong trinh: ");
			try {
				choice = check.getValidChoice(sc);
//				sc.nextLine();
				switch (choice) {
				case (1) : 
					dangKyView.hienThiMonHocNhieuNguoiDangKyNhat();
					break;
				case (2):
					findDAO.lietKeMonHocChuaDK();
					break;
				case (3):
					findDAO.top3();
					break;
				case (4):
//					addDAO.addGV();
					break;
				case (5):
					findDAO.find5();
					break;
				case (6):
					findDAO.lietKeThoiGianHoc();
					break;
				case (7):
					view();
					break;
					default: 
						System.out.println("Nhap sai");
						break;
				}
			} catch (Exception e) {
				System.out.println(choice);
				System.out.println("Khong hop le vui long nhap lai");
				sc.next();
			}
		} while (choice != 7);
		
	}

	private void addView() {
		AddDAO addDAO = new AddDAO();
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		do {
			System.out.println("");
			System.out.println("===================BAN GIAO VU===================");
			System.out.println("|	1. Thêm thông tin lớp học                    |");
			System.out.println("|	2. Thêm thông tin phụ huynh                  |");
			System.out.println("|	3. Thêm thông tin trẻ em                     |");
			System.out.println("|	4. Thêm thông tin giáo viên                  |");
			System.out.println("|	5. Thêm thông tin môn học                    |");
			System.out.println("|	6. Thêm thông tin đang ký trẻ                |");
			System.out.println("|	7. Quay lại                                  |");
			System.out.println("=================================================");
//			System.out.println("Nhap vao so cua chuong trinh: ");
			try {
				choice = check.getValidChoice(sc);
//				sc.nextLine();
				switch (choice) {
				case (1) : 
//					findView.view();
					break;
				case (2):
//					PhuHuynhView.view();
					break;
				case (4):
					addDAO.addGV();
					break;
				case (5):
					addDAO.addMonHoc();
					break;
				case (7):
					view();
					break;
					default: 
						System.out.println("Nhập sai");
						break;
				}
			} catch (Exception e) {
				System.out.println("Không hợp lệ vui lòng nhập lại");
				sc.next();
			}
		} while (choice != 7);
		
	}

	public void showView() {
		
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		do {
			System.out.println("");
			System.out.println("===================BAN GIAO VU===================");
			System.out.println("1. Xem thong tin lop hoc");
			System.out.println("2. Xem thong tin phu huynh");
			System.out.println("3. Xem thong tin tre em");
			System.out.println("4. Xem thong tin giao vien");
			System.out.println("5. Xem thong tin mon hoc");
			System.out.println("6. Xem thong tin dang ky tre");
			System.out.println("7. Quay lai");
//			System.out.println("Nhap vao so cua chuong trinh: ");
			try {
				choice = check.getValidChoice(sc);
//				sc.nextLine();
				switch (choice) {
				case (1) : 
//					findView.view();
					break;
				case (2):
//					PhuHuynhView.view();
					break;
				case (4):
					showDao.showGiaoVien();
					break;
				case (5):
					showDao.showMonHoc();
					break;
				case (7):
					view();
					break;
					default: 
						System.out.println("Nhap sai");
						break;
				}
			} catch (Exception e) {
				System.out.println("Khong hop le vui long nhap lai");
				sc.next();
			}
		} while (choice != 7);
		
	}
	
	
	public void editView() {
		EditDAO editDao = new EditDAO();
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		do {
			System.out.println("");
			System.out.println("===================BAN GIAO VU===================");
			System.out.println("1. Sua thong tin lop hoc");
			System.out.println("2. Sua thong tin phu huynh");
			System.out.println("3. Sua thong tin tre em");
			System.out.println("4. Sua thong tin giao vien");
			System.out.println("5. Sua thong tin mon hoc");
			System.out.println("6. Sua thong tin dang ky tre");
			System.out.println("7. Quay lai");
//			System.out.println("Nhap vao so cua chuong trinh: ");
			try {
				choice = check.getValidChoice(sc);
				switch (choice) {
				case (1) : 
//					findView.view();
					break;
				case (2):
//					PhuHuynhView.view();
					break;
				case (4):
					showDao.showGiaoVien();
					editDao.editGiaoVien();
					break;
				case (5):
					showDao.showMonHoc();
					editDao.editMonHoc();
					break;
				case (7):
					view();
					break;
					default: 
						System.out.println("Nhap sai");
						break;
				}
			} catch (Exception e) {
				System.out.println("Khong hop le vui long nhap lai");
				sc.next();
			}
		} while (choice != 7);
		
	}
	
//	public void deleteView() {
//		DeleteDAO deleteDao = new DeleteDAO();
//		Scanner sc = new Scanner(System.in);
//		int choice = 0;
//		do {
//			System.out.println("");
//			System.out.println("===================BAN GIÁO VỤ===================");
//			System.out.println("1. Xóa thông tin lớp học");
//			System.out.println("2. Xóa thông tin phụ huynh");
//			System.out.println("3. Xóa thông tin trẻ em");
//			System.out.println("4. Xóa thông tin giáo viên");
//			System.out.println("5. Xóa thông tin môn học");
//			System.out.println("6. Xóa thông tin đăng ký trẻ");
//			System.out.println("7. Quay lại");
//			System.out.println("Nhap vao so cua chuong trinh: ");
//			try {
//				choice = sc.nextInt();
//				switch (choice) {
//				case (1) : 
//					deleteDao.deleteLopHoc();
//					break;
//				case (2):
////					PhuHuynhView.view();
//					break;
//				case (4):
//					deleteDao.deleteGiaoVien();
//					break;
//				case (5):
//					deleteDao.deleteMonHoc();
//					break;
//				case (7):
//					view();
//					default: 
//						System.out.println("Nhap sai");
//						break;
//				}
//			} catch (Exception e) {
//				System.out.println("Khong hop le vui long nhap lai");
//				sc.next();
//			}
//		} while (choice != 7);
//		
//	}
}
