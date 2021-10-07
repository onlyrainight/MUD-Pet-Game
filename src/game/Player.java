package game;

import animal.*;
import item.*;

import java.util.ArrayList;


public class Player {
    private final ArrayList<Item> bag;
    private final ArrayList<Animal> animalRoom;
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
        BAG_FULL_DISCARD("背包已滿，物品已被丟棄"),
        DROP("有掉寶"),
        NOT_DECORATION("這不是裝飾品喔"),
        PET_DIED("你的寵物掛了"),
        TIMES_GO_BY("\n");

        public String description;

        ActionReturn(String description) {
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
        money = GameStaticConstantAndFunction.INITIAL_MONEY;
        bag = new ArrayList<>();
        animalRoom = new ArrayList<>();
        bagMax = 10;
        animalRoomMax = 10;
    }

    //////BUY ITEM////////

    /**
     * 購買道具
     *
     * @param item   要買的
     * @param amount 要買的數量
     * @return 狀態
     */
    public ActionReturn buyItem(Item item, int amount) {
        if (isEnoughBag(item, amount)) {
            return ActionReturn.NO_SPACE;
        }
        if (!isMoneyEnough(money, item, amount)) {
            return ActionReturn.NO_MONEY;
        }
        addItem(item, amount);
        costMoney(item.getBuyPrice() * amount);
        return ActionReturn.SUCCESSFUL;
    }

    /**
     * 檢查是否有重複的道具
     *
     * @param addingItem 放進包包的道具
     * @return 有就回傳，沒有就傳null(滿了一樣算null)
     */
    private Item theSameItem(Item addingItem) {
        for (Item item : bag) {
            if (item.getType() == addingItem.getType()) {
                if (item.getAmount() < GameStaticConstantAndFunction.ITEM_MAX_AMOUNT) { //如有且還可堆疊，就把重複的拿出來，沒有就回傳參數
                    return item;
                }
            }
        }
        return null;
    }

    /**
     * 檢查是否夠放道具
     *
     * @return 是否夠放
     */
    private boolean isEnoughBag(Item addingItem, int amount) {
        Item theSameItem = theSameItem(addingItem);
        if (theSameItem == null) { //新物品
            return bag.size() + 1 > bagMax;
        } else { //舊有物品
            if (theSameItem.getAmount() + amount > 20) {
                return bag.size() + 1 > bagMax;
            } else {
                return false;
            }
        }
    }

    /**
     * 檢查錢是否足夠購買的總金額
     *
     * @param money  player持有的錢
     * @param item   購買的物品
     * @param amount 購買數量
     * @return 是否夠錢
     */
    private boolean isMoneyEnough(int money, Item item, int amount) {
        return money - (item.getBuyPrice() * amount) >= 0;
    }

    /**
     * 放入物品
     *
     * @param addingItem 放進去的物品
     * @param amount     放進去的數量
     */
    public ActionReturn addItem(Item addingItem, int amount) {
        Item theSameItem = theSameItem(addingItem);
        if (isEnoughBag(addingItem, amount)) {
            return ActionReturn.NO_SPACE;
        }
        if (theSameItem == null) {
            addingItem.addAmount(amount);
            bag.add(addingItem);
            return ActionReturn.SUCCESSFUL;
        } else {
            if (theSameItem.getAmount() + amount <= GameStaticConstantAndFunction.ITEM_MAX_AMOUNT) {
                theSameItem.addAmount(amount);
                return ActionReturn.NO_SPACE;
            } else {
                int remaining = amount - (GameStaticConstantAndFunction.ITEM_MAX_AMOUNT - theSameItem.getAmount());
                theSameItem.setAmount(GameStaticConstantAndFunction.ITEM_MAX_AMOUNT);
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
     * @param animal 購買的寵物
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
     * @return 放不放得進去
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
            animalRoomMax += buffItem.getBuffNumber();
        }
        if (buffItem.getType() == Item.ItemType.BAGADDING) {
            bagMax += buffItem.getBuffNumber();
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
        }

        Item itemForSell = theSameItem(item);
        //擁有此物品得數量是否足夠
        if (amount > itemForSell.getAmount()) {
            return ActionReturn.NO_AMOUNT;
        } else {
            //獲得販售的錢
            addMoney(item.getSellPrice() * amount);
            //扣除擁有的物品
            itemAmountMinus(itemForSell, amount);
            return ActionReturn.SUCCESSFUL;
        }

    }


    //////getAnimal//////

    /**
     * 選擇寵物房裡的一個寵物
     *
     * @return 寵物房裡的一個動物
     */
    public Animal chooseAnimal() {
        //待修
        GameStaticConstantAndFunction.showChooseAnimal(allAnimal());
        int index = Input.genNumber(1, animalRoom.size());
        if (index > animalRoom.size()) {
            return null;
        }
        if (animalRoom.get(index - 1).liveStatus() != Animal.Status.ALIVE) {
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
        if (animal.liveStatus() != Animal.Status.ALIVE) {
            return ActionReturn.DEAD_ANIMAL;
        }
        return ActionReturn.SUCCESSFUL;
    }

    /**
     * 選擇背包裡的一個道具
     *
     * @return 背包裡的一個道具
     */
    public Item chooseItem() {
        GameStaticConstantAndFunction.showChooseItem(allItem());
        if (bag.size() == 0) {
            return null;
        }
        int index = Input.genNumber(1, bag.size());
        return bag.get(index - 1);
    }


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
                    return ActionReturn.NOT_DECORATION;
                }
            }
            case NEXT -> {
                for (Animal animal : animalRoom) {
                    if (animal.liveStatus() != Animal.Status.ALIVE) {
                        continue;
                    }
                    animal.update();

                    //有生小孩才做以下事件
                    if (!animal.isBorn()) {
                        continue;
                    }
                    GameStaticConstantAndFunction.showBabyBorn();
                    Animal newBorn = animal.genNewBorn();
                    newBorn.setName(Input.namingWord());
                    if (addAnimal(newBorn)) {
                        return ActionReturn.TIMES_GO_BY;
                    } else {
                        return ActionReturn.NO_SPACE;
                    }
                }
                return ActionReturn.TIMES_GO_BY;
            }
            default -> {
                return null;
            }
        }
    }

    public ActionReturn getDropItem() {
        ActionReturn actionReturn = ActionReturn.NO_DROP;
        Item dropItem;
        for (Animal animal : animalRoom) {
            dropItem = animal.countAndDrop();
            if (dropItem != null) {
                if (addItem(dropItem, 1) != ActionReturn.NO_SPACE) {
                    actionReturn = ActionReturn.DROP;
                } else {
                    actionReturn = ActionReturn.BAG_FULL_DISCARD;
                }
            }
        }
        return actionReturn;
    }


    /**
     * 遊戲結束(判斷是否寵物死光，或沒寵物)
     *
     * @return 是否輸
     */
    public boolean isGameOver() {
        if (animalRoom.size() == 0) { //沒寵物
            return true;
        }
        for (Animal animal : animalRoom) {
            if (animal.liveStatus() == Animal.Status.ALIVE) {  //有無死光
                return false;
            }
        }
        return true;
    }

    /**
     * 印出所有道具
     *
     * @return 背包所有物品
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
            str.append(item.itemInformation()).append("\n");
        }
        return str.toString();
    }

    /**
     * 印出所有寵物
     *
     * @return 寵物房裡所有寵物
     */
    public String allAnimal() {
        StringBuilder str = new StringBuilder();
        int i = 1;
        for (Animal animal : animalRoom) {
            str.append(i++).append(".\t");
            str.append(animal).append("\n");
        }
        return str.toString();
    }

    /**
     * 使用物品減少
     *
     * @param item 使用的道具
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
     * @param item   使用的道具
     * @param amount 使用數量
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
     * @return 擁有的錢
     */
    public int getMoney() {
        return money;
    }

    /**
     * 減少錢包的錢
     *
     * @param price 價格*數量
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
}