package ru.aston.hw2.task2;


import java.util.Objects;

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

    // геттеры
    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getPages() {
        return pages;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || object.getClass() != getClass()) {
            return false;
        }

        Book book = (Book) object;

        return publishedYear == book.publishedYear && pages == book.pages &&
            Objects.equals(title, book.title) &&
            Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, pages, publishedYear);
    }
}
