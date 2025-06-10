Конечно! Вот краткая, но информативная документация по логированию в Java в формате `Markdown`.

---

# 📝 **Документация: Логирование в Java**

## **1. Основные концепции**

**Логирование** – запись событий в программе для отладки, мониторинга и анализа работы приложения.

### **Уровни логирования (java.util.logging.Level)**

| Уровень   | Описание                                     |
|-----------|----------------------------------------------|
| `SEVERE`  | Критические ошибки (остановка работы)        |
| `WARNING` | Предупреждения (потенциальные проблемы)      |
| `INFO`    | Информационные сообщения (нормальная работа) |
| `CONFIG`  | Конфигурационные параметры                   |
| `FINE`    | Детальная отладочная информация              |
| `FINER`   | Более детальная отладка                      |
| `FINEST`  | Максимальная детализация                     |

---

## **2. Примеры использования**

### **2.1. Простое логирование**

```java
import java.util.logging.Logger;

public class MyClass {
    private static final Logger logger = Logger.getLogger(MyClass.class.getName());

    public void doSomething() {
        logger.info("Метод doSomething() вызван");
      
        try {
            // Код, который может вызвать ошибку
        } catch (Exception e) {
            logger.severe("Ошибка в doSomething(): " + e.getMessage());
        }
    }
}
```

### **2.2. Логирование с параметрами**

```java
String user = "Alice";
int value = 42;

// Вариант 1: String.format()
logger.log(Level.INFO, String.format("Пользователь: %s, Значение: %d", user, value));

// Вариант 2: logger.log() с подстановкой {0}, {1}
logger.log(Level.INFO, "Пользователь: {0}, Значение: {1}", new Object[]{user, value});

// Вариант 3 (Java 8+): Лямбда для отложенного форматирования
logger.info(() -> String.format("Пользователь: %s, Значение: %d", user, value));
```

### **2.3. Проверка уровня логирования**

```java
if (logger.isLoggable(Level.FINE)) {
    logger.fine("Подробная отладочная информация: " + computeExpensiveData());
}
```

---

## **3. Настройка логирования**

### **3.1. Конфигурация через `logging.properties`**

```properties
# Уровень логирования для всех логгеров
.level = INFO

# Уровень для конкретного пакета
ru.mycompany.mypackage.level = FINE

# Формат вывода (ConsoleHandler)
java.util.logging.ConsoleHandler.level = INFO
java.util.logging.ConsoleHandler.formatter = java.util.logging.SimpleFormatter
java.util.logging.SimpleFormatter.format = [%1$tF %1$tT] [%4$s] %5$s %n
```

### **3.2. Программная настройка**

```java
Logger logger = Logger.getLogger("my.logger");
logger.setLevel(Level.FINE);

ConsoleHandler handler = new ConsoleHandler();
handler.setLevel(Level.FINE);
logger.addHandler(handler);
```

---

## **4. Лучшие практики**

✅ **Использование правильных уровней** (`SEVERE` для ошибок, `INFO` для важных событий).
✅ **Отсутствие конкатенации строк в `log()`** – лучше `String.format()` или лямбда.
✅ **Проверка`isLoggable()` для дорогих операций**.
✅ **Не логировать конфиденциальные данные** (пароли, токены).
✅ **Использование `try-with-resources` для `FileHandler`**.

---

## **5. Популярные библиотеки логирования**

| Библиотека  | Особенности                               |
|-------------|-------------------------------------------|
| **SLF4J**   | Фасад (интерфейс) для разных реализаций   |
| **Log4j2**  | Высокая производительность, гибкость      |
| **Logback** | Улучшенная версия Log4j, работает с SLF4J |
| **JUL**     | Встроенный логгер (java.util.logging)     |

---

## **6. Пример SLF4J + Logback**

```xml
<!-- Зависимость в Maven -->
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.4.7</version>
</dependency>
```

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyApp {
    private static final Logger logger = LoggerFactory.getLogger(MyApp.class);

    public static void main(String[] args) {
        logger.info("Приложение запущено");
        logger.error("Ошибка!", new RuntimeException("Тестовая ошибка"));
    }
}
```

---

## **Заключение**

Логирование – важный инструмент для разработки и поддержки приложений. Выбирайте подход в
зависимости от требований к производительности и удобству.

**Рекомендация:**

- Для простых проектов → **JUL** (встроенный).
- Для сложных систем → **SLF4J + Logback/Log4j2**.

[Официальная документация JUL](https://docs.oracle.com/javase/8/docs/api/java/util/logging/package-summary.html) | [SLF4J](http://www.slf4j.org/)
