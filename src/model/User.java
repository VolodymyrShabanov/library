package model;

import lib.MyArrayList;

/**
 * Created by Volodymyr Sh on 30.10.2023
 * project name: Library
 */

public class User {
    private final int id;
    private static int countId;
    private String userName;
    private String password;
    private MyArrayList<Book> borrowedBooks;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.borrowedBooks = new MyArrayList<>();
        this.id = countId++;
    }

    public int getId() {
        return id;
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public String getUserName() {
        return userName;
    }

    public MyArrayList<Book> getUserBooks() {
        return borrowedBooks;
    }

    public boolean checkPassword(String password) {
        return password.equals(this.password);
    }
}
