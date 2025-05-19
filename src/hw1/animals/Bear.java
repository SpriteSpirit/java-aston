package hw1.animals;

public class Bear extends Mammal implements FurBearing{
    @Override
    public boolean hasFur() {
        return true;
    }

    @Override
    String getSpecies() {
        return "Ursus";
    }
}
