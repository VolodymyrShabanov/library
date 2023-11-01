package view;

import interfaces.MenuInterface;
import model.Book;
import model.User;
import service.BookService;
import service.LibraryService;
import service.UserService;

import java.sql.SQLOutput;
import java.util.Scanner;

/**
 * Created by Volodymyr Sh on 30.10.2023
 * project name: Library
 */
public class Menu implements MenuInterface {
    private LibraryService libraryService;

    public Menu() {
        this.libraryService = new LibraryService();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Select your action: ");

            System.out.println("1. Add new book");

            System.out.println();

            System.out.println("2. Sign Up");
            System.out.println("3. Login");

            System.out.println();

            System.out.println("4. Borrow book");
            System.out.println("5. Return book");

            System.out.println();

            System.out.println("6. Display library books list");
            System.out.println("7. Display unborrowed books list");
            System.out.println("8. Display borrowed books list");
            System.out.println("9. Display books by title");
            System.out.println("10. Display books by author name");
            System.out.println("11. Display user book list");
            System.out.println("12. Display how long the book is borrowed");

            System.out.println();

            System.out.println("13. Display current reader name");

            System.out.println();

            System.out.println("0. Exit");

            String answer = scanner.nextLine();

            switch (answer) {
                case "1": {
                    clearConsole();
                    System.out.println("Enter the book title: ");
                    String bookTitle = scanner.nextLine();

                    System.out.println("Enter book author: ");
                    String bookAuthor = scanner.nextLine();

                    System.out.println("Enter book year: ");
                    int bookYear = Integer.parseInt(scanner.nextLine());

                    Book tempBook = new Book(bookTitle, bookAuthor, bookYear);

                    libraryService.addNewBook(tempBook);

                    break;
                }
                case "2": {
                    clearConsole();
                    System.out.println("Enter new user name: ");
                    String userName = scanner.nextLine();

                    System.out.println("Enter new user password: ");
                    String pass = scanner.nextLine();

                    User tempUser = new User(userName, pass);

                    libraryService.registerNewUser(tempUser);
                    break;
                }
                case "3": {
                    clearConsole();
                    System.out.println("Enter user name: ");
                    String userName = scanner.nextLine();

                    System.out.println("Enter user password: ");
                    String pass = scanner.nextLine();

                    libraryService.login(userName, pass);
                    break;
                }
                case "4": {
                    clearConsole();
                    System.out.println("Enter bookID: ");
                    int bookID = Integer.parseInt(scanner.nextLine());

                    libraryService.borrowBook(bookID);
                    break;
                }
                case "5": {
                    clearConsole();
                    System.out.println("Enter bookID: ");
                    int bookID = Integer.parseInt(scanner.nextLine());

                    libraryService.returnBook(bookID);
                    break;
                }
                case "6": {
                    clearConsole();
                    libraryService.displayBookList();
                    break;
                }
                case "7": {
                    clearConsole();
                    libraryService.displayUnborrowedBooks();
                    break;
                }
                case "8": {
                    clearConsole();
                    libraryService.displayBorrowedBooks();
                    break;
                }
                case "9": {
                    clearConsole();
                    System.out.println("Enter the title: ");
                    String title = scanner.nextLine();

                    libraryService.displayBooksByTitle(title);
                    break;
                }
                case "10": {
                    clearConsole();
                    System.out.println("Enter the author name: ");
                    String authorName = scanner.nextLine();

                    libraryService.displayBooksByAuthor(authorName);
                    break;
                }
                case "11": {
                    clearConsole();
                    libraryService.displayUserBooks();
                    break;
                }
                case "12": {
                    clearConsole();
                    System.out.println("Enter bookID: ");
                    int bookID = Integer.parseInt(scanner.nextLine());

                    libraryService.displayBookRentalPeriod(bookID);
                    break;
                }
                case "13": {
                    clearConsole();
                    libraryService.displayCurrentUserName();
                    break;
                }
                case "0": {
                    clearConsole();
                    System.out.println("Shutting down...");
                    System.out.println("Thanks for using our Library App!");

                    isRunning = false;
                    break;
                }
                default: {
                    clearConsole();
                    System.err.println("Error: please choose a valid option.");
                    break;
                }
            }

            System.out.println("Press enter to continue...");
            scanner.nextLine();
            clearConsole();
        }
    }

    public void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
