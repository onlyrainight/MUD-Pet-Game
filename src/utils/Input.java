package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {

    public static Scanner sc = new Scanner(System.in);

    public static int genNumber(int min, int max) {
        int number = 0;
        try {
            number = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("輸入有誤");
            return genNumber(min, max);
        }
        if (number < min || number > max) {
            System.out.println("輸入有誤");
            return genNumber(min, max);
        }
        return number;
    }

    public static int buyAmount() {
        return genNumber(0,Global.BUYING_MAX_AMOUNT);
    }
}
