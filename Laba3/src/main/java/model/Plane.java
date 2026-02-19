package model;

import model.interfaces.Maintainable;
import model.interfaces.Rentable;
import java.time.LocalDateTime;

public class Plane extends Aircraft implements Maintainable, Rentable {
    private static final long serialVersionUID = 1L;

    private int passengerCapacity;
    private double cruiseSpeed;
    private double rentalPrice;
    private boolean isRented;
    private String renterName;
    private int rentalDays;

    public Plane(String model, int yearOfRelease, int flightRange, int loadCapacity, int passengerCapacity, double cruiseSpeed) {
        super(model, yearOfRelease, flightRange, loadCapacity);
        this.passengerCapacity = passengerCapacity;
        this.cruiseSpeed = cruiseSpeed;
        this.rentalPrice = 5000.0;
        this.isRented = false;
    }

    @Override
    public String getType() {
        return "PLANE";
    }

    @Override
    public double calculateEfficiency() {
        return (double) (passengerCapacity * flightRange) / 10000;
    }

    @Override
    public void performMaintenance(String description) {
        this.lastMaintenance = LocalDateTime.now();
        this.maintenanceHistory.add(LocalDateTime.now() + ": " + description);
    }

    @Override
    public boolean needsMaintenance() {
        if (lastMaintenance == null) return true;
        return lastMaintenance.plusMonths(6).isBefore(LocalDateTime.now());
    }

    @Override
    public LocalDateTime getNextMaintenanceDate() {
        if (lastMaintenance == null) return LocalDateTime.now();
        return lastMaintenance.plusMonths(6);
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

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public double getCruiseSpeed() {
        return cruiseSpeed;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Passengers: %d, Cruise Speed: %.1f km/h",
            passengerCapacity, cruiseSpeed);
    }
}