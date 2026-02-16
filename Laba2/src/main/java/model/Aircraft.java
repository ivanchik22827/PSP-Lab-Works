package model;

import model.enums.TypeEnum;

public class Aircraft {
    private int flightRange;
    private int loadCapacity;
    private String model;
    private TypeEnum airplaneType;
    private int yearOfRelease;

    public Aircraft(int flightRange, int loadCapacity, String model, TypeEnum airplaneType, int yearOfRelease) {
        this.flightRange = flightRange;
        this.loadCapacity = loadCapacity;
        this.model = model;
        this.airplaneType = airplaneType;
        this.yearOfRelease = yearOfRelease;
    }

    public int getFlightRange() {
        return flightRange;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }

    public String getModel() {
        return model;
    }

    public TypeEnum getAirplaneType() {
        return airplaneType;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setFlightRange(int flightRange) {
        this.flightRange = flightRange;
    }

    public void setLoadCapacity(int loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    @Override
    public String toString() {
        return model + " (" + yearOfRelease + ") - Range: " + flightRange + "km, Load: " + loadCapacity + "kg";
    }
}
