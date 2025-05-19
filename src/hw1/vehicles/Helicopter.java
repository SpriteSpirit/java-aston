package hw1.vehicles;

public class Helicopter extends Vehicle implements HasWheels, HasPropeller, CanCarryCargo {

    @Override
    public void loadCargo() {
        System.out.println(this.getClass().getName() + " load cargo");
    }

    @Override
    public void unloadCargo() {
        System.out.println(this.getClass().getName() + " unload cargo");
    }

    @Override
    public boolean isStart() {
        return true;
    }

    @Override
    public int getWheelCount() {
        return 2;
    }

    @Override
    void move() {
        System.out.println(this.getClass().getName() + " start moving...");
    }
}
