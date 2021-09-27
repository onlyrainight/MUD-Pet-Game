package Pet;

public class InsectFood extends Item {

    public InsectFood() {
        super.setName("InsectFood");
        super.setChineseName("昆蟲飼料");
        super.setBuyIn(3);
        super.setSellOut(1);
    }

    @Override
    public String toString() {
        return "InsectFood{" +
                "name='" + super.getName() + '\'' +
                ", buyIn=" + super.getBuyIn() +
                ", sellOut=" +super.getSellOut() +
                '}';
    }
}
