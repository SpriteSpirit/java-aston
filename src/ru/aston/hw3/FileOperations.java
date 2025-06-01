package ru.aston.hw3;


/**
 * Записывает данные в файл и считывает их из файла. Обрабатывает ошибки ввода-вывода (IO).
 * Использует собственные исключения
 */

public class FileOperations {

    private final String filePath;

    /**
     * Конструктор, принимающий путь к файлу.
     *
     * @param filePath - путь к файлу.
     */
    public FileOperations(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "FileOperations{" +
            "filePath='" + filePath + '\'' +
            '}';
    }
}
