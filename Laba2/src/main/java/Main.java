

import model.Aircraft;
import model.enums.TypeEnum;
import services.AirportService;
import utils.UI;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        System.out.println("Welcome to Airport Management System");

        while (true) {
            showMenu();
            try {
                int choice = UI.scanner.nextInt();
                UI.scanner.nextLine();

                switch (choice) {
                    case 1:
                        UI.addAirplane();
                        break;
                    case 2:
                        UI.addHelicopter();
                        break;
                    case 3:
                        UI.addDrone();
                        break;
                    case 4:
                        UI.viewAll();
                        break;
                    case 5:
                        UI.adminLogin();
                        break;
                    case 6:
                        UI.editAircraft();
                        break;
                    case 7:
                        UI.searchByModel();
                        break;
                    case 8:
                        UI.filterByType();
                        break;
                    case 9:
                        UI.filterByYear();
                        break;
                    case 10:
                        UI.filterByYearRange();
                        break;
                    case 11:
                        UI.sortByModel();
                        break;
                    case 12:
                        UI.sortByRange();
                        break;
                    case 13:
                        UI.showStatistics();
                        break;
                    case 14:
                        UI.saveData();
                        break;
                    case 15:
                        UI.loadData();
                        break;
                    case 0:
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Wrong choice");
                }
            }
            catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
                UI.scanner.nextLine();
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n   MENU    ");
        System.out.println("1. Add airplane");
        System.out.println("2. Add helicopter");
        System.out.println("3. Add drone");
        System.out.println("4. View all aircrafts");
        System.out.println("5. Admin login (to delete)");
        System.out.println("6. Edit aircraft range/capacity");
        System.out.println("7. Search by model");
        System.out.println("8. Filter by type");
        System.out.println("9. Filter by year");
        System.out.println("10. Filter by year range");
        System.out.println("11. Sort by model");
        System.out.println("12. Sort by range");
        System.out.println("13. Show statistics");
        System.out.println("14. Save data");
        System.out.println("15. Load data");
        System.out.println("0. Exit");
        System.out.print("Choose option: ");
    }


}
