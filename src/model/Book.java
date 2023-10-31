package model;

import interfaces.BookInterface;

import java.time.LocalDate;

/**
 * Created by Volodymyr Sh on 30.10.2023
 * project name: Library
 */
public class Book implements BookInterface {
    private String title;
    private String author;
    private int year;
    private boolean isAvailable;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public LocalDate getPublishingDate() {
        return null;
    }

    @Override
    public LocalDate getBorrowDate() {
        return null;
    }

    @Override
    public User getCurrentBookHolder() {
        return null;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
