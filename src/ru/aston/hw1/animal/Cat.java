package ru.aston.hw1.animal;

public class Cat extends Mammal implements FurBearing {

    @Override
    public boolean hasFur() {
        return true;
    }

    @Override
    public String getSpecies() {
        return "Felis catus";
    }
}
