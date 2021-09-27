
package Pet;

import java.util.Scanner;

/**
 * @author charisma
 */

public class Function {

    Scanner sc = new Scanner(System.in);

    /**
     * 傳入玩家所有資料
     */
    public Player startCommand(Player player, int movement) {

        Detect detectStatus = new Detect(player.room);

        switch (movement) {  //新增好感度判定

            /** 直接過一天 */
            case 1:
                player.room = detectStatus.checkStatus(player);
                System.out.println("主人，你就這樣浪費時間？OK？很閒？┐(´д`)┌");
                break;

            /** 餵食 */
            case 2:
                System.out.println("選擇要餵食的寵物!");
                player.checkAnimalAmount();
                int pickAnimal = sc.nextInt() - 1;
                //睡覺判定
                if (player.room[pickAnimal].sleep == 0) {
                    System.out.println("寶貝正在睡覺!");
                } else {
                    feedPet(player, pickAnimal);
                }
                break;

            /** 清潔房間 */
            case 3:
                System.out.println("選擇要清潔的寵物!");
                player.checkAnimalAmount();
                int pickAnimal2 = sc.nextInt();
                System.out.println("桑幾勒～有吿送✧*｡٩(ˊᗜˋ*)و✧*｡");
                player.room[pickAnimal2 - 1].resetShit();
                player.room = detectStatus.checkStatus(player);
                break;

            /** 散步 */
            case 4:
                System.out.println("選擇要散步的寵物!");
                player.checkAnimalAmount();
                int pickAnimal3 = sc.nextInt();

                //新增魚和昆蟲不用散步判定
                if (player.room[pickAnimal3 - 1].type.equals("Fish") || player.room[pickAnimal3 - 1].type.equals("Insect")) {
                    System.out.println("這位寶貝不用散步!");
                } else {
                    //睡覺判定
                    if (player.room[pickAnimal3 - 1].sleep == 0) {
                        System.out.println("寶貝正在睡覺!");
                    } else {
                        player.room[pickAnimal3 - 1].resetWalk();
                        System.out.println("帶我走～到遙遠的以後～～～(๑ơ ₃ ơ)♥");
                        player.room[pickAnimal3 - 1].feeling += 2;
                        System.out.println("散步很開心!" + player.room[pickAnimal3 - 1].name + "的好感度+2!");
                        player.room = detectStatus.checkStatus(player);
                    }
                }
                break;

            /** 連結 */
            case 5:
                System.out.println("請輸入要連結的寵物編號!(用空白分開，i.g： 1 3)");
                player.checkAnimalAmount();
                int pickAnimalA = sc.nextInt();
                int pickAnimalB = sc.nextInt();
                //睡覺判定
                if (player.room[pickAnimalA - 1].sleep <= 0 || player.room[pickAnimalB - 1].sleep <= 0) {
                    System.out.println("寶貝正在睡覺!");
                } else {
                    //和自己連結判定
                    if (pickAnimalA == pickAnimalB) {
                        System.out.println("和自己連結沒用啦!!");
                    } else {
                        //無連結對象判定
                        if (player.room[pickAnimalA - 1].binding == null && player.room[pickAnimalB - 1].binding == null) {
                            player.room[pickAnimalA - 1].binding = player.room[pickAnimalB - 1];
                            player.room[pickAnimalB - 1].binding = player.room[pickAnimalA - 1];
                            System.out.println("部長：不排除有 " + player.room[pickAnimalA - 1].type + " 與 " + player.room[pickAnimalB - 1].type + " 之間的連結");
                            //判斷發情狀態，是否同種，是否不同性
                            if (player.room[pickAnimalA - 1].isoEstrusState && player.room[pickAnimalA - 1].type == player.room[pickAnimalA - 1].binding.type
                                    && player.room[pickAnimalA - 1].sex != player.room[pickAnimalA - 1].binding.sex) {
                                System.out.println(player.room[pickAnimalA - 1].name + "在發情狀態時找到連結對象很開心!\t好感度+1!");
                                player.room[pickAnimalA - 1].feeling++;
                            } else {
                                System.out.println(player.room[pickAnimalA - 1].name + "對你幫牠找的對象不滿意!\t好感度-1!");
                                player.room[pickAnimalA - 1].feeling--;
                            }
                            if (player.room[pickAnimalB - 1].isoEstrusState && player.room[pickAnimalB - 1].type == player.room[pickAnimalB - 1].binding.type &&
                                    player.room[pickAnimalB - 1].sex != player.room[pickAnimalB - 1].binding.sex) {
                                System.out.println(player.room[pickAnimalB - 1].name + "在發情狀態時找到連結對象很開心!\t好感度+1!");
                                player.room[pickAnimalB - 1].feeling++;
                            } else {
                                System.out.println(player.room[pickAnimalB - 1].name + "對你幫牠找的對象不滿意!\t好感度-1!");
                                player.room[pickAnimalB - 1].feeling--;
                            }
                        } else {
                            System.out.println("有寶貝已經有和其他人連結啦!");
                        }
                    }
                }
                break;

            /** 解除連結 */
            case 6:
                System.out.println("請輸入要解除連結的寵物編號!");
                if (!player.checkAnimalBinding()) {
                    int pickAnimal4 = sc.nextInt();
                    //睡覺判定
                    if (player.room[pickAnimal4 - 1].sleep == 0) {
                        System.out.println("寶貝正在睡覺!");
                    } else {
                        System.out.println("拿勝利寶劍，斬斷 " + player.room[pickAnimal4 - 1].name + " 和 " + player.room[pickAnimal4 - 1].binding.name + " 的連結!");
                        player.room[pickAnimal4 - 1].isoEstrusState = false;
                        player.room[pickAnimal4 - 1].binding.isoEstrusState = false;
                        player.room[pickAnimal4 - 1].resetBinding();
                    }
                }
                break;
            case 7:
                System.out.println("請選擇要裝飾的寵物房!");
                player.checkAnimalAmount();
                int pickAnimal5 = sc.nextInt() - 1;
                System.out.println("要用甚麼裝飾呢?\t1.紙箱\t2.木屋\t3.海草");
                int chooseDecorate = sc.nextInt();
                String chineseDecorate = "", decorate = "";
                switch (chooseDecorate) {
                    case 1:
                        chineseDecorate = "紙箱";
                        decorate = "carton";
                        break;
                    case 2:
                        chineseDecorate = "木屋";
                        decorate = "woodhouse";
                        break;
                    case 3:
                        chineseDecorate = "海草";
                        decorate = "seaweed";
                }
                //判斷背包是否有這個裝飾物
                if (player.bag.DetectItem(decorate)) {
                    //判斷寵物房是否已經有裝飾物
                    if (player.room[pickAnimal5].decorate.equals("無")) {
                        player.room[pickAnimal5].decorate = decorate;
                        player.bag.minusItem(decorate, 1);
                        //判斷該裝飾物是否是寵物喜歡的
                        if (player.room[pickAnimal5].decorate.equals(player.room[pickAnimal5].likeDectorate)) {
                            System.out.println(player.room[pickAnimal5].name + "很喜歡這個裝物品! 好感度+2!");
                            player.room[pickAnimal5].feeling += 2;
                        } else {
                            System.out.println("在" + player.room[pickAnimal5].name + "的房間裝飾了" + chineseDecorate + "!");
                        }
                    } else {
                        System.out.println("每間寵物房只能有一個裝飾，要換掉現在的裝飾嗎?\t1.是\t2.否");
                        int choose = sc.nextInt();
                        if (choose == 1) {
                            String temp = player.room[pickAnimal5].decorate;
                            player.room[pickAnimal5].decorate = decorate;
                            player.bag.minusItem(decorate, 1);
                            player.bag.addItem(temp, 1);
                            if (player.room[pickAnimal5].decorate.equals(player.room[pickAnimal5].likeDectorate)) {
                                System.out.println(player.room[pickAnimal5].name + "很喜歡這個裝飾物! 好感度+2!");
                                player.room[pickAnimal5].feeling += 2;
                            } else {
                                System.out.println("在" + player.room[pickAnimal5].name + "的房間裝飾了" + chineseDecorate + "!");
                            }
                        }
                    }
                } else {
                    System.out.println("沒有這個裝飾物，快去商店買!");
                }
                break;
            case 8:
                break;
        }
        return player;
    }

    public void feedPet(Player player, int pickAnimal) {
        boolean haveFood = false;
        String food = "",foodCH = "";
        //載入動物對應的食物種類
        switch (player.room[pickAnimal].type) {
            case "Dog":
                food = "CannedFood";
                foodCH = "罐頭食物";
                break;
            case "Cat":
                food = "CannedFood";
                foodCH = "罐頭食物";
                break;
            case "Fish":
                food = "FishFood";
                foodCH = "魚飼料";
                break;
            case "Insect":
                food = "InsectFood";
                foodCH = "昆蟲飼料";
        }
        //檢測背包是否有對的食物
//        System.out.println("檢查用"+player.bag.getBagCount());
        for (int i = 0; i < player.bag.getBagCount(); i++) {
            if (player.bag.getItems()[i].getName().equals(food)) {
                player.bag.minusItem(food, 1);
                haveFood = true;
                break;
            }
        }

        //新增過飽判定
        if (haveFood) {
            if (player.room[pickAnimal].isoverfullState) {
                System.out.println(player.room[pickAnimal].name + "過飽了還吃東西，撐死啦!");
                player.room[pickAnimal].life = false;
            } else if (player.room[pickAnimal].feed < player.room[pickAnimal].overfull) {
                player.room[pickAnimal].resetFeed();
                player.room[pickAnimal].feeling++;
                System.out.println(player.room[pickAnimal].name + "的好感度+1!");
                System.out.println("下次想吃Lawry's，懂？(๑•́ ₃ •̀๑)");
            } else {
                System.out.println("吃太多啦! " + player.room[pickAnimal].name + "進入過飽狀態!");
                player.room[pickAnimal].isoverfullState = true;
                System.out.println(player.room[pickAnimal].name + "的好感度減2!");
                player.room[pickAnimal].feeling -= 2;
            }
        } else {
            System.out.println(foodCH + "不夠了! 趕快去商店買!!");
        }
    }

}


