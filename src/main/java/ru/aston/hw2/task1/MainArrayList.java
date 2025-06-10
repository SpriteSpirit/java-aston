package ru.aston.hw2.task1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainArrayList {

    static Logger logger = Logger.getLogger(MainArrayList.class.getName());


    public static void main(String[] args) {
        MyArrayList<String> myArrayList = new MyArrayList<>();
        MyArrayList<String> myArrayList2 = new MyArrayList<>(5);
        ArrayList<String> words = new ArrayList<>();

        words.add("One");
        words.add("Two");
        words.add("Three");

        processAdding(myArrayList, "Hello");
        processAdding(myArrayList, "world");
        processAdding(myArrayList, "!");

        processRemoving(myArrayList, 0);
        processRemoving(myArrayList, 1);
//        processRemoving(myArrayList, 3); // IndexOutOfBoundsException

        processAddingAll(myArrayList2, words);

        if (logger.isLoggable(Level.INFO)) {
            logger.info(String.format("Список: %s", myArrayList));
            logger.info(String.format("Список пуст: %s", myArrayList2.isEmpty()));
            logger.info(String.format("Элемент под индексом 0: %s", myArrayList.get(0)));
        }

    }

    private static void processAdding(MyArrayList<String> arrayList, String element) {
        if (element == null || element.isEmpty()) {
            throw new IllegalArgumentException("Строка не может быть пустой.");
        }

        if (logger.isLoggable(Level.INFO)) {
            arrayList.add(element);
            logger.info(String.format("Добавлен элемент '%s'", element));
        }
    }

    private static void processRemoving(MyArrayList<String> arrayList, int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Индекс не может быть отрицательным.");
        }

        if (logger.isLoggable(Level.INFO)) {
            String removed = arrayList.remove(index);
            logger.info(String.format("Удалён элемент '%s' по индексу %d", removed, index));
        }
    }

    private static void processAddingAll(MyArrayList<String> arrayList,
        Collection<String> collection) {
        if (logger.isLoggable(Level.INFO)) {
            boolean added = arrayList.addAll(collection);

            logger.info(String.format("Добавление коллекции: %s. Текущий размер списка: %d",
                added,
                arrayList.size()
            ));
        }
    }
}
