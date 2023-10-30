package interfaces;

import lib.MyList;
import model.Book;

public interface BookRepositoryInterface {
    public void addBook();
    public MyList<Book> getBookList();
    public void borrowBook();
    public void returnBook();
    public MyList<Book> getUnborrowedBookList();
    public MyList<Book> getBorrowedBookList();
    public MyList<Book> getBookListByAuthor();
}