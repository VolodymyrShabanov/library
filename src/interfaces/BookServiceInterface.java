package interfaces;

import lib.MyList;
import model.Book;
import model.User;

import java.time.LocalDate;

/**
 * Created by Volodymyr Sh on 30.10.2023
 * project name: Library
 */
public interface BookServiceInterface {

    public void addNewBook(String title, String author, int year);
    public void borrowBook(String bookTitle);
    public void returnBook(Book borrowedBook);
    public MyList<Book> getUnborrowedBookList();
    public MyList<Book> getBorrowedBookList();

}
