package Pet;

import java.util.Scanner;

public class Dog extends Animal{

    public int dog_Consumption = 2;
    public int dog_Hungry = 4;
    public int dog_Sex = (int) ((Math.random()*100) % 2 + 1);
    public int dog_Bored = 6;
    public int dog_Sleep = 7;
    public int dog_SleepLast = 2;
    public int dog_BindingTime = 2;
    public int dog_Excretion = 3;
    public int dog_Production = 5;
    public int dog_HungryToDie = 4;
    public int dog_BoredToDie = 6;
    public int dog_Shit = 5;
    public int dog_Estrus = 3;
    public int dog_Overfull = dog_Hungry /2;

    public Dog() {
        this.consumption = dog_Consumption;
        this.initialFeed = 4;
        this.feed = initialFeed;
        this.hungry = dog_Hungry;
        this.sex = dog_Sex;
        this.initialWalk = 3;
        this.walk = initialWalk;
        this.bored = dog_Bored;
        this.sleep = dog_Sleep;
        this.sleepLast = dog_SleepLast;
        this.bindingTime = dog_BindingTime;
        this.excretion = dog_Excretion;
        this.production = dog_Production;
        this.hungryToDie = dog_HungryToDie;
        this.boredToDie = dog_BoredToDie;
        this.life = true;
        this.shit = dog_Shit;
        this.type = "Dog";
        this.estrus = dog_Estrus;
        this.overfull = dog_Overfull;
        this.product = "doghair";
        this.likeDectorate = "woodhouse";
    }

    @Override
    public void resetSleep() {
        this.sleep = dog_Sleep;
        this.sleepLast = dog_SleepLast;
    }

    @Override
    public void resetFeed() {
        this.feed = initialFeed;
        this.hungry = dog_Hungry;
    }

    @Override
    public void resetExcertion() {
        this.excretion = dog_Excretion;
    }

    @Override
    public void resetShit() {
        this.shit = dog_Shit;
    }

    @Override
    public void resetWalk() {
        this.walk = initialWalk;
        this.bored = dog_Bored;
    }

    @Override
    public void resetProduction() {
        this.production = dog_Production;
    }

    @Override
    public void resetBindingTime() {
        this.bindingTime = dog_BindingTime;
    }

    @Override
    public Animal giveBirth() {
        Dog babyDog = new Dog();
        Scanner sc = new Scanner(System.in);
        System.out.println("寶寶要取甚麼名字呢?\t寶寶名字:");
        babyDog.name = sc.nextLine();
        return babyDog;
    }

    @Override
    public void resetEstrus() {
        this.estrus = dog_Estrus;
    }

    @Override
    public void resetOverfull() {
        this.overfull = dog_Overfull;
    }
}
