package ru.aston.hw5.proxy;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        ProductCatalog catalog = new ProxyProductCatalog();

        logging(">>> Прокси создан, но каталог еще не загружен");
        logging(">>> Первый вызов:: %s", catalog.getProducts());
        logging(">>> Второй вызов:: %s", catalog.getProducts());
    }

    public static void logging(String message, Object... args) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format(message, args));
        }
    }
}
