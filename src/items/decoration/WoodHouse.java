package items.decoration;

import items.Item;
import utils.Global;

public class WoodHouse extends Item {
    public WoodHouse() {
        super.setName("木屋");
        super.setUsage("裝飾在寵物屋中，每個寵物屋限放一個" +
                "\n適合寵物:狗、昆蟲");
        super.setType(Global.ItemType.WOODHOUSE);
    }
    @Override
    public int getSellOutPrice() {
        return 2;
    }

    @Override
    public int getBuyInPrice() {
        return 4;
    }
}
