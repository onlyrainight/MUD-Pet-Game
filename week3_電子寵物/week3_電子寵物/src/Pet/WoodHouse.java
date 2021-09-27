package Pet;

public class WoodHouse extends Item {

    public WoodHouse() {
        super.setName("WoodHouse");
        super.setChineseName("木屋");
        super.setBuyIn(4);
        super.setSellOut(2);
    }

    @Override
    public String toString() {
        return "WoodHouse{" +
                "name='" + super.getName() + '\'' +
                ", buyIn=" + super.getBuyIn() +
                ", sellOut=" +super.getSellOut() +
                '}';
    }
}
