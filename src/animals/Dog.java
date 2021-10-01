package animals;

public class Dog extends Animal{
    public Dog(String name){
        super(name);
        /**
         * 基本屬性、生命狀態
         * 種類、喜愛裝飾
         */
        setType(Type.DOG);
        setFavoriteDecoration("Nothing");
        /**
         * 飢餓狀態
         * 食量、飢餓頻率、餓死步數、餵食時間、過飽狀態
         */
        setConsumption(2);
        setHungryFrequency(4);
        setHungry2DieLimit(4);
        /**
         * 排泄狀態
         * 排泄頻率、排泄數量、髒死步數
         */
        setExcretionFrequency(3);
        setDirty2DieLimit(5);
        /**
         * 無聊狀態
         * 無聊頻率、無聊死步數、
         */
        setBoredFrequency(3);
        setBored2DieLimit(6);

        /**
         * 連結狀態
         * 綑綁時間、對象、發情狀態
         */
        setPregnantTime(2);
        /**
         * 睡眠狀態
         * 睡眠頻率、睡眠所需時間
         */
        setSleepFrequency(7);
        setSleepingTime(2);
        /**
         * 產出物品頻率
         */
        setDropFrequency(5);
//        setItem(new Item());
    }



    @Override
    public void specialConnect(Animal mate) {
        switch (mate.getType()) {
            case CAT -> {
                if (getBoredom() == Boredom.BORING || mate.getBoredom() == Boredom.BORING) {
                    translateFeeling(1);
                }
            }
            case INSECT -> {
                if(getBoredom() == Boredom.BORING){
                    //昆蟲死亡
                    mate.setStatus(Status.PLAYED2DIE);
                }
            }
        }
    }
}
