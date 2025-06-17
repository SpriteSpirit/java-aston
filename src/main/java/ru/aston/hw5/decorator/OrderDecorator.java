package ru.aston.hw5.decorator;

import java.util.Objects;

/**
 * Абстрактный класс-декоратор для добавления дополнительных услуг к заказу. Реализует паттерн
 * "Декоратор".
 */
public abstract class OrderDecorator implements OrderService {

    /**
     * Декорируемый заказ
     */
    protected final OrderService orderService;

    /**
     * Создание декоратора для указанного заказа.
     *
     * @param orderService Базовый заказ или другой декоратор.
     */
    protected OrderDecorator(OrderService orderService) {
        this.orderService = Objects.requireNonNull(orderService,
            "Оборачиваемый заказ не может быть null");
    }

    @Override
    public String toString() {
        return "OrderDecorator{" +
            "orderService=" + orderService +
            '}';
    }
}
