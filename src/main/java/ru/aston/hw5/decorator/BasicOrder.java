package ru.aston.hw5.decorator;

/**
 * Базовая реализация заказа без дополнительных услуг. Содержит минимальную стоимость и описание.
 */
public class BasicOrder implements OrderService {

    /**
     * @return Стандартное описание базового заказа.
     */
    @Override
    public String getDescription() {
        return "Базовый заказ";
    }

    /**
     * @return Базовая стоимость заказа (100 единиц)
     */
    @Override
    public double getCost() {
        return 100.0;
    }
}
