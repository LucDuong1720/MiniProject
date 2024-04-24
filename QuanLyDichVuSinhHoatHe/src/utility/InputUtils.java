package utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import connectDB.ConnectDB;

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

}