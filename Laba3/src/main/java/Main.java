import manager.AirportManager;
import model.*;
import model.interfaces.Maintainable;
import model.interfaces.Rentable;
import utils.InputValidator;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static AirportManager manager = new AirportManager();
    private static Scanner scanner = InputValidator.getScanner();
    private static boolean isAdmin = false;

    public static void main(String[] args) {
        System.out.println("  Airport Management System   ");

        while (true) {
            showMainMenu();
            int choice = InputValidator.getIntInRange("Choose option: ", 0, 20);

            try {
                switch (choice) {
                    case 1:
                        addPlane();
                        break;
                    case 2:
                        addHelicopter();
                        break;
                    case 3:
                        addDrone();
                        break;
                    case 4:
                        viewAllAircrafts();
                        break;
                    case 5:
                        deleteAircraft();
                        break;
                    case 6:
                        editAircraft();
                        break;
                    case 7:
                        searchByModel();
                        break;
                    case 8:
                        filterMenu();
                        break;
                    case 9:
                        sortMenu();
                        break;
                    case 10:
                        showStatistics();
                        break;
                    case 11:
                        performMaintenance();
                        break;
                    case 12:
                        showMaintenanceNeeded();
                        break;
                    case 13:
                        rentAircraft();
                        break;
                    case 14:
                        returnAircraft();
                        break;
                    case 15:
                        showAvailableForRent();
                        break;
                    case 16:
                        saveData();
                        break;
                    case 17:
                        loadData();
                        break;
                    case 18:
                        exportToCSV();
                        break;
                    case 19:
                        importFromCSV();
                        break;
                    case 20:
                        toggleAdminMode();
                        break;
                    case 0:
                        System.out.println("Thank you for using Airport Management System!");
                        return;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("\n      MAIN MENU       ");
        System.out.println("Current mode: " + (isAdmin ? "ADMIN" : "USER"));
        System.out.println("-----------------------------------");
        System.out.println("    Aircraft Management    ");
        System.out.println("1. Add Plane");
        System.out.println("2. Add Helicopter");
        System.out.println("3. Add Drone");
        System.out.println("4. View All Aircrafts");
        if (isAdmin) {
            System.out.println("5. Delete Aircraft (ADMIN)");
            System.out.println("6. Edit Aircraft (ADMIN)");
        } else {
            System.out.println("5. [Locked - Admin only]");
            System.out.println("6. [Locked - Admin only]");
        }

        System.out.println("\n    Search & Filter    ");
        System.out.println("7. Search by Model");
        System.out.println("8. Filter Options");
        System.out.println("9. Sort Options");

        System.out.println("\n    Statistics    ");
        System.out.println("10. Show Statistics");

        System.out.println("\n    Maintenance & Rental    ");
        if (isAdmin) {
            System.out.println("11. Perform Maintenance (ADMIN)");
        } else {
            System.out.println("11. [Locked - Admin only]");
        }
        System.out.println("12. Show Aircrafts Needing Maintenance");
        if (isAdmin) {
            System.out.println("13. Rent Aircraft (ADMIN)");
            System.out.println("14. Return Aircraft (ADMIN)");
        } else {
            System.out.println("13. [Locked - Admin only]");
            System.out.println("14. [Locked - Admin only]");
        }
        System.out.println("15. Show Available for Rent");

        System.out.println("\n    Data Operations    ");
        System.out.println("16. Save Data");
        System.out.println("17. Load Data");
        System.out.println("18. Export to CSV");
        System.out.println("19. Import from CSV");

        System.out.println("\n    System    ");
        System.out.println("20. Toggle Admin/User Mode");
        System.out.println("0. Exit");
        System.out.println("-----------------------------------");
    }

    private static void addPlane() {
        System.out.println("\n    Add New Plane    ");
        String model = InputValidator.getNonEmptyString("Enter model: ");
        int year = InputValidator.getIntInRange("Enter year of release: ", 1900, 2024);
        int range = InputValidator.getIntInRange("Enter flight range (km): ", 100, 20000);
        int loadCapacity = InputValidator.getIntInRange("Enter load capacity (kg): ", 1000, 500000);
        int passengers = InputValidator.getIntInRange("Enter passenger capacity: ", 1, 800);
        double speed = InputValidator.getDoubleInRange("Enter cruise speed (km/h): ", 100, 1000);

        Plane plane = new Plane(model, year, range, loadCapacity, passengers, speed);
        manager.addAircraft(plane);
        System.out.println("Plane added successfully!");
    }

    private static void addHelicopter() {
        System.out.println("\n    Add New Helicopter    ");
        String model = InputValidator.getNonEmptyString("Enter model: ");
        int year = InputValidator.getIntInRange("Enter year of release: ", 1900, 2024);
        int range = InputValidator.getIntInRange("Enter flight range (km): ", 50, 5000);
        int loadCapacity = InputValidator.getIntInRange("Enter load capacity (kg): ", 100, 50000);
        int rotorDiameter = InputValidator.getIntInRange("Enter rotor diameter (m): ", 5, 30);
        double maxAltitude = InputValidator.getDoubleInRange("Enter max altitude (m): ", 100, 10000);

        Helicopter helicopter = new Helicopter(model, year, range, loadCapacity, rotorDiameter, maxAltitude);
        manager.addAircraft(helicopter);
        System.out.println("Helicopter added successfully!");
    }

    private static void addDrone() {
        System.out.println("\n    Add New Drone    ");
        String model = InputValidator.getNonEmptyString("Enter model: ");
        int year = InputValidator.getIntInRange("Enter year of release: ", 2000, 2024);
        int range = InputValidator.getIntInRange("Enter flight range (km): ", 1, 1000);
        int loadCapacity = InputValidator.getIntInRange("Enter load capacity (kg): ", 1, 1000);
        double batteryLife = InputValidator.getDoubleInRange("Enter battery life (hours): ", 0.5, 24);
        int cameraRes = InputValidator.getIntInRange("Enter camera resolution (MP): ", 1, 100);
        boolean hasGPS = InputValidator.getBoolean("Has GPS?");

        Drone drone = new Drone(model, year, range, loadCapacity, batteryLife, cameraRes, hasGPS);
        manager.addAircraft(drone);
        System.out.println("Drone added successfully!");
    }

    private static void viewAllAircrafts() {
        List<Aircraft> aircrafts = manager.getAllAircrafts();
        if (aircrafts.isEmpty()) {
            System.out.println("No aircrafts in the system.");
        } else {
            System.out.println("\n    All Aircrafts    ");
            for (Aircraft aircraft : aircrafts) {
                System.out.println(aircraft);
            }
            System.out.println("Total: " + aircrafts.size() + " aircrafts");
        }
    }

    private static void deleteAircraft() {
        if (!isAdmin) {
            System.out.println("Admin access required!");
            return;
        }
        String model = InputValidator.getNonEmptyString("Enter model to delete: ");
        if (manager.removeAircraft(model)) {
            System.out.println("Aircraft deleted successfully!");
        } else {
            System.out.println("Aircraft not found!");
        }
    }

    private static void editAircraft() {
        if (!isAdmin) {
            System.out.println("Admin access required!");
            return;
        }
        String model = InputValidator.getNonEmptyString("Enter model to edit: ");
        Aircraft aircraft = manager.findByModel(model);
        if (aircraft == null) {
            System.out.println("Aircraft not found!");
            return;
        }

        System.out.println("Current: " + aircraft);
        String editChoice = InputValidator.getChoice("What to edit?", "Flight Range", "Load Capacity");

        if (editChoice.equals("Flight Range")) {
            int newRange = InputValidator.getIntInRange("Enter new flight range: ", 1, 20000);
            manager.editFlightRange(model, newRange);
        } else {
            int newCapacity = InputValidator.getIntInRange("Enter new load capacity: ", 1, 500000);
            manager.editLoadCapacity(model, newCapacity);
        }
        System.out.println("Edit successful!");
    }

    private static void searchByModel() {
        String keyword = InputValidator.getNonEmptyString("Enter search keyword: ");
        List<Aircraft> results = manager.searchByKeyword(keyword);
        if (results.isEmpty()) {
            System.out.println("No aircrafts found.");
        } else {
            System.out.println("\n    Search Results    ");
            for (Aircraft aircraft : results) {
                System.out.println(aircraft);
            }
        }
    }

    private static void filterMenu() {
        System.out.println("\n    Filter Options    ");
        System.out.println("1. Filter by Type");
        System.out.println("2. Filter by Year");
        System.out.println("3. Filter by Year Range");
        System.out.println("4. Filter by Flight Range");
        System.out.println("5. Combined Filter");
        System.out.println("0. Back");

        int choice = InputValidator.getIntInRange("Choose: ", 0, 5);
        List<Aircraft> results = null;

        switch (choice) {
            case 1:
                String type = InputValidator.getChoice("Select type:", "PLANE", "HELICOPTER", "DRONE");
                results = manager.filterByType(type);
                break;
            case 2:
                int year = InputValidator.getIntInRange("Enter year: ", 1900, 2024);
                results = manager.filterByYear(year);
                break;
            case 3:
                int startYear = InputValidator.getIntInRange("Enter start year: ", 1900, 2024);
                int endYear = InputValidator.getIntInRange("Enter end year: ", startYear, 2024);
                results = manager.filterByYearRange(startYear, endYear);
                break;
            case 4:
                int minRange = InputValidator.getIntInRange("Enter min range: ", 0, 20000);
                int maxRange = InputValidator.getIntInRange("Enter max range: ", minRange, 20000);
                results = manager.filterByFlightRange(minRange, maxRange);
                break;
            case 5:
                combinedFilter();
                return;
            case 0:
                return;
        }

        if (results != null) {
            if (results.isEmpty()) {
                System.out.println("No aircrafts match the filter.");
            } else {
                System.out.println("\n    Filter Results    ");
                for (Aircraft aircraft : results) {
                    System.out.println(aircraft);
                }
                System.out.println("Found: " + results.size() + " aircrafts");
            }
        }
    }

    private static void combinedFilter() {
        System.out.println("\n    Combined Filter  ");
        System.out.println("Leave empty to skip a filter");

        String typeInput = scanner.nextLine();
        String type = typeInput.isEmpty() ? null : typeInput.toUpperCase();

        System.out.print("Min year (or press Enter to skip): ");
        String minYearStr = scanner.nextLine();
        Integer minYear = minYearStr.isEmpty() ? null : Integer.parseInt(minYearStr);

        System.out.print("Max year (or press Enter to skip): ");
        String maxYearStr = scanner.nextLine();
        Integer maxYear = maxYearStr.isEmpty() ? null : Integer.parseInt(maxYearStr);

        System.out.print("Min range (or press Enter to skip): ");
        String minRangeStr = scanner.nextLine();
        Integer minRange = minRangeStr.isEmpty() ? null : Integer.parseInt(minRangeStr);

        System.out.print("Max range (or press Enter to skip): ");
        String maxRangeStr = scanner.nextLine();
        Integer maxRange = maxRangeStr.isEmpty() ? null : Integer.parseInt(maxRangeStr);

        List<Aircraft> results = manager.filterCombined(type, minYear, maxYear, minRange, maxRange);
        if (results.isEmpty()) {
            System.out.println("No aircrafts match the combined filter.");
        } else {
            System.out.println("\n    Combined Filter Results    ");
            for (Aircraft aircraft : results) {
                System.out.println(aircraft);
            }
            System.out.println("Found: " + results.size() + " aircrafts");
        }
    }

    private static void sortMenu() {
        System.out.println("\n    Sort Options    ");
        System.out.println("1. Sort by Model");
        System.out.println("2. Sort by Flight Range");
        System.out.println("3. Sort by Year");
        System.out.println("4. Sort by Load Capacity");
        System.out.println("5. Multi-field Sort");
        System.out.println("0. Back");

        int choice = InputValidator.getIntInRange("Choose: ", 0, 5);
        if (choice == 0) return;

        boolean ascending = InputValidator.getBoolean("Ascending order?");
        List<Aircraft> results = null;

        switch (choice) {
            case 1:
                results = manager.sortByModel(ascending);
                break;
            case 2:
                results = manager.sortByFlightRange(ascending);
                break;
            case 3:
                results = manager.sortByYear(ascending);
                break;
            case 4:
                results = manager.sortByLoadCapacity(ascending);
                break;
            case 5:
                String field1 = InputValidator.getChoice("First sort field:", "model", "year", "range", "capacity");
                boolean asc1 = InputValidator.getBoolean("First field ascending?");
                String field2 = InputValidator.getChoice("Second sort field:", "model", "year", "range", "capacity");
                boolean asc2 = InputValidator.getBoolean("Second field ascending?");
                results = manager.sortByMultipleFields(field1, asc1, field2, asc2);
                break;
        }

        if (results != null) {
            System.out.println("\n    Sorted Results    ");
            for (Aircraft aircraft : results) {
                System.out.println(aircraft);
            }
        }
    }

    private static void showStatistics() {
        System.out.println("\n         STATISTICS          ");

        Map<String, Long> typeCount = manager.countByType();
        System.out.println("\n    Count by Type    ");
        for (Map.Entry<String, Long> entry : typeCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("\n    Flight Range Statistics    ");
        System.out.printf("Average flight range: %.2f km%n", manager.getAverageFlightRange());

        Aircraft minRange = manager.getMinFlightRange();
        Aircraft maxRange = manager.getMaxFlightRange();

        if (minRange != null) {
            System.out.println("Min flight range: " + minRange.getModel() + " (" + minRange.getFlightRange() + " km)");
        }
        if (maxRange != null) {
            System.out.println("Max flight range: " + maxRange.getModel() + " (" + maxRange.getFlightRange() + " km)");
        }

        System.out.println("\n    Average Range by Type    ");
        System.out.printf("Planes: %.2f km%n", manager.getAverageFlightRangeByType("PLANE"));
        System.out.printf("Helicopters: %.2f km%n", manager.getAverageFlightRangeByType("HELICOPTER"));
        System.out.printf("Drones: %.2f km%n", manager.getAverageFlightRangeByType("DRONE"));

        System.out.println("\n    Top 3 by Load Capacity    ");
        List<Aircraft> top3 = manager.getTopNByLoadCapacity(3);
        for (int i = 0; i < top3.size(); i++) {
            Aircraft a = top3.get(i);
            System.out.printf("%d. %s - %d kg%n", i + 1, a.getModel(), a.getLoadCapacity());
        }

        Map<String, Double> summary = manager.getStatisticsSummary();
        System.out.println("\n    Summary    ");
        System.out.printf("Total aircrafts: %.0f%n", summary.get("totalCount"));
        System.out.printf("Average year: %.0f%n", summary.get("averageYear"));
        System.out.printf("Average load capacity: %.2f kg%n", summary.get("averageLoadCapacity"));
    }

    private static void performMaintenance() {
        if (!isAdmin) {
            System.out.println("Admin access required!");
            return;
        }
        String model = InputValidator.getNonEmptyString("Enter aircraft model: ");
        Aircraft aircraft = manager.findByModel(model);

        if (aircraft == null) {
            System.out.println("Aircraft not found!");
            return;
        }

        if (!(aircraft instanceof Maintainable)) {
            System.out.println("This aircraft does not support maintenance tracking!");
            return;
        }

        String description = InputValidator.getNonEmptyString("Enter maintenance description: ");
        manager.performMaintenance(model, description);
        System.out.println("Maintenance recorded successfully!");
    }

    private static void showMaintenanceNeeded() {
        List<Aircraft> needingMaintenance = manager.getAircraftNeedingMaintenance();
        if (needingMaintenance.isEmpty()) {
            System.out.println("No aircrafts need maintenance at this time.");
        } else {
            System.out.println("\n    Aircrafts Needing Maintenance    ");
            for (Aircraft aircraft : needingMaintenance) {
                Maintainable m = (Maintainable) aircraft;
                System.out.println(aircraft.getModel() + " - Next maintenance: " + m.getNextMaintenanceDate());
            }
        }
    }

    private static void rentAircraft() {
        if (!isAdmin) {
            System.out.println("Admin access required!");
            return;
        }
        String model = InputValidator.getNonEmptyString("Enter aircraft model: ");
        String renterName = InputValidator.getNonEmptyString("Enter renter name: ");
        int days = InputValidator.getIntInRange("Enter rental days: ", 1, 365);

        if (manager.rentAircraft(model, renterName, days)) {
            System.out.println("Aircraft rented successfully!");
        } else {
            System.out.println("Aircraft not available for rent!");
        }
    }

    private static void returnAircraft() {
        if (!isAdmin) {
            System.out.println("Admin access required!");
            return;
        }
        String model = InputValidator.getNonEmptyString("Enter aircraft model to return: ");
        if (manager.returnAircraft(model)) {
            System.out.println("Aircraft returned successfully!");
        } else {
            System.out.println("Aircraft not found or not rented!");
        }
    }

    private static void showAvailableForRent() {
        List<Aircraft> available = manager.getAvailableForRent();
        if (available.isEmpty()) {
            System.out.println("No aircrafts available for rent.");
        } else {
            System.out.println("\n    Available for Rent    ");
            for (Aircraft aircraft : available) {
                Rentable r = (Rentable) aircraft;
                System.out.printf("%s - $%.2f per day%n", aircraft.getModel(), r.getRentalPrice());
            }
        }
    }

    private static void saveData() {
        try {
            manager.saveData();
            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private static void loadData() {
        try {
            manager.loadData();
            System.out.println("Data loaded successfully!");
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    private static void exportToCSV() {
        try {
            manager.exportToCSV();
            System.out.println("Data exported to CSV successfully!");
        } catch (IOException e) {
            System.out.println("Error exporting to CSV: " + e.getMessage());
        }
    }

    private static void importFromCSV() {
        try {
            manager.importFromCSV();
            System.out.println("Data imported from CSV successfully!");
        } catch (IOException e) {
            System.out.println("Error importing from CSV: " + e.getMessage());
        }
    }


    private static void toggleAdminMode() {
        if (!isAdmin) {
            System.out.print("Enter admin password: ");
            String password = scanner.nextLine();
            if (password.equals("admin123")) {
                isAdmin = true;
                System.out.println("Admin mode activated!");
            } else {
                System.out.println("Incorrect password!");
            }
        } else {
            isAdmin = false;
            System.out.println("Switched to user mode.");
        }
    }
}