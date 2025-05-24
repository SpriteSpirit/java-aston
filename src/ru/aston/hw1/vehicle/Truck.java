package ru.aston.hw1.vehicle;

public class Truck extends Vehicle implements HasWheels, CanCarryCargo {

    private static final int WHEEL_COUNT = 4;

    @Override
    public void loadCargo() {
        System.out.println(this.getClass().getName() + " load cargo");
    }

    @Override
    public void unloadCargo() {
        System.out.println(this.getClass().getName() + " unload cargo");
    }

    @Override
    public int getWheelCount() {
        return WHEEL_COUNT;
    }

    @Override
    public void move() {
        System.out.println(this.getClass().getName() + " start moving...");
    }
}
