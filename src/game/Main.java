package game;

import animal.Animal;
import item.BufferItem;
import item.Item;

public class Main {

    public static void main(String[] args) {
        Player player = new Player();

        System.out.println("請選擇你的第一隻動物～(1)狗勾 (2)貓貓 (3)魚兒 (4)昆蟲 ");
        Animal.AnimalType animalChoice = Input.genAnimalType();

        Animal initialAnimal = Shop.genBuyingAnimal(animalChoice);
        initialAnimal.setName(Input.namingWord());
        System.out.println(initialAnimal.getName() + " 是" + initialAnimal.getGender().getDescription() + "喔!");
        player.addAnimal(initialAnimal);

        while (!player.isGameOver()) {
            System.out.println("\n\n請選擇：(1)對動物做動作 (2)顯示所有動物狀態 (3)顯示目前所有物品 (4)進入商店");
            int option = Input.genNumber(1, 4);
            switch (option) {
                case 1 -> {
                    System.out.println("\n\n請選擇：(0)回選單 (1)散步 (2)餵食 (3)清潔 (4)連結 (5)取消連結 (6)裝飾 (7)明天囉");
                    Player.ActionReturn action = player.doSomething();
                    if (action != Player.ActionReturn.TIMES_GO_BY) {
                        System.out.println(action.description);
                    }
                    else {
                        System.out.println(player.getDropItem().description);
                        GameStaticConstantAndFunction.anotherDayMsg();
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
                        case 1 -> {
                            Shop.showItemList();
                            System.out.println("請選擇您要購買的道具");
                            Item buyItem = Shop.genBuyingItem(Input.genItemType());
                            System.out.println("請選擇您要購買的數量");
                            int amount = Input.buyAmount();
                            System.out.println(player.buyItem(buyItem, amount).description);
                        }
                        case 2 -> {
                            Shop.showAnimalList();
                            System.out.println("請選擇您要購買的寵物");
                            Animal animal = Shop.genBuyingAnimal(Input.genAnimalType());
                            System.out.println(player.buyAnimal(animal).description);
                        }
                        case 3 -> {
                            Shop.showBuffItemList();
                            System.out.println("請選擇您要購買的加成道具");
                            BufferItem buyingItem = Shop.genBuyingBuffItem(Input.genBuffItemType());
                            System.out.println(player.buyBuffItem(buyingItem).description);
                        }
                        case 4 -> {
                            System.out.println("請選擇您要販賣的道具");
                            Item sellItem = player.chooseItem();
                            System.out.println("請選擇您要販賣的數量");
                            int sellAmout = Input.genNumber(1, 99);
                            System.out.println(player.sell(sellItem, sellAmout).description);
                        }
                        case 5 -> {
                            System.out.println("請選擇您要販賣的寵物");
                            Animal sellAnimal = player.chooseAnimal();
                            System.out.println(player.sellAnimal(sellAnimal).description);
                        }
                    }
                }
            }

        }
        System.out.println("你的寵物死光了，下次加油吧");
    }
}
