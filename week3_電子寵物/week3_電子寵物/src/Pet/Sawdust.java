package Pet;

public class Sawdust extends Item {

    public Sawdust() {
        super.setName("Sawdust");
        super.setChineseName("木屑");
        super.setBuyIn(0);
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
