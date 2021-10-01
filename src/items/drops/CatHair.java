package items.drops;

import items.Item;
import utils.Global;

public class CatHair extends Item {

    public CatHair() {
        super.setName("貓毛");
        super.setUsage("可至商店變賣");
        super.setType(Global.ItemType.CATHAIR);
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
