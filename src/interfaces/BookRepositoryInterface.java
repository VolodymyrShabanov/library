package interfaces;

import lib.MyArrayList;
import lib.MyList;
import model.Book;

public interface BookRepositoryInterface {
    public void addBook(Book book);
    public void borrowBook(Book book);
    public void returnBook(Book book);
    public MyArrayList<Book> getBookList();
    public MyArrayList<Book> getUnborrowedBookList();
    public MyArrayList<Book> getBorrowedBookList();
    public MyArrayList<Book> getBookListByAuthor(String authorName);
    public MyArrayList<Book> getBookListByTitle(String title);
}