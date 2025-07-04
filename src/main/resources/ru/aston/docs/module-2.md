# Основы Java: Модуль 2

## Алгоритмы

Алгоритмы — последовательности шагов для решения задач. Классифицируются по:

- **Типу задачи**: Сортировка, поиск, графовые алгоритмы.
- **Сложности**: Оценивается через Big O notation.
- **Подходу**: Рекурсивные, итеративные, жадные, динамическое программирование.

## Big O Notation

Big O описывает временную сложность алгоритма, показывая, как время выполнения зависит от размера
входных данных.

- **Виды временной сложности**:
    - **O(1)**: Константное время (доступ к элементу массива).
    - **O(log n)**: Логарифмическое (бинарный поиск).
    - **O(n)**: Линейное (перебор массива).
    - **O(n log n)**: Линейно-логарифмическое (эффективные сортировки, например, QuickSort).
    - **O(n²)**: Квадратичное (пузырьковая сортировка).
    - **O(2ⁿ)**: Экспоненциальное (решение задачи коммивояжера перебором).
    - **O(n!)**: Факториальное (перестановки).

## Виды сортировок

Сортировки упорядочивают элементы по ключу.

- **Пузырьковая (Bubble Sort)**: Сравнивает и меняет соседние элементы. Сложность: O(n²).
- **Выбором (Selection Sort)**: Выбирает минимальный элемент и ставит в начало. Сложность: O(n²).
- **Вставками (Insertion Sort)**: Вставляет элементы в отсортированную часть. Сложность: O(n²).
- **Быстрая (QuickSort)**: Делит массив на части относительно опорного элемента. Средняя сложность:
  O(n log n).
- **Слиянием (MergeSort)**: Делит массив пополам, сортирует и объединяет. Сложность: O(n log n).

### Отличие QuickSort от MergeSort

- **QuickSort**:
    - Работает на месте (меньше памяти).
    - Средняя сложность: O(n log n), худшая: O(n²) (при неудачном опорном элементе).
    - Быстрее на практике за счет меньшего числа операций.
- **MergeSort**:
    - Требует дополнительной памяти O(n) для слияния.
    - Стабильная сложность: O(n log n) всегда.
    - Подходит для больших данных и связанных списков.

## Generics

Generics позволяют создавать классы, методы и интерфейсы с параметризованными типами, обеспечивая
типобезопасность.

- Пример:
  ```java
  class Box<T> {
      T value;
      Box(T value) { this.value = value; }
  }
  Box<Integer> intBox = new Box<>(5);
  ```
- Преимущества: Типобезопасность, устранение приведения типов.
- Ограничения: Нельзя использовать примитивы (например, `Box<int>`).

## Коллекции

Коллекции — структуры данных для хранения и обработки наборов элементов.

### Иерархия коллекций

- **Collection** (интерфейс):
    - `List`: Упорядоченные, допускают дубликаты.
    - `Set`: Не допускают дубликаты.
    - `Queue`: Для очередей (FIFO, LIFO).
- **Map** (интерфейс): Хранит пары ключ-значение, ключи уникальны.

### List

- Хранит элементы в порядке добавления, допускает дубликаты.
- Реализации:
    - `ArrayList`: Динамический массив. Быстрый доступ по индексу (O(1)), медленная
      вставка/удаление (O(n)).
    - `LinkedList`: Двусвязный список. Быстрая вставка/удаление (O(1)), медленный доступ (O(n)).
    - `Vector`: Потокобезопасный аналог `ArrayList`, редко используется.

### Set

- Хранит уникальные элементы, порядок не гарантирован.
- Реализации:
    - `HashSet`: Хранит элементы в хэш-таблице. Быстрые операции (O(1)), неупорядочен.
    - `LinkedHashSet`: Сохраняет порядок добавления. Операции: O(1).
    - `TreeSet`: Хранит элементы в отсортированном виде (естественный порядок или `Comparator`).
      Операции: O(log n).

### Map

- Хранит пары ключ-значение, ключи уникальны.
- Реализации:
    - `HashMap`: Хэш-таблица. Быстрые операции (O(1)), неупорядочен.
    - `LinkedHashMap`: Сохраняет порядок добавления. Операции: O(1).
    - `TreeMap`: Отсортирован по ключам (естественный порядок или `Comparator`). Операции: O(log n).

## Временная сложность операций коллекций

| Коллекция     | Доступ   | Вставка  | Удаление | Поиск    |
|---------------|----------|----------|----------|----------|
| ArrayList     | O(1)     | O(n)     | O(n)     | O(n)     |
| LinkedList    | O(n)     | O(1)     | O(1)     | O(n)     |
| HashSet       | -        | O(1)     | O(1)     | O(1)     |
| LinkedHashSet | -        | O(1)     | O(1)     | O(1)     |
| TreeSet       | -        | O(log n) | O(log n) | O(log n) |
| HashMap       | O(1)     | O(1)     | O(1)     | O(1)     |
| LinkedHashMap | O(1)     | O(1)     | O(1)     | O(1)     |
| TreeMap       | O(log n) | O(log n) | O(log n) | O(log n) |

## Неизменяемые коллекции

- Создаются для защиты данных от изменений.
- **Создание**:
    - `List.of()`, `Set.of()`, `Map.of()` (Java 9+): Компактное создание неизменяемых коллекций.
      ```java
      List<String> list = List.of("a", "b"); // неизменяемый список
      ```
    - Методы `Collections`:
        - `Collections.unmodifiableList(List)`: Оборачивает коллекцию в неизменяемую.
      ```java
      List<String> mutable = new ArrayList<>();
      List<String> immutable = Collections.unmodifiableList(mutable);
      ```
- Особенности: Попытка изменения вызывает `UnsupportedOperationException`.

## LinkedHashMap, TreeMap

- **LinkedHashMap**:
    - Расширяет `HashMap`, сохраняет порядок добавления элементов.
    - Используется для кэшей с порядком вставки.
    - Сложность операций: O(1).
- **TreeMap**:
    - Отсортирован по ключам (естественный порядок или `Comparator`).
    - Используется для словарей с сортировкой.
    - Сложность операций: O(log n).

## Интерфейсы Comparable и Comparator

- **Comparable**:
    - Определяет естественный порядок сравнения объектов.
    - Реализуется классом через метод `compareTo(T o)`.
    - Пример:
      ```java
      class Person implements Comparable<Person> {
          String name;
          @Override
          public int compareTo(Person o) { return name.compareTo(o.name); }
      }
      ```
- **Comparator**:
    - Внешний объект для кастомного сравнения.
    - Реализуется через метод `compare(T o1, T o2)`.
    - Пример:
      ```java
      Comparator<Person> byName = (p1, p2) -> p1.name.compareTo(p2.name);
      ```
- **Где использовать**:
    - `Comparable`: Для стандартного порядка (например, в `TreeSet`).
    - `Comparator`: Для гибкой сортировки (например, в `Collections.sort`).

## Лямбда-выражения, функциональные интерфейсы, Stream API

- **Лямбда-выражения**:
    - Краткий синтаксис для реализации функциональных интерфейсов.
    - Пример: `(x, y) -> x + y`.
- **Функциональные интерфейсы**:
    - Интерфейсы с одним абстрактным методом (например, `Predicate`, `Function`, `Consumer`).
    - Пример:
      ```java
      Predicate<Integer> isPositive = x -> x > 0;
      ```
- **Stream API**:
    - Инструмент для обработки коллекций в функциональном стиле.
    - Основные операции:
        - Промежуточные: `filter`, `map`, `sorted` (возвращают новый поток).
        - Терминальные: `collect`, `forEach`, `reduce` (возвращают результат).
    - Пример:
      ```java
      List<Integer> numbers = List.of(1, 2, 3, 4);
      List<Integer> squares = numbers.stream()
                                     .map(x -> x * x)
                                     .filter(x -> x > 5)
                                     .collect(Collectors.toList()); // [9, 16]
      ```
- Преимущества: Читаемость, декларативный стиль, параллельная обработка (`parallelStream`).