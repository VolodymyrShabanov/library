package interfaces;

public interface LibraryServiceInterface {

    public void addNewBook(String title, String author, int year);

    public void registerNewUser();

    public void login();

    public void borrowBook();

    public void returnBook();

    public void displayBookList();

    public void displayAvailableBooks();

    public void displayBorrowedBooks();

    public void displayBooksByTitle();

    public void displayBooksByAuthor();
}
