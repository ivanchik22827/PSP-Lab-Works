package model;

import model.interfaces.Maintainable;
import model.interfaces.Rentable;
import java.time.LocalDateTime;

public class Helicopter extends Aircraft implements Maintainable, Rentable {
    private static final long serialVersionUID = 1L;

    private int rotorDiameter;
    private double maxAltitude;
    private double rentalPrice;
    private boolean isRented;
    private String renterName;
    private int rentalDays;

    public Helicopter(String model, int yearOfRelease, int flightRange, int loadCapacity, int rotorDiameter, double maxAltitude) {
        super(model, yearOfRelease, flightRange, loadCapacity);
        this.rotorDiameter = rotorDiameter;
        this.maxAltitude = maxAltitude;
        this.rentalPrice = 3500.0;
        this.isRented = false;
    }

    @Override
    public String getType() {
        return "HELICOPTER";
    }

    @Override
    public double calculateEfficiency() {
        return (double) (loadCapacity * flightRange) / (rotorDiameter * 100);
    }

    @Override
    public void performMaintenance(String description) {
        this.lastMaintenance = LocalDateTime.now();
        this.maintenanceHistory.add(LocalDateTime.now() + ": " + description);
    }

    @Override
    public boolean needsMaintenance() {
        if (lastMaintenance == null) return true;
        return lastMaintenance.plusMonths(3).isBefore(LocalDateTime.now());
    }

    @Override
    public LocalDateTime getNextMaintenanceDate() {
        if (lastMaintenance == null) return LocalDateTime.now();
        return lastMaintenance.plusMonths(3);
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

    @Override
    public double getRentalPrice() {
        return rentalPrice;
    }

    @Override
    public void setRentalPrice(double price) {
        this.rentalPrice = price;
    }

    @Override
    public boolean isAvailableForRent() {
        return !isRented;
    }

    @Override
    public void rent(String renterName, int days) {
        this.isRented = true;
        this.renterName = renterName;
        this.rentalDays = days;
    }

    @Override
    public void returnFromRent() {
        this.isRented = false;
        this.renterName = null;
        this.rentalDays = 0;
    }

    @Override
    public String getRenterInfo() {
        if (isRented) {
            return "Rented to " + renterName + " for " + rentalDays + " days";
        }
        return "Not rented";
    }

    public int getRotorDiameter() {
        return rotorDiameter;
    }

    public double getMaxAltitude() {
        return maxAltitude;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Rotor: %dm, Max Altitude: %.1f m",
            rotorDiameter, maxAltitude);
    }
}