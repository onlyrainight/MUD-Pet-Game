package Pet;

public class CannedFood extends Item {

    public CannedFood() {
        super.setName("CannedFood");
        super.setChineseName("罐頭食物");
        super.setBuyIn(5);
        super.setSellOut(2);
    }

    @Override
    public String toString() {
        return "CannedFood{" +
                "name='" + super.getName() + '\'' +
                ", buyIn=" + super.getBuyIn() +
                ", sellOut=" +super.getSellOut() +
                '}';
    }
}
