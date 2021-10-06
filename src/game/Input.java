package game;

import animal.Animal;
import item.Item;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Input {

    public static Scanner sc;

    public static int genNumber(int min, int max) {
        sc = new Scanner(System.in);
        int number;
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

    public static int buyAmount() {
        return genNumber(0, GameStaticConstantAndFunction.BUYING_MAX_AMOUNT);
    }

    public static String namingWord() {
        System.out.println("幫你的寵物取個名字吧");
        sc = new Scanner(System.in);
        StringBuilder name = new StringBuilder(sc.nextLine());
        if (name.length() > GameStaticConstantAndFunction.NAME_LIMIT) {
            System.out.printf("不可以超過%d個字喔!\n", GameStaticConstantAndFunction.NAME_LIMIT);
            return namingWord();
        }
        int leftNumber = GameStaticConstantAndFunction.NAME_LIMIT - name.length();
        name.append(" ".repeat(Math.max(0, leftNumber)));
        return name.toString();
    }

    public static Item.ItemType genItemType() {
        int choice = Input.genNumber(1,6);
        return switch (choice) {
            case 1 -> Item.ItemType.CANNEDFOOD;
            case 2 -> Item.ItemType.FISHFOOD;
            case 3 -> Item.ItemType.INSECTFOOD;
            case 4 -> Item.ItemType.CARTON;
            case 5 -> Item.ItemType.SEAWEED;
            case 6 -> Item.ItemType.WOODHOUSE;
            default -> null;
        };
    }

    public static Item.ItemType genBuffItemType() {
        int choice = Input.genNumber(1,2);
        return switch (choice) {
            case 1 -> Item.ItemType.BAGADDING;
            case 2 -> Item.ItemType.ANIMALROOMADDING;
            default -> null;
        };
    }

    public static Animal.AnimalType genAnimalType() {
        int choice = Input.genNumber(1,4);
        return switch (choice) {
            case 1 -> Animal.AnimalType.DOG;
            case 2 -> Animal.AnimalType.CAT;
            case 3 -> Animal.AnimalType.FISH;
            case 4 -> Animal.AnimalType.INSECT;
            default -> null;
        };
    }

}
