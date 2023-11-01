package service;

import interfaces.BookServiceInterface;
import lib.MyArrayList;
import lib.MyList;
import model.Book;
import repository.BookRepository;

import javax.sql.rowset.BaseRowSet;

/**
 * Created by Volodymyr Sh on 30.10.2023
 * project name: Library
 */
public class BookService {

    private BookRepository bookRepository;

    public BookService() {
        this.bookRepository = new BookRepository();
    }

    public void addNewBook(Book book) {
        System.out.printf("Added: %s\n", book.getTitle());
        bookRepository.addBook(book);
    }

    public void displayAllBooks() {
        printBookList(bookRepository.getBookList());
    }

    public Book borrowBook(int bookId, String userName) {
        Book book = bookRepository.getBookById(bookId);
        if (book == null || !book.getCurrentBookHolder().isEmpty()) {
            return null;
        }

        book.setCurrentBookHolder(userName);

        return book;
    }

    public void returnBook(Book borrowedBook) {

    }

    public void displayUnborrowedBookList() {
        printBookList(bookRepository.getUnborrowedBookList());
    }

    public void displayBorrowedBookList() {
        printBookList(bookRepository.getBorrowedBookList());
    }

    public void displayBooksByAuthor(String authorName) {
        printBookList(bookRepository.getBookListByAuthor(authorName));
    }

    public void displayBooksByTitle(String title) {
        printBookList(bookRepository.getBookListByTitle(title));
    }

    public void printBookList(MyArrayList<Book> bookList) {
        if (bookList.isEmpty()) {
            System.out.println("This list is empty.");
        } else {
            System.out.println("Book list:");

            for (int i = 0; i < bookList.size(); i++) {
                if (bookList.get(i) != null) {
                    System.out.println(bookList.get(i).toString());
                }
            }
        }
    }
}
