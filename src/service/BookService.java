package service;

import lib.MyArrayList;
import model.Book;
import repository.BookRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

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

    public void displayBookRentalPeriod(int bookId) {
        Optional<Book> book = bookRepository.getBookById(bookId);

        if(book.isEmpty()) {
            System.err.println("Error: this book doesn't exist.");
            return;
        }

        long borrowPeriod = book.get().getRentalPeriod();

        if (borrowPeriod < 0)
            System.err.println("Error: this book is not borrowed.\n");
        else
            System.out.printf("This book was borrowed %s day(s) ago\n", borrowPeriod);
    }

    public void displayAllBooks() {
        printBookList(bookRepository.getBookList());
    }

    public Book borrowBook(int bookId, String userName) {
        Optional<Book> book = bookRepository.getBookById(bookId);

        if (book.isEmpty()) {
            System.err.println("Error: no books with this identifier are registered.");
            return null;
        } else if (!book.get().getCurrentBookHolder().isEmpty()) {
            System.err.println("Error: this book is already borrowed by another user.");
            return null;
        }

        book.get().setCurrentBookHolder(userName);
        book.get().setBorrowDate(LocalDate.now());

        return book.get();
    }

    public Book returnBook(int bookId, String userName) {
        Optional<Book> book = bookRepository.getBookById(bookId);

        if (book.isEmpty()) {
            System.err.println("Error: no books with this identifier are registered.");
            return null;
        } else if (book.get().getCurrentBookHolder().isEmpty()) {
            System.err.println("Error: this book wasn't borrowed.");
            return null;
        } else if (!userName.equals(book.get().getCurrentBookHolder())) {
            System.err.println("Error: this book is borrowed by another user.");
            return null;
        }

        book.get().setCurrentBookHolder("");
        book.get().setBorrowDate(null);

        return book.get();
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

            for (Book book : bookList)
                if (book != null) System.out.println(book);
        }
    }

    public long getBookRentalPeriod(int bookId) {
        Optional<Book> book = bookRepository.getBookById(bookId);

        if (book.isPresent() && book.get().getBorrowDate() == null) {
            return -1;
        }

        return ChronoUnit.DAYS.between(book.get().getBorrowDate(), LocalDate.now());
    }

    public void displayBookHolder(int bookId) {
        Optional<Book> book = bookRepository.getBookById(bookId);

       if (book.isEmpty()) {
           System.err.println("Error: no books with this identifier are registered.");
           return;
       }

       String holder = book.get().getCurrentBookHolder();

       if (holder.isEmpty()) {
           System.out.println("This book is available.");
           return;
       }

       System.out.printf("This book is borrowed by %s\n", holder);
    }
}
