package main.java.ru.aston.hw4;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс, имитирующий поведение двух потоков .которые бесконечно уступают друг другу ресурсы.
 */
public class LiveLockDemo {

    private static final Logger logger = Logger.getLogger(LiveLockDemo.class.getName());
    private static final int TIME = 1;
    private final Lock lock1 = new ReentrantLock(true);
    private final Lock lock2 = new ReentrantLock(true);

    /**
     * Метод логирует сообщение для указанного потока.
     *
     * @param threadName Название потока.
     * @param message    Шаблон сообщения для передачи в String.format().
     * @param args       Аргументы для форматирования.
     */
    private void logInfo(String threadName, String message, Object... args) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("%s: %s", threadName, String.format(message, args)));
        }
    }

    /**
     * Выполняет ожидание/паузу для имитации активности.
     */
    private void sleepBriefly() {
        try {
            Thread.sleep(TIME);
        } catch (InterruptedException _) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Метод пытается захватить блокировку с таймаутом, обрабатывая прерывание.
     *
     * @param lock       Блокировка для захвата.
     * @param threadName Название потока.
     * @return true, если блокировка захвачена, иначе false.
     */
    private boolean tryLockWithTimeout(Lock lock, String threadName) {
        boolean isLocked = false;

        try {
            isLocked = lock.tryLock(TIME, TimeUnit.MILLISECONDS);
        } catch (InterruptedException _) {
            Thread.currentThread().interrupt();
            logInfo(threadName, "Прерван при попытке захвата блокировки");
        }
        return isLocked;
    }

    /**
     * Метод пытается захватить 2 блокировки в указанном порядке.
     *
     * @param threadName Название потока.
     * @param firstLock  Первая блокировка.
     * @param secondLock Вторая блокировка.
     */
    private void tryCaptureLocks(String threadName, Lock firstLock, Lock secondLock) {

        if (tryLockWithTimeout(firstLock, threadName)) {
            try {
                logInfo(threadName, "%s захвачен, пытаюсь захватить %s", firstLock, secondLock);

                if (tryLockWithTimeout(secondLock, threadName)) {
                    try {
                        logInfo(threadName, "%s захвачен", secondLock);
                        logInfo(threadName,
                            "Обе блокировки захвачены, продолжаю имитацию LiveLock...", secondLock);
                        sleepBriefly();
                    } finally {
                        secondLock.unlock();
                    }
                } else {
                    logInfo(threadName, "не удалось захватить %s, освобождаю %s", secondLock,
                        firstLock);
                }
            } finally {
                firstLock.unlock();
            }
        }
    }


    /**
     * Логика первого потока: пытается захватить lock1, затем lock2. Если lock2 занят, то
     * освобождает lock1 и повторяет попытку.
     */
    public void captureThread1() {
        String threadName = "Thread-1";

        while (!Thread.currentThread().isInterrupted()) {
            tryCaptureLocks(threadName, lock1, lock2);
            sleepBriefly();
        }

        logInfo(threadName, "Поток прерван.");
    }

    /**
     * Логика первого потока: пытается захватить lock2, затем lock1. Если lock1 занят, то
     * освобождает lock2 и повторяет попытку.
     */
    public void captureThread2() {
        String threadName = "Thread-2";

        while (!Thread.currentThread().isInterrupted()) {
            tryCaptureLocks(threadName, lock2, lock1);
            sleepBriefly();
        }

        logInfo(threadName, "Поток прерван.");
    }

    @Override
    public String toString() {
        return "LiveLockDemo{" +
            "lock1=" + lock1 +
            ", lock2=" + lock2 +
            '}';
    }
}
