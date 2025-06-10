package main.java.ru.aston.hw4.task2;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Класс, имитирующий работу двух потоков, поочередно выводящих "1" и "2", начиная с "1".
 */
public class TwoThreads {

    private static final Logger logger = Logger.getLogger(TwoThreads.class.getName());

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
     * Возвращает строковое представление объекта.
     *
     * @return Строковые данные: запущен ли поток и очередность.
     */
    @Override
    public String toString() {
        return "TwoThreads{" + "isFirstTurn=" + isFirstTurn + ", isRunning=" + isRunning + '}';
    }
}
