package ru.aston.hw5.decorator;

/**
 * Базовый интерфейс для заказов с возможностью добавления дополнительных услуг.
 */
public interface OrderService {

    /**
     * Получение описания заказа.
     *
     * @return Строка с описанием состава заказа.
     */
    String getDescription();

    /**
     * Получение стоимости заказа.
     *
     * @return Общая стоимость в числовом формате.
     */
    double getCost();
}
