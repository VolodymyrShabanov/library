package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Created by Volodymyr Sh on 30.10.2023
 * project name: Library
 */

public class Book {
    private final int id;
    private static int countId;
    private String title;
    private String author;
    private int year;
    private String currentBookHolder = "";
    private LocalDate borrowDate;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.id = countId++;
    }

    public long getRentalPeriod() {
        return borrowDate != null ? ChronoUnit.DAYS.between(borrowDate, LocalDate.now()) : -1;
    }

    public LocalDate getBorrowDate() {
        return this.borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getCurrentBookHolder() {
        return currentBookHolder;
    }

    public void setCurrentBookHolder(String currentBookHolder) {
        this.currentBookHolder = currentBookHolder;
    }

    public String getTitle() {
        return title;
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("\tTitle: %s\n\tAuthor: %s\n\tYear: %d\n\tBookID: %s\n",
                getTitle(),
                getAuthor(),
                getYear(),
                getId()
        ));
        if (getBorrowDate() != null) {
            sb.append(String.format("\tBorrow date: %s\n", getBorrowDate()));
        }

        return sb.toString();
    }
}
