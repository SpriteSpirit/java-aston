package ru.aston.hw5.adapter;

/**
 * Адаптер для интеграции LegacyDelivery с современной системой доставки.
 */
public class DeliveryAdapter implements DeliveryService {

    private final LegacyDelivery legacyDelivery;

    /**
     * Создание адаптера, который инициализирует устаревшую систему доставки.
     */
    public DeliveryAdapter() {
        this.legacyDelivery = new LegacyDelivery();
    }

    /**
     * Адаптация современного интерфейса доставки к устаревшей системе.
     *
     * @param orderId идентификатор заказа
     * @param address адрес доставки
     */
    @Override

    public void deliverOrder(String orderId, String address) {
        legacyDelivery.ship(orderId, address);
    }

    @Override
    public String toString() {
        return "DeliveryAdapter{" +
            "legacyDelivery=" + legacyDelivery +
            '}';
    }
}
