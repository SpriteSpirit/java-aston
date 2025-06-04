package main.java.ru.aston.hw2.task1;


import java.util.Arrays;
import java.util.Collection;

public class MyArrayList<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENT_DATA = {};
    private Object[] elements;
    private int size;

    /**
     * Инициализация массива с заданной длиной
     */
    public MyArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Инициализирует массив заданной ёмкости. Если ёмкость положительная, то создаётся массив
     * заданной длины. Если ёмкость равна 0, то используется общий пустой массив для экономии
     * памяти. Если ёмкость отрицательная, то бросает исключение.
     *
     * @param initialCapacity - ёмкость/длина массива
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elements = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elements = EMPTY_ELEMENT_DATA;
        } else {
            throw new IllegalArgumentException("Ёмкость не может иметь отрицательное значение");
        }
    }

    /**
     * Проверяет ёмкость массива на случай превышения объема. Если объём превышен, то увеличивает
     * ёмкость в 1.5 раза.
     *
     * @param minCapacity - минимальная ёмкость
     */
    private void ensureCapacity(int minCapacity) {
        int oldCapacity = elements.length;

        if (minCapacity > oldCapacity) {
            int newCapacity = oldCapacity + (oldCapacity >> 1);

            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }

            Object[] newElements = new Object[newCapacity];
            System.arraycopy(elements, 0, newElements, 0, oldCapacity);
            elements = newElements;
        }
    }


    /**
     * Проверяет, что индекс не выходит за пределы допустимых значений.
     *
     * @param index - индекс элемента.
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * Добавляет элемент в массив. Перед добавлением проверяет на предел допустимой ёмкости. В
     * случае превышения - увеличивает ёмкость в 1.5 раза.
     */
    public void add(E element) {
        ensureCapacity(size + 1);
        elements[size++] = element;
    }

    /**
     * Добавляет элемент на указанную позицию по индексу.
     *
     * @param index   - индекс/позиция для вставки элемента
     * @param element - элемент
     */
    public void add(int index, E element) {
        checkIndex(index);
        ensureCapacity(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Возвращает размер массива.
     */
    public int size() {
        return size;
    }

    /**
     * Проверяет размер на нулевое значение.
     *
     * @return true, если размер нулевой и false, если нет.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Возвращает элемент по индексу.
     *
     * @param index - индекс/позиция искомого элемента.
     * @return найденный элемент.
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }

    /**
     * Удаляет элемент по индексу.
     *
     * @param index - индекс/позиция удаляемого элемента.
     * @return удаленный элемент.
     */
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        checkIndex(index);
        E removedElement = (E) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;

        return removedElement;
    }

    /**
     * Добавляет коллекцию в массив.
     *
     * @param collection - коллекция для добавления
     * @return true, если список изменился (добавлены элементы), false, если коллекция пуста
     * @throws NullPointerException если коллекция равна null
     */
    public boolean addAll(Collection<? extends E> collection) {
        if (collection == null) {
            throw new NullPointerException("Коллекция не может быть null.");
        }

        Object[] collectionToArray = collection.toArray();
        int arrayLength = collectionToArray.length;

        ensureCapacity(size + collection.size());
        System.arraycopy(collectionToArray, 0, elements, size, arrayLength);
        size += arrayLength;

        return !collection.isEmpty();
    }

    @Override
    public String toString() {
        return "MyArrayList{" +
            "elements=" + Arrays.toString(elements) +
            ", size=" + size +
            '}';
    }
}
