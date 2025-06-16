package ru.aston.hw5.adapter;

/**
 * Современный интерфейс службы доставки.
 */
public interface DeliveryService {

    /**
     * Доставка заказа по указанному адресу.
     *
     * @param orderId Уникальный идентификатор заказа.
     * @param address Полный адрес доставки.
     */
    void deliverOrder(String orderId, String address);
}
