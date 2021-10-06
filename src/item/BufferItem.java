package item;

public class BufferItem extends Item {
    private int buffNumber;

    public BufferItem(Builder builder, int buffNumber) {
        super(builder);
        this.buffNumber = buffNumber;
    }

    public int getBuffNumber() {
        return buffNumber;
    }
}
