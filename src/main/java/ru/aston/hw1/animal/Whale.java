package ru.aston.hw1.animal;

public class Whale extends Mammal implements Waterfowl {

    @Override
    public boolean liveInWater() {
        return true;
    }

    @Override
    public String getSpecies() {
        return "Cetacea";
    }
}
