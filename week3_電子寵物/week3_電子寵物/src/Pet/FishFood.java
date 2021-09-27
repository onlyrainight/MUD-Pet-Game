package Pet;

public class FishFood extends Item {

    public FishFood() {
        super.setName("FishFood");
        super.setChineseName("魚飼料");
        super.setBuyIn(4);
        super.setSellOut(1);
    }

    @Override
    public String toString() {
        return "FishFood{" +
                "name='" + super.getName() + '\'' +
                ", buyIn=" + super.getBuyIn() +
                ", sellOut=" +super.getSellOut() +
                '}';
    }
}
