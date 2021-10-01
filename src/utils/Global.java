package utils;

import animals.Animal;
import items.Item;
import items.buffItems.*;
import items.decoration.*;
import items.drops.*;
import items.food.*;

public class Global {
    public final static int INITIAL_FOOD_AMOUNT = 5;
    public final static int BUYING_MAX_AMOUNT = 20;
    public static final int ITEM_MAX_AMOUNT = 20;

    public enum ItemType {
        DOGHAIR("狗毛"),
        CATHAIR("貓毛"),
        FISHSCALE("魚鱗"),
        SAWDUST("木屑"),
        CANNEDFOOD("罐頭食物"),
        FISHFOOD("魚飼料"),
        INSECTFOOD("昆蟲飼料"),
        CARTON("紙箱"),
        SEAWEED("海草"),
        WOODHOUSE("木屋"),
        BAGADDING("背包格擴充物件"),
        ANIMALROOMADDING("寵物格擴充物件");

        private String name;

        ItemType(String name) {
            this.name = name;
        }
    }

    public enum AnimalType {
        CAT,
        DOG,
        FISH,
        Insect;
    }

    public static Item genItem(ItemType itemType) {
        if (itemType == Global.ItemType.BAGADDING) {
            return new BagAdding();
        }
        if (itemType == Global.ItemType.ANIMALROOMADDING) {
            return new AnimalRoomAdding();
        }
        if (itemType == Global.ItemType.CARTON) {
            return new Carton();
        }
        if (itemType == ItemType.SEAWEED) {
            return new Seaweed();
        }
        if (itemType == ItemType.WOODHOUSE) {
            return new WoodHouse();
        }
        if (itemType == ItemType.CATHAIR) {
            return new CatHair();
        }
        if (itemType == ItemType.DOGHAIR) {
            return new DogHair();
        }
        if (itemType == ItemType.FISHSCALE) {
            return new FishScale();
        }
        if (itemType == ItemType.SAWDUST) {
            return new Seaweed();
        }
        if (itemType == ItemType.CANNEDFOOD) {
            return new CannedFood();
        }
        if (itemType == ItemType.FISHFOOD) {
            return new FishFood();
        }
        if (itemType == ItemType.INSECTFOOD) {
            return new InsectFood();
        }
        return null;
    }

    public static Animal genAnimal() {
        return null;
    }
}
