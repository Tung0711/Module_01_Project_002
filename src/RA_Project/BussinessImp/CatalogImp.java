package RA_Project.BussinessImp;

import RA_Project.Entity.Category;
import RA_Project.Entity.Product;
import RA_Project.Presentation.ProductMenu;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static RA_Project.Presentation.Catalogmenu.lsCatalog;
import static RA_Project.Presentation.ProductMenu.lsPro;

public class CatalogImp {
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";

    public void inputDataCatalog(Scanner scanner) {
        System.out.println("Nhập vào số lượng danh mục cần nhập thông tin:");
        int number = 0;
        try {
            number = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            System.err.println("Số lượng danh mục là số nguyên, vui lòng nhập lại!");
        }
        for (int i = 0; i < number; i++) {
            Category ctl = new Category();
            ctl.inputData(scanner);
            lsCatalog.add(ctl);
        }
    }

    public void displayDataCatalog() {
        formatPrintCatalog();
        for (int i = 0; i < lsCatalog.size(); i++) {
            lsCatalog.get(i).displayData();
        }
    }

    public static void formatPrintCatalog() {
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.println(ANSI_BLUE + "| Mã danh mục  |      Tên danh mục       |        Mô tả danh mục       | Trạng thái danh mục |" + ANSI_RESET);
        System.out.println("----------------------------------------------------------------------------------------------");
    }

    public void updateCatalogById(Scanner scanner) {
        System.out.println("Nhập mã danh mục cần cập nhật:");
        int catalogIdUpdate = 0;
        try {
            catalogIdUpdate = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            System.err.println("Mã danh mục là số nguyên, vui lòng nhập lại!");
            ex.printStackTrace();
        }
        boolean find = false;
        for (int i = 0; i < lsCatalog.size(); i++) {
            Category ctl = lsCatalog.get(i);
            if (ctl.getId() == catalogIdUpdate) {
                ctl.updateCatalog(scanner);
                System.out.println("Thông tin danh mục đã được cập nhật thành công!");
                find = true;
                break;
            }
        }
        if (!find) {
            System.err.println("Mã danh mục không tồn tại, vui lòng nhập lại!");
        }
    }

    public void deleteCatalogById(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục cần xóa: ");
        int deleteCatalogId = 0;
        try {
            deleteCatalogId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            System.err.println("Mã danh mục là số nguyên, vui lòng nhập lại!");
            ex.printStackTrace();
        }
        int indexDelete = -1;
        boolean isDelete = false;
        for (int i = 0; i < lsCatalog.size(); i++) {
            Category ctl = lsCatalog.get(i);
            if (ctl.getId() == deleteCatalogId) {
                indexDelete = i;
                isDelete = true;
                break;
            }
        }
        if (!isDelete) {
            System.err.println("Mã danh mục không tồn tại, vui lòng nhập lại!");
        } else if (CatalogImp.hasProduct(deleteCatalogId)) {
            System.err.println("Danh mục đã chứa sản phẩm, không thể xóa!");
        } else {
            lsCatalog.remove(indexDelete);
            System.out.println("Danh mục đã xóa thành công");
        }
    }

    public static boolean hasProduct(int catalogId) {
        for (Product pro : lsPro) {
            if (pro.getCategoryId() == catalogId) {
                return true;
            }
        }
        return false;
    }

    public void findCatalog(Scanner scanner) {
        System.out.println("Nhập vào từ khóa cần tìm: ");
        try {
            String findCatalog = scanner.nextLine();
            boolean isFindCatalog = lsCatalog.stream().anyMatch(category -> category.getName().contains(findCatalog));
            boolean isFind = lsCatalog.stream().anyMatch(category -> category.getDescription().contains(findCatalog));
            if (!isFindCatalog && !isFind) {
                System.err.println("Danh mục không tồn tại");
            } else {
                formatPrintCatalog();
                lsCatalog.stream().filter(category -> category.getName().contains(findCatalog)).forEach(System.out::println);
                lsCatalog.stream().filter(category -> category.getDescription().contains(findCatalog)).forEach(System.out::println);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void quantityStatisticsProduct() {
        try {
            for (Category ctl : lsCatalog) {
                long countCatalog = lsPro.stream().filter(product -> product.getCategoryId() == ctl.getId()).count();
                System.out.printf("%s có chứa %d sản phẩm\n", ctl.getName(), countCatalog);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Category> readDataCatalogFromFile() {
        List<Category> listCatalogRead = null;
        File file = new File("catalog.txt");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            listCatalogRead = (List<Category>) ois.readObject();
        } catch (Exception ex) {
            listCatalogRead = new ArrayList<>();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return listCatalogRead;
    }

    public void writeDataCatalogToFile() {
        File file = new File("catalog.txt");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(lsCatalog);
            oos.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}