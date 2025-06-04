package main.java.ru.aston.hw1.vehicle;

public class Speedboat extends Vehicle implements HasPropeller, CanCarryCargo {

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
    public void move() {
        System.out.println(this.getClass().getName() + " start moving...");
    }
}
