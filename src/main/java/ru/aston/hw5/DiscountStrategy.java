package ru.aston.hw5;


/**
 * Интерфейс определяет метод для расчета скидки.
 */
public interface DiscountStrategy {

    double applyDiscount(double totalPrice);
}
