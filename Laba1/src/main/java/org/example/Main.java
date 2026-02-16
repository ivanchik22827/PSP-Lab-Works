package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        logger.info("Приложение запущено ");

        Scanner scanner = null;

        try {
            scanner = new Scanner(System.in);
            MenuHandler menuHandler = new MenuHandler(scanner);
            menuHandler.run();

        } catch (Exception e) {
            logger.fatal("Непредвиденная критическая ошибка: {}" + e.getMessage() + e);
            System.out.println("Произошла критическая ошибка. Подробности в логах.");
        } finally {
            if (scanner != null) {
                scanner.close();
                logger.debug("Scanner закрыт");
            }
            logger.info("Приложение завершено");
        }
    }
}