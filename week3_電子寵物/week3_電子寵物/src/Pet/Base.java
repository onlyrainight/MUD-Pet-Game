package Pet;

public class Base {
    private int count = 0;
    private String typeName;

    public Base(String type) {
        this.typeName = type;
    }

    // 確認同種物品而且沒有滿，如果條件都達到才放入物品
    boolean checkIsSameItemAndNotFull(String typeName) {
        return isSameItem(typeName) && !isFull();
    }

    boolean checkIsSameItemAndNotEmpty(String typeName) {
        return isSameItem(typeName) && !isEmpty();
    }

    // 放入物品
    void addItem() {
        count += 1;
    }

    void minusItem() {
        count -= 1;
    }

    void minusItems(int num) {
        count -= num;
    }

    int getItemSize() {
        return this.count;
    }

    String getTypeName() {
        return this.typeName;
    }

    // 確認同一種物品
    private boolean isSameItem(String typeName) {
        return this.typeName == typeName;
    }

    // 確認滿了沒
    private boolean isFull() {
        return this.count == 20;
    }

    private boolean isEmpty() {
        return this.count == 0;
    }
}
