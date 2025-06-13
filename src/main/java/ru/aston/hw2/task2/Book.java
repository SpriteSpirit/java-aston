package ru.aston.hw2.task2;


import java.util.Objects;

public record Book(String author, String title, int pages, int publishedYear) {

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
    public String toString() {
        return String.format(
            "Author: %s, Title: %s, Year: %d, Pages: %d",
            author(),
            title(),
            publishedYear(),
            pages()
        );
    }
}
