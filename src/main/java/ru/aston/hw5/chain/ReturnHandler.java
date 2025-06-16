package ru.aston.hw5.chain;

/**
 * Абстрактный базовый класс для обработчиков в цепочке обязанностей (Chain of Responsibility) при
 * обработке запросов на возврат товара.
 */
public abstract class ReturnHandler {

    protected ReturnHandler nextHandler;

    /**
     * Задание следующего обработчика в цепочке.
     *
     * @param nextHandler Следующий обработчик запроса.
     */
    public void setNext(ReturnHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    /**
     * Обработка запроса на возврат товара.
     *
     * @param request Запрос на возврат.
     * @return Результат обработки запроса.
     */
    public abstract boolean handleReturn(ReturnRequest request);

    @Override
    public String toString() {
        return "ReturnHandler{" +
            "nextHandler=" + nextHandler +
            '}';
    }
}
