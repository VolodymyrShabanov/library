package service;

import lib.MyArrayList;
import model.Book;
import repository.BookRepository;

import java.sql.Array;
import java.sql.SQLOutput;
import java.time.LocalDate;

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
        if (book == null) {
            System.err.println("Error: book can't be null.");
            return;
        }

        if (book.getTitle().isBlank() || book.getAuthor().isBlank() || Integer.toString(book.getYear()).length() != 4) {
            System.err.println("Error: book data doesn't match the format.");
            return;
        }

        System.out.printf("Added: %s\n", book.getTitle());
        bookRepository.addBook(book);
    }

    public void displayAllBooks() {
        printBookList(bookRepository.getBookList());
    }

    public Book borrowBook(int bookId, String userName) {
        Book book = bookRepository.getBookById(bookId);

        if (book == null) {
            System.err.println("Error: this book doesn't exist.");
            return null;
        } else if (!book.getCurrentBookHolder().isEmpty()) {
            System.err.println("Error: this book is already borrowed by another user.");
            return null;
        }

        book.setCurrentBookHolder(userName);
        book.setBorrowDate(LocalDate.now());

        return book;
    }

    public Book returnBook(int bookId, String userName) {
        Book book = bookRepository.getBookById(bookId);

        if (book == null) {
            System.err.println("Error: this book doesn't exist.");
            return null;
        } else if (book.getCurrentBookHolder().isEmpty()) {
            System.err.println("Error: this book wasn't borrowed.");
            return null;
        } else if (!userName.equals(book.getCurrentBookHolder())) {
            System.err.println("Error: this book is borrowed by another user.");
            return null;
        }

        book.setCurrentBookHolder("");
        book.setBorrowDate(null);

        return book;
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
