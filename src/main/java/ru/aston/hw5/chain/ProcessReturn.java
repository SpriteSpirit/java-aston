package ru.aston.hw5.chain;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Обработчик, выполняющий финальное одобрение возврата товара. Завершает цепочку проверок успешным
 * статусом.
 */
public class ProcessReturn extends ReturnHandler {

    private static final Logger logger = Logger.getLogger(ProcessReturn.class.getName());

    /**
     * Логирование информации об успешном одобрении возврата.
     *
     * @param args Аргументы для форматирования сообщения (ожидается orderId).
     */
    private void logSuccessRefund(Object... args) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("Возврат одобрен для заказа ", args));
        }
    }

    /**
     * Обрабатывает запрос на возврат, всегда одобряя его после прохождения предыдущих проверок.
     *
     * @param request Запрос на возврат товара.
     * @return Всегда возвращает true, так как это финальный успешный обработчик.
     */
    @Override
    public boolean handleReturn(ReturnRequest request) {
        logSuccessRefund(request.orderId());
        return true;
    }
}
