package ru.aston.hw5.strategy;

import java.util.logging.Level;
import java.util.logging.Logger;
import ru.aston.hw5.strategy.StrategyPatternRealize.FixedDiscount;
import ru.aston.hw5.strategy.StrategyPatternRealize.Order;
import ru.aston.hw5.strategy.StrategyPatternRealize.PercentageDiscount;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        double totalPrice = 10_000.0;
        double fixedDiscount = 500.0;
        double percentageDiscount = 15.0;

        Order order = new Order(totalPrice);

        applyDiscount(order, new FixedDiscount(fixedDiscount));
        logging(
            "Цена с фиксированной скидкой [%s руб.]: %s руб.",
            fixedDiscount,
            order.calculateFinalPrice());

        applyDiscount(order, new PercentageDiscount(percentageDiscount));
        logging(
            "Цена с с процентной скидкой [%s%%]: %s руб.",
            percentageDiscount,
            order.calculateFinalPrice());
    }

    private static void applyDiscount(Order order, DiscountStrategy strategy) {
        order.setDiscountStrategy(strategy);
    }

    private static void logging(String message, Object... args) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format(message, args));
        }
    }
}
