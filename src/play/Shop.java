package play;

import items.Item;
import items.buffItems.*;
import items.decoration.*;
import items.food.*;
import utils.Global;
import utils.Input;

import java.util.ArrayList;

public class Shop {
    private ArrayList<Item> shopList = new ArrayList<>();

    public Shop() {
        shopList.add(new CannedFood());
        shopList.add(new FishFood());
        shopList.add(new InsectFood());
        shopList.add(new Carton());
        shopList.add(new Seaweed());
        shopList.add(new WoodHouse());
        shopList.add(new BagAdding());
        shopList.add(new AnimalRoomAdding());
    }


    /**
     * 販售商品
     *
     * @param item   要販售的物品
     * @param amount 販售數量
     * @return 賣出後獲得的錢
     */
    public int sellItem(Item item, int amount) {
        int sellMoney = item.getSellOutPrice() * amount;
        return sellMoney;
    }

    /**
     * 購買物品是否成功
     *
     * @param money  player持有的錢
     * @param item   購買的物品
     * @param amount 購買數量
     * @return 錢是否足夠
     */
    public boolean buyItem(int money, Item item, int amount) {
        if (money - (item.getBuyInPrice() * amount) < 0) {
            return false;
        }
        return true;
    }

    public void showShopList() {
        for (int i = 0; i < shopList.size(); i++) {
            System.out.println(shopList.get(i).itemExplanation());
        }
    }

    public int howMuch(Item item, int amount) {
        return item.getBuyInPrice() * amount;
    }

    public static Item genBuyingItem() {
        int choice = Input.genNumber(1, 6);
        switch (choice) {
            case 1:
                return Global.genItem(Global.ItemType.CANNEDFOOD);
            case 2:
                return Global.genItem(Global.ItemType.FISHFOOD);
            case 3:
                return Global.genItem(Global.ItemType.INSECTFOOD);
            case 4:
                return Global.genItem(Global.ItemType.CARTON);
            case 5:
                return Global.genItem(Global.ItemType.SEAWEED);
            case 6:
                return Global.genItem(Global.ItemType.WOODHOUSE);
            case 7:
                return Global.genItem(Global.ItemType.BAGADDING);
            case 8:
                return Global.genItem(Global.ItemType.ANIMALROOMADDING);
            default:
                return null;
        }
    }
}
