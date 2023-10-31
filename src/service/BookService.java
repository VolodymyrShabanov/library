package service;

import interfaces.BookServiceInterface;
import lib.MyList;
import model.Book;
import repository.BookRepository;

import javax.sql.rowset.BaseRowSet;

/**
 * Created by Volodymyr Sh on 30.10.2023
 * project name: Library
 */
public class BookService implements BookServiceInterface {

    private BookRepository bookRepository;

    public BookService() {
        this.bookRepository = new BookRepository();
    }

    @Override
    public void borrowBook(String bookTitle) {

    }

    @Override
    public void returnBook(Book borrowedBook) {

    }

    @Override
    public MyList<Book> getUnborrowedBookList() {
        return null;
    }

    @Override
    public MyList<Book> getBorrowedBookList() {
        return null;
    }
}
