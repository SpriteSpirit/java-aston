package main.java.ru.aston.hw4;


import main.java.ru.aston.hw4.DeadLock.Friend;

public class Main {

    public static void main(String[] args) {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tT] [%4$-7s] %5$s %n");

        final Friend vasya = new Friend("Вася");
        final Friend petya = new Friend("Петя");

        new Thread(() -> vasya.handshake(petya)).start();
        new Thread(() -> petya.handshake(vasya)).start();
    }
}
