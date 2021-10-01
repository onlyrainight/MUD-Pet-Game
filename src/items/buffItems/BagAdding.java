package items.buffItems;

import items.Item;
import utils.Global;

public class BagAdding extends Item implements Usable {
    public BagAdding() {
        super.setName("擴增背包格");
        super.setUsage("背包格+" + getBuffNumber());
        super.setType(Global.ItemType.BAGADDING);
    }

    @Override
    public int getSellOutPrice() {
        return 0;
    }

    @Override
    public int getBuyInPrice() {
        return 10;
    }

    @Override
    public int getBuffNumber() {
        return 1;
    }
}

