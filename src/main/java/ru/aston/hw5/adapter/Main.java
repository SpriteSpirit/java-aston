package ru.aston.hw5.adapter;

public class Main {

    public static void main(String[] args) {
        DeliveryService delivery = new DeliveryAdapter();
        delivery.deliverOrder("789",
            "ул. Ленина, 10"); // Доставка заказа 789 по адресу ул. Ленина, 10
    }
}
