package hw1.animals;

abstract class Mammal extends Animal implements Vertebrate  {
    @Override
    abstract String getSpecies();

    @Override
    public boolean hasSpine() {
        return true;
    }
}
