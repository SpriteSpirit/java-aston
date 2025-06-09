package main.java.ru.aston.hw4;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс, имитирующий работу DeadLock - взаимной блокировки. Содержит внутренний record
 * {@link Friend}, который демонстрирует ситуацию взаимной блокировки при вызове методов
 * {@link Friend#handshake(Friend)} и {@link Friend#handshakeBack(Friend)}
 */
public class DeadLock {

    private static final Logger logger = Logger.getLogger(DeadLock.class.getName());


    /**
     * Record-класс с простой логикой для создания объекта "друг". Содержит методы для имитации
     * рукопожатия, которые логируют действия и приводят к взаимной блокировке при параллельном
     * выполнении.
     *
     * @param name Имя друга.
     */
    public record Friend(String name) {

        /**
         * Выполняет рукопожатие с другим объектом {@link Friend}
         * <p>
         * Логирует сообщение о том, что {@code handshaker} должен пожать руку. После чего вызывает
         * {@link #handshakeBack(Friend)} на {@code handshaker}. Это приводит к взаимной блокировке,
         * так как два объекта пытаются пожать друг другу руку.
         * </p>
         *
         * @param handshaker Объект {@link Friend}, с которым выполняется рукопожатие.
         */
        public synchronized void handshake(Friend handshaker) {
            if (logger.isLoggable(Level.INFO)) {
                logger.info(
                    String.format("%s: %s должен пожать мне руку",
                        this.name,
                        handshaker.name())
                );

                handshaker.handshakeBack(this);
            }
        }

        /**
         * Отвечает на рукопожатие другого объекта {@link Friend}
         * <p>
         * Логирует сообщение о том, что {@code handshaker} пожал руку в ответ. Вызывается из
         * {@link #handshake(Friend)} и создает взаимную блокировку.
         * </p>
         *
         * @param handshaker Объект {@link Friend}, который должен пожать руку в ответ.
         */
        public synchronized void handshakeBack(Friend handshaker) {
            if (logger.isLoggable(Level.INFO)) {
                logger.info(
                    String.format("%s: %s должен пожать мне руку в ответ",
                        this.name,
                        handshaker.name())
                );
            }
        }
    }
}
