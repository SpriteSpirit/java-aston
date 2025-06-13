package ru.aston.hw5.strategy;


/**
 * Стратегия применения скидки.
 */
public interface DiscountStrategy {

    double applyDiscount(double totalPrice);
}
