package com.example.logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerUtility {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    private final String logFilePath;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public LoggerUtility(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    // Simulate two resource locks to demonstrate potential deadlock
    public void writeLogWithResources(String message, boolean acquireLock1First) {
        if (acquireLock1First) {
            synchronized (lock1) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) {}
                synchronized (lock2) {
                    writeLogInternal(message);
                }
            }
        } else {
            synchronized (lock2) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) {}
                synchronized (lock1) {
                    writeLogInternal(message);
                }
            }
        }
    }

    private void writeLogInternal(String message) {
        String line = String.format("[%s] [%s] %s",
                LocalDateTime.now().format(formatter),
                Thread.currentThread().getName(),
                message);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {
            for (char ch : line.toCharArray()) {
                writer.write(ch);
                Thread.sleep(1); // Simulate slow write to exaggerate interleaving
            }
            writer.write("\n");
        } catch (IOException | InterruptedException ignored) {}
    }
}
