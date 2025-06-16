package ru.aston.hw5.builder;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        Order order = new Order.Builder()
            .addItem("Книга")
            .addItem("Ручка")
            .setDeliveryAddress("ул. Ленина, 10")
            .setComment("Позвонить перед доставкой")
            .build();
        loggingOrder(order);
    }

    private static void loggingOrder(Object... args) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("Заказ: %s", args));
        }
    }
}
