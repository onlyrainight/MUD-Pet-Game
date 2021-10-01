package play;

import items.Item;
import utils.Input;

public class Main {

    public static void main(String[] args) {
        Player player = new Player();
        Shop shop = new Shop();

        buyItem();

    }

    public static void buyItem () {
        Player player = new Player();
        Shop shop = new Shop();
        shop.showShopList();
        System.out.println("\n請選擇要購買的物品:");
        Item item = Shop.genBuyingItem();
        System.out.println("\n請輸入購買數量");
        int amount = Input.buyAmount();
        if (!shop.buyItem(player.getMoney(), item, amount)) {
            System.out.println("金錢不足");
            return;
        }
        if (!player.isBagEnough(item, amount)) {
            System.out.println("背包不夠");
            return;
        }
        player.addItem(item,amount);
        System.out.println("購買成功");
    }
}
