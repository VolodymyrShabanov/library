package service;

import model.Book;
import model.User;

public class LibraryService {
    private BookService bookService;
    private UserService userService;

    public LibraryService() {
        bookService = new BookService();
        userService = new UserService();
    }

    public void addNewBook(Book book) {
        bookService.addNewBook(book);
    }

    public void registerNewUser(User newUser) {
        userService.createNewUser(newUser);
    }

    public void login(String userName, String pass) {
        userService.login(userName, pass);
    }

    public void borrowBook(int bookId) {
        if (userService.getCurrentUser() == null) {
            System.err.println("Error: user is not logged in.");
            return;
        }

        Book book = bookService.borrowBook(bookId, userService.getCurrentUserName());

        if (book == null) return;

        userService.borrowBook(book);
    }

    public void returnBook(int bookId) {
        if (userService.getCurrentUser() == null) {
            System.err.println("Error: user is not logged in.");
            return;
        }

        Book book = bookService.returnBook(bookId, userService.getCurrentUserName());

        if (book == null) return;

        userService.returnBook(book);
    }

    public void displayUserBooks() {
        userService.displayUserBooks();
    }

    public void displayBookList() {
        bookService.displayAllBooks();
    }

    public void displayUnborrowedBooks() {
        bookService.displayUnborrowedBookList();
    }

    public void displayBorrowedBooks() {
        bookService.displayBorrowedBookList();
    }

    public void displayBooksByTitle(String title) {
        bookService.displayBooksByTitle(title);
    }

    public void displayBooksByAuthor(String authorName) {
        bookService.displayBooksByAuthor(authorName);
    }

    public void displayCurrentUserName() {
        if (userService.getCurrentUser() != null) {
            System.out.printf("Current user is: '%s'\n", userService.getCurrentUserName());
        } else {
            System.err.println("Error: user is not logged in.");
        }
    }
}
