package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Aircraft implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String model;
    protected int yearOfRelease;
    protected int flightRange;
    protected int loadCapacity;
    protected LocalDateTime lastMaintenance;
    protected List<String> maintenanceHistory;

    public Aircraft(String model, int yearOfRelease, int flightRange, int loadCapacity) {
        this.model = model;
        this.yearOfRelease = yearOfRelease;
        this.flightRange = flightRange;
        this.loadCapacity = loadCapacity;
        this.maintenanceHistory = new ArrayList<>();
    }

    public abstract String getType();

    public abstract double calculateEfficiency();

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public int getFlightRange() {
        return flightRange;
    }

    public void setFlightRange(int flightRange) {
        this.flightRange = flightRange;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(int loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public LocalDateTime getLastMaintenance() {
        return lastMaintenance;
    }

    public void setLastMaintenance(LocalDateTime lastMaintenance) {
        this.lastMaintenance = lastMaintenance;
    }

    public List<String> getMaintenanceHistory() {
        return maintenanceHistory;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (%d) - Range: %d km, Load: %d kg",
            getType(), model, yearOfRelease, flightRange, loadCapacity);
    }
}