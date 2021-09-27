package Pet;

public class FishScale extends Item {

    public FishScale() {
        super.setName("FishScale");
        super.setChineseName("魚鱗");
        super.setBuyIn(0);
        super.setSellOut(3);
    }

    @Override
    public String toString() {
        return "FishScale{" +
                "name='" + super.getName() + '\'' +
                ", buyIn=" + super.getBuyIn() +
                ", sellOut=" +super.getSellOut() +
                '}';
    }
}
