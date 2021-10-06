package game;

import animal.*;
import item.*;

import java.util.ArrayList;


public class Player {
    private ArrayList<Item> bag;
    private ArrayList<Animal> animalRoom;
    private int bagMax;
    private int animalRoomMax;
    private int money;

    public enum ActionReturn {
        TO_MENU("回選單"),
        SUCCESSFUL("Done!"),
        NO_MONEY("大哥 你錢不夠"),
        NO_SPACE("沒有空間囉!"),
        NO_ITEM("背包無物品!"),
        NO_AMOUNT("數量不足!"),

        SLEEPING("拜託讓我睡~"),
        NO_ANIMAL("你沒有此寵物"),
        DEAD_ANIMAL("我幫你送到殯葬場，有錢喔"),
        CANT_WALK("散步不適合我"),
        NOT_MY_FOOD("我不吃謝謝"),
        DONT_DO_THAT("我不要一個人孤獨終老"),
        MARRIED("已婚"),
        SINGLE("單身中"),
        NO_DROP("沒掉寶QQ"),
        BagFull_Discard("背包已滿，物品已被丟棄"),
        DROP("有掉寶"),

        Not_Decoration("這不是裝飾品喔"),
        TimeGoesBy("\n"),
        Bug("Bug");
        public String description;

        private ActionReturn(String description) {
            this.description = description;
        }
    }

    public enum Action {
        TO_MENU,
        WALK,
        FEED,
        CLEAN,
        CONNECT,
        DISCONNECT,
        DECORATE,
        NEXT,
    }

    public Player() {
        money = Global.INITIAL_MONEY;
        bag = new ArrayList<Item>();
        animalRoom = new ArrayList<>();
        bagMax = 10;
        animalRoomMax = 10;
    }

    //////BUY ITEM////////

    /**
     * 購買道具
     *
     * @param item
     * @param amount
     * @return
     */
    public ActionReturn buyItem(Item item, int amount) {
        if (!isBagEnough(item, amount)) {
            return ActionReturn.NO_SPACE;
        }
        if (!isMoneyEnough(money, item, amount)) {
            return ActionReturn.NO_MONEY;
        }
        addItem(item, amount);
        costMoney(howMuch(item, amount));
        return ActionReturn.SUCCESSFUL;
    }

    /**
     * 檢查是否有重複的道具
     *
     * @param addingItem
     * @return 有就回傳，沒有就傳null(滿了一樣算null)
     */
    private Item theSameItem(Item addingItem) {
        for (Item item : bag) {
            if (item.getType() == addingItem.getType()) {
                if (item.getAmount() < Global.ITEM_MAX_AMOUNT) { //如有且還可堆疊，就把重複的拿出來，沒有就回傳參數
                    return item;
                }
            }
        }
        return null;
    }

    /**
     * 檢查是否夠放道具
     *
     * @return
     */
    private boolean isBagEnough(Item addingItem, int amount) {
        Item theSameItem = theSameItem(addingItem);
        if (theSameItem == null) { //新物品
            if (bag.size() + 1 <= bagMax) {
                return true;
            }
        } else { //舊有物品
            if (theSameItem.getAmount() + amount > 20) {
                if (bag.size() + 1 <= bagMax) {
                    return true;
                }
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 檢查錢是否足夠購買的總金額
     *
     * @param money  player持有的錢
     * @param item   購買的物品
     * @param amount 購買數量
     * @return
     */
    private boolean isMoneyEnough(int money, Item item, int amount) {
        if (money - (item.getBuyPrice() * amount) < 0) {
            return false;
        }
        return true;
    }

    /**
     * 放入物品
     *
     * @param addingItem
     * @param amount
     */
    public ActionReturn addItem(Item addingItem, int amount) {
        Item theSameItem = theSameItem(addingItem);
        if (!isBagEnough(addingItem, amount)) {
            return ActionReturn.NO_SPACE;
        }
        if (theSameItem == null) {
            addingItem.addAmount(amount);
            bag.add(addingItem);
            return ActionReturn.SUCCESSFUL;
        } else {
            if (theSameItem.getAmount() + amount <= Global.ITEM_MAX_AMOUNT) {
                theSameItem.addAmount(amount);
                return ActionReturn.NO_SPACE;
            } else {
                int remaining = amount - (Global.ITEM_MAX_AMOUNT - theSameItem.getAmount());
                theSameItem.setAmount(Global.ITEM_MAX_AMOUNT);
                addingItem.addAmount(remaining);
                bag.add(addingItem);
                return ActionReturn.SUCCESSFUL;
            }
        }
    }


    //////BUY ANIMAL////////

    /**
     * 購買寵物
     *
     * @param animal
     */
    public ActionReturn buyAnimal(Animal animal) {
        if (money < animal.getBuyInPrice()) {
            return ActionReturn.NO_MONEY;
        }
        if (addAnimal(animal)) {
            costMoney(animal.getBuyInPrice());
            animal.setName(Input.namingWord());
            return ActionReturn.SUCCESSFUL;
        }
        return ActionReturn.NO_SPACE;
    }

    /**
     * 放入寵物
     *
     * @return
     */
    public boolean addAnimal(Animal animal) {
        if (animalRoom.size() < animalRoomMax) {
            animalRoom.add(animal);
            return true;
        }
        return false;
    }

    /**
     * 購買加成道具
     */
    public ActionReturn buyBuffItem(BufferItem buffItem) {
        if (money < buffItem.getBuyPrice()) {
            return ActionReturn.NO_MONEY;
        }
        if (buffItem.getType() == Item.ItemType.ANIMALROOMADDING) {
            animalRoomMax++;
        }
        if (buffItem.getType() == Item.ItemType.BAGADDING) {
            bagMax++;
        }
        costMoney(buffItem.getBuyPrice());
        return ActionReturn.SUCCESSFUL;
    }


    //////SELL//////

    /**
     * 賣出物品
     *
     * @param item   要賣的商品
     * @param amount 要賣得數量
     */
    public ActionReturn sell(Item item, int amount) {
        //是否擁有此物品
        if (theSameItem(item) == null) {
            return ActionReturn.NO_ITEM;
        } else {
            Item itemForSell = theSameItem(item);
            //擁有此物品得數量是否足夠
            if (amount > itemForSell.getAmount()) {
                return ActionReturn.NO_AMOUNT;
            } else {
                //獲得販售的錢
                addMoney(howMuch(item, amount));
                //扣除擁有的物品
                itemAmountMinus(itemForSell, amount);
                return ActionReturn.SUCCESSFUL;
            }
        }
    }


    //////getAnimal//////

    /**
     * 選擇寵物房裡的一個寵物
     *
     * @return
     */
    public Animal chooseAnimal() {
        //待修
        Show.showChooseAnimal(allAnimal());
        int index = Input.genNumber(1, animalRoom.size());
        if (index > animalRoom.size()) {
            return null;
        }
        if (animalRoom.get(index - 1).getStatus() != Animal.Status.ALIVE) {
            return null;
        }
        return animalRoom.get(index - 1);
    }

    public ActionReturn sellAnimal(Animal animal) {
        if (animal == null) {
            return ActionReturn.NO_ANIMAL;
        } else {
            addMoney(animal.getSellOutPrice());
            animalRoom.remove(animal);
        }
        if (animal.getStatus() != Animal.Status.ALIVE) {
            return ActionReturn.DEAD_ANIMAL;
        }
        return ActionReturn.SUCCESSFUL;
    }

    /**
     * 選擇背包裡的一個道具
     *
     * @return
     */
    public Item chooseItem() {
        Show.showChooseItem(allItem());
        if (bag.size() == 0) {
            return null;
        }
        int index = Input.genNumber(1, bag.size());
        return bag.get(index - 1);
    }


    //////MOTION//////
//    WALK,
//    FEED,
//    CLEAN,
//    CONNECT,
//    DECORATE,
//    DISCONNECT,
//    NEXT;

    public ActionReturn doSomething() {
        int tmp = Input.genNumber(0, 7);
        Action actionReturn = Action.values()[tmp];
        switch (actionReturn) {
            case TO_MENU -> {
                return ActionReturn.TO_MENU;
            }
            case WALK -> {
                Animal animal = chooseAnimal();
                return animal.walk();
            }
            case FEED -> {
                Animal animal = chooseAnimal();
                Item food = chooseItem();
                if (food == null) {
                    return ActionReturn.NO_ITEM;
                } else {
                    itemAmountMinus(food);
                    return animal.eat(food);
                }
            }
            case CLEAN -> {
                Animal animal = chooseAnimal();
                return animal.clean();
            }
            case CONNECT -> {
                Animal animal = chooseAnimal();
                Animal mate = chooseAnimal();
                return animal.connect(mate);
            }
            case DISCONNECT -> {
                Animal animal = chooseAnimal();
                return animal.disConnect();
            }
            case DECORATE -> {
                Animal animal = chooseAnimal();
                Item item = chooseItem();
                if (item == null) {
                    return ActionReturn.NO_ITEM;
                } else {
                    Item exchangeItem = animal.setDecoration(item);
                    if (exchangeItem != null) {
                        itemAmountMinus(item);
                        return addItem(exchangeItem, 1);
                    } else if (animal.getDecoration() != null) {
                        return ActionReturn.SUCCESSFUL;
                    }
                    return ActionReturn.Not_Decoration;
                }
            }
            case NEXT -> {
                for (Animal animal : animalRoom) {
                    if (animal.getStatus() == Animal.Status.ALIVE) {
                        animal.update();
                    }
                }
                return ActionReturn.TimeGoesBy;
            }
            default -> {
                return ActionReturn.Bug;
            }
        }
    }

    public ActionReturn getDropItem() {
        ActionReturn actionReturn = ActionReturn.NO_DROP;
        Item dropItem;
        for (Animal animal : animalRoom) {
            dropItem = animal.drop();
            if (dropItem != null) {
                if (addItem(dropItem, 1) == ActionReturn.NO_SPACE) {
                    actionReturn = ActionReturn.BagFull_Discard;
                }
                actionReturn = ActionReturn.DROP;
            }
        }
        return actionReturn;
    }


    public void debugMode() {
    }

    /**
     * 遊戲結束(判斷是否寵物死光，或沒寵物)
     *
     * @return
     */
    public boolean isGameOver() {
        if (animalRoom.size() == 0) { //沒寵物
            return true;
        }
        for (Animal animal : animalRoom) {
            if (animal.getStatus() == Animal.Status.ALIVE) {  //有無死光
                return false;
            }
        }
        return true;
    }

    /**
     * 印出所有道具
     *
     * @return
     */
    public String allItem() {
        if (bag.size() == 0) {
            return "背包無物品!";
        }
        int i = 1;
        StringBuilder str = new StringBuilder();
        for (Item item : bag) {
            str.append(i++);
            str.append(".\t");
            str.append(item.itemInformation() + "\n");
        }
        return str.toString();
    }

    /**
     * 印出所有寵物
     *
     * @return
     */
    public String allAnimal() {
        StringBuilder str = new StringBuilder();
        int i = 1;
        for (Animal animal : animalRoom) {
            str.append(i++ + ".\t");
            str.append(animal + "\n");
        }
        return str.toString();
    }

    /**
     * 使用物品減少
     *
     * @param item
     */
    public void itemAmountMinus(Item item) {
        item.addAmount(-1);
        if (item.getAmount() == 0) {
            bag.remove(item);
        }
    }

    /**
     * 使用物品減少(大量)
     *
     * @param item
     * @param amount
     */
    public void itemAmountMinus(Item item, int amount) {
        item.addAmount(-amount);
        if (item.getAmount() == 0) {
            bag.remove(item);
        }
    }

    /**
     * 擁有的錢
     *
     * @return
     */
    public int getMoney() {
        return money;
    }

    /**
     * 減少錢包的錢
     *
     * @param price
     */
    public void costMoney(int price) {
        money -= price;
    }

    /**
     * 增加錢包的錢
     */
    public void addMoney(int price) {
        money += price;
    }

    /**
     * 計算多少錢
     *
     * @param item
     * @param amount
     * @return
     */
    public int howMuch(Item item, int amount) {
        return item.getBuyPrice() * amount;
    }


    /**
     * 批量選擇寵物
     *
     * @return
     */
    private ArrayList chooseNumerousAnimal() {
        ArrayList<Animal> animals = new ArrayList<>();
        int index = -1;
        while (true) {
            index = Input.genNumber(0, animalRoom.size());
            if (index <= 0) {
                break;
            }
            Animal animal = animalRoom.get(index - 1);
            if (!animals.contains(animal)) {
                animals.add(animalRoom.get(index - 1));
            }
        }
        return animals;
    }

    public int getBagSize() {
        return bag.size();
    }

    //    public void addItem(Items addingItem, int amount) {
//        for (Items items : bag) { //遍歷整個背包
//            if (items.getName().equals(addingItem.getName())) { //如果背包裡有跟即將要增加的物品相同時
//                if (items.getCount() + amount <= Items.MAX_COUNT) { //又數量沒有超過
//                    items.addCount(amount); //直接增加持有數
//                    return true;
//                } else { //如果數量有超過
//                    amount -= (Items.MAX_COUNT - items.getCount()); //超過的量
//                    items.setCount(Items.MAX_COUNT); //補滿
//                    addingItem.addCount(amount); //額外新增的item量
//                    if (bag.size() < bagMax) { //背包欄位格夠
//                        bag.add(addingItem);
//                        return true;
//                    }
//                }
//            }
//        }
//        if (amount <= Items.MAX_COUNT) { //又數量沒有超過
//            addingItem.addCount(amount);//直接增加持有數
//            bag.add(addingItem);
//            return true;
//        } else { //如果數量有超過
//            amount -= Items.MAX_COUNT; //超過的量
//            Items item = new
//                    addingItem.addCount(Items.MAX_COUNT); //額外新增的item量
//            if (bag.size() < bagMax) { //背包欄位格夠
//                bag.add(addingItem);
//                return true;
//            }
//        }
//        if (bag.size() < bagMax) {
//            addingItem.addCount(amount);
//            bag.add(addingItem);
//            return true;
//        }
//        return false;
//    }
}