package main.java.ru.aston.hw4.task1;


public class MainLiveLock {

    public static void main(String[] args) {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tT] [%4$-7s] %5$s %n");

        LiveLockDemo liveLock = new LiveLockDemo();

        new Thread(liveLock::captureThread1, "Thread-1").start();
        new Thread(liveLock::captureThread2, "Thread-2").start();
    }
}
