package animal;

import game.Player;
import item.Item;

public class Fish extends Animal {
    public Fish() {


        /**
         * 基本屬性、生命狀態
         * 種類、喜愛裝飾
         */
        setType(Animal.Type.FISH);
        setFavoriteDecoration(Item.ItemType.SEAWEED);

        /**
         * 睡眠狀態
         * 睡眠頻率、睡眠所需時間
         */
        setSleepFrequency(8);
        setSleepingTime(1);

        /**
         * 飢餓狀態
         * 食量、飢餓頻率、餓死步數、餵食時間、過飽狀態
         */
        setConsumption(1);
        setHungryFrequency(5);
        setHungryTime(getHungryFrequency());
        setHungry2DieLimit(3);

        /**
         * 排泄狀態
         * 排泄頻率、排泄數量、髒死步數
         */
        setExcretionFrequency(2);
        setDirty2DieLimit(20);
        setEatable(Item.ItemType.FISHFOOD);

        /**
         * 無聊狀態
         * 無聊頻率、無聊死步數、
         */
        setBoredFrequency(-1);
        setBored2DieLimit(-1);

        /**
         * 連結狀態
         * 綑綁時間、對象、發情狀態
         */
        setPregnantTime(3);

        /**
         * 產出物品頻率
         */
        setDropFrequency(7);
        setItemList(Item.ItemType.FISHSCALE);
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
}