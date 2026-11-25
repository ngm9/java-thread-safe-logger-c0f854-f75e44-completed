package com.example.logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestMain {
    public static void main(String[] args) throws InterruptedException {
        String logFilePath = "logs/app.log";
        LoggerUtility logger = new LoggerUtility(logFilePath);

        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 4; i++) {
            final int threadNum = i;
            executor.submit(() -> {
                String threadId = "T" + threadNum;
                boolean acquireLock1First = threadNum % 2 == 0;
                for (int m = 0; m < 15; m++) {
                    logger.writeLogWithResources("Log from " + threadId + " - Message " + m, acquireLock1First);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ignored) {}
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            Thread.sleep(100);
        }
        System.out.println("Logging complete. Check the log file for results.");
    }
}
