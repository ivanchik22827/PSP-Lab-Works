package model;

import model.enums.TypeEnum;

public class Airplane extends Aircraft{
    private final int capacity;

    public Airplane(int flightRange, int loadCapacity, String model, TypeEnum airplaneType, int yearOfRelease, int capacity) {
        super(flightRange, loadCapacity, model, airplaneType, yearOfRelease);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "[AIRPLANE] " + super.toString() + ", Passengers: " + capacity;
    }
}
