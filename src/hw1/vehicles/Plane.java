package hw1.vehicles;

public class Plane extends Vehicle implements HasWheels, HasPropeller, HasWings, CanCarryCargo {
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
        return false;
    }

    @Override
    public int getWheelCount() {
        return 2;
    }

    @Override
    public String wingType() {
        return "Straight";
    }

    @Override
    void move() {
         System.out.println(this.getClass().getName() + " start moving...");
    }
}
