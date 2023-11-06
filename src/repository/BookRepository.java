
package repository;

import lib.MyArrayList;
import model.Book;

import java.util.function.Predicate;

/**
 * Created by Volodymyr Sh on 30.10.2023
 * project name: Library
 */
public class BookRepository {

    private final MyArrayList<Book> books;

    public BookRepository() {
        this.books = new MyArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public MyArrayList<Book> getBookList() {
        return books;
    }

    public MyArrayList<Book> getBookByPredicate(Predicate<Book> predicate) {
        MyArrayList<Book> result = new MyArrayList<>();
        for (Book book : books) {
            if (predicate.test(book)) {
                result.add(book);
            }
        }
        return result;
    }

    public MyArrayList<Book> getUnborrowedBookList() {
        return getBookByPredicate(book -> book.getCurrentBookHolder().isEmpty());
    }

    public MyArrayList<Book> getBorrowedBookList() {
        return getBookByPredicate(book -> !book.getCurrentBookHolder().isEmpty());
    }

    public MyArrayList<Book> getBookListByAuthor(String authorName) {
        return getBookByPredicate(book -> book.getAuthor().equals(authorName));
    }

    public MyArrayList<Book> getBookListByTitle(String title) {
        return getBookByPredicate(book -> book.getTitle().equals(title));
    }

    public Book getBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }
}
