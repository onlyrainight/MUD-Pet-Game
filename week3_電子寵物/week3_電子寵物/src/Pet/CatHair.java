package Pet;

public class CatHair extends Item {

    public CatHair() {
        super.setName("CatHair");
        super.setChineseName("貓毛");
        super.setBuyIn(0);
        super.setSellOut(1);
    }

    @Override
    public String toString() {
        return "CatHair{" +
                "name='" + super.getName() + '\'' +
                ", buyIn=" + super.getBuyIn() +
                ", sellOut=" +super.getSellOut() +
                '}';
    }
}
