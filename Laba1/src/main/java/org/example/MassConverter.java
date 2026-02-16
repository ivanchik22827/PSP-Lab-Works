package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class MassConverter {

    private static final Logger logger = LogManager.getLogger(MassConverter.class);

    public void convert(Scanner scanner) {
        logger.info("Начало конвертации массы---");

        try {
            printUnits();

            System.out.print("Из какой единицы (номер): ");
            int from = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("В какую единицу (номер): ");
            int to = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Введите значение: ");
            double value = Double.parseDouble(scanner.nextLine().trim());

            logger.info("Параметры: значение={}, из='{}', в='{}'" + value + getUnitName(from) + getUnitName(to));

            double valueInKg = toKilograms(value, from);
            double result = fromKilograms(valueInKg, to);

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
            logger.error("Неожиданная ошибка: {}" + e.getMessage(), e);
            System.out.println("Произошла ошибка. Попробуйте снова.\n");
        } finally {
            logger.info("--- Конец конвертации массы ---");
        }
    }

    private void printUnits() {
        System.out.println("\nЕдиницы массы:");
        System.out.println("  1 - Килограммы (кг)");
        System.out.println("  2 - Граммы (г)");
        System.out.println("  3 - Миллиграммы (мг)");
        System.out.println("  4 - Тонны (т)");
        System.out.println("  5 - Фунты (lb)");
        System.out.println("  6 - Унции (oz)");
    }

    private double toKilograms(double value, int unit) {
        switch (unit) {
            case 1: return value;
            case 2: return value / 1000.0;
            case 3: return value / 1_000_000.0;
            case 4: return value * 1000.0;
            case 5: return value * 0.453592;
            case 6: return value * 0.0283495;
            default:
                throw new IllegalArgumentException("Неизвестная единица массы: " + unit);
        }
    }

    private double fromKilograms(double kg, int unit) {
        switch (unit) {
            case 1: return kg;
            case 2: return kg * 1000.0;
            case 3: return kg * 1_000_000.0;
            case 4: return kg / 1000.0;
            case 5: return kg / 0.453592;
            case 6: return kg / 0.0283495;
            default:
                throw new IllegalArgumentException("Неизвестная единица массы: " + unit);
        }
    }

    private String getUnitName(int unit) {
        switch (unit) {
            case 1: return "кг";
            case 2: return "г";
            case 3: return "мг";
            case 4: return "т";
            case 5: return "фунты";
            case 6: return "унции";
            default: return "???";
        }
    }
}