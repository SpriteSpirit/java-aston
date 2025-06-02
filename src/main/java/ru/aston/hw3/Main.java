package main.java.ru.aston.hw3;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tT] [%4$-7s] %5$s %n");

        String filePath = "src/ru/aston/hw3/example-file.txt";
        FileOperations fileOperations = new FileOperations(filePath);

        List<String> dataList = new ArrayList<>();

        dataList.add("Первая строка");
        dataList.add("Вторая строка");
        dataList.add("Третья строка");

        processWriting(fileOperations, dataList);
        processReading(fileOperations);
    }

    /**
     * Записывает список строк в файл.
     *
     * @param operations Объект для выполнения операций с фалом.
     * @param list       Список строк для записи в файл, не должен быть null.
     */
    public static void processWriting(FileOperations operations, List<String> list) {
        if (list == null) {
            throw new IllegalArgumentException("Список не может быть пуст");
        }

        try {
            operations.writeToFile(list);
        } catch (FileOperationException e) {
            logger.severe("Ошибка при записи: " + e.getMessage());

            if (e.getCause() != null) {
                logger.severe("Причина ошибки: " + e.getCause().getMessage());
            }
        }
    }

    /**
     * Считывает данные из файла. Исключаются пустые строки из вывода.
     *
     * @param operations Объект для выполнения операций с фалом.
     */
    public static void processReading(FileOperations operations) {
        try {
            List<String> lines = operations.readFromFile();

            if (logger.isLoggable(Level.INFO)) {
                logger.info(String.format("Данные файла:%n%s", lines.stream()
                    .filter(line -> !line.isEmpty())
                    .collect(Collectors.joining(System.lineSeparator()))));
            }
        } catch (FileOperationException e) {
            logger.severe("Ошибка при чтении: " + e.getMessage());

            if (e.getCause() != null) {
                logger.severe("Причина ошибки: " + e.getCause().getMessage());
            }
        }
    }
}
