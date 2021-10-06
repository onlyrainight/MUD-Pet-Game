package game;

import animal.Animal;
import item.BufferItem;
import item.Item;

public class Main {

    public static void main(String[] args) {
        Player player = new Player();
        Shop shop = new Shop();
        System.out.println("請選擇你的第一隻動物～(1)狗勾 (2)貓貓 (3)魚兒 (4)昆蟲 ");
        int animalChoice = Input.genNumber(1, 4);

        Animal initialAnimal = shop.genBuyingAnimal(animalChoice);
        initialAnimal.setName(Input.namingWord());
        initialAnimal.setGender(Animal.randomGender());
        System.out.println(initialAnimal.getName() + " 是" + initialAnimal.getGender().getDescription() + "喔!");
        player.addAnimal(initialAnimal);

        while (!player.isGameOver()) {
            System.out.println("\n\n請選擇：(1)對動物做動作 (2)顯示所有動物狀態 (3)顯示目前所有物品 (4)進入商店");
            int option = Input.genNumber(1, 4);
            switch (option) {
                case 1 -> {
                    System.out.println("\n\n請選擇：(1)散步 (2)餵食 (3)清潔 (4)連結 (5)取消連結 (6)裝飾 (7)明天囉");
                    Player.ActionReturn action = player.doSomething();
                    if (action != Player.ActionReturn.TimeGoesBy) {
                        System.out.println(action.description);
                        continue;
                    } else {
                        System.out.println(player.getDropItem().description);
                        Input.anotherDayMsg();
                    }
                }
                case 2 -> {
                    System.out.println(player.allAnimal());
                }
                case 3 -> {
                    System.out.println(player.allItem());
                }
                case 4 -> {
                    System.out.println("進入商店");
                    System.out.println("你有" + player.getMoney() + "摳摳");
                    System.out.println("1.購買道具  2.購買寵物  3.購買容量  4.販賣道具  5.販賣寵物");
                    int shopOption = Input.genNumber(1, 4);
                    switch (shopOption) {
                        case 1:
                            shop.showItemList();
                            System.out.println("請選擇您要購買的道具");
                            Item buyItem = shop.genBuyingItem(Input.genNumber(1, 6));
                            System.out.println("請選擇您要購買的數量");
                            int amount = Input.buyAmount();
                            System.out.println(player.buyItem(buyItem, amount).description);
                            break;
                        case 2:
                            shop.showAnimalList();
                            System.out.println("請選擇您要購買的寵物");
                            Animal animal = shop.genBuyingAnimal(Input.genNumber(1, 4));
                            System.out.println(player.buyAnimal(animal).description);
                            break;
                        case 3:
                            shop.showBuffItemList();
                            System.out.println("請選擇您要購買的加成道具");
                            Item buyingItem = shop.genBuyingBuffItem(Input.genNumber(1, 2));
                            System.out.println(player.buyBuffItem((BufferItem) buyingItem).description);
                            break;
                        case 4:
                            System.out.println("請選擇您要販賣的道具");
                            Item sellItem = player.chooseItem();
                            System.out.println("請選擇您要販賣的數量");
                            int sellAmout = Input.genNumber(1, 99);
                            System.out.println(player.sell(sellItem, sellAmout).description);
                            break;
                        case 5:
                            System.out.println("請選擇您要販賣的寵物");
                            Animal sellAnimal = player.chooseAnimal();
                            System.out.println(player.sellAnimal(sellAnimal).description);
                    }
                }
            }

        }
        System.out.println("你的寵物死光了，下次加油吧");
//        Input.anotherDayMsg();
//     StringBuilder str = new StringBuilder();
//        str.append(null);


//        while (player.isGameOver()) {
//            int action = Input.genNumber(1, 5);
//            switch (action) {
//                case 1:
//                    System.out.println("請輸入執行動作: (1)直接過一天 (2)餵食 (3)清潔房間 (4)散步 (5)連結 (6)解除連結 (7)裝飾寵物房 (8)回上一頁");
//                    int movement = Input.genNumber(1,8);
//                    while (movement > 8 || movement < 1) {
//                        System.out.println("請輸入執行動作: (1)直接過一天 (2)餵食 (3)清潔房間 (4)散步 (5)連結 (6)解除連結 (7)裝飾寵物房 (8)回上一頁");
//                        movement = sc.nextInt();
//                    }
//                    newPlayer = makeCommand.startCommand(newPlayer, movement);
//                    break;
//                case 2:
//                    newPlayer.checkAnimalAmount(); //顯示所有動物陣列中的動物狀態
//                    break;
//                case 3:
//                    newPlayer.checkAnimalBinding(); //印出所有有配對的動物
//                    break;
//                case 4:
//                    newPlayer.printOutAllItem(); //印出所有包包中物品
//                    break;
//                case 5:
//                    enterShop.intoShop(newPlayer);
//                    break;
//            }
//        }   //寵物全死提示+結束遊戲
//        System.out.println("你的寶貝們都死掉了T.T");
//    }
//}

//    public static void buyItem () {
//        Player player = new Player();
//        Shop shop = new Shop();
//        shop.showShopList();
//        System.out.println("\n請選擇要購買的物品:");
//        Item item = Shop.genBuyingItem();
//        System.out.println("\n請輸入購買數量");
//        int amount = Input.buyAmount();
//        if (!shop.buyItem(player.getMoney(), item, amount)) {
//            System.out.println("金錢不足");
//            return;
//        }
//        if (!player.isBagEnough(item, amount)) {
//            System.out.println("背包不夠");
//            return;
//        }
//        player.addItem(item,amount);
//        System.out.println("購買成功");
//    }
    }
}
