package ru.aston.hw2.task2;


public class Book {

    private final String author;
    private final String title;
    private final int pages;
    private final int publishedYear;

    public Book(String author, String title, int pages, int publishedYear) {
        this.author = author;
        this.title = title;
        this.pages = pages;
        this.publishedYear = publishedYear;
    }
}
