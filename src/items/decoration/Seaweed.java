package items.decoration;

import items.Item;
import utils.Global;

public class Seaweed extends Item {
    public Seaweed() {
        super.setName("海草");
        super.setUsage("裝飾在寵物屋中，每個寵物屋限放一個" +
                "\n適合寵物:魚");
        super.setType(Global.ItemType.SEAWEED);
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
