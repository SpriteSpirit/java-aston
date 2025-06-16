package ru.aston.hw5.proxy;

import java.util.List;

/**
 * Интерфейс для работы с каталогом товаров. Определяет основной метод получения списка товаров.
 */
public interface ProductCatalog {

    List<String> getProducts();
}
