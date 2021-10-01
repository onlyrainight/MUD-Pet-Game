package items.food;

import items.Item;
import utils.Global;

public class CannedFood extends Item {
    public CannedFood() {
        super.setName("罐頭飼料");
        super.setUsage("只能給貓及狗吃");
        super.setType(Global.ItemType.CANNEDFOOD);
    }

    @Override
    public int getSellOutPrice() {
        return 2;
    }

    @Override
    public int getBuyInPrice() {
        return 5;
    }
}