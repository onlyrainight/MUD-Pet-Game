package Pet;

public class DogHair extends Item {

    public DogHair() {
        super.setName("DogHair");
        super.setChineseName("狗毛");
        super.setBuyIn(0);
        super.setSellOut(2);
    }

    @Override
    public String toString() {
        return "DogHair{" +
                "name='" + super.getName() + '\'' +
                ", buyIn=" + super.getBuyIn() +
                ", sellOut=" +super.getSellOut() +
                '}';
    }
}
