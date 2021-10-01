package items.buffItems;

import items.Item;
import utils.Global;

public class AnimalRoomAdding extends Item implements Usable {

    public AnimalRoomAdding() {
        super.setName("擴增寵物格");
        super.setUsage("寵物格+" + getBuffNumber());
        super.setType(Global.ItemType.ANIMALROOMADDING);
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
