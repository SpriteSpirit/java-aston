package ru.aston.hw5.chain;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Обработчик, проверяющий статус доставки заказа перед возвратом. Отклоняет возврат, если заказ не
 * был доставлен.
 */
public class OrderStatusCheck extends ReturnHandler {

    private static final Logger logger = Logger.getLogger(OrderStatusCheck.class.getName());

    /**
     * Логирование сообщения о невозможности возврата из-за не доставленного заказа.
     */
    private void logUndeliveredOrder() {
        if (logger.isLoggable(Level.INFO)) {
            logger.info("Возврат невозможен: заказ не доставлен");
        }
    }

    /**
     * Обработка запроса на возврат и проверка был ли заказ доставлен.
     *
     * @param request Запрос на возврат товара.
     * @return false если заказ не доставлен, иначе передает запрос следующему обработчику или
     * возвращает true, если следующего обработчика нет
     */
    @Override
    public boolean handleReturn(ReturnRequest request) {
        boolean canProceed = request.orderDelivered();

        if (!canProceed) {
            logUndeliveredOrder();
        }

        return canProceed && (nextHandler == null || nextHandler.handleReturn(request));
    }
}
