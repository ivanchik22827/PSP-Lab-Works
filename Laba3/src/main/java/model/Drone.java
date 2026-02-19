package model;

import model.interfaces.Maintainable;
import java.time.LocalDateTime;

public class Drone extends Aircraft implements Maintainable {
    private static final long serialVersionUID = 1L;

    private double batteryLife;
    private int cameraResolution;
    private boolean hasGPS;

    public Drone(String model, int yearOfRelease, int flightRange, int loadCapacity, double batteryLife, int cameraResolution, boolean hasGPS) {
        super(model, yearOfRelease, flightRange, loadCapacity);
        this.batteryLife = batteryLife;
        this.cameraResolution = cameraResolution;
        this.hasGPS = hasGPS;
    }

    @Override
    public String getType() {
        return "DRONE";
    }

    @Override
    public double calculateEfficiency() {
        return (double) (flightRange * batteryLife) / 100;
    }

    @Override
    public void performMaintenance(String description) {
        this.lastMaintenance = LocalDateTime.now();
        this.maintenanceHistory.add(LocalDateTime.now() + ": " + description);
    }

    @Override
    public boolean needsMaintenance() {
        if (lastMaintenance == null) return true;
        return lastMaintenance.plusMonths(1).isBefore(LocalDateTime.now());
    }

    @Override
    public LocalDateTime getNextMaintenanceDate() {
        if (lastMaintenance == null) return LocalDateTime.now();
        return lastMaintenance.plusMonths(1);
    }

    @Override
    public String getMaintenanceReport() {
        StringBuilder report = new StringBuilder();
        report.append("Maintenance Report for ").append(model).append(":\n");
        for (String record : maintenanceHistory) {
            report.append("- ").append(record).append("\n");
        }
        return report.toString();
    }

    public double getBatteryLife() {
        return batteryLife;
    }

    public void setBatteryLife(double batteryLife) {
        this.batteryLife = batteryLife;
    }

    public int getCameraResolution() {
        return cameraResolution;
    }

    public boolean hasGPS() {
        return hasGPS;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Battery: %.1f hours, Camera: %dMP, GPS: %s",
            batteryLife, cameraResolution, hasGPS ? "Yes" : "No");
    }
}