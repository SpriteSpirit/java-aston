package ru.aston.hw2.task2;

import java.util.Arrays;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        List<Student> students = getStudentList();
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

        List<Student> students = Arrays.asList(
            new Student("Гоша", Arrays.asList(book1, book2, book3, book4, book5)),
            new Student("Гриша", Arrays.asList(book6, book7, book8, book9, book10)),
            new Student("Даниил", Arrays.asList(book7, book1, book8, book3, book10)),
            new Student("Маша", Arrays.asList(book1, book7, book8, book9, book10)),
            new Student("Даша", Arrays.asList(book6, book1, book7, book9, book3)),
            new Student("Катя", Arrays.asList(book5, book7, book1, book7, book10))
        );

        return students;
    }
}
