package ru.aston.hw5.proxy;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Реализация каталога товаров, загружающая данные из базы данных. Используется прокси-объектом для
 * ленивой загрузки.
 */
public class RealProductCatalog implements ProductCatalog {

    private static final Logger logger = Logger.getLogger(RealProductCatalog.class.getName());

    /**
     * Создание нового экземпляра каталога и подключение к БД.
     */
    public RealProductCatalog() {
        logging(">>> Подключение к базе данных...");
    }

    /**
     * Логирование сообщения на уровне INFO.
     *
     * @param message Формат сообщения.
     * @param args    Аргументы для подстановки в сообщение.
     */
    private void logging(String message, Object... args) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format(message, args));
        }
    }

    /**
     * Получение списка товаров из каталога.
     *
     * @return Неизменяемый список товаров.
     */
    @Override
    public List<String> getProducts() {
        return Arrays.asList("Книга", "Ручка", "Пенал");
    }
}
