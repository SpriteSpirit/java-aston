package ru.aston.hw3;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Записывает данные в файл и считывает их из файла. Обрабатывает ошибки ввода-вывода (IO).
 * Использует собственные исключения
 */

public class FileOperations {

    private static final Logger logger = Logger.getLogger(FileOperations.class.getName());
    private final String filePath;

    /**
     * Конструктор, принимающий путь к файлу.
     *
     * @param filePath Путь к файлу.
     */
    public FileOperations(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Записывает список строк в файл.
     *
     * @param dataList Список строк.
     * @throws FileOperationException Если произошла ошибка при записи.
     */
    public void writeToFile(List<String> dataList) throws FileOperationException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (String line : dataList) {
                writer.write(line + System.lineSeparator());
            }

            if (logger.isLoggable(Level.INFO)) {
                logger.info(String.format("Данные успешно записаны в файл: %s", filePath));
            }

        } catch (IOException e) {
            throw new FileOperationException("Ошибка записи в файл: " + filePath, e);
        }
    }


    /**
     * Считывает данные из файла и возвращает список строк.
     *
     * @return Список строк.
     * @throws FileOperationException Если произошла ошибка считывания.
     */
    public List<String> readFromFile() throws FileOperationException {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            if (logger.isLoggable(Level.INFO)) {
                logger.info(String.format("Данные успешно считаны из файла: %s", filePath));
            }
            return lines;

        } catch (IOException e) {
            throw new FileOperationException("Ошибка чтения файла: " + filePath, e);
        }
    }


    @Override
    public String toString() {
        return "FileOperations{" +
            "filePath='" + filePath + '\'' +
            '}';
    }
}
