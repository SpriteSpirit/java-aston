package ru.aston.hw5.chain;

/**
 * Запрос на возврат товара, содержащий информацию о заказе и сроке доставки.
 *
 * @param orderId           Уникальный идентификатор заказа.
 * @param orderDelivered    Флаг, указывающий был ли заказ доставлен.
 * @param daysSinceDelivery Количество дней с момента доставки.
 */
public record ReturnRequest(String orderId, boolean orderDelivered, int daysSinceDelivery) {

    @Override
    public String toString() {
        return "ReturnRequest{" +
            "orderId='" + orderId + '\'' +
            ", orderDelivered=" + orderDelivered +
            ", daysSinceDelivery=" + daysSinceDelivery +
            '}';
    }
}
