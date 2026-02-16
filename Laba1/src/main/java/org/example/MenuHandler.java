package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class MenuHandler {

    private static final Logger logger = LogManager.getLogger(MenuHandler.class);

    private final Scanner scanner;
    private final LengthConverter lengthConverter;
    private final MassConverter massConverter;
    private final TemperatureConverter temperatureConverter;

    public MenuHandler(Scanner scanner) {
        this.scanner = scanner;
        this.lengthConverter = new LengthConverter();
        this.massConverter = new MassConverter();
        this.temperatureConverter = new TemperatureConverter();
        logger.debug("MenuHandler инициализирован, конвертеры созданы");
    }

    public void run() {
        boolean running = true;

        while (running) {
            printMainMenu();

            String choice = scanner.nextLine().trim();
            logger.debug("Пользователь выбрал пункт меню: '{}'" + choice);

            switch (choice) {
                case "1":
                    lengthConverter.convert(scanner);
                    break;
                case "2":
                    massConverter.convert(scanner);
                    break;
                case "3":
                    temperatureConverter.convert(scanner);
                    break;
                case "0":
                    running = false;
                    logger.info("Пользователь завершил работу");
                    System.out.println("До свидания!");
                    break;
                default:
                    logger.warn("Некорректный выбор меню: '{}'" + choice);
                    System.out.println("Ошибка: неверный пункт меню. Попробуйте снова.\n");
                    break;
            }
        }
    }

    private void printMainMenu() {
        System.out.println("1 - Длина      ");
        System.out.println("2 - Масса      ");
        System.out.println("3 - Температура");
        System.out.println("0 - Выход      ");
        System.out.print("Ваш выбор: ");
    }
}