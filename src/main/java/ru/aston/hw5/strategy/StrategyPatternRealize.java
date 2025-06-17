package ru.aston.hw5.strategy;

/**
 * Реализация паттерна "Стратегия" для применения скидок к заказу. Содержит конкретные стратегии,
 * например, {@link FixedDiscount}
 */
public class StrategyPatternRealize {

    private StrategyPatternRealize() {
        throw new IllegalStateException("StrategyPattern class");
    }

    /**
     * Реализация стратегии скидки, которая вычитает фиксированную сумму из общей цены. Если скидка
     * превышает цену, то итоговая стоимость будет равна 0.
     */
    public static class FixedDiscount implements DiscountStrategy {

        private final double discountAmount;

        /**
         * Стратегия фиксированной скидки. Вычитает переданную сумму из общей стоимости. Итоговая
         * стоимость не может быть отрицательной.
         *
         * @param discountAmount Сумма скидки (положительное число)
         */
        public FixedDiscount(double discountAmount) {
            if (discountAmount < 0) {
                throw new IllegalArgumentException("Скидка не может быть отрицательной");
            }
            this.discountAmount = discountAmount;
        }

        /**
         * Применяет фиксированную скидку к общей цене.
         *
         * @param totalPrice Полная стоимость.
         * @return Стоимость после применения скидки.
         */
        @Override
        public double applyDiscount(double totalPrice) {
            return Math.max(totalPrice - discountAmount, 0);
        }

        @Override
        public String toString() {
            return "FixedDiscount{" +
                "discountAmount=" + discountAmount +
                '}';
        }
    }

    /**
     * Реализация стратегии процентной скидки.
     */
    public static class PercentageDiscount implements DiscountStrategy {

        private final double percentage;

        /**
         * @param percentage Значение
         */
        public PercentageDiscount(double percentage) {
            if (percentage < 0) {
                throw new IllegalArgumentException("Процент скидки не может быть отрицательным.");
            }
            this.percentage = percentage;
        }

        /**
         * Применяет фиксированную скидку к общей цене.
         *
         * @param totalPrice Полная стоимость.
         * @return Стоимость после применения скидки.
         */
        @Override
        public double applyDiscount(double totalPrice) {
            return Math.max(totalPrice * (1 - percentage / 100), 0);
        }

        @Override
        public String toString() {
            return "PercentageDiscount{" +
                "percentage=" + percentage +
                '}';
        }
    }

    /**
     * Контекст. Заказ с возможностью применения скидки. Использует стратегию
     * {@link DiscountStrategy} для расчета итоговой стоимости.
     */
    public static class Order {

        private final double totalPrice;
        private DiscountStrategy discountStrategy;

        /**
         * Создаёт заказ с указанной стоимостью.
         *
         * @param totalPrice Стоимость заказа (>= 0).
         */
        public Order(double totalPrice) {
            if (totalPrice < 0) {
                throw new IllegalArgumentException(
                    "Стоимость не может иметь отрицательное значение.");
            }

            this.totalPrice = totalPrice;
        }

        /**
         * Устанавливает стратегию расчета скидки.
         *
         * @param discountStrategy Стратегия (может быть null)
         */
        public void setDiscountStrategy(DiscountStrategy discountStrategy) {
            this.discountStrategy = discountStrategy;
        }

        /**
         * Рассчитывает итоговую стоимость после применения скидки.
         *
         * @return Стоимость заказа после применения скидки.
         */
        public double calculateFinalPrice() {
            return discountStrategy != null ?
                discountStrategy.applyDiscount(totalPrice) : totalPrice;
        }


        @Override
        public String toString() {
            return "Order{" +
                "totalPrice=" + totalPrice +
                ", discountStrategy=" + discountStrategy +
                '}';
        }
    }
}
