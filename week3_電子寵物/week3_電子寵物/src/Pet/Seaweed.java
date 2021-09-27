package Pet;

public class Seaweed extends Item {

    public Seaweed() {
        super.setName("Seaweed");
        super.setChineseName("海草");
        super.setBuyIn(3);
        super.setSellOut(1);
    }

    @Override
    public String toString() {
        return "Seaweed{" +
                "name='" + super.getName() + '\'' +
                ", buyIn=" + super.getBuyIn() +
                ", sellOut=" +super.getSellOut() +
                '}';
    }
}
