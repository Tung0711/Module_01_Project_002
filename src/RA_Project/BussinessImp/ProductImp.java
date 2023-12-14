package RA_Project.BussinessImp;

import RA_Project.Entity.Product;

import java.io.*;
import java.util.*;

import static RA_Project.Presentation.ProductMenu.lsPro;

public class ProductImp {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public void inputDataProduct(Scanner scanner) {
        System.out.println("Nhập vào số lượng sản phẩm cần nhập thông tin:");
        int number = 0;
        try {
            number = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            System.err.println("Số lượng sản phẩm là số nguyên, vui lòng nhập lại!");
        }
        for (int i = 0; i < number; i++) {
            Product pro = new Product();
            pro.inputData(scanner);
            lsPro.add(pro);
        }
    }

    public void displayDataProduct() {
        formatPrintPro();
        for (int i = 0; i < lsPro.size(); i++) {
            lsPro.get(i).displayData();
        }
    }

    public void updateProById(Scanner scanner) {
        System.out.println("Nhập mã sản phẩm cần cập nhật:");
        try {
            String proIdUpdate = scanner.nextLine();
            boolean find = false;
            for (int i = 0; i < lsPro.size(); i++) {
                Product pro = lsPro.get(i);
                if (pro.getId().equals(proIdUpdate)) {
                    pro.updateProduct(scanner);
                    System.out.println("Thông tin sản phẩm đã được cập nhật thành công!");
                    find = true;
                    break;
                }
            }
            if (!find) {
                System.err.println("Mã sản phẩm không tồn tại, vui lòng nhập lại!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePro(Scanner scanner) {
        System.out.println("Nhập vào mã sản phẩm cần xóa:");
        try {
            String proIdDelete = scanner.nextLine();
            boolean isDelete = false;
            for (Product product : lsPro) {
                if (product.getId().equals(proIdDelete)) {
                    lsPro.remove(product);
                    isDelete = true;
                    System.out.println("Xóa sản phẩm thành công!");
                    break;
                }
            }
            if (!isDelete) {
                System.err.println("Mã sản phẩm không tồn tại, vui lòng nhập lại!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void formatPrintPro() {
        System.out.println("----------------------------------------------------------------------------------------------" +
                "--------------------------------------------------");
        System.out.println(ANSI_GREEN + "| Mã sản phẩm  |   Tên sản phẩm    | Giá nhập | Giá bán | Lợi nhuận | " +
                "     Mô tả sản phẩm      | Trạng thái sản phẩm |        Danh mục         |" + ANSI_RESET);
        System.out.println("----------------------------------------------------------------------------------------------" +
                "--------------------------------------------------");
    }

    public void sortProbyName() {
        System.out.println("Sắp xếp sản phẩm theo tên từ A-Z");
        try {
            formatPrintPro();
            lsPro.stream().sorted(Comparator.comparing(Product::getName)).forEach(System.out::println);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void sortProByProfit() {
        System.out.println("Sắp xếp sản phẩm theo lợi nhuận giảm dần");
        try {
            formatPrintPro();
            lsPro.stream().sorted(Comparator.comparing(Product::calProfit).reversed()).forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void findPro(Scanner scanner) {
        System.out.println("Nhập vào tên sản phẩm: ");
        try {
            String findProductName = scanner.nextLine();
            boolean isFindPro = lsPro.stream().anyMatch(product -> product.getName().equalsIgnoreCase(findProductName));
            if (!isFindPro) {
                System.err.println("Sản phẩm không tồn tại!");
            } else {
                formatPrintPro();
                lsPro.stream().filter(product -> product.getName().equalsIgnoreCase(findProductName)).forEach(System.out::println);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static List<Product> readDataProductFromFile() {
        List<Product> listProRead = null;
        File file = new File("product.txt");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            listProRead = (List<Product>) ois.readObject();
        } catch (Exception ex) {
            listProRead = new ArrayList<>();
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
        return listProRead;
    }

    public void writeDataProductToFile() {
        File file = new File("product.txt");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(lsPro);
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
