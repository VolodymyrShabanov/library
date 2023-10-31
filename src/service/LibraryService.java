package service;

import interfaces.LibraryServiceInterface;
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

    }

    public void registerNewUser(User newUser) {
        userService.createNewUser(newUser);
    }

    public void login(String userName, String pass) {
        userService.login(userName, pass);
    }

    public void borrowBook() {

    }

    public void returnBook() {

    }

    public void displayBookList() {

    }

    public void displayAvailableBooks() {

    }

    public void displayBorrowedBooks() {

    }

    public void displayBooksByTitle(String title) {

    }

    public void displayBooksByAuthor() {

    }

    public void displayCurrentUserName() {
        if(userService.getCurrentUser() != null) {
            System.out.printf("Current user is: '%s'\n", userService.getCurrentUserName());
        } else {
            System.err.println("Error: you are not logged in.");
        }
    }
}
