package model;

import interfaces.UserInterface;
import lib.MyArrayList;

/**
 * Created by Volodymyr Sh on 30.10.2023
 * project name: Library
 */
public class User implements UserInterface {

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
    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public boolean checkPassword(String password) {
        return false;
    }

    @Override
    public MyArrayList<Book> getUserBooks() {
        return borrowedBooks;
    }

    public int getId() {
        return id;
    }
}
