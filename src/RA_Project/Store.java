package RA_Project;

import RA_Project.BussinessImp.CatalogImp;
import RA_Project.BussinessImp.ProductImp;
import RA_Project.Presentation.Catalogmenu;
import RA_Project.Presentation.ProductMenu;
import static RA_Project.Presentation.ProductMenu.lsPro;
import static RA_Project.Presentation.Catalogmenu.lsCatalog;
import java.util.Scanner;

public class Store {

    public static void main(String[] args) {
        try {
            lsPro = ProductImp.readDataProductFromFile();
            lsCatalog = CatalogImp.readDataCatalogFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        Catalogmenu ctl = new Catalogmenu();
        ProductMenu prm = new ProductMenu();
        do {
            System.out.println("************ QUẢN LÝ KHO ************");
            System.out.println("1. Quản lý danh mục");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.println("Lựa chọn của bạn: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if ((choice != 1) && (choice != 2) && (choice != 3)) {
                }
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
            switch (choice) {
                case 1:
                    ctl.displayCatalogMenu(scanner);
                    break;
                case 2:
                    prm.displayProductMenu(scanner);
                    break;
                case 3:
                    System.out.println("Chương trình kết thúc, Xin tạm biệt!");
                    System.exit(0);
                default:
                    System.err.println("Lựa chọn không phù hợp, vui lòng nhập lại!");
            }
        } while (true);
    }
}
