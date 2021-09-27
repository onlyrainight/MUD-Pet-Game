package Pet;

public class Player {

    private static final int DOG = 0;
    private static final int CAT = 1;
    private static final int FISH = 2;
    private static final int INSECT = 3;
    String name;
    int wallet = 10000;
    Bag bag = new Bag();
    int index = 0;
    int type;
    Animal[] room = new Animal[10];

    public void setNewAnimal(Animal[] room) {
        this.room = room;
    }

    /**
     * 新增一隻動物
     */
    public void addNewAnimal(Animal animal) {
        room[index] = animal;
        //先不管動物刪減？？？？
    }

    /**
     * 印出目前所有的動物
     */
    public void checkAnimalAmount() {
//        新增顯示寵物時加性別和種類
        for (int i = 0; i < room.length; i++) {
            if (room[i] != null) {
                switch (room[i].type) {
                    case "Dog":
                        System.out.print((i + 1) + ".名字: " + room[i].name + "\t種類: 狗勾");
                        break;
                    case "Cat":
                        System.out.print((i + 1) + ".名字: " + room[i].name + "\t種類: 貓貓");
                        break;
                    case "Fish":
                        System.out.print((i + 1) + ".名字: " + room[i].name + "\t種類: 魚兒");
                        break;
                    case "Insect":
                        System.out.print((i + 1) + ".名字: " + room[i].name + "\t種類: 蟲蟲");
                }
                room[i].printOutStatus();
            }
        }
    }

    /**
     * 印出正在交配的動物
     */
    public boolean checkAnimalBinding() {
//        所有連結顯示修改
        boolean haveNoBindingAnimal = true;
        for (int i = 0; i < room.length; i++) {
            if (room[i] == null) {
                continue;
            } else {
                if (room[i].binding != null) {
                    System.out.print("(" + (i + 1) + ")" + room[i].name
                            + "的連結對象為:" + room[i].binding.name + "!\t");
                    haveNoBindingAnimal = false;
                }
            }
        }
        if (haveNoBindingAnimal) {
            System.out.println("沒有連結中的寵物~");
        }
        return haveNoBindingAnimal;
    }

    /**
     * 印出包包所有東西
     */
    public void printOutAllItem() {
        boolean haveNoItem = true;
        for (int i = 0; i < bag.getItems().length; i++) {
            if (bag.getItems()[i] != null) {
                System.out.println((i + 1) + ". " + bag.getItems()[i].getChineseName() + " - " + bag.getItems()[i].getCount() + "個");
                haveNoItem = false;
            }
        }
        if (haveNoItem) {
            System.out.println("背包中沒有東西~");
        }
    }
//    public static void checkBackpack(Animal[] room, Bag[] backpack) {  // 背包
//        int len = room.length;
//        for (int i = 0; i < len; i++) {
//            if (room[i] != null) {
//                System.out.println("Pet");
//                System.out.println("編號: " + (i + 1) + ", " + "種類: " + room[i].type + ", " + "姓名: " + room[i].name);
//            }
//        }
//
//        int len2 = backpack.length;
//        for (int i = 0; i < len2; i++) {
//            if (backpack[i] != null) {
//                System.out.println("Backpack");
//                System.out.println("編號: " + (i + 1) + ", " + "種類: " + room[i].type + ", " + "數量: " + room[i].name);
//            }
//        }
//    }
}



