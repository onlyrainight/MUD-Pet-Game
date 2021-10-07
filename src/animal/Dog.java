package animal;

import item.Item;

public class Dog extends Animal {
    public Dog() {
        /**
         * 基本屬性、生命狀態
         * 種類、喜愛裝飾
         */
        animalType = Animal.AnimalType.DOG;
        favoriteDecoration = Item.ItemType.WOODHOUSE;
        /**
         * 飢餓狀態
         * 食量、飢餓頻率、餓死步數、餵食時間、過飽狀態
         */
        hungryNeedTime = 4;
        hungryTime = hungryNeedTime;
        hungry2DieLimit = 4;
        eatableFood = Item.ItemType.CANNEDFOOD;
        /**
         * 排泄狀態
         * 排泄頻率、排泄數量、髒死步數
         */
        pooNeedTime = 3;
        dirty2DieLimit = 5;
        /**
         * 無聊狀態
         * 無聊頻率、無聊死步數、
         */
        setBoredNeedTime(3);
        bored2DieLimit = 6;

        /**
         * 連結狀態
         * 綑綁時間、對象、發情狀態
         */
        pregnantNeedTime = 2;
        /**
         * 睡眠狀態
         * 睡眠頻率、睡眠所需時間
         */
        fallAsleepTime = 7;
        wakeUpTime = 2;
        /**
         * 產出物品頻率
         */
        dropNeedTime = 5;
    }


    @Override
    public void specialConnect(Animal mate) {
        switch (mate.animalType) {
            case CAT -> {
                if (statusMap.get(AnimalAction.BOREDOM) == Status.BORED || mate.statusMap.get(AnimalAction.BOREDOM) == Status.BORED) {
                    translateFeeling(1);
                }
            }
            case INSECT -> {
                if (statusMap.get(AnimalAction.BOREDOM) == Status.BORED) {
                    //昆蟲死亡
                    mate.statusMap.put(AnimalAction.LIVE, Status.PLAYED2DIE);
                }
            }
        }
    }

    @Override
    public int getBuyInPrice() {
        return 20;
    }

    @Override
    public int getSellOutPrice() {
        return 10;
    }

    @Override
    public Item genItem() {
        return new Item.Builder()
                .setSellPrice(2)
                .setName("狗毛")
                .setUsage("狗狗的毛，聽說可賣錢")
                .setType(Item.ItemType.DOGHAIR)
                .gen();
    }
}
