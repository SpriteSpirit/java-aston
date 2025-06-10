package ru.aston.hw1.animal;

public class Bear extends Mammal implements FurBearing {

    @Override
    public boolean hasFur() {
        return true;
    }

    @Override
    public String getSpecies() {
        return "Ursus";
    }
}
