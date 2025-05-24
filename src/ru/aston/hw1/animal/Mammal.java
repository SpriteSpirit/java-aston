package ru.aston.hw1.animal;

abstract class Mammal extends Animal implements Vertebrate {

    @Override
    abstract String getSpecies();

    @Override
    public boolean hasSpine() {
        return true;
    }
}
