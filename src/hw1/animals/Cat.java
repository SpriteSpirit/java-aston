package hw1.animals;

public class Cat extends Mammal implements FurBearing {
    @Override
    public boolean hasFur() {
        return true;
    }

    @Override
    String getSpecies() {
        return "Felis catus";
    }
}
