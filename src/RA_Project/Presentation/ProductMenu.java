package RA_Project.Presentation;

import RA_Project.BussinessImp.ProductImp;
import RA_Project.Entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductMenu {
    ProductImp pro = new ProductImp();
    public static List<Product> lsPro = new ArrayList<>();

    public void displayProductMenu(Scanner scanner) {
        try {
            lsPro = ProductImp.readDataProductFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean isExit = true;
        do {
            System.out.println("************ QUẢN LÝ SẢN PHẨM ************");
            System.out.println("1. Thêm mới sản phẩm");
            System.out.println("2. Cập nhật sản phẩm");
            System.out.println("3. Xóa sản phẩm");
            System.out.println("4. Hiển thị sản phẩm theo tên A - Z");
            System.out.println("5. Hiển thị sản phẩm theo lợi nhuận từ cao - thấp");
            System.out.println("6. Tìm kiếm sản phẩm");
            System.out.println("7. Quay lại");
            System.out.println("Lựa chọn của bạn: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if ((choice != 1) && (choice != 2) && (choice != 3) && (choice != 4) && (choice != 5)
                        && (choice != 6) && (choice != 7)) {
                }
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
            switch (choice) {
                case 1:
                    pro.inputDataProduct(scanner);
                    pro.displayDataProduct();
                    pro.writeDataProductToFile();
                    break;
                case 2:
                    pro.updateProById(scanner);
                    pro.writeDataProductToFile();
                    pro.displayDataProduct();
                    break;
                case 3:
                    pro.deletePro(scanner);
                    pro.writeDataProductToFile();
                    pro.displayDataProduct();
                    break;
                case 4:
                    pro.sortProbyName();
                    break;
                case 5:
                    pro.sortProByProfit();
                    break;
                case 6:
                    pro.findPro(scanner);
                    break;
                case 7:
                    isExit = false;
                    break;
                default:
                    System.err.println("Lựa chọn không phù hợp, Vui lòng nhập lại!");
            }
        } while (isExit);
    }
}
