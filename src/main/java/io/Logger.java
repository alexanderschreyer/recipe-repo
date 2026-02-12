package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Logger {

    public static void logDebug(String component, String message) {
        log("DEBUG", component, message);
    }

    public static void logInfo(String component, String message) {
        log("INFO", component, message);
    }

    public static void logWarning(String component, String message) {
        log("WARNING", component, message);
    }

    public static void logError(String component, String message) {
        log("ERROR", component, message);
    }

    public static void logFatal(String component, String message) {
        log("FATAL", component, message);
    }

    private static void log(String logLevel, String component, String message) {
        String currentDate = getCurrentTimestamp("yyyy-MM-dd");

        Path path = Path.of("./src/oop2/project/data/log.txt");
        List<String> logEntries = new ArrayList<>();

        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                System.err.println("ERROR LoggingService - Log file could not be created.");
            }
        } else {
            try {
                logEntries.addAll(Files.readAllLines(path));
            } catch (IOException e) {
                System.err.println("ERROR LoggingService - Could not read from log file.");
            }
        }

        if (!logEntries.contains(currentDate)) {
            logEntries.add(currentDate);
        }
        logEntries.add(
            getCurrentTimestamp("yyyy-MM-dd HH:mm:ss.nn") + " " + logLevel + " " + component + " - " + message);

        try {
            Files.write(path, logEntries);
        } catch (IOException e) {
            System.err.println("ERROR LoggingService - Could not write to log file.");
        }
    }

    private static String getCurrentTimestamp(String format) {
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return timestamp.format(formatter);
    }
}
