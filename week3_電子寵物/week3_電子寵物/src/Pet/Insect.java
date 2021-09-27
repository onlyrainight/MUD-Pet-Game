package Pet;

import java.util.Scanner;

public class Insect extends Animal {

    public int insect_Consumption = 1;
    public int insect_Hungry = 5;
    public int insect_Sex = (int) ((Math.random()*100) % 2 + 1);
    public int insect_Walk = 999999999; //不能散步
    public int insect_Bored = 999999999; //不會無聊
    public int insect_Sleep = 999999999; //不用睡覺
    public int insect_SleepLast = 999999999; //不用睡覺
    public int insect_BindingTime = 2;
    public int insect_Excretion = 1;
    public int insect_Production = 4;
    public int insect_HungryToDie = 10;
    public int insect_BoredToDie = 999999999; //不會無聊
    public int insect_Shit = 30;
    public int insect_Estrus = 13;
    public int insect_Overfull = insect_Hungry /2;

    public Insect() {
        this.consumption = insect_Consumption;
        this.initialFeed = 4;
        this.feed = initialFeed;
        this.hungry = insect_Hungry;
        this.sex = insect_Sex;
        this.walk = insect_Walk; //不能散步
        this.bored = insect_Bored; //不會無聊
        this.sleep = insect_Sleep; //不用睡覺
        this.sleepLast = insect_SleepLast; //不用睡覺
        this.bindingTime = insect_BindingTime;
        this.excretion = insect_Excretion;
        this.production = insect_Production;
        this.hungryToDie = insect_HungryToDie;
        this.boredToDie = insect_BoredToDie; //不會無聊
        this.life = true;
        this.shit = insect_Shit;
        this.type = "Insect";
        this.estrus = insect_Estrus;
        this.overfull = insect_Overfull;
        this.product = "sawdust";
    }

    @Override
    public void resetSleep() {

    }

    @Override
    public void resetFeed() {
        this.feed = initialFeed;
        this.hungry = insect_Hungry;
    }

    @Override
    public void resetExcertion() {
        this.excretion = insect_Excretion;
    }

    @Override
    public void resetShit() {
        this.shit = insect_Shit;
    }

    @Override
    public void resetProduction() {
        this.production = insect_Production;
    }

    @Override
    public void resetBindingTime() {
        this.bindingTime = insect_BindingTime;
    }

    @Override
    public Animal giveBirth() {
        Insect babyInsect = new Insect();
        Scanner sc = new Scanner(System.in);
        System.out.println("寶寶要取甚麼名字呢?\t寶寶名字:");
        babyInsect.name = sc.nextLine();
        return babyInsect;
    }

    @Override
    public void resetEstrus() {
        this.estrus = insect_Estrus;
    }

    @Override
    public void resetOverfull() {
        this.overfull = insect_Overfull;
    }

}
