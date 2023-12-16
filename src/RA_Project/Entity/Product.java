package RA_Project.Entity;

import RA_Project.Bussiness.IProduct;

import java.io.Serializable;
import java.util.Scanner;

import static RA_Project.Presentation.Catalogmenu.lsCatalog;
import static RA_Project.Presentation.ProductMenu.lsPro;

public class Product implements IProduct, Serializable {
    private String id;
    private String name;
    private double importPrice;
    private double exportPrice;
    private double profit;
    private String description;
    private boolean status;
    private int categoryId;

    public Product() {
    }

    public Product(String id, String name, double importPrice, double exportPrice,
                   double profit, String description, boolean status, int categoryId) {
        this.id = id;
        this.name = name;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.profit = profit;
        this.description = description;
        this.status = status;
        this.categoryId = categoryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.id = inputProId(scanner);
        this.name = inputProName(scanner);
        this.importPrice = inputImportPrice(scanner);
        this.exportPrice = inputExportPrice(scanner);
        this.description = inputDescription(scanner);
        calProfit();
        this.status = inputStatus(scanner);
        this.categoryId = inputCatalogId(scanner);
    }

    public void calProfit() {
        profit = exportPrice - importPrice;

    }

    @Override
    public void displayData() {
        System.out.printf("| %12s | %17s |   %.1f |  %.1f |    %.1f | %24s | %19s | %23s |\n", this.id, this.name, this.importPrice, this.exportPrice,
                this.profit, this.description, this.status ? "Còn hàng" : "Ngừng kinh doanh", displayCatalogNameById(categoryId));
        System.out.println("----------------------------------------------------------------------------------------------" +
                "--------------------------------------------------");
    }

    @Override
    public String toString() {
        return String.format("| %12s | %17s |   %.1f |  %.1f |    %.1f | %24s | %19s | %23s |\n" +
                        "----------------------------------------------------------------------------------------------" +
                        "--------------------------------------------------",
                this.id, this.name, this.importPrice, this.exportPrice,
                this.profit, this.description, this.status ? "Còn hàng" : "Ngừng kinh doanh", displayCatalogNameById(categoryId));
    }

    public String inputProId(Scanner scanner) {
        System.out.println("Nhập vào mã sản phẩm: ");
        do {
            try {
                String productId = scanner.nextLine();
                if (productId.length() == 4) {
                    if (productId.charAt(0) == 'P') {
                        boolean isExit = false;
                        for (int i = 0; i < lsPro.size(); i++) {
                            if (lsPro.get(i).getId().equals(productId)) {
                                isExit = true;
                                break;
                            }
                        }
                        if (isExit) {
                            System.err.println("Mã sản phẩm đã tồn tại, vui lòng nhập lại!");
                        } else {
                            return productId;
                        }
                    } else {
                        System.err.println("Mã sản phẩm bắt đầu là P, Vui lòng nhập lại!");
                    }
                } else {
                    System.err.println("Mã sản phẩm gồm 4 ký tự, vui lòng nhập lại!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (true);
    }

    public String inputProName(Scanner scanner) {
        System.out.println("Nhập vào tên sản phẩm: ");
        do {
            try {
                String productName = scanner.nextLine().trim();
                if ((productName.length() >= 6) && (productName.length() < 30)) {
                    boolean isExist = false;
                    for (int i = 0; i < lsPro.size(); i++) {
                        if (lsPro.get(i).getName().equalsIgnoreCase(productName)) {
                            isExist = true;
                            break;
                        }
                    }
                    if (isExist) {
                        System.err.println("Tên sản phẩm đã tồn tại, vui lòng nhập lại!");
                    } else {
                        return productName;
                    }
                } else {
                    System.err.println("Tên sản phẩm có từ 6-30 ký tự, vui lòng nhập lại!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (true);
    }

    public double inputImportPrice(Scanner scanner) {
        System.out.println("Nhập giá nhập sản phẩm: ");
        do {
            try {
                double importPrice = Double.parseDouble(scanner.nextLine());
                if (importPrice > 0) {
                    return importPrice;
                } else {
                    System.err.println("Giá nhập phải có giá trị lớn hơn 0, vui lòng nhập lại!");
                }
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        } while (true);
    }

    public double inputExportPrice(Scanner scanner) {
        System.out.println("Nhập giá bán sản phẩm: ");
        do {
            try {
                double exportPrice = Double.parseDouble(scanner.nextLine());
                if (exportPrice > 0) {
                    if (exportPrice >= importPrice * (1 + MIN_INTEREST_RATE)) {
                        return exportPrice;
                    } else {
                        System.err.println("Giá bán phải lớn hơn giá nhập 0.2 lần, vui lòng nhập lại!");
                    }
                } else {
                    System.err.println("Giá nhập phải có giá trị lớn hơn 0, vui lòng nhập lại!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (true);
    }

    public String inputDescription(Scanner scanner) {
        System.out.println("Nhập vào mô tả sản phẩm: ");
        do {
            try {
                String description = scanner.nextLine().trim();
                if (description != null) {
                    return description;
                } else {
                    System.err.println("Mô tả sản phẩm không được bỏ trống, vui lòng nhập lại!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (true);
    }

    public boolean inputStatus(Scanner scanner) {
        System.out.println("Nhập vào trạng thái sản phẩm: ");
        do {
            try {
                String status = scanner.nextLine();
                if (status.equals("true") || status.equals("false")) {
                    return Boolean.parseBoolean(status);
                } else {
                    System.err.println("Trạng thái sản phẩm chỉ nhận giá trị true hoặc false, Vui lòng nhập lại!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (true);
    }

    public int inputCatalogId(Scanner scanner) {
        System.out.println("Chọn danh mục của sản phẩm: ");
        do {
            for (int i = 0; i < lsCatalog.size(); i++) {
                boolean isxStatus = false;
                if (!lsCatalog.get(i).isStatus() == isxStatus) {
                    System.out.printf("%d.%s\n", i + 1, lsCatalog.get(i).getName());
                } else {
                    System.err.println("Danh mục của sản phẩm không khả dụng!");
                }
            }
            System.out.println("Lựa chọn của bạn: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                return lsCatalog.get(choice - 1).getId();
            } catch (NumberFormatException ex) {
                System.err.println("Danh mục không tồn tại!");
            }
        } while (true);
    }

    public String displayCatalogNameById(int categoryId) {
        for (Category ctl : lsCatalog) {
            if (ctl.getId() == categoryId) {
                return ctl.getName();
            }
        }
        return " ";
    }

    public void updateProduct(Scanner scanner) {
        //Cập nhật sản phẩm
        boolean isExit = true;
        do {
            System.out.println("1. Cập nhật tên sản phẩm: ");
            System.out.println("2. Cập nhật giá bán");
            System.out.println("3. Cập nhật mô tả sản phẩm: ");
            System.out.println("4. Cập nhật trạng thái sản phẩm: ");
            System.out.println("5. Cập nhật mã danh mục sản phẩm:");
            System.out.println("6. Thoát");
            System.out.println("Lựa chọn của bạn: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if ((choice != 1) && (choice != 2) && (choice != 3) && (choice != 4) && (choice != 5) && (choice != 6)) {
                }
            } catch (NumberFormatException ex) {
                System.err.println("Lựa chọn không phù hợp, vui lòng nhập lại!");
                ex.printStackTrace();
            }
            switch (choice) {
                case 1:
                    this.name = inputProName(scanner);
                    break;
                case 2:
                    this.exportPrice = inputExportPrice(scanner);
                    break;
                case 3:
                    this.description = inputDescription(scanner);
                    break;
                case 4:
                    this.status = inputStatus(scanner);
                    break;
                case 5:
                    this.categoryId = inputCatalogId(scanner);
                    break;
                default:
                    isExit = false;
            }
        } while (isExit);
    }
}