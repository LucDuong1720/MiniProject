package utility;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import connectDB.ConnectDB;
import connectDB.ketNoi;

public class InputUtils {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\d{10}(?:,\\d{10})*$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$");


    public static String getStringInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.nextLine().trim(); // Xóa các khoảng trắng ở đầu và cuối chuỗi
                if (input.isEmpty()) {
                    throw new IllegalArgumentException("Dữ liệu không được rỗng.");
                }
                return checkNullInput(input, "Dữ liệu không được rỗng.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static <T> T checkNullInput(T input, String errorMessage) {
        if (input == null) {
            throw new IllegalArgumentException(errorMessage);
        }
        return input;
    }

    public static int getIntInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số nguyên.");
            }
        }
    }


    public static LocalDate getDateInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.nextLine();
                String[] parts = input.split("/");

                // Kiểm tra số lượng phần tử và giá trị hợp lệ của ngày, tháng và năm
                if (parts.length != 3) {
                    throw new DateTimeException("Định dạng ngày không hợp lệ. Vui lòng nhập lại.");
                }

                int day = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]);
                int year = Integer.parseInt(parts[2]);

                if (year < 1 || year > 9999 || month < 1 || month > 12 || day < 1 || day > LocalDate.of(year, month, 1).lengthOfMonth()) {
                    throw new DateTimeException("Ngày tháng không hợp lệ. Vui lòng nhập lại.");
                }

                // Parse thành LocalDate nếu tất cả các giá trị hợp lệ
                return LocalDate.parse(input, DATE_FORMATTER);

            } catch (DateTimeException | NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public static boolean getBooleanInput(String message) {
        while (true) {
            System.out.print(message + " (C/K): ");
            String choice = scanner.nextLine().toUpperCase();
            if (choice.equals("C")) {
                return true;
            } else if (choice.equals("K")) {
                return false;
            } else {
                System.out.println("Vui lòng chỉ chọn 'C' hoặc 'K'.");
            }
        }
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        Matcher matcher = PHONE_PATTERN.matcher(phoneNumber);
        return matcher.matches();
    }

    public static boolean isValidEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
    
    public boolean containsNumber(String input) {
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }
    
    public int getValidMa(Scanner sc) {
        int ma = 0;
        boolean inputValid = false;
        System.out.println("Mời nhập mã: ");
        while (!inputValid) {   
            try {
                ma = Integer.parseInt(sc.nextLine());
                if (ma <= 0) {
                    System.out.println("Phải là số nguyên dương.");
                } else {
                    inputValid = true;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Không hợp lệ vui lòng nhập lại");
            }
        }
        return ma;
    }
    public int getValidChoice(Scanner sc) {
        int ma = 0;
        boolean inputValid = false;
        System.out.println("Nhập lựa chọn: ");
        while (!inputValid) {   
            try {
                ma = Integer.parseInt(sc.nextLine());
                if (ma <= 0) {
                    System.out.println("Phải là số nguyên dương.");
                } else {
                    inputValid = true;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Không hợp lệ vui lòng nhập lại");
            }
        }
        return ma;
    }
    
    public String getValidName(Scanner sc) {
        String name = "";
        while (name.trim().isEmpty() || containsNumber(name) || !name.matches("[\\p{L}\\s]+")) {
            System.out.println("Nhập tên: ");
            name = sc.nextLine();
            if (name.trim().isEmpty()) {
                System.out.println("Không được để trống.");
            } else if (containsNumber(name)) {
                System.out.println("Không được chứa số.");
            } else if (!name.matches("[\\p{L}\\s]+")) {
            	System.out.println("Không chứa ký tự đặc biệt.");
            }
        }
        return name;
    }
    public boolean isExists(String tableName, String columnName, String value) {
        ConnectDB conn = new ConnectDB();
        Connection con = conn.getConnectDB();
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean exists = false;

        try {
            String sql = "SELECT * FROM " + tableName + " WHERE " + columnName + " = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, value);
            rs = pst.executeQuery();

            if (rs.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (pst != null) {
//                    pst.close();
//                }
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return exists;
    }
	public String nhapHoTenTre (Scanner sc) {
		System.out.println("Nhập vào họ tên của trẻ");
		String hoTenTre = sc.nextLine();
		while (hoTenTre.trim().isEmpty() || !hoTenTre.matches("[\\p{L}\\s]+")) {
			System.out.println("Họ tên không hợp lệ, vui lòng nhập lại");
			hoTenTre = sc.nextLine();
		}
		return hoTenTre;
	}

	public String nhapGioiTinh (Scanner sc) {
		System.out.println("Nhập vào giới tính của trẻ");
		String gioiTinh = sc.nextLine();
		while (gioiTinh.trim().isEmpty() || (!gioiTinh.equalsIgnoreCase("Nam") && !gioiTinh.equalsIgnoreCase("Nữ"))) { // equalsIgnoreCase:
																														// so
																														// sánh
																														// bỏ
																														// qua
																														// kiểu
																														// chữ
			System.out.println("Giới tính không hợp lệ, vui lòng nhập lại");
			gioiTinh = sc.nextLine();
		}
		return gioiTinh;
	}

	public int nhapMaPH(Scanner sc) {
		int maPH = 0;
		boolean pH = false;
		while (!pH) {
			System.out.println("Nhập vào mã phụ huynh");
			String input = sc.nextLine();
			if (input.matches("\\d+")) {
				maPH = Integer.parseInt(input);
				try {
					if (kiemTraMaPHTonTai(maPH)) {
						pH = true;
					} else {
						System.out.println("Mã phụ huynh không tồn tại");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("Mã phụ huynh không hợp lệ");
			}
		}
		return maPH;
	}
	
	public boolean kiemTraMaPHTonTai(int monHocID) throws SQLException {
		Connection con = ketNoi.getConnection();
		String sql = "SELECT * FROM PHUHUYNH WHERE MaPH = ?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setInt(1, monHocID);
		ResultSet rs = pst.executeQuery();
		return rs.next();
	}
	
	public Date nhapNgaySinh (Scanner sc) {
        java.sql.Date ngaySinhSua = null;
        boolean ns = true;
        while (ns) {
            try {
                System.out.println("Nhập vào ngày sinh của trẻ (YYYY-MM-DD)");
                String NgaySinhSuaStr = sc.nextLine();
                ngaySinhSua = java.sql.Date.valueOf(NgaySinhSuaStr);
                LocalDate ngaySinhLocalDate = ngaySinhSua.toLocalDate(); // chuyển đối tượng java.sql.date sang
                                                                            // local date để so sánh với ngày hiện
                                                                            // tại
                LocalDate ngayHienTai = LocalDate.now();
                int tuoi = Period.between(ngaySinhLocalDate, ngayHienTai).getYears(); // phương thức period.between:
                                                                                        // tính toán khoảng thời
                                                                                        // gian giữa 2 đối tượng,
                                                                                        // .getYears để lấy năm tính
                                                                                        // tuổi
                if (tuoi <= 5 || tuoi >= 15) {
                    System.out.println("Tuổi của trẻ không hợp lệ, phải lớn hơn 5 và nhỏ hơn 15");
                    continue;
                }
                ns = false;
            } catch (Exception e) {
                System.out.println("Ngày tháng năm không hợp lệ, vui lòng nhập lại");
            }
        }    
        return ngaySinhSua;
    }
	
	public int nhapLuaChon (Scanner sc) {
        int luaChon = 0;
        boolean ct1 = true;
        while (ct1) {
            try {
                System.out.println("nhập vào lựa chọn");
                luaChon = Integer.parseInt(sc.nextLine());
                if (luaChon < 0 || luaChon > 6) {
                    System.out.println("Lựa chọn chưa được thiết lập vui lòng chọn lại ");
                } else {
                    ct1 = false;
                }
            } catch (NumberFormatException e) {
                System.out.println(" Lựa chọn không hợp lệ vui lòng nhập lại");
            }
        }
        return luaChon;
    }
}
