# Конкуренция в Java: Deadlock, Livelock, Многопоточность, Semaphore, Synchronized, ReentrantLock

## Многопоточность

Многопоточность позволяет выполнять несколько задач одновременно, разделяя ресурсы процессора. Она
используется для повышения производительности и отзывчивости приложений.

### Когда нужна многопоточность

- **Параллельная обработка задач**: Выполнение независимых операций, например, обработка данных,
  загрузка файлов, вычисления.
- **Асинхронные операции**: Обработка запросов в веб-серверах, выполнение фоновых задач.
- **Улучшение отзывчивости UI**: Выполнение длительных операций в фоновом потоке, чтобы интерфейс
  оставался активным.
- **Параллелизм в многоядерных системах**: Распределение задач по ядрам процессора для ускорения
  вычислений.

### Пример многопоточности

```java
class Task implements Runnable {

    public void run() {
        System.out.println("Поток " + Thread.currentThread().getName() + " выполняется");
    }
}

public class main.Main {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Task());
        Thread thread2 = new Thread(new Task());
        thread1.start();
        thread2.start();
    }
}
```

Вывод (порядок не гарантирован):

```
Поток Thread-0 выполняется
Поток Thread-1 выполняется
```

## Deadlock

Deadlock (взаимная блокировка) возникает, когда два или более потока бесконечно ждут друг друга,
чтобы освободить ресурсы. Каждый поток удерживает ресурс, который нужен другому, и никто не может
продолжить выполнение.

### Пример Deadlock

```java
public class DeadlockExample {

    public static void main(String[] args) {
        String resource1 = "Ресурс 1";
        String resource2 = "Ресурс 2";

        Thread thread1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Поток 1: удерживает ресурс 1");
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }
                synchronized (resource2) {
                    System.out.println("Поток 1: удерживает ресурс 2");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println("Поток 2: удерживает ресурс 2");
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }
                synchronized (resource1) {
                    System.out.println("Поток 2: удерживает ресурс 1");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
```

**Проблема**: Поток 1 удерживает `resource1` и ждет `resource2`, а поток 2 удерживает `resource2` и
ждет `resource1`. Программа зависает.

### Как избежать Deadlock

- Устанавливать единый порядок захвата ресурсов.
- Использовать таймауты при блокировке (например, `tryLock` в `ReentrantLock`).
- Минимизировать количество блокируемых ресурсов.

## Livelock

Livelock — ситуация, когда потоки активно выполняются, но не могут продвинуться вперед, так как
каждый реагирует на действия другого, не освобождая ресурсы.

### Пример Livelock

Представим двух людей, пытающихся разойтись в узком коридоре. Каждый делает шаг в сторону, но
одновременно, и они снова сталкиваются. Это повторяется бесконечно.

```java
public class LivelockExample {

    static class Person {

        String name;
        boolean canMove = true;

        Person(String name) {
            this.name = name;
        }

        void giveWay(Person other) {
            while (other.canMove) {
                System.out.println(name + ": пытаюсь уступить");
                canMove = false;
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }
                canMove = true;
            }
        }
    }

    public static void main(String[] args) {
        Person person1 = new Person("Человек 1");
        Person person2 = new Person("Человек 2");

        new Thread(() -> person1.giveWay(person2)).start();
        new Thread(() -> person2.giveWay(person1)).start();
    }
}
```

**Проблема**: Оба потока бесконечно "уступают" друг другу, не достигая цели.

### Как избежать Livelock

- Ввести случайные задержки или приоритеты.
- Использовать единый алгоритм принятия решений.
- Применять механизмы координации, такие как семафоры.

## Механизмы синхронизации

### Synchronized

Ключевое слово `synchronized` используется для блокировки доступа к критической секции или объекту,
чтобы только один поток мог выполнять код в определенный момент.

#### Когда использовать

- Простые сценарии, где требуется синхронизация на уровне метода или блока.
- Нет необходимости в сложной логике блокировки.

#### Пример

```java
class Counter {

    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

public class main.Main {

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Итоговое значение: " + counter.getCount()); // 2000
    }
}
```

#### Плюсы

- Простота использования.
- Встроен в язык, не требует дополнительных библиотек.

#### Минусы

- Нет гибкости (нельзя прервать блокировку или установить таймаут).
- Может привести к взаимным блокировкам.

### ReentrantLock

`ReentrantLock` — более гибкая альтернатива `synchronized`, предоставляющая дополнительные
возможности, такие как таймауты, попытки захвата и возможность прерывания.

#### Когда использовать

- Нужны таймауты или попытки захвата блокировки (`tryLock`).
- Требуется явное управление блокировкой и разблокировкой.
- Необходима поддержка справедливости (fairness) в обработке потоков.

#### Пример

```java
import java.util.concurrent.locks.ReentrantLock;

class Counter {

    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        return count;
    }
}

public class main.Main {

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Итоговое значение: " + counter.getCount()); // 2000
    }
}
```

#### Плюсы

- Гибкость: поддержка `tryLock`, таймаутов, прерываний.
- Возможность настройки справедливости (fairness).
- Явное управление блокировкой.

#### Минусы

- Требует явного вызова `unlock` (желательно в `finally`).
- Более сложный синтаксис, чем `synchronized`.

### Semaphore

`Semaphore` управляет доступом к ограниченному количеству ресурсов, позволяя определенному числу
потоков одновременно работать с ресурсом.

#### Когда использовать

- Ограничение числа потоков, имеющих доступ к ресурсу (например, пул соединений).
- Координация доступа к ресурсам с фиксированным количеством.

#### Пример

```java
import java.util.concurrent.Semaphore;

class ConnectionPool {

    private final Semaphore semaphore = new Semaphore(2); // Максимум 2 соединения

    public void connect() throws InterruptedException {
        semaphore.acquire();
        System.out.println(Thread.currentThread().getName() + ": подключено");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        System.out.println(Thread.currentThread().getName() + ": отключено");
        semaphore.release();
    }
}

public class main.Main {

    public static void main(String[] args) {
        ConnectionPool pool = new ConnectionPool();
        Runnable task = () -> {
            try {
                pool.connect();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 5; i++) {
            new Thread(task).start();
        }
    }
}
```

**Вывод**: Только два потока одновременно подключаются, остальные ждут.

#### Плюсы

- Гибкое управление количеством потоков.
- Подходит для ограничения ресурсов.

#### Минусы

- Требует явного управления (`acquire`/`release`).
- Может быть сложнее в отладке, чем `synchronized`.

## Рекомендации по выбору механизма

- **Synchronized**: Для простых сценариев с минимальной синхронизацией.
- **ReentrantLock**: Для сложных случаев, где нужны таймауты, прерывания или справедливость.
- **Semaphore**: Для ограничения числа потоков, работающих с ресурсом.