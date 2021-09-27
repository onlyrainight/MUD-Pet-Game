package Pet;

public class Bag {
    // 放置物品的陣列
    private Item[] items = new Item[10];
    // 包包放了幾格，有起始食物所以從1開始
    private int bagCount = 1;


    /**
     * 將物品放到背包裡
     *
     * @param itemName String 傳入物品的名稱
     * @param itemNum  int 傳入物品的數量
     */
    public void addItem(String itemName, int itemNum) {
        // 將輸入的名稱統一轉換成小寫
        itemName = itemName.toLowerCase();

        // 掃過整個包包的物品陣列
        for (int i = 0; i < bagCount; i++) {
            //防爆空值
            if (items[i] != null) {
                // 如果這個物品名稱(items[i].getName().toLowerCase())和傳入的物品名稱(itemName)相同
                if (items[i].getName().toLowerCase().equals(itemName)) {
                    // 將加入的數量(itemNum)加到原本的是數量中(items[i].getCount())
                    if (items[i].getCount() + itemNum <= 20) {
                        //判斷物品堆疊數量
                        items[i].setCount(items[i].getCount() + itemNum);
                        return;
                    } else {
                        //超過堆疊數量就堆滿一疊，剩下的到另一疊
                        itemNum += items[i].getCount() - 20;
                        items[i].setCount(20);
//                        bagCount++;
                    }
                }
            }
        }
        // 如果包包裡沒有相同的物品名稱就會執行到這裡，根據加入的物品名稱決定要新增哪個物品
        switch (itemName) {
            // 如果物品名稱是"cannedfood"
            case "cannedfood":
                // 在 bagCount 的位置創造一個 CannedFood 物件
                items[bagCount] = new CannedFood();
                // 將這個 CannedFood 物件的數量 ++
                items[bagCount].setCount(itemNum);
                // 增加 bagCount 的數量
                bagCount++;
                break;
            case "carton":
                items[bagCount] = new Carton();
                items[bagCount].setCount(itemNum);
                bagCount++;
                break;
            case "cathair":
                items[bagCount] = new CatHair();
                items[bagCount].setCount(itemNum);
                bagCount++;
                break;
            case "doghair":
                items[bagCount] = new DogHair();
                items[bagCount].setCount(itemNum);
                bagCount++;
                break;
            case "fishfood":
                items[bagCount] = new FishFood();
                items[bagCount].setCount(itemNum);
                bagCount++;
                break;
            case "fishscale":
                items[bagCount] = new FishScale();
                items[bagCount].setCount(itemNum);
                bagCount++;
                break;
            case "insectfood":
                items[bagCount] = new InsectFood();
                items[bagCount].setCount(itemNum);
                bagCount++;
                break;
            case "sawdust":
                items[bagCount] = new Sawdust();
                items[bagCount].setCount(itemNum);
                bagCount++;
                break;
            case "seaweed":
                items[bagCount] = new Seaweed();
                items[bagCount].setCount(itemNum);
                bagCount++;
                break;
            case "woodhouse":
                items[bagCount] = new WoodHouse();
                items[bagCount].setCount(itemNum);
                bagCount++;
                break;
        }
    }

    /**
     * 將從背包裡移除
     *
     * @param itemName String 移除物品的名稱
     * @param itemNum  int 移除物品的數量
     */
    public void minusItem(String itemName, int itemNum) {
        // 將輸入的名稱統一轉換成小寫
        itemName = itemName.toLowerCase();
        int totalItemNum = 0;
        //掃過整個陣列，計算目標道具數量
        totalItemNum = checkBagTotalItem(items, itemName);

        //總量夠才進判斷
        if (totalItemNum - itemNum >= 0) {
            // 掃過整個包包的物品陣列
            for (int i = 0; i < items.length && itemNum != 0; i++) {
                //空值防爆
                if (items[i] != null) {
                    // 如果這個物品名稱(items[i].getName().toLowerCase())和傳入的物品名稱(itemName)相同
                    if (items[i].getName().toLowerCase().equals(itemName)) {
                        //一疊就夠的話就直接減那蝶數量
                        if (items[i].getCount() >= itemNum) {
                            items[i].setCount(items[i].getCount() - itemNum);
                            if (items[i].getCount() == 0) {
                                items[i] = null;

                                //第i格變空格，後面道具往前移
                                Item[] temp = new Item[items.length];
                                //複製i之前的
                                for (int j = 0; j < i; j++) {
                                    temp[j] = items[j];
                                }
                                //i之後的往前移
                                for (int j = i + 1; j < items.length; j++) {
                                    temp[j - 1] = items[j];
                                }
                                items = temp;
                                bagCount--;
                            }
                            return;

                            //不夠的話第一疊清空，再扣後面幾疊的量
                        } else {
                            itemNum -= items[i].getCount();
                            items[i] = null;
                            //第i格變空格，後面道具往前移
                            Item[] temp = new Item[items.length];
                            //複製i之前的
                            for (int j = 0; j < i; j++) {
                                temp[j] = items[j];
                            }
                            //i之後的往前移
                            for (int j = i + 1; j < items.length; j++) {
                                temp[j - 1] = items[j];
                            }
                            items = temp;
                            bagCount--;
                        }
                    }
                }
            }
        } else {
            System.out.println("醒醒吧!你沒有這麼多道具!");
        }
        return;
    }

    //掃描背包是否有指定道具
    public boolean DetectItem(String itemName) {
        // 將輸入的名稱統一轉換成小寫
        itemName = itemName.toLowerCase();
        // 掃過整個包包的物品陣列
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                if (items[i].getName().toLowerCase().equals(itemName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int checkBagTotalItem(Item[] items, String itemName) {
        int totalItemNum = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                if (items[i].getName().toLowerCase().equals(itemName)) {
                    totalItemNum += items[i].getCount();
                }
            }
        }
        return totalItemNum;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public int getBagCount() {
        return bagCount;
    }

    public void setBagCount(int bagCount) {
        this.bagCount = bagCount;
    }
}
