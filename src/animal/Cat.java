package animal;

import game.Shop;
import item.Item;

public class Cat extends Animal {
    public Cat() {
        /**
         * 基本屬性、生命狀態
         * 種類、喜愛裝飾
         */
        animalType = Animal.AnimalType.CAT;
        favoriteDecoration = Item.ItemType.CARTON;

        /**
         * 睡眠狀態
         * 睡眠頻率、睡眠所需時間
         */
        fallAsleepTime = 8;
        wakeUpTime = 4;

        /**
         * 飢餓狀態
         * 食量、飢餓頻率、餓死步數、餵食時間、過飽狀態
         */
        hungryNeedTime = 3;
        hungryTime = hungryNeedTime;
        hungry2DieLimit = 3;
        eatableFood = Item.ItemType.CANNEDFOOD;

        /**
         * 排泄狀態
         * 排泄頻率、排泄數量、髒死步數
         */
        pooNeedTime = 5;
        dirty2DieLimit = 3;

        /**
         * 無聊狀態
         * 無聊頻率、無聊死步數、
         */
        setBoredNeedTime(10);
        bored2DieLimit = 9;

        /**
         * 連結狀態
         * 綑綁時間、對象、發情狀態
         */
        pregnantNeedTime = 3;

        /**
         * 產出物品頻率
         */
        dropNeedTime = 6;
    }

    @Override
    public void specialConnect(Animal mate) {
        if (mate == null) {
            return;
        }
        switch (mate.animalType) {
            // 貓跟狗放在一起，不論是狗或者貓進入無聊狀態時，好感度都會 -1
            case DOG -> {
                if (statusMap.get(AnimalAction.BOREDOM) == Status.BORED || mate.statusMap.get(AnimalAction.BOREDOM) == Status.BORED) {
                    translateFeeling(-1);
                }
            }
            // 貓跟魚放在一起，當貓處於飢餓狀態時，則魚會死亡，貓會被餵食一次
            case FISH -> {
                if (statusMap.get(AnimalAction.EAT) == Status.STARVING) {
                    mate.statusMap.put(AnimalAction.LIVE, Status.EATEN2DIE);// 魚被吃死
                    eat(Shop.genBuyingItem(Item.ItemType.CANNEDFOOD));// 貓被餵食
                }
            }
            // 當貓與昆蟲放在一起時，當貓處於無聊狀態，則昆蟲會死亡，貓會被散步一次
            case INSECT -> {
                if (statusMap.get(AnimalAction.BOREDOM) == Status.BORED) {
                    mate.statusMap.put(AnimalAction.LIVE, Status.PLAYED2DIE);// 昆蟲死亡
                    walk();// 貓散步
                }
            }
        }
    }

    @Override
    public int getBuyInPrice() {
        return 15;
    }

    @Override
    public int getSellOutPrice() {
        return 7;
    }

    @Override
    public Item genItem() {
        return new Item.Builder()
                .setSellPrice(1)
                .setName("貓毛")
                .setUsage("貓咪的毛，聽說可賣錢")
                .setType(Item.ItemType.CATHAIR)
                .gen();
    }
}