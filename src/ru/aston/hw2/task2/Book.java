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
        boolean isEqual = false;

        if (this == object) {
            isEqual = true;
        } else if (object != null && object.getClass() == getClass()) {
            Book book = (Book) object;

            isEqual = publishedYear == book.publishedYear && pages == book.pages &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author);
        }
        return isEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, pages, publishedYear);
    }

    @Override
    public String toString() {
        return String.format(
            "Author: %s, Title: %s, Year: %d, Pages: %d",
            getAuthor(),
            getTitle(),
            getPublishedYear(),
            getPages()
        );
    }
}
