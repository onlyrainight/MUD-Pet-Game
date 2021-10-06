package game;


public class GameStaticConstantAndFunction {
    public final static int BUYING_MAX_AMOUNT = 20;
    public static final int ITEM_MAX_AMOUNT = 20;
    public final static int NAME_LIMIT = 5;
    public final static int INITIAL_MONEY = 100;
    public static boolean isDebug = true;

    public static void showChooseAnimal(String animals) {
        System.out.println(animals);
        System.out.println("請選擇一隻你要進行動作的寵物");
    }

    public static void showChooseItem(String items) {
        System.out.println(items);
        System.out.println("請選擇你要使用的物品");
    }

    public static void anotherDayMsg() {
        for (int i = 0; i < 3; i++) {
            System.out.println(".");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("又平安度過一天");
    }
}
