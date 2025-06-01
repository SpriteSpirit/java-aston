# StringBuilder и логирование в Java и C#

## `sb.toString()` и метод `toString()`

Метод `toString()` в Java и C# используется для получения строкового представления объекта. В случае
с `StringBuilder` (обозначенного как `sb`) он преобразует последовательность символов в строку (
`String` в Java или `string` в C#).

### Java

- **Класс**: `StringBuilder` (не потокобезопасный) или `StringBuffer` (потокобезопасный).
- **Назначение**: `toString()` вызывается для получения финальной строки после операций, таких как
  `append`, `insert` или `delete`.
- **Когда используется**: Когда нужна строка для вывода, передачи в методы или дальнейшей обработки.
- **Пример**:
  ```java
  StringBuilder sb = new StringBuilder();
  sb.append("Привет, ");
  sb.append("Мир!");
  String result = sb.toString(); // Возвращает "Привет, Мир!"
  System.out.println(result);
  ```
- **Особенности**:
    - Определён в классе `Object`, переопределяется в `StringBuilder`/`StringBuffer`.
    - Вызывается неявно при конкатенации строк (`"текст" + obj`) или в `System.out.println(obj)`.

### C#

- **Класс**: `StringBuilder` (из `System.Text`, не потокобезопасный по умолчанию).
- **Назначение**: `ToString()` преобразует содержимое `StringBuilder` в строку `string`.
- **Когда используется**: Аналогично Java, для получения строки после манипуляций.
- **Пример**:
  ```csharp
  StringBuilder sb = new StringBuilder();
  sb.Append("Привет, ");
  sb.Append("Мир!");
  string result = sb.ToString(); // Возвращает "Привет, Мир!"
  Console.WriteLine(result);
  ```
- **Особенности**:
    - Определён в `System.Object`, переопределяется в `StringBuilder`.
    - Вызывается неявно в `Console.WriteLine(obj)` или при интерполяции строк (
      `$"Значение: {obj}"`).

### Сравнение

- **Сходства**: В обоих языках `toString()` (или `ToString()` в C#) для `StringBuilder` преобразует
  последовательность символов в строку. Поведение почти идентичное.
- **Различия**:
    - В Java есть `StringBuilder` и `StringBuffer`; в C# — только `StringBuilder`.
    - Именование: `toString()` (Java) против `ToString()` (C#).
    - В Java `toString()` чаще используется для отладки, в C# — для форматирования (например, через
      `IFormattable`).

## Итог

- **`sb.toString()`**: Преобразует содержимое `StringBuilder` в строку в Java и C# с практически
  идентичным поведением.
