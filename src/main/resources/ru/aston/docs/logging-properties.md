# **Документация для `logging.properties`**

*Настройка логирования в Java (java.util.logging)*

---

## **1. Назначение файла**

Файл `logging.properties` используется для настройки:

- **Уровней логирования** (INFO, WARNING, SEVERE и др.)
- **Формата вывода** сообщений
- **Обработчиков (handlers)** – куда выводятся логи (консоль, файл и т.д.)

---

## **2. Расположение файла**

### **Стандартные пути**

- Для автоматической загрузки:
  ```
  src/main/resources/logging.properties  # Maven/Gradle
  ```
- Для ручной загрузки:
  ```java
  System.setProperty("java.util.logging.config.file", "путь/к/logging.properties");
  ```

---

## **3. Основные параметры**

### **3.1. Уровни логирования**

```properties
# Глобальный уровень (для всех логгеров)
.level = INFO

# Для конкретного пакета/класса
ru.myapp.package.level = FINE
```

**Доступные уровни**:
`SEVERE`, `WARNING`, `INFO`, `CONFIG`, `FINE`, `FINER`, `FINEST`, `OFF`

---

### **3.2. Обработчики (Handlers)**

```properties
# Вывод в консоль
handlers = java.util.logging.ConsoleHandler

# Вывод в файл (дополнительно)
handlers = java.util.logging.ConsoleHandler, java.util.logging.FileHandler
```

---

### **3.3. Настройка ConsoleHandler**

```properties
java.util.logging.ConsoleHandler.level = INFO
java.util.logging.ConsoleHandler.formatter = java.util.logging.SimpleFormatter
```

---

### **3.4. Настройка FileHandler**

```properties
java.util.logging.FileHandler.pattern = logs/app_%u.log  # Шаблон имени файла
java.util.logging.FileHandler.limit = 50000             # Макс. размер (байт)
java.util.logging.FileHandler.count = 1                 # Кол-во файлов
java.util.logging.FileHandler.formatter = java.util.logging.SimpleFormatter
java.util.logging.FileHandler.append = true             # Дозапись в файл
```

---

### **3.5. Формат сообщений (SimpleFormatter)**

```properties
java.util.logging.SimpleFormatter.format = 
    [%1$tF %1$tT] [%4$-7s] %5$s %n
```

**Доступные параметры**:

- `%1$tF` – дата (год-месяц-день)
- `%1$tT` – время (часы:минуты:секунды)
- `%4$s` – уровень логирования
- `%5$s` – текст сообщения
- `%n` – перенос строки

**Пример вывода**:

```
[2023-10-25 14:30:45] [INFO   ] Запуск приложения
```

---

## **4. Полный пример конфигурации**

```properties
# Глобальный уровень
.level = INFO

# Обработчики
handlers = java.util.logging.ConsoleHandler, java.util.logging.FileHandler

# ConsoleHandler
java.util.logging.ConsoleHandler.level = INFO
java.util.logging.ConsoleHandler.formatter = java.util.logging.SimpleFormatter

# FileHandler
java.util.logging.FileHandler.pattern = logs/app_%u.log
java.util.logging.FileHandler.limit = 50000
java.util.logging.FileHandler.count = 3
java.util.logging.FileHandler.formatter = java.util.logging.SimpleFormatter

# Формат сообщений
java.util.logging.SimpleFormatter.format = [%1$tF %1$tT] [%4$-7s] %5$s %n

# Уровень для конкретных пакетов
com.example.service.level = FINE
```

---

## **5. Подключение в проекте**

### **5.1. Автоматическая загрузка**

Положите файл в `src/main/resources` – JUL подхватит его автоматически.

### **5.2. Ручная загрузка**

```java
try (InputStream is = getClass().getClassLoader()
        .getResourceAsStream("logging.properties")) {
    LogManager.getLogManager().readConfiguration(is);
}
```

---

## **6. Проверка конфигурации**

Добавьте в код:

```java
Logger logger = Logger.getLogger("test");
logger.info("Тест логирования");  // Должно появиться в консоли/файле
```

---

## **7. Решение проблем**

### **Файл не загружается**

1. Убедитесь, что он в `target/classes` после сборки.
2. Проверьте имя файла – должно быть **logging.properties**.

### **Логи не выводятся**

1. Проверьте уровень `.level` в конфиге.
2. Убедитесь, что обработчики указаны верно (`handlers = ...`).

---

## **8. Ссылки**

- [Официальная документация](https://docs.oracle.com/javase/8/docs/api/java/util/logging/package-summary.html)
- [Примеры форматирования](https://www.baeldung.com/java-logging-formatters)

---
