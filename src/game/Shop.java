package game;

import animal.*;
import item.*;

public class Shop {

    public static void showItemList() {
        System.out.println("1.罐頭飼料 售價：5 用途：只能給貓及狗吃 ");
        System.out.println("2.魚飼料   \t售價：4 \t用途：只能給魚吃 ");
        System.out.println("3.昆蟲飼料  \t售價：3 \t用途：只能給昆蟲吃 ");
        System.out.println("4.紙箱   \t售價：3 \t裝飾在寵物屋中，每個寵物屋限放一個(適合寵物:狗、貓) ");
        System.out.println("5.海草   \t售價：3 \t裝飾在寵物屋中，每個寵物屋限放一個(適合寵物:魚) ");
        System.out.println("6.木屋   \t售價：3 \t裝飾在寵物屋中，每個寵物屋限放一個(適合寵物:狗、昆蟲) ");
    }

    public static void showBuffItemList() {
        System.out.println("1.擴增背包格 售價：10 用途：背包格+ ");
        System.out.println("2.擴增寵物格  \t售價：10 \t用途：寵物格+ ");
    }

    public static void showAnimalList() {
        System.out.println("1.狗 \t售價：20  ");
        System.out.println("2.貓 \t售價：15 ");
        System.out.println("3.魚 \t售價：10 ");
        System.out.println("4.蟲 \t售價：10 ");
    }

    public static Item genBuyingItem(Item.ItemType itemType) {
        return switch (itemType) {
            case CANNEDFOOD -> new Item.Builder()
                    .setBuyPrice(5)
                    .setSellPrice(2)
                    .setName("罐頭飼料")
                    .setUsage("只能給貓及狗吃")
                    .setType(Item.ItemType.CANNEDFOOD)
                    .gen();
            case FISHFOOD -> new Item.Builder()
                    .setBuyPrice(4)
                    .setSellPrice(1)
                    .setName("魚飼料")
                    .setUsage("只能給魚吃")
                    .setType(Item.ItemType.FISHFOOD)
                    .gen();
            case INSECTFOOD -> new Item.Builder()
                    .setBuyPrice(4)
                    .setSellPrice(1)
                    .setName("昆蟲飼料")
                    .setUsage("只能給昆蟲吃")
                    .setType(Item.ItemType.INSECTFOOD)
                    .gen();
            case CARTON -> new Item.Builder()
                    .setBuyPrice(3)
                    .setSellPrice(1)
                    .setName("紙箱")
                    .setUsage("裝飾在寵物屋中，每個" +
                            "寵物屋限放一個")
                    .setType(Item.ItemType.CARTON)
                    .gen();
            case SEAWEED -> new Item.Builder()
                    .setBuyPrice(3)
                    .setSellPrice(1)
                    .setName("海草")
                    .setUsage("裝飾在寵物屋中，每個" +
                            "寵物屋限放一個")
                    .setType(Item.ItemType.SEAWEED)
                    .gen();
            case WOODHOUSE -> new Item.Builder()
                    .setBuyPrice(4)
                    .setSellPrice(2)
                    .setName("木屋")
                    .setUsage("裝飾在寵物屋中，每個" +
                            "寵物屋限放一個")
                    .setType(Item.ItemType.WOODHOUSE)
                    .gen();
            default -> null;
        };
    }

    public static BufferItem genBuyingBuffItem(Item.ItemType buffItemType) {
        return switch (buffItemType) {
            case BAGADDING -> new BufferItem(new Item.Builder()
                    .setBuyPrice(10)
                    .setName("擴增背包格")
                    .setUsage("背包格+1")
                    .setType(Item.ItemType.BAGADDING), 1
            );
            case ANIMALROOMADDING -> new BufferItem(new Item.Builder()
                    .setBuyPrice(10)
                    .setName("擴增寵物格")
                    .setUsage("寵物格+1")
                    .setType(Item.ItemType.ANIMALROOMADDING), 1
            );
            default -> null;
        };
    }

    public static Animal genBuyingAnimal(Animal.AnimalType animalType) {
        return switch (animalType) {
            case DOG -> new Dog();
            case CAT -> new Cat();
            case FISH -> new Fish();
            case INSECT -> new Insect();
            default -> null;
        };
    }
}