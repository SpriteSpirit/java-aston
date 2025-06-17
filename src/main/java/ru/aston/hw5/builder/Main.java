package ru.aston.hw5.builder;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        Order order = Order.builder()
            .item("Книга")
            .items(List.of("Рюкзак", "Кепка"))
            .deliveryAddress("ул. Ленина, 10")
            .comment("Позвонить перед доставкой")
            .build();
        loggingOrder(order);
    }

    private static void loggingOrder(Object... args) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("Заказ: %s", args));
        }
    }
}
