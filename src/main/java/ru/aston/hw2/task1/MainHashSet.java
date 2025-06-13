package ru.aston.hw2.task1;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainHashSet {

    static final Logger logger = Logger.getLogger(MainHashSet.class.getName());


    public static void main(String[] args) {
        System.setProperty("java.util.logging.SimpleFormatter.format",
            "[%1$tT] [%4$-7s] %5$s %n");

        int loadCapacity = 12;
        MyHashSet<String> myHashSet = new MyHashSet<>();

        initializeHashSet(myHashSet, loadCapacity);
        verifyElements(myHashSet, loadCapacity);

        try (Scanner scanner = new Scanner(System.in)) {
            processInserting(myHashSet, scanner);
            processRemoving(myHashSet, scanner);
        }

        logAndPrintAllElements(myHashSet);
    }

    private static void initializeHashSet(MyHashSet<String> hashSet, int capacity) {
        for (int i = 0; i < capacity; i++) { // 16 * 0.75 = 12
            hashSet.insert("item_" + i);
        }

        hashSet.insert("trigger_resize");

        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("Размер HashSet: %d", hashSet.getSize()));
        }
    }

    private static void logAndPrintAllElements(MyHashSet<String> hashSet) {
        logger.info("Вывод всех элементов:\n");
        hashSet.printAllElements();
    }

    private static void verifyElements(MyHashSet<String> hashSet, int capacity) {
        for (int i = 0; i < capacity; i++) {
            String item = "item_" + i;

            if (hashSet.doesNotContainsElement(item)) {
                throw new AssertionError("Потерян элемент: " + item);
            }

            if (hashSet.doesNotContainsElement("trigger_resize")) {
                throw new AssertionError("Потерян элемент: trigger_resize");
            }
        }
    }

    private static void processInserting(MyHashSet<String> hashSet, Scanner scanner) {
        logger.info("Режим добавления элементов (пустая строка - завершить)");

        while (true) {
            logger.info("Введите строковый элемент для добавления: ");
            String userInput = scanner.nextLine().trim();

            if (userInput.isEmpty()) {
                logger.info("Завершение добавления элементов");
                break;
            }

            if (logger.isLoggable(Level.INFO)) {
                boolean inserted = hashSet.insert(userInput);

                logger.info(String.format("%s. Текущий размер: %d",
                    inserted ? "Добавлено" : "Уже существует",
                    hashSet.getSize()));
            }
        }

    }

    private static void processRemoving(MyHashSet<String> hashSet, Scanner scanner) {
        logger.info("Режим удаления элементов (пустая строка - завершить)");

        while (true) {
            logger.info("Введите строковый элемент для удаления: ");
            String userInput = scanner.nextLine().trim();

            if (userInput.isEmpty()) {
                logger.info("Завершение удаления элементов");
                break;
            }

            if (logger.isLoggable(Level.INFO)) {
                boolean removed = hashSet.remove(userInput);

                logger.info(String.format("%s. Текущий размер: %d",
                    removed
                        ? "Удалено"
                        : "Не найдено",
                    hashSet.getSize())
                );

            }
        }
    }
}
