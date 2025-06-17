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
     */
    private Order(Builder builder) {
        this.items = List.copyOf(builder.items);
        this.deliveryAddress = Objects.requireNonNull(builder.deliveryAddress,
            "Адрес доставки обязателен");
        this.comment = builder.comment != null ? builder.comment : "";
    }

    /**
     * Фабричный метод для создания строителя заказа. Пример:
     * {@code Order.builder().item("Товар").build()}
     */
    public static Builder builder() {
        return new Builder();
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
    public static final class Builder {

        private final List<String> items = new ArrayList<>();
        private String deliveryAddress;
        private String comment;

        /**
         * Приватный конструктор строителя. Использование: только через {@link Order#builder()}.
         */
        private Builder() {
        }

        /**
         * Добавление товара в заказ.
         *
         * @param item Название товара.
         * @return Текущий строитель.
         */
        public Builder item(String item) {
            this.items.add(Objects.requireNonNull(item, "Товар не может быть null"));
            return this;
        }

        /**
         * Добавление товаров в заказ.
         *
         * @param items Список товаров.
         * @return Текущий строитель.
         */
        public Builder items(List<String> items) {
            this.items.addAll(Objects.requireNonNull(items, "Список товаров не может быть null"));
            return this;
        }

        /**
         * Добавление адреса доставки.
         *
         * @param address Адрес доставки.
         * @return Текущий строитель.
         */
        public Builder deliveryAddress(String address) {
            this.deliveryAddress = address;
            return this;
        }

        /**
         * Добавление комментария к заказу.
         *
         * @param comment Текст комментария.
         * @return Текущий строитель.
         */
        public Builder comment(String comment) {
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
            return new Order(this);
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
