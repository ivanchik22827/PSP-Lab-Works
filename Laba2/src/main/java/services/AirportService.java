package services;

import model.*;
import model.enums.TypeEnum;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AirportService {
    private List<Aircraft> aircrafts = new ArrayList<Aircraft>();
    public void addAircraft(Aircraft aircraft) {
        aircrafts.add(aircraft);
    }
    public void printAll() {
        if (aircrafts.isEmpty()) return;
        for (Aircraft aircraft : aircrafts) {
            System.out.println(aircraft.toString());
        }
    }
    public void deleteAircraft(String model) {
        if (aircrafts.isEmpty())
        {
            System.out.println("airport is empty");
            return;
        }
        for (Aircraft aircraft : aircrafts) {
            if (aircraft.getModel().equals(model)) {
                aircrafts.remove(aircraft);
                return;
            }
        }
        System.out.println("aircraft not found");
    }

    public void editAircraft(String model) {
        for (Aircraft aircraft : aircrafts) {
            if (aircraft.getModel().equals(model)) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("New flight range: ");
                aircraft.setFlightRange(scanner.nextInt());
                System.out.print("New load capacity: ");
                aircraft.setLoadCapacity(scanner.nextInt());
                System.out.println("Aircraft updated");
                return;
            }
        }
        System.out.println("Aircraft not found");
    }

    public void searchByModel(String model) {
        for (Aircraft aircraft : aircrafts) {
            if (aircraft.getModel().toLowerCase().contains(model.toLowerCase())) {
                System.out.println(aircraft.toString());
            }
        }
    }

    public void filterByType(TypeEnum type) {
        for (Aircraft aircraft : aircrafts) {
            if (aircraft.getAirplaneType() == type) {
                System.out.println(aircraft.toString());
            }
        }
    }

    public void filterByYear(int year) {
        for (Aircraft aircraft : aircrafts) {
            if (aircraft.getYearOfRelease() == year) {
                System.out.println(aircraft.toString());
            }
        }
    }

    public void filterByYearRange(int startYear, int endYear) {
        for (Aircraft aircraft : aircrafts) {
            int year = aircraft.getYearOfRelease();
            if (year >= startYear && year <= endYear) {
                System.out.println(aircraft.toString());
            }
        }
    }

    public void sortByModel() {
        aircrafts.sort(Comparator.comparing(Aircraft::getModel));
        System.out.println("Sorted by model:");
        printAll();
    }

    public void sortByRange() {
        aircrafts.sort(Comparator.comparing(Aircraft::getFlightRange));
        System.out.println("Sorted by range:");
        printAll();
    }

    public void showStatistics() {
        System.out.println("Statistics:");
        long airplanes = aircrafts.stream().filter(a -> a.getAirplaneType() == TypeEnum.AIRPLANE).count();
        long helicopters = aircrafts.stream().filter(a -> a.getAirplaneType() == TypeEnum.HELICOPTER).count();
        long drones = aircrafts.stream().filter(a -> a.getAirplaneType() == TypeEnum.DRONE).count();

        System.out.println("Airplanes: " + airplanes);
        System.out.println("Helicopters: " + helicopters);
        System.out.println("Drones: " + drones);
    }

    public void saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Aircraft aircraft : aircrafts) {
                if (aircraft instanceof Airplane) {
                    Airplane airplane = (Airplane) aircraft;
                    writer.println("AIRPLANE;" + airplane.getFlightRange() + ";" + airplane.getLoadCapacity() + ";" +
                                  airplane.getModel() + ";" + airplane.getYearOfRelease() + ";" + airplane.getCapacity());
                } else if (aircraft instanceof Helicopter) {
                    Helicopter helicopter = (Helicopter) aircraft;
                    writer.println("HELICOPTER;" + helicopter.getFlightRange() + ";" + helicopter.getLoadCapacity() + ";" +
                                  helicopter.getModel() + ";" + helicopter.getYearOfRelease() + ";" + helicopter.getMaxHeight());
                } else if (aircraft instanceof Drone) {
                    Drone drone = (Drone) aircraft;
                    writer.println("DRONE;" + drone.getFlightRange() + ";" + drone.getLoadCapacity() + ";" +
                                  drone.getModel() + ";" + drone.getYearOfRelease() + ";" + drone.getWeight());
                }
            }
            System.out.println("Data saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            aircrafts.clear();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                if (parts.length == 6) {
                    String type = parts[0];
                    int range = Integer.parseInt(parts[1]);
                    int loadCapacity = Integer.parseInt(parts[2]);
                    String model = parts[3];
                    int year = Integer.parseInt(parts[4]);
                    int specificValue = Integer.parseInt(parts[5]);

                    switch (type) {
                        case "AIRPLANE":
                            aircrafts.add(new Airplane(range, loadCapacity, model, TypeEnum.AIRPLANE, year, specificValue));
                            break;
                        case "HELICOPTER":
                            aircrafts.add(new Helicopter(range, loadCapacity, model, TypeEnum.HELICOPTER, year, specificValue));
                            break;
                        case "DRONE":
                            aircrafts.add(new Drone(range, loadCapacity, model, TypeEnum.DRONE, year, specificValue));
                            break;
                    }
                }
            }
            System.out.println("Data loaded from " + filename);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

}
