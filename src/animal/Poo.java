package animal;

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
    public String toString(){
        return Integer.toString(startExcrete);
    }
}
