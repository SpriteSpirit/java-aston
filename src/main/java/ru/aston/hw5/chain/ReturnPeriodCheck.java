package ru.aston.hw5.chain;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Обработчик проверки срока возврата товара в цепочке обязанностей.
 */
public class ReturnPeriodCheck extends ReturnHandler {

    private static final Logger logger = Logger.getLogger(ReturnPeriodCheck.class.getName());

    /**
     * Логирование сообщения о невозможности возврата и возврата false.
     *
     * @return Всегда возвращает false
     */
    private boolean logAndReturnFalse() {
        if (logger.isLoggable(Level.INFO)) {
            logger.info("Возврат невозможен: срок возврата истёк");
        }
        return false;
    }

    /**
     * Обработка запроса на возврат с проверкой истечения срока возврата.
     *
     * @param request Запрос на возврат товара
     * @return false если срок возврата истёк, иначе передаёт запрос следующему обработчику
     */
    @Override
    public boolean handleReturn(ReturnRequest request) {
        return request.daysSinceDelivery() > 14
            ? logAndReturnFalse()
            : nextHandler != null && nextHandler.handleReturn(request);
    }
}