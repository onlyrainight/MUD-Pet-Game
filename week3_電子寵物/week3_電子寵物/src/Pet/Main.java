package Pet;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Player newPlayer = new Player();
        /** 一開始新增一隻動物 */
        System.out.println("請選擇你的第一隻動物～(1)狗勾 (2)貓貓 (3)魚兒 (4)昆蟲 ");
        int action = sc.nextInt();
        System.out.println("請幫他命名：");
        String animalName = sc.next();
        switch (action) {       //選擇起始動物會給5個相對應的食物，新增餵食種類判定
            case 1:
                Dog newDog = new Dog();
                newDog.setName(animalName);
                newPlayer.addNewAnimal(newDog); //新增動物到陣列
                printGender(newPlayer,action);  //選擇起始寵物時顯示性別
                newPlayer.bag.getItems()[0] = new CannedFood();
                newPlayer.bag.getItems()[0].setCount(5);
                break;
            case 2:
                Cat newCat = new Cat();
                newCat.setName(animalName);
                newPlayer.addNewAnimal(newCat);
                printGender(newPlayer,action);
                newPlayer.bag.getItems()[0] = new CannedFood();
                newPlayer.bag.getItems()[0].setCount(5);
                break;
            case 3:
                Fish newFish = new Fish();
                newFish.setName(animalName);
                newPlayer.addNewAnimal(newFish);
                printGender(newPlayer,action);
                newPlayer.bag.getItems()[0] = new FishFood();
                newPlayer.bag.getItems()[0].setCount(5);
                break;
            case 4:
                Insect newInsect = new Insect();
                newInsect.setName(animalName);
                newPlayer.addNewAnimal(newInsect);
                printGender(newPlayer,action);
                newPlayer.bag.getItems()[0] = new InsectFood();
                newPlayer.bag.getItems()[0].setCount(5);
                break;
        }

        /** 創建之後需要用到的檢查狀態 */
        Shop enterShop = new Shop(newPlayer);//創建商店

        /** 建立做動作的實體*/
        Function makeCommand = new Function();

        /** 檢查所有動物是否都活著 */
        while (isGameOver(newPlayer)) {

            System.out.println("\n\n請選擇：(1)對動物做動作 (2)顯示所有動物狀態 (3)顯示所有連結狀態 (4)顯示目前所有物品 (5)進入商店");
            action = sc.nextInt();
            switch (action) {
                case 1:
                    System.out.println("請輸入執行動作: (1)直接過一天 (2)餵食 (3)清潔房間 (4)散步 (5)連結 (6)解除連結 (7)裝飾寵物房 (8)回上一頁");
                    int movement = sc.nextInt();
                    while (movement > 8 || movement < 1) {
                        System.out.println("請輸入執行動作: (1)直接過一天 (2)餵食 (3)清潔房間 (4)散步 (5)連結 (6)解除連結 (7)裝飾寵物房 (8)回上一頁");
                        movement = sc.nextInt();
                    }
                    newPlayer = makeCommand.startCommand(newPlayer, movement);
                    break;
                case 2:
                    newPlayer.checkAnimalAmount(); //顯示所有動物陣列中的動物狀態
                    break;
                case 3:
                    newPlayer.checkAnimalBinding(); //印出所有有配對的動物
                    break;
                case 4:
                    newPlayer.printOutAllItem(); //印出所有包包中物品
                    break;
                case 5:
                    enterShop.intoShop(newPlayer);
                    break;
            }
        }   //寵物全死提示+結束遊戲
        System.out.println("你的寶貝們都死掉了T.T");
    }

    //判定遊戲是否繼續
    public static boolean isGameOver(Player newplayer) {
        for (int i = 0; i < newplayer.room.length; ++i) {
            if (newplayer.room[i] != null) {
                if (newplayer.room[i].life) {
                    return true;
                }
            }
        }
        return false;
    }


    //顯示起始寵物性別
    public static void printGender(Player newplayer, int action) {
        String temp = "";
        switch (action) {
            case 1:
                temp = "小狗勾";
                break;
            case 2:
                temp = "小貓貓";
                break;
            case 3:
                temp = "小魚兒";
                break;
            case 4:
                temp = "小蟲蟲";
        }
        if (newplayer.room[0].sex == 1) {
            System.out.println("恭喜你！這隻可愛的" + temp +"(V●ᴥ●V) 是男生唷！");
        } else if (newplayer.room[0].sex == 2) {
            System.out.println("恭喜你！這隻可愛的" + temp +"(V●ᴥ●V) 是女生唷！");
        }
    }
}
