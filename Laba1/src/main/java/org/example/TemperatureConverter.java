package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class TemperatureConverter {

    private static final Logger logger = LogManager.getLogger(TemperatureConverter.class);

    public void convert(Scanner scanner) {
        logger.info("Начало конвертации температуры ");

        try {
            printUnits();

            System.out.print("Из какой единицы (номер): ");
            int from = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("В какую единицу (номер): ");
            int to = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Введите значение: ");
            double value = Double.parseDouble(scanner.nextLine().trim());

            logger.info("Параметры: значение={}, из='{}', в='{}'" + value + getUnitName(from) + getUnitName(to));

            double result = calculate(value, from, to);

            System.out.printf("%nРезультат: %.2f %s = %.2f %s%n%n", value, getUnitName(from), result, getUnitName(to));

            logger.info("Результат: {} {} = {} {}" + value + getUnitName(from) + result + getUnitName(to));

        } catch (NumberFormatException e) {
            logger.error("Ошибка ввода числа: {}" + e.getMessage());
            System.out.println("Ошибка: введено некорректное число\n");
        } catch (IllegalArgumentException e) {
            logger.error("Неизвестная единица: {}" + e.getMessage());
            System.out.println("Ошибка: " + e.getMessage() + "\n");
        } catch (Exception e) {
            logger.error("Неожиданная ошибка: {}" + e.getMessage() + e);
            System.out.println("Произошла ошибка. Попробуйте снова.\n");
        } finally {
            logger.info("Конец конвертации температуры ");
        }
    }

    private void printUnits() {
        System.out.println("\nЕдиницы температуры:");
        System.out.println("  1 - Цельсий (°C)");
        System.out.println("  2 - Фаренгейт (°F)");
        System.out.println("  3 - Кельвин (K)");
    }

    private double calculate(double value, int from, int to) {
        double celsius = toCelsius(value, from);
        return fromCelsius(celsius, to);
    }

    private double toCelsius(double value, int unit) {
        switch (unit) {
            case 1: return value;
            case 2: return (value - 32.0) * 5.0 / 9.0;
            case 3: return value - 273.15;
            default:
                throw new IllegalArgumentException("Неизвестная единица температуры: " + unit);
        }
    }

    private double fromCelsius(double celsius, int unit) {
        switch (unit) {
            case 1: return celsius;
            case 2: return celsius * 9.0 / 5.0 + 32.0;
            case 3: return celsius + 273.15;
            default:
                throw new IllegalArgumentException("Неизвестная единица температуры: " + unit);
        }
    }

    private String getUnitName(int unit) {
        switch (unit) {
            case 1: return "°C";
            case 2: return "°F";
            case 3: return "K";
            default: return "???";
        }
    }
}