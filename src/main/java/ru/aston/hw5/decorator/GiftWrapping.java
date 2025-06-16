package ru.aston.hw5.decorator;

/**
 * Декоратор для добавления подарочной упаковки к заказу. Увеличивает стоимость заказа на 5 единиц.
 */
public class GiftWrapping extends OrderDecorator {

    /**
     * Создание декоратора подарочной упаковки.
     *
     * @param orderService Базовый заказ или другой декоратор.
     */
    public GiftWrapping(OrderService orderService) {
        super(orderService);
    }

    /**
     * Получение описания заказа.
     *
     * @return Описание заказа с добавлением подарочной упаковки.
     */
    @Override
    public String getDescription() {
        return orderService.getDescription() + ", подарочная упаковка";
    }

    /**
     * Получение стоимости заказа.
     *
     * @return Стоимость заказа с учетом упаковки.
     */
    @Override
    public double getCost() {
        return orderService.getCost() + 5.0;
    }
}
