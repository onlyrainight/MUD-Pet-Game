package game;

import animal.*;
import item.*;

public class Shop {
    public Shop() {
    }

    public void showItemList() {
        System.out.println("1.罐頭飼料 售價：5 用途：只能給貓及狗吃 ");
        System.out.println("2.魚飼料   \t售價：4 \t用途：只能給魚吃 ");
        System.out.println("3.昆蟲飼料  \t售價：3 \t用途：只能給昆蟲吃 ");
        System.out.println("4.紙箱   \t售價：3 \t裝飾在寵物屋中，每個寵物屋限放一個(適合寵物:狗、貓) ");
        System.out.println("5.海草   \t售價：3 \t裝飾在寵物屋中，每個寵物屋限放一個(適合寵物:魚) ");
        System.out.println("6.木屋   \t售價：3 \t裝飾在寵物屋中，每個寵物屋限放一個(適合寵物:狗、昆蟲) ");
    }

    public void showBuffItemList() {
        System.out.println("1.擴增背包格 售價：10 用途：背包格+ ");
        System.out.println("2.擴增寵物格  \t售價：10 \t用途：寵物格+ ");
    }

    public void showAnimalList() {
        System.out.println("1.狗 \t售價：20  ");
        System.out.println("2.貓 \t售價：15 ");
        System.out.println("3.魚 \t售價：10 ");
        System.out.println("4.蟲 \t售價：10 ");
    }

    public Item genBuyingItem(int choice) {
        switch (choice) {
            case 1:
                return Global.genItem(Item.ItemType.CANNEDFOOD);
            case 2:
                return Global.genItem(Item.ItemType.FISHFOOD);
            case 3:
                return Global.genItem(Item.ItemType.INSECTFOOD);
            case 4:
                return Global.genItem(Item.ItemType.CARTON);
            case 5:
                return Global.genItem(Item.ItemType.SEAWEED);
            case 6:
                return Global.genItem(Item.ItemType.WOODHOUSE);
            default:
                return null;
        }
    }

    public BufferItem genBuyingBuffItem(int choice) {
        switch (choice) {
            case 1:
                return (BufferItem) Global.genItem(Item.ItemType.BAGADDING);
            case 2:
                return (BufferItem) Global.genItem(Item.ItemType.ANIMALROOMADDING);
            default:
                return null;
        }
    }

    public Animal genBuyingAnimal(int choice) {
        switch (choice) {
            case 1:
                return Global.genAnimal(Animal.Type.DOG);
            case 2:
                return Global.genAnimal(Animal.Type.CAT);
            case 3:
                return Global.genAnimal(Animal.Type.FISH);
            case 4:
                return Global.genAnimal(Animal.Type.INSECT);
            default:
                return null;
        }
    }
}