package repository;

import interfaces.BookRepositoryInterface;
import lib.MyArrayList;
import lib.MyList;
import model.Book;

import java.util.function.Predicate;

/**
 * Created by Volodymyr Sh on 30.10.2023
 * project name: Library
 */
public class BookRepository implements BookRepositoryInterface {

    private final MyArrayList<Book> books;

    public BookRepository() {
        this.books = new MyArrayList<>();
    }

    public void addAllBook(Book...book) {
        books.addAll(book);
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
    public MyArrayList<Book> getUnborrowedBookList() {
        return null;
    }

    @Override
    public MyArrayList<Book> getBorrowedBookList() {
        return null;
    }

    @Override
    public MyArrayList<Book> getBookListByAuthor(String authorName) {
        return null;
    }

    @Override
    public MyArrayList<Book> getBookListByTitle(String title) {
        return null;
    }

    public MyList<Book> getBookByPredicate(Predicate<Book> predicate) {
        MyList<Book> result = new MyArrayList<>();
        for (Book book: books) {
            if (predicate.test(book)) {
                result.add(book);
            }
        }
        return result;
    }




    @Override
    public void borrowBook(Book book) {

    }

    @Override
    public void returnBook(Book book) {

    }
}
