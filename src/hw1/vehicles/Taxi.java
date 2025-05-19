package hw1.vehicles;

public class Taxi extends Vehicle implements HasWheels {
    @Override
    public int getWheelCount() {
        return 2;
    }

    @Override
    void move() {
        System.out.println(this.getClass().getName() + " start moving...");
    }
}
