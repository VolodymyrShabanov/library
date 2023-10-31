package interfaces;

import lib.MyArrayList;
import lib.MyList;
import model.Book;

public interface BookRepositoryInterface {
    public void addBook(Book book);
    public MyArrayList<Book> getBookList();
    public void borrowBook(Book book);
    public void returnBook();
    public MyArrayList<Book> getBookListByAuthor();
}