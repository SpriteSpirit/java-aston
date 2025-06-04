package ru.aston.hw2.task2;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public class Main {

    private static final int YEAR_FILTER = 2_000;
    private static final int BOOK_LIMIT = 3;
    private static final Comparator<Book> PAGE_COMPARATOR = Comparator.comparingInt(Book::getPages);
    static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        List<Student> students = getStudentList();

        students.stream()
            .flatMap(student -> student.getBooks().stream())
            .sorted(PAGE_COMPARATOR)
            .distinct()
            .filter(book -> book.getPublishedYear() > YEAR_FILTER)
            .limit(BOOK_LIMIT)
            .map(Book::getPublishedYear)
            .findFirst()
            .ifPresentOrElse(
                publishedYear -> logger.info("Год выпуска найденной книги: " + publishedYear),
                () -> logger.info("Книга не найдена."));
    }

    private static List<Student> getStudentList() {
        Book book1 = new Book("John Doe", "Java Basics", 300, 1999);
        Book book2 = new Book("Jane Smith", "Advanced Java", 450, 2005);
        Book book3 = new Book("Robert Martin", "Design Patterns", 500, 2008);
        Book book4 = new Book("Robert Martin", "Clean Code", 350, 2010);
        Book book5 = new Book("Thomas Cormen", "Algorithms", 1200, 2001);
        Book book6 = new Book("Mark Allen", "Data Structures", 600, 2003);
        Book book7 = new Book("Guido Rossum", "Python for Beginners", 250, 2015);
        Book book8 = new Book("Andrew Ng", "Machine Learning", 700, 2018);
        Book book9 = new Book("James Gray", "Database Systems", 800, 2007);
        Book book10 = new Book("Linus Torvalds", "Operating Systems", 900, 2004);

        return List.of(
            new Student("Гоша", List.of(book1, book2, book3, book4, book5)),
            new Student("Гриша", List.of(book6, book7, book8, book9, book10)),
            new Student("Даниил", List.of(book7, book1, book8, book3, book10)),
            new Student("Маша", List.of(book1, book7, book8, book9, book10)),
            new Student("Даша", List.of(book6, book1, book7, book9, book3)),
            new Student("Катя", List.of(book5, book7, book1, book7, book10)));
    }
}
