package animal;

import game.Player;
import item.Item;

public class Insect extends Animal {
    public Insect() {

        /**
         * 基本屬性、生命狀態
         * 種類、喜愛裝飾
         */
        animalType = Animal.AnimalType.INSECT;
        favoriteDecoration = Item.ItemType.CARTON;

        /**
         * 睡眠狀態
         * 睡眠頻率、睡眠所需時間
         */
        fallAsleepTime = -1;
        wakeUpTime = -1;

        /**
         * 飢餓狀態
         * 食量、飢餓頻率、餓死步數、餵食時間、過飽狀態
         */
        hungryNeedTime = 4;
        hungryTime = hungryNeedTime;
        hungry2DieLimit = 10;
        eatableFood = Item.ItemType.INSECTFOOD;

        /**
         * 排泄狀態
         * 排泄頻率、排泄數量、髒死步數
         */
        pooNeedTime = 1;
        dirty2DieLimit = 30;

        /**
         * 無聊狀態
         * 無聊頻率、無聊死步數、
         */
        setBoredNeedTime(-1);
        bored2DieLimit = -1;

        /**
         * 連結狀態
         * 綑綁時間、對象、發情狀態
         */
        pregnantNeedTime = 2;

        /**
         * 產出物品頻率
         */
        dropNeedTime = 4;
    }

    @Override
    public void specialConnect(Animal mate) {
    }

    public void sleep() {

    }

    @Override
    public Player.ActionReturn walk() {
        return Player.ActionReturn.CANT_WALK;
    }

    @Override
    public int getBuyInPrice() {
        return 10;
    }

    @Override
    public int getSellOutPrice() {
        return 5;
    }


    @Override
    public Item genItem() {
        return new Item.Builder()
                .setSellPrice(1)
                .setName("木屑")
                .setUsage("蟲蟲吃剩的，可賣錢")
                .setType(Item.ItemType.SAWDUST)
                .gen();
    }
}