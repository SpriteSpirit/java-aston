package ru.aston.hw5.proxy;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Прокси-объект для ленивой загрузки каталога товаров. Контролирует доступ к реальному каталогу,
 * загружая данные только при первом запросе.
 */
public class ProxyProductCatalog implements ProductCatalog {

    private static final Logger logger = Logger.getLogger(ProxyProductCatalog.class.getName());
    private RealProductCatalog realCatalog;

    /**
     * Логирование информационного сообщения.
     *
     * @param message текст сообщения с возможностью форматирования
     * @param args    аргументы для подстановки в сообщение
     */
    private void logging(String message, Object... args) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format(message, args));
        }
    }

    /**
     * Получение списка товаров, загрузка данных при первом вызове.
     *
     * @return список товаров (кешируется после первой загрузки)
     */
    @Override
    public List<String> getProducts() {
        if (realCatalog == null) {
            logging(">>> Первый запрос - загружаю данные из БД");
            realCatalog = new RealProductCatalog();
        } else {
            logging(">>> Использую кешированные данные");
        }
        return realCatalog.getProducts();
    }

    @Override
    public String toString() {
        return "ProxyProductCatalog{" +
            "realCatalog=" + realCatalog +
            '}';
    }
}
