package ru.aston.hw5.adapter;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Устаревшая система доставки, требующая адаптации к новой системе.
 */
public class LegacyDelivery {

    private static final Logger logger = Logger.getLogger(LegacyDelivery.class.getName());

    /**
     * Логирование сообщения на уровне INFO.
     *
     * @param args Аргументы для подстановки в сообщение.
     */
    private void logging(Object... args) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("Доставка заказа %s по адресу: %s.", args));
        }
    }

    /**
     * Отправляет заказ по указанному адресу (устаревший метод).
     *
     * @param orderNumber Номер заказа.
     * @param destination Адрес доставки.
     */
    public void ship(String orderNumber, String destination) {
        logging(orderNumber, destination);
    }
}
