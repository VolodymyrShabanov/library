package repository;

import interfaces.BookRepositoryInterface;
import lib.MyArrayList;
import lib.MyList;
import model.Book;

/**
 * Created by Volodymyr Sh on 30.10.2023
 * project name: Library
 */
public class BookRepository implements BookRepositoryInterface {

    private final MyArrayList<Book> books;

    public BookRepository() {
        this.books = new MyArrayList<>();
    }


    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public MyArrayList<Book> getBookList() {
        return books;
    }

    @Override
    public void borrowBook(Book book) {
        for (int i = 0; i < books.size(); i++) {
            
        }
    }

    @Override
    public void returnBook() {

    }

    @Override
    public MyArrayList<Book> getBookListByAuthor() {
        return null;
    }
}
