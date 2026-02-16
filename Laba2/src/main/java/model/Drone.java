package model;

import model.enums.TypeEnum;

public class Drone extends Aircraft {
    private final int weight;

    public Drone(int flightRange, int loadCapacity, String model, TypeEnum airplaneType, int yearOfRelease, int weight) {
        super(flightRange, loadCapacity, model, airplaneType, yearOfRelease);
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "[DRONE] " + super.toString() + ", Weight: " + weight + "kg";
    }
}
