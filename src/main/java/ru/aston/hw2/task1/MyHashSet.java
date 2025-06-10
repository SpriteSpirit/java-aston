package ru.aston.hw2.task1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Реализация аналога HashSet с двумя методами (вставить и удалить). HashSet состоит из массива
 * ячеек buckets фиксированного размера (16 ячеек). Каждая ячейка bucket - это связный список для
 * хранения элементов.
 */

public class MyHashSet<E> {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;
    static Logger logger = Logger.getLogger(MyHashSet.class.getName());
    private List<E>[] buckets;
    private int size;
    private int threshold;

    /**
     * Создает пустой HashSet с начальной вместимостью 16 и коэффициентом загрузки 0.75.
     * Инициализирует массив ячеек buckets связными списками. Порог threshold вычисляется как
     * вместимость * коэффициент загрузки.
     */
    public MyHashSet() {
        initializeBuckets(DEFAULT_INITIAL_CAPACITY);
        logger.info("Объем HashSet: " + DEFAULT_INITIAL_CAPACITY);
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public int getThreshold() {
        return threshold;
    }

    /**
     * Инициализирует массив ячеек при создании или расширении множества. Создает массив списков
     * заданной емкости и вычисляет порог для автоматического расширения.
     */
    @SuppressWarnings("unchecked")
    private void initializeBuckets(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Ёмкость ен может быть отрицательной.");
        }

        buckets = new List[capacity];
        threshold = (int) (capacity * LOAD_FACTOR);

        for (int i = 0; i < capacity; i++) {
            buckets[i] = new ArrayList<>();
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
    private List<E> getBucket(E element) {
        Objects.requireNonNull(element, "Элемент не может быть null");

        return buckets[getBucketIndex(element)];
    }

    /**
     * Проверка наличия элемента в ячейке.
     */
    private boolean isElementExist(E element, List<E> bucket) {
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
    private void resize() {
        List<E>[] oldBuckets = buckets;
        initializeBuckets(oldBuckets.length * 2);

        size = 0;

        Arrays.stream(oldBuckets)
            .flatMap(List::stream)
            .forEach(element -> {
                getBucket(element).add(element);
                size++;
            });
    }

    /**
     * Добавляет элемент во множество, если его там нет. Если коэффициент загрузки превышен, то
     * вместимость увеличивается в 2 раза. Элемент для добавления не может быть null.
     */
    public boolean insert(E element) {
        checkResize();

        List<E> bucket = getBucket(element);
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
        List<E> bucket = getBucket(element);
        boolean removed = bucket.remove(element);

        if (removed) {
            size--;
        }

        return removed;
    }

    /**
     * Проверяет, отсутствует ли элемент. Элемент для проверки не может быть null.
     */
    public boolean doesNotContainsElement(E element) {
        return !isElementExist(element, getBucket(element));
    }

    /**
     * Вывод всех элементов множества для отладки.
     */
    public void printAllElements() {
        if (size == 0) {
            logger.info("HashSet пуст");
        }

        String elements = Arrays.stream(buckets)
            .flatMap(List::stream)
            .map(element -> "- " + element)
            .collect(Collectors.joining("\n", "Содержимое HashSet:\n", ""));

        logger.info(elements);
    }

    @Override
    public String toString() {
        return "MyHashSet{" +
            "buckets=" + Arrays.toString(buckets) +
            ", size=" + getSize() +
            ", threshold=" + getThreshold() +
            '}';
    }
}
