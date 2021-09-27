package Pet;

public class Detect {

    Animal[] allAnimals;

    /**
     * 將動物陣列傳入修改
     */
    public Detect(Animal[] allAnimals) {
        this.allAnimals = allAnimals;
    }

    /**
     * 檢查完狀態後回傳更新的動物狀態
     */
    public Animal[] checkStatus(Player player) {
        for (int i = 0; i < allAnimals.length; i++) {
            if (allAnimals[i] != null) {
                Animal thePet = allAnimals[i];
                //新增發情狀態未連結判定
                if (thePet.isoEstrusState && thePet.binding == null){
                    System.out.println("在發情狀態下沒有對象，好感度-1!\t快幫牠找個對象!!");
                    thePet.feeling--;
                }
                //好感度影響判定
                for (int j = 1; j <= 4; j++) {
                    String temp = "";
                    switch (j) {
                        case 1:
                            temp = "Dog";
                            break;
                        case 2:
                            temp = "Cat";
                            break;
                        case 3:
                            temp = "Fish";
                            break;
                        case 4:
                            temp = "Insect";
                    }
                    if (thePet.type.equals(temp) && thePet.feeling > 10) {
                        thePet.initialWalk += 2;
                        thePet.initialFeed += 2;
                        break;
                    } else if (thePet.type.equals(temp) && thePet.feeling < 0) {
                        thePet.initialWalk -= 1;
                        thePet.initialFeed -= 1;
                        break;
                    }
                }

                //檢查是否餓死，新增寵物死亡時顯示提示
                if (thePet.hungry <= 0) {
                    thePet.life = false;
                    thePet.die = 1;
                    System.out.println("你的寶貝" + thePet.name + "餓死了QAQ");
                    continue;
                }
                //檢查是否無聊死
                if (thePet.bored <= 0) {
                    thePet.life = false;
                    thePet.die = 2;
                    System.out.println("你的寶貝" + thePet.name + "無聊死了QAQ");
                    continue;
                }
                //檢查是否髒死
                if (thePet.shit <= 0) {
                    thePet.life = false;
                    thePet.die = 3;
                    System.out.println("你的寶貝" + thePet.name + "髒死了QAQ");
                    continue;
                }

                if (thePet.life) {
                    //檢查是否要睡覺
                    thePet.sleep--;
                    if (thePet.sleep >= 0) {

                        //檢查是否需要餵食
                        thePet.feed--;
                        if (thePet.feed <= 0) {
                            thePet.feeling--;
                            System.out.println("警告：" + thePet.name + "進入飢餓狀態，需要餵食！");
                            thePet.isoverfullState = false;
                            thePet.hungry--;
                            System.out.println(thePet.name + "肚子餓不高興，好感度-1!");
                        }

                        //檢查是否要散步
                        thePet.walk--;
                        if (thePet.walk <= 0) {
                            thePet.feeling--;
                            System.out.println("警告：" + thePet.name + "進入無聊狀態，需要散步！");
                            thePet.bored--;
                            System.out.println(thePet.name + "太無聊了不高興，好感度-1!");
                        }

                        //檢查是否有交配對象
                        if (thePet.binding != null) {
                            //檢查交配對象是否同種
                            if (thePet.type == thePet.binding.type) {
                                //檢查是否異性
                                if (thePet.sex != thePet.binding.sex) {
                                    thePet.bindingTime--;
                                    //檢查交配時間
                                    if (thePet.bindingTime <= 0) {

                                        int animalsAmount = 0;

                                        for (int k = 0; k < allAnimals.length; k++) {
                                            if (allAnimals[k] != null) {
                                                animalsAmount++;
                                                continue;
                                                //新增連結生小孩時顯示，修正一組連結會重覆生的問題，新增連結生小孩可讓玩家自行命名
                                            } else {
                                                try {
                                                    Thread.sleep(1000);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                System.out.println("..!");
                                                try {
                                                    Thread.sleep(1000);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                System.out.println("..!!");
                                                try {
                                                    Thread.sleep(1000);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                System.out.println("寶貝" + thePet.name + "和" + thePet.binding.name + "生小寶寶囉!!!");
                                                try {
                                                    Thread.sleep(2000);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                allAnimals[k] = thePet.giveBirth();
                                                allAnimals[i].resetBindingTime();
                                                allAnimals[i].binding.resetBindingTime();
                                                allAnimals[i].binding.bindingTime+=1;
                                                break;
                                            }
                                        }
                                        if (animalsAmount == allAnimals.length) {
                                            System.out.println("寶貝" + thePet.name + "和" + thePet.binding.name
                                                    + "生小寶寶了! 但是動物房間已滿，孩子生不下哩：）））再不買動物房啊");
                                        }
                                    }
                                }
                            } else {
                                //檢查是否無法交配死
                                thePet.notOkaySex--;
                                //不同種兩個都死掉
                                if (thePet.notOkaySex <= 0) {
                                    thePet.life = false;
                                    thePet.binding.life = false;
                                    System.out.println("你的寶貝" + thePet.name + "和" + thePet.binding.name + "不同種交配死了QAQ");
                                    thePet.die = 4;
                                    thePet.binding.die = 4;
                                    continue;
                                }

                                /** 主角是貓 */
                                if (thePet.type == "Cat") {
                                    //不同情境A：貓＆魚
                                    if (thePet.binding.type == "Fish") {
                                        if (thePet.feed <= 0) {
                                            //貓在餓，魚會死
                                            thePet.binding.life = false;
                                            /** 要餵貓 */
                                            thePet.resetFeed();
                                            System.out.println("連結中的貓把魚吃啦!!\t貓被餵食了一次!");
                                        }
                                    }

                                    //不同情境B：貓＆昆蟲
                                    if (thePet.binding.type == "Insect") {
                                        if (thePet.bored <= 0) {
                                            //貓在無聊，昆蟲會死
                                            thePet.binding.life = false;
                                            /** 要帶貓散步 */
                                            thePet.resetWalk();
                                            System.out.println("連結中的貓把昆蟲玩死啦!!\t貓玩得很開心!");
                                        }
                                    }

                                    //不同情境C：貓＆狗
                                    if (thePet.binding.type == "Dog") {
                                        if (thePet.bored <= 0) {
                                            //貓在無聊 好感度-1
                                            thePet.feeling--;
                                            System.out.println("連結中的" + thePet.name + "覺得和"
                                                    + thePet.binding.name + "待在一起很無聊，好感度減一!");
                                        }
                                        if (thePet.binding.bored <= 0) {
                                            //狗在無聊 好感度-1
                                            thePet.binding.feeling--;
                                            System.out.println("連結中的" + thePet.binding.name + "覺得和"
                                                    + thePet.name + "待在一起很無聊，好感度減一!");
                                        }
                                    }
                                }

                                /** 主角是狗 */
                                if (thePet.type == "Dog") {

                                    //不同情境A：狗＆昆蟲
                                    if (thePet.binding.type == "Insect") {
                                        if (thePet.bored <= 0) {
                                            //狗在無聊，昆蟲會死
                                            thePet.binding.life = false;
                                            thePet.resetWalk();
                                            System.out.println("連結中的狗把昆蟲玩死啦!!\t狗玩得很開心!");
                                        }
                                    }

                                    //不同情境B：狗＆貓，貓那邊判斷一次就好
                                    if (thePet.binding.type == "Cat") {
                                        if (thePet.bored <= 0 || thePet.binding.bored <= 0) {
                                            //狗在無聊，貓在無聊 好感度都-1
                                            thePet.feeling--;
                                            thePet.binding.feeling--;
                                        }
                                    }
                                }


                                /** 主角是昆蟲 */
                                if (thePet.type == "Insect") {

                                    //不同情境A：昆蟲＆狗
                                    if (thePet.binding.type == "Dog") {
                                        if (thePet.binding.bored <= 0) {
                                            //狗在無聊，昆蟲會死
                                            thePet.life = false;
                                        }
                                    }

                                    //不同情境B：昆蟲＆貓
                                    if (thePet.binding.type == "Cat") {
                                        if (thePet.binding.bored <= 0) {
                                            //狗在無聊，昆蟲會死
                                            thePet.life = false;
                                            /** 貓被散步一次 */
                                            thePet.binding.resetWalk();
                                        }
                                    }
                                }

                                /** 主角是魚 */
                                if (thePet.type == "Fish") {
                                    //不同情境A：魚&貓
                                    if (thePet.binding.type == "Cat") {
                                        if (thePet.binding.feed <= 0) {
                                            //貓在餓，魚會死
                                            thePet.life = false;
                                            /** 要餵貓 */
                                            thePet.binding.resetFeed();
                                        }
                                    }
                                }

                            }
                        }

                        //檢查是否產出物品，新增隨機產生數量1到5個
                        thePet.production--;
                        if (thePet.production <= 0) {
                            int productNum =(int)(1 + Math.random() * 5);
                            player.bag.addItem(thePet.product, productNum);
                            thePet.resetProduction();
                            String temp = "";
                            switch (thePet.product){
                                case "doghair":
                                    temp = "狗毛";
                                    break;
                                case "cathair":
                                    temp = "貓毛";
                                    break;
                                case "fishscale":
                                    temp = "魚鱗";
                                    break;
                                case "sawdust":
                                    temp = "木屑";
                            }
                            System.out.println(thePet.name + "產出了" + productNum + "個" + temp + "!");
                        }

                    } else {
                        //檢查是否清醒
                        thePet.sleepLast--;
                        if (thePet.sleepLast <= 0) {
                            thePet.resetSleep();
                        }
                    }
                    //新增隨機發情狀態，每回合10%機率觸發
                    if (thePet.binding == null && !thePet.isoEstrusState && (Math.random() * 10) < 1){
                        thePet.isoEstrusState = true;
                        System.out.println("寶貝" + thePet.name + "進入了發情狀態!\t快幫牠找個連結伴侶!");
                    }
                }
            }
        }
        return allAnimals;
    }

    public Animal[] doubleArray(Animal[] originalArray) {
        Animal[] newArray = new Animal[feList(originalArray.length)];
        for (int i = 0; i < originalArray.length; i++) {
            newArray[i] = originalArray[i];
        }
        return newArray;
    }

    private int feList(int originalArray) {
        if (originalArray == 0) {
            return 1;
        }
        if (originalArray == 1) {
            return 2;
        }
        return (originalArray - 1) + (originalArray - 2);
    }
}
