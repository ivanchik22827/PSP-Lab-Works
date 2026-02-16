package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class LengthConverter {

    private static final Logger logger = LogManager.getLogger(LengthConverter.class);

    public void convert(Scanner scanner) {
        logger.info("--- Начало конвертации длины ---");

        try {
            printUnits();

            System.out.print("Из какой единицы (номер): ");
            int from = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("В какую единицу (номер): ");
            int to = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Введите значение: ");
            double value = Double.parseDouble(scanner.nextLine().trim());

            logger.info("Параметры: значение={}, из='{}', в='{}'" + value + getUnitName(from) + getUnitName(to));

            double valueInMeters = toMeters(value, from);
            double result = fromMeters(valueInMeters, to);

            System.out.printf("%nРезультат: %.6f %s = %.6f %s%n%n",
                    value, getUnitName(from), result, getUnitName(to));

            logger.info("Результат: {} {} = {} {}" + value + getUnitName(from) + result + getUnitName(to));

        } catch (NumberFormatException e) {
            logger.error("Ошибка ввода числа: {}" + e.getMessage());
            System.out.println("Ошибка: введено некорректное число!\n");
        } catch (IllegalArgumentException e) {
            logger.error("Неизвестная единица: {}" + e.getMessage());
            System.out.println("Ошибка: " + e.getMessage() + "\n");
        } catch (Exception e) {
            logger.error("Неожиданная ошибка: {}" + e.getMessage() + e);
            System.out.println("Произошла ошибка. Попробуйте снова.\n");
        } finally {
            logger.info("--- Конец конвертации длины ---");
        }
    }

    private void printUnits() {
        System.out.println("\nЕдиницы длины:");
        System.out.println("  1 - Метры (м)");
        System.out.println("  2 - Километры (км)");
        System.out.println("  3 - Сантиметры (см)");
        System.out.println("  4 - Миллиметры (мм)");
        System.out.println("  5 - Мили");
        System.out.println("  6 - Футы");
        System.out.println("  7 - Дюймы");
    }

    private double toMeters(double value, int unit) {
        switch (unit) {
            case 1: return value;
            case 2: return value * 1000.0;
            case 3: return value / 100.0;
            case 4: return value / 1000.0;
            case 5: return value * 1609.344;
            case 6: return value * 0.3048;
            case 7: return value * 0.0254;
            default:
                throw new IllegalArgumentException("Неизвестная единица длины: " + unit);
        }
    }

    private double fromMeters(double meters, int unit) {
        switch (unit) {
            case 1: return meters;
            case 2: return meters / 1000.0;
            case 3: return meters * 100.0;
            case 4: return meters * 1000.0;
            case 5: return meters / 1609.344;
            case 6: return meters / 0.3048;
            case 7: return meters / 0.0254;
            default:
                throw new IllegalArgumentException("Неизвестная единица длины: " + unit);
        }
    }

    private String getUnitName(int unit) {
        switch (unit) {
            case 1: return "м";
            case 2: return "км";
            case 3: return "см";
            case 4: return "мм";
            case 5: return "мили";
            case 6: return "футы";
            case 7: return "дюймы";
            default: return "???";
        }
    }
}