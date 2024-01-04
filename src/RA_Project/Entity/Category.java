package RA_Project.Entity;

import RA_Project.Bussiness.ICategory;
import java.io.Serializable;
import java.util.Scanner;
import static RA_Project.Presentation.Catalogmenu.lsCatalog;

public class Category implements ICategory, Serializable {
    private int id;
    private String name;
    private String description;
    private boolean status;

    public Category() {
    }

    public Category(int id, String name, String description, boolean status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public void inputData(Scanner scanner) {
        this.id = catalogIdAuto();
        this.name = inputName(scanner);
        this.description = inputDescription(scanner);
        this.status = inputStatus(scanner);
    }

    @Override
    public void displayData() {
        System.out.printf("| %12d | %23s | %27s | %19s |\n", this.id, this.name, this.description, this.status ? "Hoạt động" : "Không hoạt động");
        System.out.println("----------------------------------------------------------------------------------------------");
    }

    @Override
    public String toString() {
        return String.format("| %12d | %23s | %27s | %19s |\n" +
                        "----------------------------------------------------------------------------------------------"
                , this.id, this.name, this.description, this.status ? "Hoạt động" : "Không hoạt động");

    }

    public int catalogIdAuto() {
        //Ma danh muc tu dong tang
        if (lsCatalog.size() == 0) {
            return 1;
        } else {
            int max = lsCatalog.get(0).getId();
            for (int i = 0; i < lsCatalog.size(); i++) {
                if (max < lsCatalog.get(i).getId()) {
                    max = lsCatalog.get(i).getId();
                }
            }
            return max + 1;
        }
    }

    public String inputName(Scanner scanner) {
        System.out.println("Nhập vào tên danh mục: ");
        do {
            try {
                String name = scanner.nextLine().trim();
                if ((name.length() >= 6) && (name.length() < 30)) {
                    boolean isExist = false;
                    for (int i = 0; i < lsCatalog.size(); i++) {
                        if (lsCatalog.get(i).getName().equalsIgnoreCase(name)) {
                            isExist = true;
                            break;
                        }
                    }
                    if (isExist) {
                        System.err.println("Tên danh mục đã tồn tại, vui lòng nhập lại!");
                    } else {
                        return name;
                    }
                } else {
                    System.err.println("Tên danh mục có từ 6-30 ký tự, vui lòng nhập lại!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (true);
    }

    public String inputDescription(Scanner scanner) {
        System.out.println("Nhập vào mô tả danh mục: ");
        do {
            try {
                String description = scanner.nextLine().trim();
                if (description != null) {
                    return description;
                } else {
                    System.err.println("Mô tả danh mục không được bỏ trống, vui lòng nhập lại!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (true);
    }

    public boolean inputStatus(Scanner scanner) {
        System.out.println("Nhập vào trạng thái danh mục: ");
        do {
            try {
                String status = scanner.nextLine();
                if (status.equals("true") || status.equals("false")) {
                    return Boolean.parseBoolean(status);
                } else {
                    System.err.println("Trạng thái danh mục chỉ nhận giá trị true hoặc false, Vui lòng nhập lại!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (true);
    }

    public void updateCatalog(Scanner scanner) {
        //Cập nhật danh mục
        boolean isExit = true;
        do {
            System.out.println("1. Cập nhật tên danh mục: ");
            System.out.println("2. Cập nhật mô tả danh mục: ");
            System.out.println("3. Cập nhật trạng thái danh mục: ");
            System.out.println("4. Thoát");
            System.out.println("Lựa chọn của bạn: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if ((choice != 1) && (choice != 2) && (choice != 3) && (choice != 4)) {
                }
            } catch (NumberFormatException ex) {
                System.err.println("Lựa chọn không phù hợp, vui lòng nhập lại!");
                ex.printStackTrace();
            }
            switch (choice) {
                case 1:
                    this.name = inputName(scanner);
                    break;
                case 2:
                    this.description = inputDescription(scanner);
                    break;
                case 3:
                    this.status = inputStatus(scanner);
                    break;
                default:
                    isExit = false;
            }
        } while (isExit);
    }
}
