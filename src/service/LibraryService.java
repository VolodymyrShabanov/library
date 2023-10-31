package service;

import interfaces.BookServiceInterface;
import interfaces.LibraryServiceInterface;

public class LibraryService implements LibraryServiceInterface {

    private BookServiceInterface bookService;

    public LibraryService() {
        bookService = new BookService();
    }

    @Override
    public void addNewBook(String title, String author, int year) {
        bookService.addNewBook(title, author, year);
    }

    @Override
    public void registerNewUser() {

    }

    @Override
    public void login() {

    }

    @Override
    public void borrowBook() {

    }

    @Override
    public void returnBook() {

    }

    @Override
    public void displayBookList() {

    }

    @Override
    public void displayAvailableBooks() {

    }

    @Override
    public void displayBorrowedBooks() {

    }

    @Override
    public void displayBooksByTitle() {

    }

    @Override
    public void displayBooksByAuthor() {

    }
}
