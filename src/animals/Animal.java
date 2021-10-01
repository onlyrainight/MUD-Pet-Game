package animals;

import items.Item;
import utils.Global;

import java.util.ArrayList;

public abstract class Animal implements Walkable , Sleepable{
    /**
     * 共24屬性
     * 父類別設定10個屬性
     * 基本屬性、生命狀態
     * 名稱、種類、性別、好感度、喜愛裝飾
     */
    private Status status;
    private String name;
    private Type type;
    private Gender gender;
    private int feeling;
    private String favoriteDecoration;

    public enum Status {
        ALIVE("我思故我在"),
        HUNGERY2DIE("餓死"),
        FULL2DIE("撐死"),
        DIRTY2DIE("髒死"),
        BORED2DIE("無聊死"),
        PLAYED2DIE("被玩死"),
        BEEATED2DIE("被吃死");

        private String description;

        private Status(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
    public enum Type {
        DOG("狗勾"),
        CAT("貓貓"),
        FISH("魚兒"),
        INSECT("小蟲");

        private String description;

        private Type(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
    public enum Gender {
        MALE("男生"),
        FEMALE("女生");

        private String description;

        private Gender(String description) {
            this.description = description;
        }

        public String getDescription() {
            return this.description;
        }
    }
    /**
     * 飢餓狀態
     * 食量、飢餓頻率、餓死步數、餵食時間、過飽狀態
     */
    private Satiety satiety;
    private int consumption;
    private int hungryTime;// 飢餓倒數
    private int hungryFrequency;
    private int hungry2DieLimit;
    private Global.ItemType eatable;
    public enum Satiety {
        STARVING("飢餓狀態"),
        NORMAL("剛剛好喔"),
        FULL("飽足狀態"),
        OVERFULL("過飽狀態");
        private String description;
        private Satiety(String description) {
            this.description = description;
        }
        public String getDescription() {
            return description;
        }
    }

    /**
     * 排泄狀態
     * 排泄頻率、排泄數量、髒死步數
     */
    private ArrayList<Poo> poo;
    private int excretionFrequency;
    private int excrement;
    private int dirty2DieLimit;

    /**
     * 無聊狀態
     * 無聊頻率、無聊死步數、
     */
    private Boredom boredom;
    private int startBored;
    private int boredFrequency;
    private int bored2DieLimit;
    public enum Boredom {
        NORMAL("不無聊"),
        BORING("無聊中");

        private String description;

        private Boredom(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
    /**
     * 連結狀態
     * 綑綁時間、對象、發情狀態
     */
    private int startPregnant ;
    private int pregnantTime;
    private int connectedTime;
    private Animal mate;
    private Estrus estrus;
    public enum Connection {
        Single("偶單身"),
        Married("連結中");
        private String description;

        private Connection(String description) {
            this.description = description;
        }
        public String getDescription() {
            return description;
        }
    }
    public enum Estrus {
        Master("聖人模式"),
        ESTRUS("咿咿喔喔");

        private String description;

        private Estrus(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
    /**
     * 睡眠狀態
     * 睡眠頻率、睡眠所需時間
     */
    private int startSleep;
    private int sleepFrequency;
    private int sleepingTime;
    private boolean isSleep;

    /**
     * 產出物品頻率
     */
    private int startDrop;
    private int dropFrequency;
    private Item item;

    public Animal(String name) {
        this.name = name;
        this.status = Status.ALIVE;
        this.gender = Gender.MALE;
        this.feeling = 0;
        this.satiety = Satiety.FULL;
        this.excrement = 0;
        this.startBored = 0;
        this.boredom = Boredom.NORMAL;
        this.connectedTime = 0;
        this.startPregnant = 0;
        this.estrus = Estrus.Master;
        this.startDrop = 0;
        this.startSleep = 0;
        this.hungryTime = 0;
        this.isSleep = false;
    }

    /**
     * 操作飢餓、排泄
     */
    private boolean hasEat = false;
    public void eat(Global.ItemType itemType) {
        // 若為睡眠狀態則不能吃
        if (isSleep) {
            return;
        }
        // 不能吃就不能吃
        if(eatable != itemType){
            return;
        }
        // 判斷不同飽食度下進行餵食動作
        switch (satiety) {// 飢餓狀態下吃
            case STARVING, NORMAL ->// 正常狀態下吃
                    hungryTime = hungryFrequency;// 重製飢餓倒數時間為飢餓頻率
            case FULL -> {// 飽足狀態下吃 -> 進入過飽狀態
                satiety = Satiety.OVERFULL;
                hungryTime = 2 * hungryFrequency;// 飢餓倒數時間重製為過飽狀態下之飢餓倒數
            }
            default -> status = Status.FULL2DIE;// 過飽狀態下吃 -> 死亡
        }

        hasEat = true;
    }
    private void hungry() {
        if(hasEat == true){
            poo.add(new Poo(excretionFrequency));
            hasEat = false;
            return;
        }
        if (isSleep) {
            return;
        }
        hungryTime--;// 飢餓倒數
        // 飢餓倒數後更改狀態
        switch (satiety) {
            case STARVING:// 飢餓狀態下未進食 -> 進入飢餓狀態n個動作後，則餓死
                if (hungryTime == -(hungry2DieLimit)) {
                    status = Status.HUNGERY2DIE;
                }
                break;
            case NORMAL:// 正常狀態下未進食 -> 飢餓倒數為0時，進入飢餓狀態
                if (hungryTime == 0) {
                    satiety = Satiety.STARVING;
                }
                break;
            case FULL:// 飽足狀態(未到達飢餓頻率的1/2時(無條件捨去小數位後面數字)下未進食 -> 進入正常狀態
                if (hungryTime < hungryFrequency / 2) {
                    satiety = Satiety.NORMAL;
                }
                break;
            case OVERFULL:// 過飽狀態下未進食 -> 進入飽足狀態
            default:
                if (hungryTime == hungryFrequency) {
                    satiety = Satiety.FULL;
                }
        }

    }
    public void checkPoo(){
        for(Poo a : poo){
            if(a.willExcrete()){
                excrement ++ ;
            }
        }
        //會重複扣好感度
        if(excrement > dirty2DieLimit / 2){
            feeling -- ;
        }
        if(excrement == dirty2DieLimit){
            excrement = 0;
            status = Status.DIRTY2DIE;
        }

    }
    public void clean(){
        poo = null;
    }


    /**
     * 操作連結
     * 判斷是有連結
     * 有就準備懷孕
     * 特殊連結事件
     */
    public void connect(Animal mate){
        if(this == mate){
            return;
        }
        if(mate != null && mate.mate != null){
            return;
        }
        setMate(mate);
        connectedTime = 0;
        mate.connectedTime = 0;
    }
    public void disConnect(){
        if(mate==null){
            return;
        }
        mate.mate = null;
        mate = null;
    }
    public void randomEstrus(){
        estrus = Estrus.values()[(int)Math.random()*2];
    }
    private boolean canPregnant(Animal mate){
        if(mate == null){
            return false;
        }
        //有連結 時間就+1
        connectedTime += 1;
        //特殊事件
        specialConnect(mate);
        if(type == mate.type && gender != mate.gender && gender == Gender.FEMALE){
            return true;
        }
        return false;
    }
    public abstract void specialConnect(Animal mate);
    public void pregnant() {
        if(!canPregnant(mate)){
            return;
        }
        //睡覺不給交配，但可懷孕
        if(isSleep && startPregnant == 0){
            return;
        }
        if(startPregnant == 0){
            if(estrus == Estrus.ESTRUS){
                feeling ++;
            }else{
                feeling --;
            }
        }
        if(startPregnant >= getPregnantTime()){
            //寵物增加
            startPregnant = 0;
        }
        startPregnant ++ ;
    }

    /**
     * 操作無聊 散步
     */
    @Override
    public void walk(){
        startBored = 0;
    }
    @Override
    public void bored(){
        if(isSleep == true){
            return;
        }
        startBored ++ ;
        if(startBored > getBoredFrequency()){
            boredom = Boredom.BORING;
        }
        if(startBored == boredFrequency + bored2DieLimit){
            status = Status.BORED2DIE;
        }
    }


    /**
     * 操作掉落物
     */
    public void drop(){
        if(startDrop == dropFrequency){
            //拿到Item
//            new Item();
            startDrop = 0;
        }
        startDrop ++;
    }

    /**
     * 操作睡覺
     */

    @Override
    public void sleep() {
        startSleep++;// 每經過1個動作，啟動睡眠時間+1

        // 判斷是否進入睡眠狀態
        isSleep = startSleep % (sleepFrequency + sleepingTime) > sleepFrequency ||
                startSleep % (sleepFrequency + sleepingTime) == 0;

    }

    public void update(){
        hungry();
        checkPoo();
        bored();
        randomEstrus();
        pregnant();
        drop();
        sleep();
    };


    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("狀態:");
        str.append(status.description);
        str.append("\n名字:");
        str.append(name);
        str.append("\n性別:");
        str.append(gender.description);
        str.append("\n好感度:");
        str.append(feeling);
        str.append("\n無聊:");
        str.append(boredom);
        str.append("\n配偶:");
        str.append(mate);
        str.append("\n掉落物:");
        str.append(item);
        str.append("\n");
        return str.toString();
    }


    public void setStatus(Status status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void translateFeeling(int x) {
        this.feeling += x;
    }

    public void setFavoriteDecoration(String favoriteDecoration) {
        this.favoriteDecoration = favoriteDecoration;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }


    public void setHungryFrequency(int hungryFrequency) {
        this.hungryFrequency = hungryFrequency;
    }

    public void setHungry2DieLimit(int hungry2DieLimit) {
        this.hungry2DieLimit = hungry2DieLimit;
    }

    public void setExcretionFrequency(int excretionFrequency) {
        this.excretionFrequency = excretionFrequency;
    }

    public void setDirty2DieLimit(int dirty2DieLimit) {
        this.dirty2DieLimit = dirty2DieLimit;
    }

    public Boredom getBoredom() {
        return boredom;
    }

    public int getBoredFrequency() {
        if(feeling > 10){
            return boredFrequency + 2;
        }
        if(feeling < 0){
            return boredFrequency -1;
        }
        return boredFrequency;
    }
    public void setBoredFrequency(int boredFrequency) {
        this.boredFrequency = boredFrequency;
    }

    public void setBored2DieLimit(int bored2DieLimit) {
        this.bored2DieLimit = bored2DieLimit;
    }


    public int getPregnantTime() {
        return pregnantTime;
    }
    public void setPregnantTime(int pregnantTime) {
        this.pregnantTime = pregnantTime;
    }

    public int getConnectedTime() {
        return connectedTime;
    }

    public void setMate(Animal mate) {
        this.mate = mate;
    }

    public void setSleepFrequency(int sleepFrequency) {
        this.sleepFrequency = sleepFrequency;
    }

    public void setSleepingTime(int sleepingTime) {
        this.sleepingTime = sleepingTime;
    }

    public void setDropFrequency(int dropFrequency) {
        this.dropFrequency = dropFrequency;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Satiety getSatiety(){
        return satiety;
    }
}
