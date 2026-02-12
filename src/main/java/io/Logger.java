package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Logger {
    public static final Path filePath = Path.of("logs/log.txt");

    public static void logDebug(Object component, String message) {
        log("DEBUG", component, message);
    }

    public static void logInfo(Object component, String message) {
        log("INFO", component, message);
    }

    public static void logWarning(Object component, String message) {
        log("WARNING", component, message);
    }

    public static void logError(Object component, String message) {
        log("ERROR", component, message);
    }

    public static void logFatal(Object component, String message) {
        log("FATAL", component, message);
    }

    private static void log(String logLevel, Object component, String message) {
        String currentDate = getCurrentTimestamp("yyyy-MM-dd");

        List<String> logEntries = new ArrayList<>();

        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                System.err.println("ERROR LoggingService - Log file could not be created.");
            }
        } else {
            try {
                logEntries.addAll(Files.readAllLines(filePath));
            } catch (IOException e) {
                System.err.println("ERROR LoggingService - Could not read from log file.");
            }
        }

        if (!logEntries.contains(currentDate)) {
            logEntries.add(currentDate);
        }
        logEntries.add(
            getCurrentTimestamp("yyyy-MM-dd HH:mm:ss.nn") + " " + logLevel + " " + component.getClass().getSimpleName() + " - " + message);

        try {
            Files.write(filePath, logEntries);
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
