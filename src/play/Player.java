package play;

import animals.Animal;
import items.Item;
import items.buffItems.Usable;
import utils.Global;
import utils.Input;

import java.util.ArrayList;


public class Player {
    private ArrayList<Item> bag = new ArrayList<Item>();
    private int bagMax = 10;
    private int animalRoomMax = 10;
    private int money;
    private ArrayList<Animal> animalRoom = new ArrayList<>();
//    private int actionTime = 0;

    public Player() {
        money = 1000;
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
    public boolean isBagEnough(Item addingItem, int amount) {
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
     * 放入物品
     *
     * @param addingItem
     * @param amount
     */
    public void addItem(Item addingItem, int amount) {
        Item theSameItem = theSameItem(addingItem);
        if (!isBagEnough(addingItem, amount)) {
            return;
        }
        if (theSameItem == null) {
            addingItem.addAmount(amount);
            bag.add(addingItem);
        } else {
            if (theSameItem.getAmount() + amount <= Global.ITEM_MAX_AMOUNT) {
                theSameItem.addAmount(amount);
            } else {
                int remaining = amount - (Global.ITEM_MAX_AMOUNT - theSameItem.getAmount());
                theSameItem.setAmount(Global.ITEM_MAX_AMOUNT);
                addingItem.addAmount(remaining);
                bag.add(addingItem);
            }
        }
    }

    /**
     * 選擇一個寵物
     *
     * @param index
     * @return
     */
    public Animal chooseAnimal(int index) {
        if (index > animalRoom.size()) {
            return null;
        }
        return animalRoom.get(index - 1);
    }

    /**
     * 選擇一個道具
     *
     * @param index
     * @return
     */
    public Item chooseItem(int index) {
        if (index > bag.size()) {
            return null;
        }
        return bag.get(index - 1);
    }

    /**
     * 放入寵物
     *
     * @return
     */
    public boolean addAnimal(Animal animal) {
        if (animalRoom.size() + 1 <= animalRoomMax) {
            animalRoom.add(animal);
            return true;
        }
        return false;
    }

    /**
     * 幫寵物取名
     *
     * @param animal
     */
    public void naming(Animal animal, String name) {

    }

    /**
     * 帶寵物散步
     *
     * @param animal
     */
    public void walking(Animal animal) {
    }

    /**
     * 餵寵物吃東西
     *
     * @param animal
     * @param item
     * @return
     */
    public boolean feed(Animal animal, Item item) {
        if (item.getType() != Global.ItemType.FISHFOOD ||
                item.getType() != Global.ItemType.INSECTFOOD ||
                item.getType() != Global.ItemType.CANNEDFOOD) {
            return false;
        }
        return true;
    }

    /**
     * 選擇兩隻寵物連結
     *
     * @param animal
     * @param animal2
     * @return
     */
    public boolean connect(Animal animal, Animal animal2) {
        if (animal == animal2) {
            return false;
        }
        return true;
    }

    /**
     * 選擇兩隻寵物取消連結
     *
     * @param animal
     * @param animal2
     * @return
     */
    public boolean disconnect(Animal animal, Animal animal2) {
        return true;
    }

    /**
     * 裝飾寵物房間
     */
    public boolean decorate(Animal animal, Item item) {
        return true;
    }

    /**
     * 清潔房間
     *
     * @return
     */
    public boolean clean(Animal animal) {
        return true;
    }

    /**
     * 遊戲結束(判斷是否寵物死光，或沒寵物)
     *
     * @return
     */
    public boolean isGameOver() {
        for (Animal animal : animalRoom) {

        }
        return true;
    }

    /**
     * 印出所有道具
     *
     * @return
     */
    public boolean printAllItem() {
        if (bag.size() == 0) {
            return false;
        }
        for (Item item : bag) {
            System.out.println(item.getName() + ": " + item.getAmount() + "個    用途:" + item.getUsage());
        }
        return true;
    }

    /**
     * 印出所有寵物
     *
     * @return
     */
    public boolean printAllAnimal() {
        return true;
    }

    public void use(Usable usable) {
        if(((Item)usable).getType()== Global.ItemType.ANIMALROOMADDING) {

        }
    }

    /**
     * 動作時間增加
     */
//    public void nextAction() {
//        actionTime++;
//    }

    /**
     * 取得動作時間
     *
     * @return
     */
//    public int getActionTime() {
//        return actionTime;
//    }

    public int getMoney() {
        return money;
    }

    public void costMoney(int price) {

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
}
