package repository;

import interfaces.BookRepositoryInterface;
import lib.MyList;
import model.Book;

/**
 * Created by Volodymyr Sh on 30.10.2023
 * project name: Library
 */
public class BookRepository implements BookRepositoryInterface {



    @Override
    public void addBook() {

    }

    @Override
    public MyList<Book> getBookList() {
        return null;
    }

    @Override
    public void borrowBook() {

    }

    @Override
    public void returnBook() {

    }

    @Override
    public MyList<Book> getBookListByAuthor() {
        return null;
    }
}
