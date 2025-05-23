package ru.aston.hw2.task2;

import java.util.List;

public class Student {

    private final String name;
    private final List<Book> books;

    public Student(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }
}
