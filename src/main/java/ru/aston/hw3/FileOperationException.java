package main.java.ru.aston.hw3;


/**
 * Настраиваемое исключения при работе с файлами.
 */
public class FileOperationException extends Exception {

    public FileOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
