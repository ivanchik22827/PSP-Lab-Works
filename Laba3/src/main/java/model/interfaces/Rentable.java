package model.interfaces;

public interface Rentable {
    double getRentalPrice();
    void setRentalPrice(double price);
    boolean isAvailableForRent();
    void rent(String renterName, int days);
    void returnFromRent();
    String getRenterInfo();
}