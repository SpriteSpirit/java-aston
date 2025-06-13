package ru.aston.hw5.strategy;

/**
 * Реализация паттерна "Стратегия" для применения скидок к заказу. Содержит конкретные
 * стратегии, например, {@link FixedDiscount}
 */
public class StrategyPatternRealize {

    /**
     * Реализация стратегии скидки, которая вычитает фиксированную сумму из общей цены. Если скидка
     * превышает цену, то итоговая стоимость будет равна 0.
     */
    static class FixedDiscount implements DiscountStrategy {

        private final double discountAmount;

        /**
         * Стратегия фиксированной скидки. Вычитает переданную сумму из общей стоимости.
         * Итоговая стоимость не может быть отрицательной.
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
}
