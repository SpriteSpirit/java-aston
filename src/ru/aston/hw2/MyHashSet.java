package ru.aston.hw2;


import java.util.LinkedList;

/**
 * Реализация аналога HashSet с двумя методами (вставить и удалить)
 */

public class MyHashSet<E> {

    // размер массива по умолчанию
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    // коэффициент загрузки
    private static final float LOAD_FACTOR = 0.75f;
    // массив ячеек связанного списка
    private LinkedList<E>[] buckets;
    // размер сета
    private int size;
    // пороговое значение
    private int threshold;

    /**
     * Конструктор. Инициализация массива связным списком размера 16 Инициализация длины множества
     * Инициализация порога Заполнение ячеек массива связными списками
     */
    @SuppressWarnings("unchecked")
    public MyHashSet() {
        buckets = (LinkedList<E>[]) new LinkedList[DEFAULT_INITIAL_CAPACITY];
        threshold = (int) (DEFAULT_INITIAL_CAPACITY * LOAD_FACTOR);
        size = 0;

        for (int i = 0; i < DEFAULT_INITIAL_CAPACITY; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    public static void main(String[] args) {
        MyHashSet<String> myHashSet = new MyHashSet<>();
        int load_capacity = 12;

        // проверка изменения размера массива resize()
        // заполнение до порога расширения
        for (int i = 0; i < load_capacity; i++) { // 16 * 0.75 = 12
            myHashSet.insert("item_" + i);
        }

        // добавление этого элемента вызовет resize()
        myHashSet.insert("trigger_resize");
        System.out.println(myHashSet.size);

        myHashSet.printAllElements();

        for (int i = 0; i < load_capacity; i++) {
            System.out.println("item_" + i);
            if (!myHashSet.containsElement("item_" + i) &&
                !myHashSet.containsElement("trigger_resize")) {
                throw new AssertionError("Элементы потеряны после изменения размера");
            }
        }

        System.out.println(myHashSet.insert("Hello"));
        System.out.println(myHashSet.insert("world"));
        System.out.println(myHashSet.insert("!"));
        System.out.println(myHashSet.insert("Привет"));

        System.out.println(myHashSet.remove("Привет!"));

        myHashSet.printAllElements();

    }

    /**
     * Вычисляет индекс по формуле: модуль остатка от деления хеш кода элемента на длину массива
     *
     * @param element добавляемый элемент
     * @return индекс ячейки
     */
    private int getBucketIndex(E element) {
        return Math.abs(element.hashCode() % buckets.length);
    }

    /**
     * Увеличивает размер массива в 2 раза от старого при достижении порогового значения
     * нагрузочного коэффициента Коэффициент загрузки = Количество хранимых элементов в таблице /
     * размер хэш-таблицы Например, если изначальное количество ячеек в таблице равно 16, и
     * коэффициент загрузки равен 0,75, то из этого следует, что когда количество заполненных ячеек
     * достигнет 12, их количество автоматически увеличится.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        // сохранение старых ячеек
        LinkedList<E>[] oldBuckets = buckets;
        // увеличение размера в 2 раза
        buckets = (LinkedList<E>[]) new LinkedList[oldBuckets.length * 2];
        // обновление порога
        threshold = (int) (buckets.length * LOAD_FACTOR);

        // инициализация новых ячеек
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }

        // сброс размера
        size = 0;

        // повторное хеширование всех элементов из старого массива
        for (LinkedList<E> bucket : oldBuckets) {
            for (E element : bucket) {
                // добавление элемента в новый массив
                int index = getBucketIndex(element);

                buckets[index].add(element);
                size++;
            }
        }
    }

    /**
     * Реализует вставку элемента по быстрому поиску O(1) Вычисляет индекс элемента. Создает
     * связанный список в ячейке массива по индексу. Проверяет, содержится ли элемент в списке. Если
     * нет, то добавляет элемент в список и увеличивает размер списка на 1. Возвращает true, если
     * элемент добавлен в список, false - если такой элемент уже есть в списке.
     */
    public boolean insert(E element) {
        if (element == null) {
            throw new NullPointerException("Элемент не может быть null");
        }

        if (size + 1 > threshold) {
            // увеличить размер
            resize();
        }

        int index = getBucketIndex(element);
        LinkedList<E> bucket = buckets[index];

        if (bucket.contains(element)) {
            return false;
        }

        bucket.add(element);
        size++;

        return true;
    }

    /**
     * Удаляет элемент из массива. Если элемент null, то возвращает false. Получает индекс элемента
     * и удаляет его из массива. Если удаление произошло, то уменьшает размер массива. Возвращает
     * результат удаления: true (элемент удален) или false (элемент не удален).
     */
    public boolean remove(E element) {
        if (element == null) {
            return false;
        }

        int index = getBucketIndex(element);
        boolean removed = buckets[index].remove(element);

        if (removed) {
            size--;
        }
        return removed;
    }

    /**
     * Проверяет вхождение элемента в массив.
     */
    public boolean containsElement(E element) {
        if (element == null) {
            throw new NullPointerException("Элемент не может быть null");
        }

        int index = getBucketIndex(element);
        return buckets[index].contains(element);
    }

    /**
     * Вывод всех элементов массива
     */
    public void printAllElements() {
        for (LinkedList<E> bucket : buckets) {
            for (E element : bucket) {
                System.out.println(element);
            }
        }
    }
}
