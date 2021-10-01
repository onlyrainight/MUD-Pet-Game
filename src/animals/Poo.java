package animals;

public class Poo {
    private int startExcrete;
    private int excretionFrequency;

    public Poo(int excretionFrequency) {
        this.excretionFrequency = excretionFrequency;
        this.startExcrete = 0;
    }

    public boolean willExcrete() {
        startExcrete++;
        return startExcrete == excretionFrequency;
    }

}
