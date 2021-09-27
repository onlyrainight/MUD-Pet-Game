package Pet;

import java.util.Scanner;

public class Fish extends Animal{

    public int fish_Consumption = 1;
    public int fish_Hungry = 5;
    public int fish_Sex = (int) ((Math.random()*100) % 2 + 1);
    public int fish_Walk = 999999999; //不能散步
    public int fish_Bored = 999999999; //不會無聊
    public int fish_Sleep = 8;
    public int fish_SleepLast = 1;
    public int fish_BindingTime = 3;
    public int fish_Excretion = 2;
    public int fish_Production = 7;
    public int fish_HungryToDie = 3;
    public int fish_BoredToDie = 999999999; //不會無聊
    public int fish_Shit = 20;
    public int fish_Estrus = 8;
    public int fish_Overfull = fish_Hungry /2;

    public Fish() {
        this.consumption = fish_Consumption;
        this.initialFeed = 5;
        this.feed = initialFeed;
        this.hungry = fish_Hungry;
        this.sex = fish_Sex;
        this.walk = fish_Walk;
        this.bored = fish_Bored;
        this.sleep = fish_Sleep;
        this.sleepLast = fish_SleepLast;
        this.bindingTime = fish_BindingTime;
        this.excretion = fish_Excretion;
        this.production = fish_Production;
        this.hungryToDie = fish_HungryToDie;
        this.boredToDie = fish_BoredToDie;
        this.life = true;
        this.shit = fish_Shit;
        this.type = "Fish";
        this.estrus = fish_Estrus;
        this.overfull = fish_Overfull;
        this.product = "fishscale";
        this.likeDectorate = "seaweed";
    }

    @Override
    public void resetSleep() {
        this.sleep = fish_Sleep;
        this.sleepLast = fish_SleepLast;
    }

    @Override
    public void resetFeed() {
        this.feed = initialFeed;
        this.hungry = fish_Hungry;
    }

    @Override
    public void resetExcertion() {
        this.excretion = fish_Excretion;
    }

    @Override
    public void resetShit() {
        this.shit = fish_Shit;
    }

    @Override
    public void resetProduction() {
        this.production = fish_Production;
    }

    @Override
    public void resetBindingTime() {
        this.bindingTime = fish_BindingTime;
    }

    @Override
    public Animal giveBirth() {
        Fish babyFish = new Fish();
        Scanner sc = new Scanner(System.in);
        System.out.println("寶寶要取甚麼名字呢?\t寶寶名字:");
        babyFish.name = sc.nextLine();
        return babyFish;
    }

    @Override
    public void resetEstrus() {
        this.estrus = fish_Estrus;
    }

    @Override
    public void resetOverfull() {
        this.overfull = fish_Overfull;
    }
}
