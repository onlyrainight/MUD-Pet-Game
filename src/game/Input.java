package game;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Input {

    public static Scanner sc;

    public static int genNumber(int min, int max) {
        sc = new Scanner(System.in);
        int number ;
        try {
            number = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("輸入有誤");
            return genNumber(min, max);
        }
        if (number < min || number > max) {
            System.out.println("超出範圍");
            return genNumber(min, max);
        }
        return number;
    }

    public static Player.Action act(int n){
        return Player.Action.values()[genNumber(1,7)];
    }

    public static int buyAmount() {
        return genNumber(0, Global.BUYING_MAX_AMOUNT);
    }

    public static String namingWord() {
        System.out.println("幫你的寵物取個名字吧");
        sc = new Scanner(System.in);
        String name = sc.nextLine();
        if (name.length() > Global.NAME_LIMIT) {
            System.out.printf("不可以超過%d個字喔!\n",Global.NAME_LIMIT);
            return namingWord();
        }
        int leftNumber = Global.NAME_LIMIT - name.length();
        for (int i = 0; i < leftNumber; i++) {
            name += " ";
        }
        return name;
    }

    public static void anotherDayMsg(){
        for(int i=0; i<3; i++){
            System.out.println(".");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("又平安度過一天");
    }

}
