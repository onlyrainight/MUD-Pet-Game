package items.drops;

import items.Item;
import utils.Global;

public class Sawdust extends Item {
    public Sawdust() {
        super.setName("木屑");
        super.setUsage("可至商店變賣");
        super.setType(Global.ItemType.SAWDUST);
    }

    @Override
    public int getSellOutPrice() {
        return 1;
    }

    @Override
    public int getBuyInPrice() {
        return 0;
    }
}
