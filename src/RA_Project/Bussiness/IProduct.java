package RA_Project.Bussiness;

import java.util.Scanner;

public interface IProduct {
    float MIN_INTEREST_RATE = 0.2F;

    void inputData(Scanner scanner);

    void displayData();

    double calProfit();
}