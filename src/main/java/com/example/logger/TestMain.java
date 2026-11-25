package com.example.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerUtility {
    private final Object lock = new Object();
    private final String logFilePath;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public LoggerUtility(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    // Simulate two resource locks to demonstrate potential deadlock
    public void writeLogWithResources(String message, boolean acquireLock1First) {
            synchronized (lock) {
                writeLogInternal(message);
            }
        }

    private void writeLogInternal(String message) {
        String line = String.format("[%s] [%s] %s",
                LocalDateTime.now().format(formatter),
                Thread.currentThread().getName(),
                message);
                File file=new File(logFilePath);
                File parent=file.getParentFile();
                if(parent != null) {
                    parent.mkdirs();
                }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            // for (char ch : line.toCharArray()) {
                writer.write(line);
                writer.newLine();
                writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Unable to write log message", e);
        }
    }
}
