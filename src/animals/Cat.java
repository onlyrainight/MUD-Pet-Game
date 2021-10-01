package animals;

import items.Item;

public class Cat extends Animal {
    public Cat(String name) {
        super(name);

        /**
         * 基本屬性、生命狀態
         * 種類、喜愛裝飾
         */
        setType(Type.CAT);
        setFavoriteDecoration("Nothing");

        /**
         * 睡眠狀態
         * 睡眠頻率、睡眠所需時間
         */
        setSleepFrequency(8);
        setSleepingTime(4);

        /**
         * 飢餓狀態
         * 食量、飢餓頻率、餓死步數、餵食時間、過飽狀態
         */
        setConsumption(1);
        setHungryFrequency(3);
        setHungry2DieLimit(3);

        /**
         * 排泄狀態
         * 排泄頻率、排泄數量、髒死步數
         */
        setExcretionFrequency(5);
        setDirty2DieLimit(3);

        /**
         * 無聊狀態
         * 無聊頻率、無聊死步數、
         */
        setBoredFrequency(10);
        setBored2DieLimit(9);

        /**
         * 連結狀態
         * 綑綁時間、對象、發情狀態
         */
        setPregnantTime(3);

        /**
         * 產出物品頻率
         */
        setDropFrequency(6);
//        setItem(new Item());
    }

    @Override
    public void specialConnect(Animal mate) {
        if (mate == null) {
            return;
        }
        switch (mate.getType()) {
            // 貓跟狗放在一起，不論是狗或者貓進入無聊狀態時，好感度都會 -1
            case DOG -> {
                if (getBoredom() == Boredom.BORING || mate.getBoredom() == Boredom.BORING) {
                    translateFeeling(-1);
                }
            }
            // 貓跟魚放在一起，當貓處於飢餓狀態時，則魚會死亡，貓會被餵食一次
            case FISH -> {
                if (getSatiety() == Satiety.STARVING) {
                    mate.setStatus(Status.BEEATED2DIE);// 魚被吃死
//                    eat();// 貓被餵食
                }
            }
            // 當貓與昆蟲放在一起時，當貓處於無聊狀態，則昆蟲會死亡，貓會被散步一次
            case INSECT -> {
                if (getBoredom() == Boredom.BORING) {
                    mate.setStatus(Status.PLAYED2DIE);// 昆蟲死亡
                    walk();// 貓散步
                }
            }
        }
    }
}