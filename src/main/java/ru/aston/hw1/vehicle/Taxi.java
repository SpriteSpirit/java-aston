package ru.aston.hw1.vehicle;

public class Taxi extends Vehicle implements HasWheels {

    private static final int WHEEL_COUNT = 4;

    @Override
    public int getWheelCount() {
        return WHEEL_COUNT;
    }

    @Override
    public void move() {
        System.out.println(this.getClass().getName() + " start moving...");
    }
}
