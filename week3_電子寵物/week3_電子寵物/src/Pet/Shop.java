/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pet;

import java.util.Scanner;

/**
 * @author mandy
 */
public class Shop {
    //Attribute
    Scanner sc = new Scanner(System.in);
    int pocket; //  money 從 player抓
    //商店買賣價錢列表
    String[] strSell = {"罐頭(狗/貓)", "魚飼料", "昆蟲飼料", "擴增寵物格", "擴增背包", "狗", "貓", "魚", "昆蟲", "狗毛", "貓毛", "魚麟", "木屑", "紙箱", "木屋", "海草"};
    int[] sell = {2, 1, 1, 0, 0, 10, 7, 3, 3, 2, 1, 3, 1, 1, 2, 1};
    String[] strBuy = {"罐頭(狗/貓)", "魚飼料", "昆蟲飼料", "擴增寵物格", "擴增背包", "狗", "貓", "魚", "昆蟲", "狗毛", "貓毛", "魚麟", "木屑", "紙箱", "木屋", "海草"};
    int[] buy = {5, 4, 3, 10, 10, 20, 15, 15, 15, 0, 0, 0, 0, 3, 4, 3};


    //從main傳入pocket引數
    public Shop(Player newPlayer) {
        this.pocket = newPlayer.wallet;
    }

    //進入商店
    public void intoShop(Player player) {
        boolean enterShop = true;
        int opt = 0;
        while (enterShop) {
            System.out.println("購買商品請按1  賣出產品請按2  離開請按3");
            opt = sc.nextInt();
            if (opt == 1 || opt == 2 || opt == 3) {
                break;
            } else {
                System.out.println("沒有這個選項喔");
            }
        }
        while (enterShop) {

            switch (opt) {
                case 1:
                    buying(player);  //買
                    while (true) {
                        System.out.println("購買商品請按1  賣出產品請按2  離開請按3");
                        opt = sc.nextInt();
                        if (opt == 1 || opt == 2 || opt == 3) {
                            break;
                        } else {
                            System.out.println("沒有這個選項喔");
                        }
                    }

                    break;
                case 2:
                    sell(player); //賣

                    while (true) {
                        System.out.println("購買商品請按1  賣出產品請按2  離開請按3");
                        opt = sc.nextInt();
                        if (opt == 1 || opt == 2 || opt == 3) {
                            break;
                        } else {
                            System.out.println("沒有這個選項喔");
                        }
                    }
                    break;
                case 3:
                    enterShop = false;
                    break;
                default:
                    break;
            }
        }

    }

    public void isEnd(Animal[] room) {  //確認賣完寵物後是否死完了
        int overLine = 0;
        for (Animal room1 : room) {
            if (room1 != null) {
                // != 有人 ==沒人
                overLine++;
                break;
            }
        }
        if (overLine == 0) {//如果程式進行到這邊依然沒有人=所有房間都是空的>Gameover
            System.out.println("你的寶貝全都死撩撩了啦！！！！！");
            System.exit(0);
        }
    }

    public int checkIsEnoughAnimal(Animal[] room, String type) { // 確認足夠寵物數量
        int count = 0;
        for (int i = 0; i < room.length; i++) {
            if (room[i] == null) {
                continue;
            } else if (room[i].type.compareTo(type) == 0) {
                count++;
            }
        }
        return count;

    }


    public int checkIsEnoughItem(Item[] items, String type) { // 確認足夠物品數量
        int totalItemNum = 0;
        for (int i = 0; i < items.length; i++) {
            // 如果這個物品名稱(items[i].getName().toLowerCase())和傳入的物品名稱(itemName)相同
            if (items[i] != null) {
                if (items[i].getName().equals(type)) {
                    totalItemNum += items[i].getCount();
                }
            }
        }
        return totalItemNum;
    }

    public boolean checkBackpak(Item[] item, int numItem, String type) { // 查背包空位
        for (int i = 0; i < item.length; i++) {
            if (item[i] == null) {
                return true;
            } else if (item[i].getName().compareTo(type) == 0 && (20 - item[i].getCount()) >= numItem) {
                return true;
            }
        }
        return false;
    }

    public void buying(Player player) {
        System.out.println("您要購買第幾項產品\n 1 罐頭(狗/貓)(價格：5) 2 魚飼料 (價格：4) 3 昆蟲飼料 (價格：3) 4 擴增寵物格(價格：10) 5 擴增背包(價格：10) 6 狗(價格：20) 7 貓(價格：15) 8 魚(價格：15) 9 昆蟲 (價格：15) 14 紙箱 (價格：15) 15 木屋 (價格：15) 16 海草 (價格：15)"); //有要從item和 player抓嗎？
        int opt1;
        //防呆
        while (true) {
            opt1 = sc.nextInt();
            if (opt1 == 1 || opt1 == 2 || opt1 == 3 || opt1 == 4 || opt1 == 5 || opt1 == 6 | opt1 == 7 || opt1 == 8 || opt1 == 9 || opt1 == 14 || opt1 == 15 || opt1 == 16) {
                break;
            } else {
                System.out.println("輸入有的東西啦");
            }
        }
        System.out.println("你的摳摳:" + pocket);
        int numItem;
        boolean enoughMoney;
        boolean enoughBackpack;
        int enoughRoom;
        int remainItem;
        switch (opt1) {

            case 1: //買罐頭
                remainItem = checkIsEnoughItem(player.bag.getItems(), "CannedFood");
                System.out.println("你有" + remainItem + "個罐頭");
                while (true) {
                    System.out.println("請輸入你要買多少(1~20):");
                    numItem = sc.nextInt();
                    if (numItem > 0 && numItem < 21) {
                        break;
                    }
                }
                enoughMoney = checkMoney(buy[opt1 - 1], numItem, pocket); //檢查錢
                enoughBackpack = checkBackpak(player.bag.getItems(), numItem, "CannedFood"); //檢查背包
                if (enoughMoney && enoughBackpack) {
                    player.bag.addItem("CannedFood", numItem); // 背包
                    pocket -= buy[opt1 - 1] * numItem;
                    System.out.println("買入" + strBuy[opt1 - 1] + " " + numItem);
                    System.out.println("摳摳剩下" + pocket);

                } else {
                    System.out.println("你的money或背包格不夠");
                }
                break;

            case 2: //買魚飼料
                remainItem = checkIsEnoughItem(player.bag.getItems(), "FishFood");
                System.out.println("你有" + remainItem + "個魚飼料");
                while (true) {
                    System.out.println("請輸入你要買多少(1~20):");
                    numItem = sc.nextInt();
                    if (numItem > 0 && numItem < 21) {
                        break;
                    }
                }
                enoughMoney = checkMoney(buy[opt1 - 1], numItem, pocket); //檢查錢
                enoughBackpack = checkBackpak(player.bag.getItems(), numItem, "FishFood"); //檢查背包
                if (enoughMoney && enoughBackpack) {
                    player.bag.addItem("FishFood", numItem); // 背包
                    pocket -= buy[opt1 - 1] * numItem;
                    System.out.println("買入" + strBuy[opt1 - 1] + " " + numItem);
                    System.out.println("摳摳剩下" + pocket);
                } else {
                    System.out.println("你的money或背包格不夠");
                }
                break;

            case 3: //買蟲飼料
                remainItem = checkIsEnoughItem(player.bag.getItems(), "InsectFood");
                System.out.println("你有" + remainItem + "個蟲飼料");
                while (true) {
                    System.out.println("請輸入你要買多少(1~20):");
                    numItem = sc.nextInt();
                    if (numItem > 0 && numItem < 21) {
                        break;
                    }
                }
                enoughMoney = checkMoney(buy[opt1 - 1], numItem, pocket); //檢查錢
                enoughBackpack = checkBackpak(player.bag.getItems(), numItem, "InsectFood"); //檢查背包
                if (enoughMoney && enoughBackpack) {
                    player.bag.addItem("InsectFood", numItem); // 背包
                    pocket -= buy[opt1 - 1] * numItem;
                    System.out.println("買入" + strBuy[opt1 - 1] + " " + numItem);
                    System.out.println("摳摳剩下" + pocket);
                } else {
                    System.out.println("你的money或背包格不夠");
                }
                break;
            //對Animal做擴增寵物格 +amount
            case 4:
                System.out.println("你有" + player.room.length + "個寶貝房");
                while (true) {
                    System.out.println("請輸入你要買多少(1~20):");
                    numItem = sc.nextInt();
                    if (numItem > 0 && numItem < 21) {
                        break;
                    }
                }
                enoughMoney = checkMoney(buy[opt1 - 1], numItem, pocket); //檢查錢
                if (enoughMoney) {
                    int newRoom = player.room.length + numItem;
                    Animal[] temp = new Animal[newRoom];
                    for (int i = 0; i < player.room.length; i++) {
                        if (player.room[i] != null) {
                            temp[i] = player.room[i];
                        }
                    }
                    player.setNewAnimal(temp);
                    System.out.println("你有" + player.room.length + "個寶貝房");
                } else {
                    System.out.println("你的money不夠");
                }

                break;
            //對背包做擴增背包 +amount
            case 5:
                System.out.println("你有" + player.bag.getItems().length + "個背包格");
                while (true) {
                    System.out.println("請輸入你要買多少(1~20):");
                    numItem = sc.nextInt();
                    if (numItem > 0 && numItem < 21) {
                        break;
                    }
                }
                enoughMoney = checkMoney(buy[opt1 - 1], numItem, pocket); //檢查錢
                if (enoughMoney) {
                    int newItem = player.bag.getItems().length + numItem;
                    Item[] temp = new Item[newItem];
                    for (int i = 0; i < player.bag.getItems().length; i++) {
                        if (player.bag.getItems()[i] != null) {
                            temp[i] = player.bag.getItems()[i];
                        }

                    }
                    player.bag.setItems(temp);
                    System.out.println("你有" + player.bag.getItems().length + "個背包格");
                } else {
                    System.out.println("你的money不夠");
                }
                break;
            //對Animal做 new Dog() of Animal
            case 6:
                remainItem = checkIsEnoughAnimal(player.room, "Dog");
                System.out.println("你有" + remainItem + "隻狗仔");
                enoughRoom = checkRoom(player.room); //檢查寵物格
                System.out.println("你有" + enoughRoom + "空房間");
                while (true) {
                    System.out.println("請輸入你要買多少(1~20):");
                    numItem = sc.nextInt();
                    if (numItem > 0 && numItem < 21) {
                        break;
                    }
                }
                enoughMoney = checkMoney(buy[opt1 - 1], numItem, pocket); //檢查錢

                if (enoughMoney && (enoughRoom >= numItem)) {
                    for (int i = 0; i < numItem; i++) {
                        System.out.println("你買的第" + (i + 1) + "隻寶貝:");
                        newAnimal(strBuy[opt1 - 1], player.room);
                    }
                    pocket -= buy[opt1 - 1] * numItem;
                    remainItem = checkIsEnoughAnimal(player.room, "Dog");
                    System.out.println("你有" + remainItem + "隻狗仔");
                    System.out.println("摳摳剩下" + pocket);
                } else {
                    System.out.println("你的money或空房間不夠");
                }
                break;
            //對Animal做 new Cat() of Animal
            case 7:
                remainItem = checkIsEnoughAnimal(player.room, "Cat");
                System.out.println("你有" + remainItem + "隻貓仔");
                enoughRoom = checkRoom(player.room); //檢查寵物格
                System.out.println("你有" + enoughRoom + "空房間");
                while (true) {
                    System.out.println("請輸入你要買多少(1~20):");
                    numItem = sc.nextInt();
                    if (numItem > 0 && numItem < 21) {
                        break;
                    }
                }
                enoughMoney = checkMoney(buy[opt1 - 1], numItem, pocket); //檢查錢
                enoughRoom = checkRoom(player.room); //檢查背包
                if (enoughMoney && (enoughRoom >= numItem)) {
                    for (int i = 0; i < numItem; i++) {
                        System.out.println("你買的第" + (i + 1) + "隻寶貝:");
                        newAnimal(strBuy[opt1 - 1], player.room);
                    }
                    pocket -= buy[opt1 - 1] * numItem;
                    remainItem = checkIsEnoughAnimal(player.room, "Cat");
                    System.out.println("你有" + remainItem + "隻貓仔");
                    System.out.println("摳摳剩下" + pocket);
                } else {
                    System.out.println("你的money或空房間不夠");
                }
                break;
            //對Animal做 new Fish() of Animal
            case 8:
                remainItem = checkIsEnoughAnimal(player.room, "Fish");
                System.out.println("你有" + remainItem + "隻魚仔");
                enoughRoom = checkRoom(player.room); //檢查寵物格
                System.out.println("你有" + enoughRoom + "空房間");
                while (true) {
                    System.out.println("請輸入你要買多少(1~20):");
                    numItem = sc.nextInt();
                    if (numItem > 0 && numItem < 21) {
                        break;
                    }
                }
                enoughMoney = checkMoney(buy[opt1 - 1], numItem, pocket); //檢查錢
                enoughRoom = checkRoom(player.room); //檢查背包
                if (enoughMoney && (enoughRoom >= numItem)) {
                    for (int i = 0; i < numItem; i++) {
                        System.out.println("你買的第" + (i + 1) + "隻寶貝:");
                        newAnimal(strBuy[opt1 - 1], player.room);
                    }
                    pocket -= buy[opt1 - 1] * numItem;
                    remainItem = checkIsEnoughAnimal(player.room, "Fish");
                    System.out.println("你有" + remainItem + "隻魚仔");
                    System.out.println("摳摳剩下" + pocket);
                } else {
                    System.out.println("你的money或空房間不夠");
                }
                break;

            case 9: //對Animal做 new Insect() of Animal
                remainItem = checkIsEnoughAnimal(player.room, "Insect");
                System.out.println("你有" + remainItem + "隻蟲仔");
                enoughRoom = checkRoom(player.room); //檢查寵物格
                System.out.println("你有" + enoughRoom + "空房間");
                while (true) {
                    System.out.println("請輸入你要買多少(1~20):");
                    numItem = sc.nextInt();
                    if (numItem > 0 && numItem < 21) {
                        break;
                    }
                }
                enoughMoney = checkMoney(buy[opt1 - 1], numItem, pocket); //檢查錢
                enoughRoom = checkRoom(player.room); //檢查背包
                if (enoughMoney && (enoughRoom >= numItem)) {
                    for (int i = 0; i < numItem; i++) {
                        System.out.println("你買的第" + (i + 1) + "隻寶貝:");
                        newAnimal(strBuy[opt1 - 1], player.room);
                    }
                    pocket -= buy[opt1 - 1] * numItem;
                    remainItem = checkIsEnoughAnimal(player.room, "Insect");
                    System.out.println("你有" + remainItem + "隻蟲仔");
                    System.out.println("摳摳剩下" + pocket);
                } else {
                    System.out.println("你的money或空房間不夠");
                }
                break;

            case 14: //買紙箱
                remainItem = checkIsEnoughItem(player.bag.getItems(), "Carton");
                System.out.println("你有" + remainItem + "個紙箱");
                while (true) {
                    System.out.println("請輸入你要買多少(1~20):");
                    numItem = sc.nextInt();
                    if (numItem > 0 && numItem < 21) {
                        break;
                    }
                }
                enoughMoney = checkMoney(buy[opt1 - 1], numItem, pocket); //檢查錢
                enoughBackpack = checkBackpak(player.bag.getItems(), numItem, "Carton"); //檢查背包
                if (enoughMoney && enoughBackpack) {
                    player.bag.addItem("Carton", numItem); // 背包
                    pocket -= buy[opt1 - 1] * numItem;
                    System.out.println("買入" + strBuy[opt1 - 1] + " " + numItem);
                    System.out.println("摳摳剩下" + pocket);

                } else {
                    System.out.println("你的money或背包格不夠");
                }
                break;
            case 15: //買木屋
                remainItem = checkIsEnoughItem(player.bag.getItems(), "WoodHouse");
                System.out.println("你有" + remainItem + "個木屋");
                while (true) {
                    System.out.println("請輸入你要買多少(1~20):");
                    numItem = sc.nextInt();
                    if (numItem > 0 && numItem < 21) {
                        break;
                    }
                }
                enoughMoney = checkMoney(buy[opt1 - 1], numItem, pocket); //檢查錢
                enoughBackpack = checkBackpak(player.bag.getItems(), numItem, "WoodHouse"); //檢查背包
                if (enoughMoney && enoughBackpack) {
                    player.bag.addItem("WoodHouse", numItem); // 背包
                    pocket -= buy[opt1 - 1] * numItem;
                    System.out.println("買入" + strBuy[opt1 - 1] + " " + numItem);
                    System.out.println("摳摳剩下" + pocket);

                } else {
                    System.out.println("你的money或背包格不夠");
                }
                break;
            case 16: //買海草
                remainItem = checkIsEnoughItem(player.bag.getItems(), "Seaweed");
                System.out.println("你有" + remainItem + "個海草");
                while (true) {
                    System.out.println("請輸入你要買多少(1~20):");
                    numItem = sc.nextInt();
                    if (numItem > 0 && numItem < 21) {
                        break;
                    }
                }
                enoughMoney = checkMoney(buy[opt1 - 1], numItem, pocket); //檢查錢
                enoughBackpack = checkBackpak(player.bag.getItems(), numItem, "Seaweed"); //檢查背包
                if (enoughMoney && enoughBackpack) {
                    player.bag.addItem("Seaweed", numItem); // 背包
                    pocket -= buy[opt1 - 1] * numItem;
                    System.out.println("買入" + strBuy[opt1 - 1] + " " + numItem);
                    System.out.println("摳摳剩下" + pocket);

                } else {
                    System.out.println("你的money或背包格不夠");
                }
                break;
        }

    }


    public void sell(Player player) {
        System.out.println("您要賣第幾項產品 \n 1 罐頭(狗/貓)(價格：2) 2 魚飼料 (價格：1) 3 昆蟲飼料 (價格：1) 6 狗(價格：10) 7 貓(價格：7) 8 魚(價格：3) 9 昆蟲 (價格：3) 10 狗毛(價格：2) 11 貓毛(價格：1) 12 魚麟(價格：3) 13 木屑 (價格：1) 14 紙箱 (價格：1) 15 木屋 (價格：2) 16 海草 (價格：1) ");
        int opt2;
        //防呆
        while (true) {
            opt2 = sc.nextInt();
            if (opt2 == 1 || opt2 == 2 || opt2 == 3 || opt2 == 6 || opt2 == 7 || opt2 == 8 || opt2 == 9 || opt2 == 10 || opt2 == 11 || opt2 == 13 || opt2 == 14 || opt2 == 15 || opt2 == 16) {
                break;
            } else {
                System.out.println("輸入有的東西啦");
            }
        }

        boolean check;
        int numItem;
        int remainItem;
        switch (opt2) {
            case 1: //賣罐頭
                remainItem = checkIsEnoughItem(player.bag.getItems(), "CannedFood");
                if (remainItem > 0) {
                    System.out.println("你有" + remainItem + "個罐頭,請輸入你要賣幾個");
                    numItem = sc.nextInt();
                    if (numItem <= remainItem) {
                        player.bag.minusItem("CannedFood", numItem);
                        pocket += sell[opt2 - 1] * numItem;
                        System.out.println("你賣了" + numItem + "個罐頭");
                        System.out.println("摳摳剩下" + pocket);
                    } else {
                        System.out.println("罐頭不夠");
                    }
                } else {
                    System.out.println("醒醒吧,你沒有罐頭");
                }
                break;
            case 2: //賣魚飼料
                remainItem = checkIsEnoughItem(player.bag.getItems(), "FishFood");
                if (remainItem > 0) {
                    System.out.println("你有" + remainItem + "個魚飼料,請輸入你要賣幾個");
                    numItem = sc.nextInt();
                    if (numItem <= remainItem) {
                        player.bag.minusItem("FishFood", numItem);
                        pocket += sell[opt2 - 1] * numItem;
                        System.out.println("你賣了" + numItem + "個魚飼料");
                        System.out.println("摳摳剩下" + pocket);
                    } else {
                        System.out.println("魚飼料不夠");
                    }
                } else {
                    System.out.println("醒醒吧,你沒有魚飼料");
                }
                break;
            case 3: //賣蟲飼料
                remainItem = checkIsEnoughItem(player.bag.getItems(), "InsectFood");
                if (remainItem > 0) {
                    System.out.println("你有" + remainItem + "個蟲飼料,請輸入你要賣幾個");
                    numItem = sc.nextInt();
                    if (numItem <= remainItem) {
                        player.bag.minusItem("InsectFood", numItem);
                        pocket += sell[opt2 - 1] * numItem;
                        System.out.println("你賣了" + numItem + "個蟲飼料");
                        System.out.println("摳摳剩下" + pocket);
                    } else {
                        System.out.println("蟲飼料不夠");
                    }
                } else {
                    System.out.println("醒醒吧,你沒有蟲飼料");
                }
                break;
            case 6: //賣狗和重排 animal[]
                remainItem = checkIsEnoughAnimal(player.room, "Dog");
                if (remainItem > 0) {
                    System.out.println("你有" + remainItem + "隻狗仔,請輸入你要賣幾隻");
                    numItem = sc.nextInt();
                    if (numItem <= remainItem) {
                        deAnimal("Dog", player, numItem);
                        pocket += sell[opt2 - 1] * numItem;
                        System.out.println("你賣了" + numItem + "隻狗仔");
                        System.out.println("錢包剩下" + pocket);
                    } else {
                        System.out.println("狗仔不夠");
                    }
                } else {
                    System.out.println("醒醒吧,你沒有狗仔");
                }
                break;

            case 7: //賣貓和重排 animal[]
                remainItem = checkIsEnoughAnimal(player.room, "Cat");
                if (remainItem > 0) {
                    System.out.println("你有" + remainItem + "隻貓仔,請輸入你要賣幾隻");
                    numItem = sc.nextInt();
                    if (numItem <= remainItem) {
                        deAnimal("Cat", player, numItem);
                        pocket += sell[opt2 - 1] * numItem;
                        System.out.println("你賣了" + numItem + "隻貓仔");
                        System.out.println("錢包剩下" + pocket);
                    } else {
                        System.out.println("貓仔不夠");
                    }
                } else {
                    System.out.println("醒醒吧,你沒有貓仔");
                }
                break;
            case 8: //賣魚和重排 animal[]
                remainItem = checkIsEnoughAnimal(player.room, "Fish");
                if (remainItem > 0) {
                    System.out.println("你有" + remainItem + "隻魚仔,請輸入你要賣幾隻");
                    numItem = sc.nextInt();
                    if (numItem <= remainItem) {
                        deAnimal("Fish", player, numItem);
                        pocket += sell[opt2 - 1] * numItem;
                        System.out.println("你賣了" + numItem + "隻魚仔");
                        System.out.println("錢包剩下" + pocket);
                    } else {
                        System.out.println("魚仔不夠");
                    }
                } else {
                    System.out.println("醒醒吧,你沒有魚仔");
                }
                break;
            case 9: //賣蟲和重排 animal[]
                remainItem = checkIsEnoughAnimal(player.room, "Insect");
                if (remainItem > 0) {
                    System.out.println("你有" + remainItem + "隻蟲仔,請輸入你要賣幾隻");
                    numItem = sc.nextInt();
                    if (numItem <= remainItem) {
                        deAnimal("Insect", player, numItem);
                        pocket += sell[opt2 - 1] * numItem;
                        System.out.println("你賣了" + numItem + "隻蟲仔");
                        System.out.println("錢包剩下" + pocket);
                    } else {
                        System.out.println("蟲仔不夠");
                    }
                } else {
                    System.out.println("醒醒吧,你沒有蟲仔");
                }
                break;
            case 10: //賣狗毛
                remainItem = checkIsEnoughItem(player.bag.getItems(), "DogHair");
                if (remainItem > 0) {
                    System.out.println("你有" + remainItem + "個狗毛,請輸入你要賣幾個");
                    numItem = sc.nextInt();
                    if (numItem <= remainItem) {
                        player.bag.minusItem("DogHair", numItem);
                        pocket += sell[opt2 - 1] * numItem;
                        System.out.println("你賣了" + numItem + "個狗毛");
                        System.out.println("錢包剩下" + pocket);
                    } else {
                        System.out.println("狗毛不夠");
                    }
                } else {
                    System.out.println("醒醒吧,你沒有狗毛");
                }
                break;
            case 11: //賣貓毛
                remainItem = checkIsEnoughItem(player.bag.getItems(), "CatHair");
                if (remainItem > 0) {
                    System.out.println("你有" + remainItem + "個貓毛,請輸入你要賣幾個");
                    numItem = sc.nextInt();
                    if (numItem <= remainItem) {
                        player.bag.minusItem("CatHair", numItem);
                        pocket += sell[opt2 - 1] * numItem;
                        System.out.println("你賣了" + numItem + "個貓毛");
                        System.out.println("錢包剩下" + pocket);
                    } else {
                        System.out.println("貓毛不夠");
                    }
                } else {
                    System.out.println("醒醒吧,你沒有貓毛");
                }
                break;
            case 12: //賣魚鱗
                remainItem = checkIsEnoughItem(player.bag.getItems(), "FishScale");
                if (remainItem > 0) {
                    System.out.println("你有" + remainItem + "個魚麟,請輸入你要賣幾個");
                    numItem = sc.nextInt();
                    if (numItem <= remainItem) {
                        player.bag.minusItem("FishScale", numItem);
                        pocket += sell[opt2 - 1] * numItem;
                        System.out.println("你賣了" + numItem + "個魚麟");
                        System.out.println("錢包剩下" + pocket);
                    } else {
                        System.out.println("魚麟不夠");
                    }
                } else {
                    System.out.println("醒醒吧,你沒有魚麟");
                }
                break;
            case 13: //賣木屑
                remainItem = checkIsEnoughItem(player.bag.getItems(), "Sawdust");
                if (remainItem > 0) {
                    System.out.println("你有" + remainItem + "個木屑,請輸入你要賣幾個");
                    numItem = sc.nextInt();
                    if (numItem <= remainItem) {
                        player.bag.minusItem("Sawdust", numItem);
                        pocket += sell[opt2 - 1] * numItem;
                        System.out.println("你賣了" + numItem + "個木屑");
                        System.out.println("錢包剩下" + pocket);
                    } else {
                        System.out.println("木屑不夠");
                    }
                } else {
                    System.out.println("醒醒吧,你沒有木屑");
                }
                break;
            case 14: //賣紙箱
                remainItem = checkIsEnoughItem(player.bag.getItems(), "Carton");
                if (remainItem > 0) {
                    System.out.println("你有" + remainItem + "個紙箱,請輸入你要賣幾個");
                    numItem = sc.nextInt();
                    if (numItem <= remainItem) {
                        player.bag.minusItem("Carton", numItem);
                        pocket += sell[opt2 - 1] * numItem;
                        System.out.println("你賣了" + numItem + "個紙箱");
                        System.out.println("錢包剩下" + pocket);
                    } else {
                        System.out.println("紙箱不夠");
                    }
                } else {
                    System.out.println("醒醒吧,你沒有紙箱");
                }
                break;
            case 15: //賣木屋
                remainItem = checkIsEnoughItem(player.bag.getItems(), "WoodHouse");
                if (remainItem > 0) {
                    System.out.println("你有" + remainItem + "個木屋,請輸入你要賣幾個");
                    numItem = sc.nextInt();
                    if (numItem <= remainItem) {
                        player.bag.minusItem("WoodHouse", numItem);
                        pocket += sell[opt2 - 1] * numItem;
                        System.out.println("你賣了" + numItem + "個木屋");
                        System.out.println("錢包剩下" + pocket);
                    } else {
                        System.out.println("木屋不夠");
                    }
                } else {
                    System.out.println("醒醒吧,你沒有木屋");
                }
                break;
            case 16: //賣海草
                remainItem = checkIsEnoughItem(player.bag.getItems(), "Seaweed");
                if (remainItem > 0) {
                    System.out.println("你有" + remainItem + "個海草,請輸入你要賣幾個");
                    numItem = sc.nextInt();
                    if (numItem <= remainItem) {
                        player.bag.minusItem("Seaweed", numItem);
                        pocket += sell[opt2 - 1] * numItem;
                        System.out.println("你賣了" + numItem + "個海草");
                        System.out.println("錢包剩下" + pocket);
                    } else {
                        System.out.println("海草不夠");
                    }
                } else {
                    System.out.println("醒醒吧,你沒有海草");
                }
                break;
        }
        isEnd(player.room);
    }


    //當寵物格子或背包滿了強制進入購買流程，a=3為擴增寵物格，a=4為擴增背包
//    public boolean forceBuying(Animal[] allAnimals, Item[] item , int a){
//                int tmp = pocket - buy[a];            
//                while(tmp<0){
//                    System.out.println("餘額不足，請賣東西");
//                    sell(allAnimals , item);
//                    tmp = pocket - buy[a];
//                }
//                if (a == 4){
//                    int newItem = item.length + 1;
//                    Item[] temp = new Item[newItem];
//                    for(int i = 0; i < item.length; i++) {
//                        if(item[i] != null) {
//                        temp[i] = item[i];
//                        }
//                                
//                    }
//                    item = temp;
//                    pocket -= buy[a];
//                    System.out.println("買入"+ strBuy[a] + " " + "1");
//                    System.out.println("摳摳剩下"+ pocket);
//                } else if (a == 3) {
//                    int newRoom = allAnimals.length + 1;
//                    Animal[] temp = new Animal[newRoom];
//                    for(int i = 0; i < item.length; i++) {
//                        if(item[i] != null) {
//                        temp[i] = allAnimals[i];
//                        }
//                                
//                    }
//                    allAnimals = temp;
//                    pocket -= buy[a];
//                    System.out.println("買入"+ strBuy[a] + " " + "1");
//                    System.out.println("摳摳剩下"+ pocket);                    
//                }    
//
//            
//
//                return true;
//            
//    }   


    public boolean checkMoney(int price, int numItem, int pocket) { //檢查錢量
        return pocket > (price * numItem);
    }

    //        public boolean checkBackpak ( Item [] item , int numItem) {
//            return true;
//        }
    public int checkRoom(Animal[] room) { //檢查房量
        int count = 0;
        for (int i = 0; i < room.length; i++) {
            if (room[i] == null) {
                count++;

            }
        }
        return count;
    }

//    public static int gender(){//寵物性別
//        int i = (int)(Math.random()*(2-1+1))+1;//用亂數隨機選1或2，1公 2母，同人類ID開頭
//        return i;
//    }   

    public void deAnimal(String type, Player player, int numItem) { //賣寵物 寵物死
        for (int k = 0; k < numItem; k++) {
            for (int i = player.room.length - 1; i >= 0; i--) {
                if (player.room[i] != null) {
                    if (player.room[i].type == type) {
                        player.room[i] = null;
                        numItem--;
                    }
                    if (numItem == 0) {
                        break;
                    }
                }
            }
        }
        Animal temp[] = new Animal[player.room.length];
        int count = 0;
        for (int i = 0; i < player.room.length - 1; i++) {
            if (player.room[i] != null) {
                temp[count] = player.room[i];
                count++;

            }
        }
        player.room = temp;

    }


    public static void newAnimal(String type, Animal[] room) { //買寵物，改為可選擇性別
        Scanner sc = new Scanner(System.in);
        String name;
        switch (type) {
            case "狗":
                Dog dog = new Dog();
                System.out.println("要買男生還是女生呢?\t1.男生\t2.女生");
                dog.sex = sc.nextInt();
                if (dog.sex == 1) {
                    System.out.println("恭喜你！這隻可愛的小狗狗(V●ᴥ●V) 是男生唷！");
                } else if (dog.sex == 2) {
                    System.out.println("恭喜你！這隻可愛的小狗狗(V●ᴥ●V) 是女生唷！");
                }
                System.out.println("現在，來幫牠取名吧！");
                System.out.println("：");
                sc.nextLine();
                dog.name = sc.nextLine();
                for (int i = 0; i < room.length; i++) {
                    if (room[i] == null) {//如果找到空房間
                        room[i] = dog;//把this dog放進房間
                        break;

                    }
                }
                break;
            case "貓":
                Cat cat = new Cat();
                System.out.println("要買男生還是女生呢?\t1.男生\t2.女生");
                cat.sex = sc.nextInt();
                if (cat.sex == 1) {
                    System.out.println("恭喜你！這隻可愛的小貓貓(V●ᴥ●V) 是男生唷！");
                } else if (cat.sex == 2) {
                    System.out.println("恭喜你！這隻可愛的小貓貓(V●ᴥ●V) 是女生唷！");
                }
                System.out.println("現在，來幫牠取名吧！");
                System.out.println("：");
                sc.nextLine();
                name = sc.nextLine();
                cat.name = name;
                for (int i = 0; i < room.length; i++) {
                    if (room[i] == null) {//如果找到空房間
                        room[i] = cat;//把this dog放進房間
                        break;

                    }
                }
                break;
            case "魚":
                Fish fish = new Fish();
                System.out.println("要買男生還是女生呢?\t1.男生\t2.女生");
                fish.sex = sc.nextInt();
                if (fish.sex == 1) {
                    System.out.println("恭喜你！這隻可愛的小魚魚(V●ᴥ●V) 是男生唷！");
                } else if (fish.sex == 2) {
                    System.out.println("恭喜你！這隻可愛的小魚魚(V●ᴥ●V) 是女生唷！");
                }
                System.out.println("現在，來幫牠取名吧！");
                System.out.println("：");
                sc.nextLine();
                name = sc.nextLine();
                fish.name = name;
                for (int i = 0; i < room.length; i++) {
                    if (room[i] == null) {//如果找到空房間
                        room[i] = fish;//把this dog放進房間
                        break;

                    }
                }
                break;
            case "昆蟲":
                Insect insect = new Insect();
                System.out.println("要買男生還是女生呢?\t1.男生\t2.女生");
                insect.sex = sc.nextInt();
                if (insect.sex == 1) {
                    System.out.println("恭喜你！這隻可愛的小蟲蟲(V●ᴥ●V) 是男生唷！");
                } else if (insect.sex == 2) {
                    System.out.println("恭喜你！這隻可愛的小蟲蟲(V●ᴥ●V) 是女生唷！");
                }
                System.out.println("現在，來幫牠取名吧！");
                System.out.println("：");
                sc.nextLine();
                insect.name = sc.nextLine();
                for (int i = 0; i < room.length; i++) {
                    if (room[i] == null) {//如果找到空房間
                        room[i] = insect;//把this dog放進房間
                        break;

                    }
                }
                break;
            default:
                break;
        }
    }
}        
     
