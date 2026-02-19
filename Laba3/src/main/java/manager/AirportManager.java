package manager;

import model.*;
import model.interfaces.Maintainable;
import model.interfaces.Rentable;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class AirportManager {
    private List<Aircraft> aircrafts;
    private String dataFilePath = "airport_data.dat";
    private String csvFilePath = "airport_data.csv";

    public AirportManager() {
        this.aircrafts = new ArrayList<>();
    }

    public void addAircraft(Aircraft aircraft) {
        aircrafts.add(aircraft);
    }

    public boolean removeAircraft(String model) {
        Aircraft toRemove = findByModel(model);
        if (toRemove != null) {
            aircrafts.remove(toRemove);
            return true;
        }
        return false;
    }

    public Aircraft findByModel(String model) {
        return aircrafts.stream()
            .filter(a -> a.getModel().equalsIgnoreCase(model))
            .findFirst()
            .orElse(null);
    }

    public boolean editFlightRange(String model, int newRange) {
        Aircraft aircraft = findByModel(model);
        if (aircraft != null) {
            aircraft.setFlightRange(newRange);
            return true;
        }
        return false;
    }

    public boolean editLoadCapacity(String model, int newCapacity) {
        Aircraft aircraft = findByModel(model);
        if (aircraft != null) {
            aircraft.setLoadCapacity(newCapacity);
            return true;
        }
        return false;
    }

    public List<Aircraft> searchByKeyword(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return aircrafts.stream()
            .filter(a -> a.getModel().toLowerCase().contains(lowerKeyword) ||
                        a.getType().toLowerCase().contains(lowerKeyword))
            .collect(Collectors.toList());
    }

    public List<Aircraft> filterByType(String type) {
        return aircrafts.stream()
            .filter(a -> a.getType().equalsIgnoreCase(type))
            .collect(Collectors.toList());
    }

    public List<Aircraft> filterByYear(int year) {
        return aircrafts.stream()
            .filter(a -> a.getYearOfRelease() == year)
            .collect(Collectors.toList());
    }

    public List<Aircraft> filterByYearRange(int startYear, int endYear) {
        return aircrafts.stream()
            .filter(a -> a.getYearOfRelease() >= startYear && a.getYearOfRelease() <= endYear)
            .collect(Collectors.toList());
    }

    public List<Aircraft> filterByFlightRange(int minRange, int maxRange) {
        return aircrafts.stream()
            .filter(a -> a.getFlightRange() >= minRange && a.getFlightRange() <= maxRange)
            .collect(Collectors.toList());
    }

    public List<Aircraft> filterCombined(String type, Integer yearStart, Integer yearEnd, Integer rangeMin, Integer rangeMax) {
        return aircrafts.stream()
            .filter(a -> (type == null || a.getType().equalsIgnoreCase(type)))
            .filter(a -> (yearStart == null || a.getYearOfRelease() >= yearStart))
            .filter(a -> (yearEnd == null || a.getYearOfRelease() <= yearEnd))
            .filter(a -> (rangeMin == null || a.getFlightRange() >= rangeMin))
            .filter(a -> (rangeMax == null || a.getFlightRange() <= rangeMax))
            .collect(Collectors.toList());
    }

    public List<Aircraft> sortByModel(boolean ascending) {
        return aircrafts.stream()
            .sorted(ascending ?
                Comparator.comparing(Aircraft::getModel) :
                Comparator.comparing(Aircraft::getModel).reversed())
            .collect(Collectors.toList());
    }

    public List<Aircraft> sortByFlightRange(boolean ascending) {
        return aircrafts.stream()
            .sorted(ascending ?
                Comparator.comparingInt(Aircraft::getFlightRange) :
                Comparator.comparingInt(Aircraft::getFlightRange).reversed())
            .collect(Collectors.toList());
    }

    public List<Aircraft> sortByYear(boolean ascending) {
        return aircrafts.stream()
            .sorted(ascending ?
                Comparator.comparingInt(Aircraft::getYearOfRelease) :
                Comparator.comparingInt(Aircraft::getYearOfRelease).reversed())
            .collect(Collectors.toList());
    }

    public List<Aircraft> sortByLoadCapacity(boolean ascending) {
        return aircrafts.stream()
            .sorted(ascending ?
                Comparator.comparingInt(Aircraft::getLoadCapacity) :
                Comparator.comparingInt(Aircraft::getLoadCapacity).reversed())
            .collect(Collectors.toList());
    }

    public List<Aircraft> sortByMultipleFields(String field1, boolean asc1, String field2, boolean asc2) {
        Comparator<Aircraft> comparator = getComparator(field1, asc1);
        comparator = comparator.thenComparing(getComparator(field2, asc2));
        return aircrafts.stream()
            .sorted(comparator)
            .collect(Collectors.toList());
    }

    private Comparator<Aircraft> getComparator(String field, boolean ascending) {
        Comparator<Aircraft> comp = null;
        switch (field.toLowerCase()) {
            case "model":
                comp = Comparator.comparing(Aircraft::getModel);
                break;
            case "year":
                comp = Comparator.comparingInt(Aircraft::getYearOfRelease);
                break;
            case "range":
                comp = Comparator.comparingInt(Aircraft::getFlightRange);
                break;
            case "capacity":
                comp = Comparator.comparingInt(Aircraft::getLoadCapacity);
                break;
            default:
                comp = Comparator.comparing(Aircraft::getModel);
        }
        return ascending ? comp : comp.reversed();
    }

    public Map<String, Long> countByType() {
        return aircrafts.stream()
            .collect(Collectors.groupingBy(Aircraft::getType, Collectors.counting()));
    }

    public double getAverageFlightRange() {
        return aircrafts.stream()
            .mapToInt(Aircraft::getFlightRange)
            .average()
            .orElse(0.0);
    }

    public double getAverageFlightRangeByType(String type) {
        return aircrafts.stream()
            .filter(a -> a.getType().equalsIgnoreCase(type))
            .mapToInt(Aircraft::getFlightRange)
            .average()
            .orElse(0.0);
    }

    public Aircraft getMinFlightRange() {
        return aircrafts.stream()
            .min(Comparator.comparingInt(Aircraft::getFlightRange))
            .orElse(null);
    }

    public Aircraft getMaxFlightRange() {
        return aircrafts.stream()
            .max(Comparator.comparingInt(Aircraft::getFlightRange))
            .orElse(null);
    }

    public List<Aircraft> getTopNByLoadCapacity(int n) {
        return aircrafts.stream()
            .sorted(Comparator.comparingInt(Aircraft::getLoadCapacity).reversed())
            .limit(n)
            .collect(Collectors.toList());
    }

    public Map<String, Double> getStatisticsSummary() {
        Map<String, Double> stats = new HashMap<>();
        stats.put("totalCount", (double) aircrafts.size());
        stats.put("averageFlightRange", getAverageFlightRange());
        stats.put("averageLoadCapacity", aircrafts.stream()
            .mapToInt(Aircraft::getLoadCapacity)
            .average()
            .orElse(0.0));
        stats.put("averageYear", aircrafts.stream()
            .mapToInt(Aircraft::getYearOfRelease)
            .average()
            .orElse(0.0));
        return stats;
    }

    public void performMaintenance(String model, String description) {
        Aircraft aircraft = findByModel(model);
        if (aircraft instanceof Maintainable) {
            ((Maintainable) aircraft).performMaintenance(description);
        }
    }

    public List<Aircraft> getAircraftNeedingMaintenance() {
        return aircrafts.stream()
            .filter(a -> a instanceof Maintainable)
            .filter(a -> ((Maintainable) a).needsMaintenance())
            .collect(Collectors.toList());
    }

    public boolean rentAircraft(String model, String renterName, int days) {
        Aircraft aircraft = findByModel(model);
        if (aircraft instanceof Rentable) {
            Rentable rentable = (Rentable) aircraft;
            if (rentable.isAvailableForRent()) {
                rentable.rent(renterName, days);
                return true;
            }
        }
        return false;
    }

    public boolean returnAircraft(String model) {
        Aircraft aircraft = findByModel(model);
        if (aircraft instanceof Rentable) {
            ((Rentable) aircraft).returnFromRent();
            return true;
        }
        return false;
    }

    public List<Aircraft> getAvailableForRent() {
        return aircrafts.stream()
            .filter(a -> a instanceof Rentable)
            .filter(a -> ((Rentable) a).isAvailableForRent())
            .collect(Collectors.toList());
    }

    public void saveData() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFilePath))) {
            oos.writeObject(aircrafts);
        }
    }

    @SuppressWarnings("unchecked")
    public void loadData() throws IOException, ClassNotFoundException {
        File file = new File(dataFilePath);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFilePath))) {
                aircrafts = (List<Aircraft>) ois.readObject();
            }
        }
    }

    public void exportToCSV() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFilePath))) {
            writer.println("Type,Model,Year,FlightRange,LoadCapacity,Details");
            for (Aircraft aircraft : aircrafts) {
                writer.printf("%s,%s,%d,%d,%d,\"%s\"%n",
                    aircraft.getType(),
                    aircraft.getModel(),
                    aircraft.getYearOfRelease(),
                    aircraft.getFlightRange(),
                    aircraft.getLoadCapacity(),
                    aircraft.toString()
                );
            }
        }
    }

    public void importFromCSV() throws IOException {
        File file = new File(csvFilePath);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    String type = parts[0];
                    String model = parts[1];
                    int year = Integer.parseInt(parts[2]);
                    int range = Integer.parseInt(parts[3]);
                    int capacity = Integer.parseInt(parts[4]);

                    Aircraft aircraft = null;
                    switch (type) {
                        case "PLANE":
                            aircraft = new Plane(model, year, range, capacity, 150, 850);
                            break;
                        case "HELICOPTER":
                            aircraft = new Helicopter(model, year, range, capacity, 15, 5000);
                            break;
                        case "DRONE":
                            aircraft = new Drone(model, year, range, capacity, 2.5, 12, true);
                            break;
                    }
                    if (aircraft != null) {
                        aircrafts.add(aircraft);
                    }
                }
            }
        }
    }

    public List<Aircraft> getAllAircrafts() {
        return new ArrayList<>(aircrafts);
    }

}