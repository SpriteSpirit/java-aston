package ru.aston.hw5.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Неизменяемый класс заказа, создаваемый через Builder. Гарантирует обязательное заполнение полей,
 * защищает список товаров от изменений после создания.
 */
public final class Order {

    private final List<String> items;
    private final String deliveryAddress;
    private final String comment;

    /**
     * Создание нового заказа.
     *
     * @param items           Список товаров (создается защитная копия).
     * @param deliveryAddress Адрес доставки (обязательное поле).
     * @param comment         Комментарий к заказу (пустая строка если null).
     */
    private Order(List<String> items, String deliveryAddress, String comment) {
        this.items = List.copyOf(items);
        this.deliveryAddress = Objects.requireNonNull(deliveryAddress, "Поле адрес не быть пустым");
        this.comment = comment != null ? comment : "";
    }

    @Override
    public String toString() {
        return "Order{items=" + items
            + ", address=" + deliveryAddress
            + ", comment=" + comment
            + "}";
    }

    /**
     * Строитель для создания объектов Order. Позволяет задавать параметры пошагово.
     */
    public static class Builder {

        private final List<String> items = new ArrayList<>();
        private String deliveryAddress;
        private String comment;

        /**
         * Добавление товара в заказ.
         *
         * @param item Название товара.
         * @return Текущий строитель.
         */
        public Builder addItem(String item) {
            items.add(item);
            return this;
        }

        /**
         * Добавление адреса доставки.
         *
         * @param address Адрес доставки.
         * @return Текущий строитель.
         */
        public Builder setDeliveryAddress(String address) {
            this.deliveryAddress = address;
            return this;
        }

        /**
         * Добавление комментария к заказу.
         *
         * @param comment Текст комментария.
         * @return Текущий строитель.
         */
        public Builder setComment(String comment) {
            this.comment = comment;
            return this;
        }

        /**
         * Создание объекта Order.
         *
         * @return Новый заказ.
         * @throws NullPointerException Если не указан адрес доставки.
         */
        public Order build() {
            return new Order(items, deliveryAddress, comment);
        }

        @Override
        public String toString() {
            return "Builder{" +
                "items=" + items +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", comment='" + comment + '\'' +
                '}';
        }
    }
}
