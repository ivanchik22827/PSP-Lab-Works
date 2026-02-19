package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Logger {
    private static final String LOG_FILE = "airport_operations.log";
    private List<String> logs;
    private DateTimeFormatter formatter;

    public Logger() {
        this.logs = new ArrayList<>();
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public void log(String message) {
        String logEntry = LocalDateTime.now().format(formatter) + " - " + message;
        logs.add(logEntry);
        writeToFile(logEntry);
    }

    private void writeToFile(String logEntry) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(logEntry);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }

    public List<String> getLogs() {
        return new ArrayList<>(logs);
    }

    public void printRecentLogs(int count) {
        int start = Math.max(0, logs.size() - count);
        System.out.println("\n==== Recent Operations ====");
        for (int i = start; i < logs.size(); i++) {
            System.out.println(logs.get(i));
        }
        System.out.println("==========================");
    }
}