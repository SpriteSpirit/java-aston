package main.java.ru.aston.hw4.task2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Класс, имитирующий работу двух потоков, поочередно выводящих "1" и "2", начиная с "1".
 */
public class TwoThreads {

    private static final Logger logger = Logger.getLogger(TwoThreads.class.getName());

    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();

    private volatile boolean isFirstTurn = true;
    private volatile boolean isRunning = true;

    /**
     * @param threadName Название потока.
     * @param message    Текстовое сообщение для логирования.
     * @param args       Аргументы для форматирования сообщения.
     */
    private void loggerInfo(String threadName, String message, Object... args) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("%s: %s", threadName, String.format(message, args)));
        }
    }

    /**
     * Выполнение первого потока, который выводит "1".
     */
    public void captureThread1() {
        String threadName = "Thread1";

        while (isRunning) {
            lock.lock();

            try {
                if (!isFirstTurn) {
                    condition.await();
                }

                if (!isRunning) {
                    break;
                }

                loggerInfo(threadName, "1");
                isFirstTurn = false;
                // передача сигнала второму потоку.
                condition.signal();
            } catch (InterruptedException e) {
                logger.log(Level.SEVERE, "Поток 1 прерван. Ошибка: ", e);
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * Выполнение второго потока, который выводит "2".
     */
    public void captureThread2() {
        String threadName = "Thread2";

        while (isRunning) {
            lock.lock();

            try {
                if (isFirstTurn) {
                    condition.await();
                }

                loggerInfo(threadName, "2");
                isFirstTurn = true;
                // отправление сигнала первому потоку.
                condition.signal();
            } catch (InterruptedException e) {
                logger.log(Level.WARNING, "Поток 2 прерван.", e);
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * Останавливает выполнение всех потоков.
     */
    public void stop() {
        lock.lock();

        try {
            isRunning = false;
            // снятие блокировки со всех потоков.
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }


    /**
     * Возвращает строковое представление объекта.
     *
     * @return Строковые данные: запущен ли поток и очередность.
     */
    @Override
    public String toString() {
        return "TwoThreads{" + "isFirstTurn=" + isFirstTurn + ", isRunning=" + isRunning + '}';
    }
}
