package ru.aston.hw2;


public class MyArrayList<E> {

    private Object[] elements;
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENT_DATA = {};
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
}
