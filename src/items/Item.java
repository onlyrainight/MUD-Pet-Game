package items;

import utils.Global;
import utils.Tradable;

public abstract class Item implements Tradable {
    private int amount = 0;
    private String name;
    private String usage;
    private Global.ItemType type;

    public Item() {

    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public String getUsage() {
        return usage;
    }

    public Global.ItemType getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public void setType(Global.ItemType type) {
        this.type = type;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addAmount(int value) {
        amount += value;
    }

    public String itemExplanation() {
        return getName() + "\t售價： " + getBuyInPrice() + "\t用途： " + getUsage();
    }
}
