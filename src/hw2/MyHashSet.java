package hw2;


import java.util.LinkedList;

public class MyHashSet<E> {
    // размер массива по умолчанию
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;
    // массив ячеек связанного списка
    private LinkedList[] buckets;
    // размер сета
    private int size;
    // пороговое значение
    private int treshold;

    /**
     * Конструктор.
     * Инициализация массива связным списком размера 16
     * Инициализация длины множества
     * Заполнение ячеек массива связными списками
     */
    public MyHashSet() {
        buckets = new LinkedList[DEFAULT_INITIAL_CAPACITY];
        treshold = (int)(DEFAULT_INITIAL_CAPACITY * LOAD_FACTOR);
        size = 0;

        for (int i = 0; i < DEFAULT_INITIAL_CAPACITY; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    /**
     * Вычисляет индекс по формуле: модуль остатка от деления хешкода элемента на длину массива
     * @param element добавляемый элемент
     * @return индекс ячейки
     */
    private int getBucketIndex(E element) {
        return Math.abs(element.hashCode() % buckets.length);
    }

    /**
     * Увеличивает размер массива в 2 раза от старого при достижении порогового значения
     * нагрузочного коэффициента
     * Коэффициент загрузки = Количество хранимых элементов в таблице / размер хэш-таблицы
     * Например, если изначальное количество ячеек в таблице равно 16, и коэффициент загрузки равен 0,75,
     * то из этого следует, что когда количество заполненных ячеек достигнет 12, их количество автоматически увеличится.
     */
    private void resize() {
        // сохранение старых ячеек
        LinkedList<E>[] oldBuckets = buckets;
        // увеличение размера в 2 раза
        buckets = new LinkedList[oldBuckets.length * 2];
        treshold = (int)(buckets.length * LOAD_FACTOR);

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
     * Реализует вставку элемента по быстрому поиску O(1)
     * Вычисляет индекс элемента. Создает связанный список в ячейке массива по индексу.
     * Проверяет, содержится ли элемент в списке. Если нет, то добавляет элемент в список
     * и увеличивает размер списка на 1.
     * Возвращает true, если элемент добавлен в список, false - если такой элемент уже есть в списке.
     */
    public boolean insert(E element) {
        if (element == null) {
            throw  new NullPointerException("Элемент не может быть null");
        }

        int index = getBucketIndex(element);

        LinkedList<E> bucket = buckets[index];

        if (bucket.contains(element)) {
            return false;
        }

        if (++size > treshold) {
            // увеличить размер
            resize();

        }

        bucket.add(element);
        size++;

        return true;
    }

    /**
     * Удаляет элемент из массива. Если элемент null, то возвращает false.
     * Получает индекс элемента и удаляет его из массива. Если удаление произошло, то уменьшает размер массива.
     * Возвращает результат удаления: true (элемент удален) или false (элемент не удален).
     */
    public boolean remove(E element) {
        if (element == null) return false;

        int index = getBucketIndex(element);
        boolean removed = buckets[index].remove(element);

        if (removed) {
            size--;
        }
        return removed;
    }

    public static void main(String[] args) {
        MyHashSet<String> myHashSet = new MyHashSet<>();
        System.out.println(myHashSet.insert("Hello"));
        System.out.println(myHashSet.insert("world"));
        System.out.println(myHashSet.insert("!"));
        System.out.println(myHashSet.insert("Привет"));

        System.out.println(myHashSet.remove("Привет!"));

    }
}
