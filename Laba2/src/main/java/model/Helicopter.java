package model;

import model.enums.TypeEnum;

public class Helicopter extends Aircraft{
    private final int maxHeight;

    public Helicopter(int flightRange, int loadCapacity, String model, TypeEnum airplaneType, int yearOfRelease, int maxHeight) {
        super(flightRange, loadCapacity, model, airplaneType, yearOfRelease);
        this.maxHeight = maxHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    @Override
    public String toString() {
        return "[HELICOPTER] " + super.toString() + ", Max Height: " + maxHeight + "m";
    }
}
