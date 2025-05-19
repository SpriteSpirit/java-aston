package hw1.animals;

public class Fish extends Animal implements Vertebrate, Waterfowl{
    @Override
    public boolean hasSpine() {
        return true;
    }

    @Override
    public boolean liveInWater() {
        return true;
    }

    @Override
    String getSpecies() {
        return "Pisces";
    }
}
