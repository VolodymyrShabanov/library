package interfaces;

import lib.MyList;
import model.Book;

public interface BookRepositoryInterface {
    public void addBook(Book book);
    public MyList<Book> getBookList();
    public void borrowBook(Book book);
    public void returnBook();
    public MyList<Book> getBookListByAuthor();
}