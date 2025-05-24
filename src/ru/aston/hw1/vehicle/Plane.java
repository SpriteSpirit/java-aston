package ru.aston.hw1.vehicle;

public class Plane extends Vehicle implements HasWheels, HasPropeller, HasWings, CanCarryCargo {

    private static final int WHEEL_COUNT = 2;

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
        return WHEEL_COUNT;
    }

    @Override
    public String wingType() {
        return "Straight";
    }

    @Override
    public void move() {
        System.out.println(this.getClass().getName() + " start moving...");
    }
}
