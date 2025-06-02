package main.java.ru.aston.hw1.animal;

public class Fish extends Animal implements Vertebrate, Waterfowl {

    @Override
    public boolean hasSpine() {
        return true;
    }

    @Override
    public boolean liveInWater() {
        return true;
    }

    @Override
    public String getSpecies() {
        return "Pisces";
    }
}
