package ru.aston.hw2.task1;


import java.util.LinkedList;
import java.util.Objects;

/**
 * Реализация аналога HashSet с двумя методами (вставить и удалить). HashSet состоит из массива
 * ячеек buckets фиксированного размера (16 ячеек). Каждая ячейка bucket - это связный список для
 * хранения элементов.
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
     * Создает пустой HashSet с начальной вместимостью 16 и коэффициентом загрузки 0.75.
     * Инициализирует массив ячеек buckets связными списками. Порог threshold вычисляется как
     * вместимость * коэффициент загрузки.
     */
    @SuppressWarnings("unchecked")
    public MyHashSet() {
        initBuckets(DEFAULT_INITIAL_CAPACITY);
        size = 0;
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
        System.out.printf("Размер hashSet: %n", myHashSet.size);

        System.out.println("Вывод всех элементов");
        myHashSet.printAllElements();

        for (int i = 0; i < load_capacity; i++) {
            if (!myHashSet.containsElement("item_" + i) &&
                !myHashSet.containsElement("trigger_resize")) {
                throw new AssertionError("Элементы потеряны после изменения размера");
            }
        }

        System.out.println(myHashSet.insert("Hello"));
        System.out.println(myHashSet.insert("world"));
        System.out.println(myHashSet.insert("!"));
        System.out.println(myHashSet.insert("Привет"));
        System.out.println(myHashSet.insert("Привет"));

        System.out.println(myHashSet.remove("!"));
        System.out.println("Вывод элементов после удаления '!'");
        myHashSet.printAllElements();
    }

    /**
     * Инициализация ячеек множества.
     */
    @SuppressWarnings("unchecked")
    private void initBuckets(int capacity) {
        buckets = (LinkedList<E>[]) new LinkedList[capacity];
        threshold = (int) (capacity * LOAD_FACTOR);

        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    /**
     * Проверяет нужно ли увеличивать размер множества при увеличении размера.
     */
    private void checkResize() {
        if (size + 1 > threshold) {
            resize();
        }
    }

    /**
     * Возвращает ячейку (bucket), в которой находится элемент. Элемент не может быть null.
     */
    private LinkedList<E> getBucket(E element) {
        Objects.requireNonNull(element, "Элемент не может быть null");

        return buckets[getBucketIndex((element))];
    }

    /**
     * Проверка наличия элемента в ячейке.
     */
    private boolean isElementExist(E element, LinkedList<E> bucket) {
        return bucket.contains(element);
    }

    /**
     * Вычисляет индекс ячейки (bucket) для элемента.
     */
    private int getBucketIndex(E element) {
        return Math.abs(element.hashCode() % buckets.length);
    }

    /**
     * Увеличивает вместимость множества в 2 раза и выполняет перехеширование всех элементов.
     * Вызывается автоматически при достижении порогового значения (capacity * LOAD_FACTOR).
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        LinkedList<E>[] oldBuckets = buckets;
        initBuckets(oldBuckets.length * 2);

        size = 0;

        // повторное хеширование всех элементов из старого массива
        for (LinkedList<E> bucket : oldBuckets) {
            for (E element : bucket) {
                // добавление элемента и увеличение размера
                getBucket(element).add(element);
                size++;
            }
        }
    }

    /**
     * Добавляет элемент во множество, если его там нет. Если коэффициент загрузки превышен, то
     * вместимость увеличивается в 2 раза. Элемент для добавления не может быть null.
     */
    public boolean insert(E element) {
        checkResize();

        LinkedList<E> bucket = getBucket(element);
        boolean elementExists = isElementExist(element, bucket);

        if (!elementExists) {
            bucket.add(element);
            size++;
        }

        return !elementExists;
    }

    /**
     * Удаляет элемент из множества, если он найден. Элемент для удаления не может быть null.
     */
    public boolean remove(E element) {
        LinkedList<E> bucket = getBucket(element);
        boolean removed = bucket.remove(element);

        if (removed) {
            size--;
        }

        return removed;
    }

    /**
     * Проверяет вхождение элемента во множество. Элемент для проверки не может быть null.
     */
    public boolean containsElement(E element) {
        return isElementExist(element, getBucket(element));
    }

    /**
     * Вывод всех элементов множества для отладки.
     */
    public void printAllElements() {
        for (LinkedList<E> bucket : buckets) {
            for (E element : bucket) {
                System.out.println(element);
            }
        }
    }
}
