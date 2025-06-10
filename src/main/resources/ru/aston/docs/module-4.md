# Основы Java: Модуль 4

## Многопоточность

Многопоточность позволяет выполнять несколько задач одновременно в рамках одного процесса, используя
потоки. Поток — минимальная единица выполнения в Java.

## Способы создания потоков

- **Наследование от `Thread`**:
    - Создается класс, расширяющий `Thread`, с переопределением метода `run()`.
    - Пример:
      ```java
      class MyThread extends Thread {
          public void run() { System.out.println("Поток работает"); }
      }
      new MyThread().start();
      ```
- **Реализация `Runnable`**:
    - Создается класс, реализующий интерфейс `Runnable`, с методом `run()`.
    - Передается в конструктор `Thread`.
    - Пример:
      ```java
      class MyRunnable implements Runnable {
          public void run() { System.out.println("Поток работает"); }
      }
      new Thread(new MyRunnable()).start();
      ```
- **Лямбда-выражения** (Java 8+):
    - Используется для компактного создания потока.
    - Пример:
      ```java
      new Thread(() -> System.out.println("Поток работает")).start();
      ```

## Виды состояния потоков

Поток проходит следующие состояния:

- **NEW**: Создан, но не запущен.
- **RUNNABLE**: Выполняется или готов к выполнению.
- **BLOCKED**: Ожидает монитор (блокировку).
- **WAITING**: Ожидает другого потока (например, через `wait()` или `await()`).
- **TIMED_WAITING**: Ожидает с таймаутом (например, `Thread.sleep(1000)`).
- **TERMINATED**: Завершил выполнение.

## Ключевое слово volatile

Модификатор `volatile` гарантирует видимость изменений переменной для всех потоков и предотвращает
кэширование в локальной памяти потока.

- Пример:
  ```java
  volatile boolean isRunning = true;
  ```
- Используется для простых флагов, но не решает проблему атомарности операций.

## Проблемы многопоточных приложений

- **Состояние гонки (Race Condition)**:
    - Возникает, когда несколько потоков одновременно изменяют общие данные, приводя к
      непредсказуемым результатам.
    - Решение: Синхронизация (например, `synchronized`, `Lock`).
- **Дедлок (Deadlock)**:
    - Потоки взаимно блокируют друг друга, ожидая ресурсы.
    - Решение: Упорядочить захват ресурсов, использовать таймауты.
- **Лайвлок (Livelock)**:
    - Потоки активно выполняются, но не могут продвинуться из-за взаимных уступок.
    - Решение: Ввести случайные задержки или фиксированный порядок действий.

## Пакет java.util.concurrent

Пакет предоставляет высокоуровневые инструменты для многопоточности, упрощающие синхронизацию и
управление потоками.

### Atomic

Классы (`AtomicInteger`, `AtomicLong`, `AtomicReference`) обеспечивают атомарные операции без
блокировок.

- Пример:
  ```java
  AtomicInteger counter = new AtomicInteger(0);
  counter.incrementAndGet(); // Атомарное увеличение
  ```
- Используются для счетчиков или флагов в многопоточной среде.

### Lock

Интерфейс `Lock` (реализация `ReentrantLock`) предоставляет гибкую альтернативу `synchronized`.

- Преимущества: Таймауты, прерываемость, условия (`Condition`).
- Пример:
  ```java
  Lock lock = new ReentrantLock();
  lock.lock();
  try { /* критическая секция */ } finally { lock.unlock(); }
  ```

### Executors

`Executors` упрощает создание пулов потоков для выполнения задач.

- Основные методы:
    - `newFixedThreadPool(int n)`: Пул с фиксированным числом потоков.
    - `newCachedThreadPool()`: Пул с динамическим числом потоков.
    - `newSingleThreadExecutor()`: Однопоточный исполнитель.
- Пример:
  ```java
  ExecutorService executor = Executors.newFixedThreadPool(2);
  executor.submit(() -> System.out.println("Задача"));
  executor.shutdown();
  ```

### Future, CompletableFuture

- **Future**:
    - Представляет результат асинхронной задачи.
    - Пример:
      ```java
      Future<Integer> future = executor.submit(() -> 42);
      Integer result = future.get(); // Блокирует до завершения
      ```
- **CompletableFuture** (Java 8+):
    - Расширяет `Future`, поддерживает цепочки асинхронных операций.
    - Пример:
      ```java
      CompletableFuture.supplyAsync(() -> 42)
          .thenApply(x -> x * 2)
          .thenAccept(System.out::println); // Выводит 84
      ```

### Синхронизаторы

Инструменты для координации потоков:

- **CountDownLatch**: Ожидает завершения заданного числа операций.
    - Пример:
      ```java
      CountDownLatch latch = new CountDownLatch(3);
      executor.submit(() -> { latch.countDown(); });
      latch.await(); // Ожидает 3 countDown
      ```
- **CyclicBarrier**: Синхронизирует потоки в точке встречи.
    - Пример:
      ```java
      CyclicBarrier barrier = new CyclicBarrier(2);
      executor.submit(() -> { barrier.await(); });
      ```
- **Semaphore**: Ограничивает число одновременно работающих потоков.
    - Пример:
      ```java
      Semaphore semaphore = new Semaphore(2);
      semaphore.acquire(); // Захват разрешения
      semaphore.release();
      ```
- **Exchanger**: Обменивает данные между двумя потоками.
    - Пример:
      ```java
      Exchanger<String> exchanger = new Exchanger<>();
      executor.submit(() -> exchanger.exchange("Data"));
      ```
- **Phaser**: Гибкая синхронизация для многофазных задач.
