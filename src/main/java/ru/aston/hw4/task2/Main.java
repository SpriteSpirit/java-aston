package main.java.ru.aston.hw4.task2;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tT] [%4$-7s] %5$s %n");

        final int TIME_SLEEP = 1000;

        TwoThreads twoThreads = new TwoThreads();

        new Thread(twoThreads::captureThread1, "Thread-1").start();
        new Thread(twoThreads::captureThread2, "Thread-2").start();

        try {
            Thread.sleep(TIME_SLEEP);
            twoThreads.stop();
        } catch (InterruptedException e) {
            logger.log(Level.WARNING, "Ошибка прерывания.", e);
            Thread.currentThread().interrupt();
        }
    }
}
