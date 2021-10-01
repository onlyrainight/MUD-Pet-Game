package items.decoration;

import items.Item;
import utils.Global;

public class Carton extends Item {
    public Carton() {
        super.setName("紙箱");
        super.setUsage("裝飾在寵物屋中，每個寵物屋限放一個" +
                "\n適合寵物:狗、貓");
        super.setType(Global.ItemType.CARTON);
    }
    @Override
    public int getSellOutPrice() {
        return 1;
    }

    @Override
    public int getBuyInPrice() {
        return 3;
    }
}
