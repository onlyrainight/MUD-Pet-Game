package animal;


import game.GameStaticConstantAndFunction;
import game.Player.ActionReturn;
import item.Item;

import java.util.ArrayList;

public abstract class Animal {
    /**
     * 共24屬性
     * 父類別設定10個屬性
     * 基本屬性、生命狀態
     * 名稱、種類、性別、好感度、喜愛裝飾
     */
    protected Status status;
    protected String name;
    protected AnimalType animalType;
    protected Status gender;
    protected int feeling;
    protected Item.ItemType favoriteDecoration;
    protected Item decoration;

    public enum AnimalType {
        DOG("狗勾"),
        CAT("貓貓"),
        FISH("魚兒"),
        INSECT("小蟲");

        private String description;

        AnimalType(String description) {
            this.description = description;
        }
    }

    public enum Status {
        ALIVE("我思故我在"),
        HUNGRY2DIE("餓死"),
        FULL2DIE("撐死"),
        DIRTY2DIE("髒死"),
        BORED2DIE("無聊死"),
        PLAYED2DIE("被玩死"),
        EATEN2DIE("被吃死"),
        MALE("男生"),
        FEMALE("女生"),
        STARVING("飢餓狀態"),
        FULL("吃飽囉"),
        OVERFULL("快撐死"),
        NORMAL("普通"),
        BORING("無聊中"),
        MASTER("聖人模式"),
        ESTRUS("咿咿喔喔");

        private final String description;

        Status(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    /**
     * 飢餓狀態
     * 食量、飢餓頻率、餓死步數、餵食時間、過飽狀態
     */
    protected Status satiety;
    protected int hungryTime;// 飢餓倒數
    protected int hungryNeedTime;
    protected int hungry2DieLimit;
    protected Item.ItemType eatableFood;

    /**
     * 排泄狀態
     * 排泄頻率、排泄數量、髒死步數
     */
    protected final ArrayList<Poo> pooArr;
    protected int pooNeedTime;
    protected int pooAmount;
    protected int dirty2DieLimit;

    /**
     * 無聊狀態
     * 無聊頻率、無聊死步數、
     */
    protected Status boredom;
    protected int boredTime;
    private int boredNeedTime;
    protected int bored2DieLimit;

    /**
     * 連結狀態
     * 綑綁時間、對象、發情狀態
     */
    protected int pregnantTime;
    protected int pregnantNeedTime;
    protected int connectedTime;
    protected Animal mate;
    protected Status estrus;

    /**
     * 睡眠狀態
     * 睡眠頻率、睡眠所需時間
     */
    protected int sleepTime;
    protected int fallAsleepTime;
    protected int wakeUpTime;
    protected boolean isSleep;

    /**
     * 產出物品頻率
     */
    protected int dropTime;
    protected int dropNeedTime;

    public Animal() {
        this.name = "尚未取名";
        this.status = Status.ALIVE;
        this.gender = Status.MALE;
        this.feeling = 0;
        this.satiety = Status.FULL;
        this.pooAmount = 0;
        this.boredTime = 0;
        this.boredom = Status.NORMAL;
        this.connectedTime = 0;
        this.pregnantTime = 0;
        this.estrus = Status.MASTER;
        this.dropTime = 0;
        this.sleepTime = 0;
        this.hungryTime = 0;
        this.isSleep = false;
        this.pooArr = new ArrayList<>();
    }

    /**
     * 操作飢餓、排泄
     */
    protected boolean hasEat = false;

    public ActionReturn eat(Item food) {
        // 若為睡眠狀態則不能吃
        if (isSleep) {
            return ActionReturn.SLEEPING;
        }
        // 不能吃就不能吃
        if (eatableFood != food.getType()) {
            return ActionReturn.NOT_MY_FOOD;
        }
        // 判斷不同飽食度下進行餵食動作
        switch (satiety) {// 飢餓狀態下吃
            case STARVING, NORMAL ->// 正常狀態下吃
                    hungryTime = hungryNeedTime;// 重製飢餓倒數時間為飢餓頻率
            case FULL -> {// 飽足狀態下吃 -> 進入過飽狀態
                satiety = Status.OVERFULL;
                hungryTime = 2 * hungryNeedTime;// 飢餓倒數時間重製為過飽狀態下之飢餓倒數
            }
            default -> status = Status.FULL2DIE;// 過飽狀態下吃 -> 死亡
        }
        hasEat = true;
        return ActionReturn.SUCCESSFUL;
    }

    private void hungry() {
        if (hasEat) {
            pooArr.add(new Poo(pooNeedTime));
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
                    status = Status.HUNGRY2DIE;
                }
                break;
            case NORMAL:// 正常狀態下未進食 -> 飢餓倒數為0時，進入飢餓狀態
                if (hungryTime == 0) {
                    satiety = Status.STARVING;
                }
                break;
            case FULL:// 飽足狀態(未到達飢餓頻率的1/2時(無條件捨去小數位後面數字)下未進食 -> 進入正常狀態
                if (hungryTime < hungryNeedTime / 2) {
                    satiety = Status.NORMAL;
                }
                break;
            case OVERFULL:// 過飽狀態下未進食 -> 進入飽足狀態
            default:
                if (hungryTime == hungryNeedTime) {
                    satiety = Status.FULL;
                }
        }

    }

    private void checkPoo() {
        if (pooArr.isEmpty()) {
            return;
        }
        for (Poo a : pooArr) {
            if (a.willExcrete()) {
                pooAmount++;
            }
        }
        //會重複扣好感度
        if (pooAmount > dirty2DieLimit / 2) {
            feeling--;
        }
        if (pooAmount == dirty2DieLimit) {
            pooAmount = 0;
            status = Status.DIRTY2DIE;
        }

    }

    public ActionReturn clean() {
        pooArr.clear();
        return ActionReturn.SUCCESSFUL;
    }

    /**
     * 操作連結
     * 判斷是有連結
     * 有就準備懷孕
     * 特殊連結事件
     */
    public ActionReturn connect(Animal inputMate) {
        if (this == inputMate) {
            return ActionReturn.DONT_DO_THAT;
        }
        if (this.mate != null || inputMate.mate != null) {
            return ActionReturn.MARRIED;
        }
        this.mate = inputMate;
        inputMate.mate = this;
        connectedTime = 0;
        this.mate.connectedTime = 0;
        return ActionReturn.SUCCESSFUL;
    }

    public ActionReturn disConnect() {
        if (mate == null) {
            return ActionReturn.SINGLE;
        }
        //先重置伴侶狀態
        mate.pregnantTime = 0;
        mate.mate = null;
        //再重置自身狀態
        pregnantTime = 0;
        mate = null;
        return ActionReturn.SUCCESSFUL;
    }

    public void randomEstrus() {
        if ((int) (Math.random() * 2) == 0) {
            estrus = Status.ESTRUS;
        } else {
            estrus = Status.MASTER;
        }
    }

    protected void countConnected() {
        if (mate == null) {
            return;
        }
        //有連結 時間就+1
        connectedTime += 1;
        //特殊事件
        specialConnect(mate);
    }

    private boolean canPregnant() {
        if (mate == null) {
            return false;
        }
        return animalType == mate.animalType && gender != mate.gender && gender == Status.FEMALE;
    }

    protected abstract void specialConnect(Animal mate);

    public void pregnant() {
        if (!canPregnant()) {
            return;
        }
        //睡覺不給交配，但可懷孕
        if (isSleep && pregnantTime == 0) {
            return;
        }
        if (pregnantTime == 0) {
            if (estrus == Status.ESTRUS) {
                feeling++;
            } else {
                feeling--;
            }
        }
        if (pregnantTime >= pregnantNeedTime) {
            //寵物增加
            pregnantTime = 0;
        }
        pregnantTime++;
    }

    /**
     * 操作無聊 散步
     */
    public ActionReturn walk() {
        if (isSleep) {
            return ActionReturn.SLEEPING;
        } else {
            boredTime = 0;
            return ActionReturn.SUCCESSFUL;
        }
    }

    public void bored() {
        if (isSleep) {
            return;
        }
        boredTime++;
        if (boredTime > getBoredNeedTime()) {
            boredom = Status.BORING;
        }
        if (boredTime == getBoredNeedTime() + bored2DieLimit) {
            status = Status.BORED2DIE;
        }
    }


    /**
     * 操作掉落物
     */
    public Item drop() {
        dropTime++;
        if (dropTime == dropNeedTime) {
            dropTime = 0;
            return genItem();
        }
        return null;
    }

    /**
     * 裝飾囉
     *
     * @param decoration
     * @return
     */
    public Item setDecoration(Item decoration) {
        Item.ItemType tmp = decoration.getType();
        if (tmp != Item.ItemType.CARTON && tmp != Item.ItemType.SEAWEED && tmp != Item.ItemType.WOODHOUSE) {
            return null;
        } else if (this.decoration == null) {
            this.decoration = decoration;
            return null;
        }
        Item temp = this.decoration;
        this.decoration = decoration;
        return temp;
    }

    /**
     * 操作睡覺
     */

    public void sleep() {
        sleepTime++;// 每經過1個動作，啟動睡眠時間+1

        // 判斷是否進入睡眠狀態
        isSleep = sleepTime % (fallAsleepTime + wakeUpTime) > fallAsleepTime ||
                sleepTime % (fallAsleepTime + wakeUpTime) == 0;

    }

    public void update() {
        hungry();
        checkPoo();
        bored();
        randomEstrus();
        countConnected();
        pregnant();
        sleep();
    }


    /**
     * 其他
     *
     * @return
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("種類:");
        str.append(animalType.description);
        str.append("狀態:");
        str.append(status.description);
        str.append("\t名字:");
        str.append(name);
        str.append("\t性別:");
        str.append(gender.description);
        str.append("\t\t配偶:");
        if (mate != null) {
            str.append(mate.getName());
        } else {
            str.append("無配偶");
        }
        str.append("\t掉落物:");
        str.append(genItem().getName());
        str.append("\t當前裝飾:");
        if (decoration != null) {
            str.append(decoration.getName());
        } else {
            str.append("無");
        }
        str.append("\n");
        str.append("\t飢餓狀態:");
        str.append(satiety.description);
        str.append("\t好感度:");
        str.append(feeling);
        str.append("  \t無聊:");
        str.append(boredom.description);
        str.append("\t骯髒程度:");
        str.append(pooAmount + "/" + dirty2DieLimit);
        str.append("\n");
        if (GameStaticConstantAndFunction.isDebug) {
            str.append("hungry:").append(hungryTime);
            str.append("\tpregnant:").append(pregnantTime);
            str.append("\tbored:").append(boredTime);
            str.append("\tdrop:").append(dropTime);
            str.append("\tsleep:").append(sleepTime);
            str.append("\tpoo:");
            if (!pooArr.isEmpty()) {
                for (Poo value : pooArr) {
                    str.append(value + "\t");
                }
            }
            str.append("\n");
        }
        return str.toString();
    }


    protected static class Poo {
        private int startExcrete;
        private final int excretionFrequency;

        public Poo(int excretionFrequency) {
            this.excretionFrequency = excretionFrequency;
            this.startExcrete = 0;
        }

        public boolean willExcrete() {
            startExcrete++;
            return startExcrete == excretionFrequency;
        }

        public String toString() {
            return Integer.toString(startExcrete);
        }
    }

    public void setGender(Status gender) {
        this.gender = gender;
    }

    public void translateFeeling(int x) {
        this.feeling += x;
    }

    public String getName() {
        return name;
    }

    /**
     * 因為會受好感度影響，所以要另外計算
     */
    public void setBoredNeedTime(int boredNeedTime) {
        this.boredNeedTime = boredNeedTime;
    }

    public int getBoredNeedTime() {
        if (feeling > 10) {
            return boredNeedTime + 2;
        }
        if (feeling < 0) {
            return boredNeedTime - 1;
        }
        return boredNeedTime;
    }

    public Status status() {
        return status;
    }

    public Status getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item getDecoration() {
        return decoration;
    }

    public abstract int getBuyInPrice();

    public abstract int getSellOutPrice();

    public static Status randomGender() {
        if (Math.random() > 0.5) {
            return Status.MALE;
        }
        return Status.FEMALE;
    }

    public abstract Item genItem();
}
