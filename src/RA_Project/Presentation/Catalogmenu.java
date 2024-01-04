package RA_Project.Presentation;

import RA_Project.BussinessImp.CatalogImp;
import RA_Project.Entity.Category;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Catalogmenu {
    CatalogImp ctl = new CatalogImp();
    public static List<Category> lsCatalog = new ArrayList<>();

    public void displayCatalogMenu(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("************ QUẢN LÝ DANH MỤC ************");
            System.out.println("1. Thêm mới danh mục");
            System.out.println("2. Cập nhật danh mục");
            System.out.println("3. Xóa danh mục");
            System.out.println("4. Tìm kiếm danh mục theo tên danh mục");
            System.out.println("5. Thống kê số lượng sản phẩm đang có trong danh mục");
            System.out.println("6. Quay loại");
            System.out.println("Lựa chọn của bạn: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if ((choice != 1) && (choice != 2) && (choice != 3) && (choice != 4)
                        && (choice != 5) && (choice != 6)) {
                }
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
            switch (choice) {
                case 1:
                    ctl.inputDataCatalog(scanner);
                    ctl.displayDataCatalog();
                    ctl.writeDataCatalogToFile();
                    break;
                case 2:
                    ctl.updateCatalogById(scanner);
                    ctl.writeDataCatalogToFile();
                    ctl.displayDataCatalog();
                    break;
                case 3:
                    ctl.deleteCatalogById(scanner);
                    ctl.writeDataCatalogToFile();
                    ctl.displayDataCatalog();
                    break;
                case 4:
                    ctl.findCatalog(scanner);
                    break;
                case 5:
                    ctl.quantityStatisticsProduct();
                    break;
                case 6:
                    isExit = false;
                    break;
                default:
                    System.err.println("Lựa chọn không phù hợp, Vui lòng nhập lại!");
            }
        } while (isExit);
    }
}
