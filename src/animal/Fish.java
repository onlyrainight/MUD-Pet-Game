package animal;

import game.Player;
import item.Item;

public class Fish extends Animal {
    public Fish() {


        /**
         * 基本屬性、生命狀態
         * 種類、喜愛裝飾
         */
        animalType = Animal.AnimalType.FISH;
        favoriteDecoration = Item.ItemType.SEAWEED;

        /**
         * 睡眠狀態
         * 睡眠頻率、睡眠所需時間
         */
        fallAsleepTime = 8;
        wakeUpTime = 1;

        /**
         * 飢餓狀態
         * 食量、飢餓頻率、餓死步數、餵食時間、過飽狀態
         */
        hungryNeedTime = 5;
        hungryTime = hungryNeedTime;
        hungry2DieLimit = 3;
        eatableFood = Item.ItemType.FISHFOOD;

        /**
         * 排泄狀態
         * 排泄頻率、排泄數量、髒死步數
         */
        pooNeedTime = 2;
        dirty2DieLimit = 20;

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
        pregnantNeedTime = 3;

        /**
         * 產出物品頻率
         */
        dropNeedTime = 7;
    }

    @Override
    public void specialConnect(Animal mate) {

    }

    /**
     * 不會睡覺
     */
    @Override
    public Player.ActionReturn walk(){
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
                .setSellPrice(3)
                .setName("魚鱗")
                .setUsage("美麗的魚鱗，頗為值錢")
                .setType(Item.ItemType.FISHSCALE)
                .gen();
    }
}