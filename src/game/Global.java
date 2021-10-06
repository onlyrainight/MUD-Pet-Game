package game;

import animal.*;
import item.*;


public class Global {
    public final static int INITIAL_FOOD_AMOUNT = 5;
    public final static int BUYING_MAX_AMOUNT = 20;
    public static final int ITEM_MAX_AMOUNT = 20;
    public final static int NAME_LIMIT = 5;
    public final static int INITIAL_MONEY = 100;
    public static boolean isDebug = true;

    public static Item genItem(Item.ItemType type) {

        if (type == Item.ItemType.BAGADDING) {
            return new BufferItem(new Item.Builder()
                    .setBuyPrice(10)
                    .setName("擴增背包格")
                    .setUsage("背包格+1")
                    .setType(Item.ItemType.BAGADDING), 1
            );
        }
        if (type == Item.ItemType.ANIMALROOMADDING) {
            return new BufferItem(new Item.Builder()
                    .setBuyPrice(10)
                    .setName("擴增寵物格")
                    .setUsage("寵物格+1")
                    .setType(Item.ItemType.ANIMALROOMADDING), 1
            );
        }
        if (type == Item.ItemType.CARTON) {
            return new Item.Builder()
                    .setBuyPrice(3)
                    .setSellPrice(1)
                    .setName("紙箱")
                    .setUsage("裝飾在寵物屋中，每個" +
                            "寵物屋限放一個")
                    .setType(Item.ItemType.CARTON)
                    .gen();
        }
        if (type == Item.ItemType.SEAWEED) {
            return new Item.Builder()
                    .setBuyPrice(3)
                    .setSellPrice(1)
                    .setName("海草")
                    .setUsage("裝飾在寵物屋中，每個" +
                            "寵物屋限放一個")
                    .setType(Item.ItemType.SEAWEED)
                    .gen();
        }
        if (type == Item.ItemType.WOODHOUSE) {
            return new Item.Builder()
                    .setBuyPrice(4)
                    .setSellPrice(2)
                    .setName("木屋")
                    .setUsage("裝飾在寵物屋中，每個" +
                            "寵物屋限放一個")
                    .setType(Item.ItemType.WOODHOUSE)
                    .gen();
        }
        if (type == Item.ItemType.CATHAIR) {
            return new Item.Builder()
                    .setSellPrice(1)
                    .setName("貓毛")
                    .setUsage("貓咪的毛，聽說可賣錢")
                    .setType(Item.ItemType.CATHAIR)
                    .gen();
        }
        if (type == Item.ItemType.DOGHAIR) {
            return new Item.Builder()
                    .setSellPrice(2)
                    .setName("狗毛")
                    .setUsage("狗狗的毛，聽說可賣錢")
                    .setType(Item.ItemType.DOGHAIR)
                    .gen();
        }
        if (type == Item.ItemType.FISHSCALE) {
            return new Item.Builder()
                    .setSellPrice(3)
                    .setName("魚鱗")
                    .setUsage("美麗的鱗片，頗為值錢")
                    .setType(Item.ItemType.FISHSCALE)
                    .gen();
        }
        if (type == Item.ItemType.SAWDUST) {
            return new Item.Builder()
                    .setSellPrice(1)
                    .setName("木屑")
                    .setUsage("蟲蟲吃剩的，可賣錢")
                    .setType(Item.ItemType.SAWDUST)
                    .gen();
        }
        if (type == Item.ItemType.CANNEDFOOD) {
            return new Item.Builder()
                    .setBuyPrice(5)
                    .setSellPrice(2)
                    .setName("罐頭飼料")
                    .setUsage("只能給貓及狗吃")
                    .setType(Item.ItemType.CANNEDFOOD)
                    .gen();
        }
        if (type == Item.ItemType.FISHFOOD) {
            return new Item.Builder()
                    .setBuyPrice(4)
                    .setSellPrice(1)
                    .setName("魚飼料")
                    .setUsage("只能給魚吃")
                    .setType(Item.ItemType.FISHFOOD)
                    .gen();

        }
        if (type == Item.ItemType.INSECTFOOD) {
            return new Item.Builder()
                    .setBuyPrice(4)
                    .setSellPrice(1)
                    .setName("昆蟲飼料")
                    .setUsage("只能給昆蟲吃")
                    .setType(Item.ItemType.INSECTFOOD)
                    .gen();
        }
        return null;
    }

    public static Animal genAnimal(Animal.Type type) {
        if (type == Animal.Type.DOG) {
            return new Dog();
        }
        if (type == Animal.Type.CAT) {
            return new Cat();
        }
        if (type == Animal.Type.FISH) {
            return new Fish();
        }
        if (type == Animal.Type.INSECT) {
            return new Insect();
        }
        return null;
    }


}
