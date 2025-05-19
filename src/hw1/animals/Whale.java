package hw1.animals;

public class Whale extends Mammal implements Waterfowl {

    @Override
    public boolean liveInWater() {
        return true;
    }

    @Override
    String getSpecies() {
        return "Cetacea";
    }
}
