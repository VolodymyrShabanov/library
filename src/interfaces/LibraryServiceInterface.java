package interfaces;

import model.Book;

public interface LibraryServiceInterface {

    public void addNewBook(Book book);

    public void registerNewUser();

    public void login();

    public void borrowBook();

    public void returnBook();

    public void displayBookList();

    public void displayAvailableBooks();

    public void displayBorrowedBooks();

    public void displayBooksByTitle(String title);

    public void displayBooksByAuthor();
}
