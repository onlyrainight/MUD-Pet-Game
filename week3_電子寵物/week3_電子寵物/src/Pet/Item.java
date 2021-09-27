package Pet;


public class Item {
    private String name, chineseName;
    private int buyIn;
    private int sellOut;
    private int count = 0;
    private int itemsLimit = 20;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChineseName(String name) {
        this.chineseName = name;
    }

    public String getChineseName() {
        return this.chineseName;
    }

    public int getBuyIn() {
        return buyIn;
    }

    public void setBuyIn(int buyIn) {
        this.buyIn = buyIn;
    }

    public int getSellOut() {
        return sellOut;
    }

    public void setSellOut(int sellOut) {
        this.sellOut = sellOut;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getItemsLimit() {
        return itemsLimit;
    }

    public void setItemsLimit(int itemsLimit) {
        this.itemsLimit = itemsLimit;
    }



    @Override
    public String toString() {
        return name + ": " + count;
    }
}
