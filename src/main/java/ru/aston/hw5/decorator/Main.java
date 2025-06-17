package ru.aston.hw5.decorator;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        OrderService order = new BasicOrder();
        order = new GiftWrapping(order);
        logging(order.getDescription() + ": $"
            + order.getCost()); // Базовый заказ, подарочная упаковка: $105.0
    }

    public static void logging(String message, Object... args) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format(message, args));
        }
    }
}
