package ru.aston.hw2.task2;

import java.util.List;
import java.util.stream.Collectors;

public record Student(String name, List<Book> books) {

    @Override
    public String toString() {
        return "Студент: " + name + ".\n"
            + "Количество книг: " + books.size() + "\n"
            + "Книги:\n" + books.stream()
            .distinct()
            .map(Book::toString)
            .collect(Collectors.joining("\n"));
    }
}
