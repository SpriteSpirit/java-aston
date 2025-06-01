package ru.aston.hw3;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tT] [%4$-7s] %5$s %n");

        String filePath = "src/ru/aston/hw3/exampleFile.txt";
        FileOperations fileOperations = new FileOperations(filePath);

        List<String> dataList = new ArrayList<>();

        dataList.add("Первая строка");
        dataList.add("Вторая строка");
        dataList.add("Третья строка");

        processWriting(fileOperations, dataList);
        processReading(fileOperations);
    }

    public static void processWriting(FileOperations operations, List<String> list) {
        try {
            operations.writeToFile(list);
        } catch (FileOperationException e) {
            logger.severe("Ошибка при записи: " + e.getMessage());

            if (e.getCause() != null) {
                logger.severe("Причина ошибки: " + e.getCause().getMessage());
            }
        }
    }

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
