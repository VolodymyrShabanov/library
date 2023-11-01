package model;

import interfaces.BookInterface;

import java.time.LocalDate;

/**
 * Created by Volodymyr Sh on 30.10.2023
 * project name: Library
 */
public class Book implements BookInterface {

    private final int id;
    private static int countId;
    private String title;
    private String author;
    private int year;
    private String currentBookHolder = "";

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.id = countId++;
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
    public String getCurrentBookHolder() {
        return currentBookHolder;
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

    public int getId() {
        return id;
    }

    public void setCurrentBookHolder(String currentBookHolder) {
        this.currentBookHolder = currentBookHolder;
    }
}
