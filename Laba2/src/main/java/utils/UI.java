package utils; 

import model.*;
import model.enums.TypeEnum;
import services.AirportService;

import java.util.Scanner;

public class UI {

    private static AirportService service = new AirportService();
    public static Scanner scanner = new Scanner(System.in);
    private static boolean isAdmin = false;

    public static void addAirplane() {
        System.out.println("Adding airplane:");
        Airplane airplane = createAirplane();
        service.addAircraft(airplane);
        System.out.println("Airplane added!");
    }

    public static void addHelicopter() {
        System.out.println("Adding helicopter:");
        Helicopter helicopter = createHelicopter();
        service.addAircraft(helicopter);
        System.out.println("Helicopter added!");
    }

    public static void addDrone() {
        System.out.println("Adding drone:");
        Drone drone = createDrone();
        service.addAircraft(drone);
        System.out.println("Drone added!");
    }

    public static Airplane createAirplane() {
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Flight range (km): ");
        int range = scanner.nextInt();
        System.out.print("Load capacity (kg): ");
        int loadCapacity = scanner.nextInt();
        System.out.print("Year of release: ");
        int year = scanner.nextInt();
        System.out.print("Passenger capacity: ");
        int passengerCapacity = scanner.nextInt();
        scanner.nextLine();

        return new Airplane(range, loadCapacity, model, TypeEnum.AIRPLANE, year, passengerCapacity);
    }

    public static Helicopter createHelicopter() {
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Flight range (km): ");
        int range = scanner.nextInt();
        System.out.print("Load capacity (kg): ");
        int loadCapacity = scanner.nextInt();
        System.out.print("Year of release: ");
        int year = scanner.nextInt();
        System.out.print("Max height (m): ");
        int maxHeight = scanner.nextInt();
        scanner.nextLine();

        return new Helicopter(range, loadCapacity, model, TypeEnum.HELICOPTER, year, maxHeight);
    }

    public static Drone createDrone() {
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Flight range (km): ");
        int range = scanner.nextInt();
        System.out.print("Load capacity (kg): ");
        int loadCapacity = scanner.nextInt();
        System.out.print("Year of release: ");
        int year = scanner.nextInt();
        System.out.print("Weight (kg): ");
        int weight = scanner.nextInt();
        scanner.nextLine();

        return new Drone(range, loadCapacity, model, TypeEnum.DRONE, year, weight);
    }

    public static void viewAll() {
        System.out.println("All aircrafts:");
        service.printAll();
    }

    public static void adminLogin() {
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();
        if (password.equals("admin123")) {
            isAdmin = true;
            System.out.println("Admin access granted!");
            deleteAircraft();
        } else {
            System.out.println("Wrong password!");
        }
    }

    public static void deleteAircraft() {
        if (!isAdmin) {
            System.out.println("Access denied! Admin only.");
            return;
        }
        System.out.print("Enter model to delete: ");
        String model = scanner.nextLine();
        service.deleteAircraft(model);
        isAdmin = false;
    }

    public static void editAircraft() {
        System.out.print("Enter model to edit: ");
        String model = scanner.nextLine();
        service.editAircraft(model);
    }

    public static void searchByModel() {
        System.out.print("Enter model to search: ");
        String model = scanner.nextLine();
        service.searchByModel(model);
    }

    public static void filterByType() {
        System.out.println("1. AIRPLANE");
        System.out.println("2. HELICOPTER");
        System.out.println("3. DRONE");
        System.out.print("Choose type: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        TypeEnum type = null;
        switch (choice) {
            case 1:
                type = TypeEnum.AIRPLANE;
                break;
            case 2:
                type = TypeEnum.HELICOPTER;
                break;
            case 3:
                type = TypeEnum.DRONE;
                break;
            default:
                System.out.println("Wrong choice");
                return;
        }
        service.filterByType(type);
    }

    public static void filterByYear() {
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        service.filterByYear(year);
    }

    public static void filterByYearRange() {
        System.out.print("Enter start year: ");
        int startYear = scanner.nextInt();
        System.out.print("Enter end year: ");
        int endYear = scanner.nextInt();
        scanner.nextLine();
        service.filterByYearRange(startYear, endYear);
    }

    public static void sortByModel() {
        service.sortByModel();
    }

    public static void sortByRange() {
        service.sortByRange();
    }

    public static void showStatistics() {
        service.showStatistics();
    }

    public static void saveData() {
        System.out.print("Enter filename: ");
        String filename = scanner.nextLine();
        service.saveToFile(filename);
    }

    public static void loadData() {
        System.out.print("Enter filename: ");
        String filename = scanner.nextLine();
        service.loadFromFile(filename);
    }
}