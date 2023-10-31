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

            System.out.println("2. Register a new user");
            System.out.println("3. Login");

            System.out.println("4. Borrow book");
            System.out.println("5. Return book");

            System.out.println("6. Library books list");
            System.out.println("7. Available books list");
            System.out.println("8. Borrowed books list");
            System.out.println("9. Display books by title");
            System.out.println("10. Display books by author name");

            System.out.println("11. Display current reader name");

            System.out.println("0. Exit");

            String answer = scanner.nextLine();

            switch (answer) {
                case "1" : {
                    // Add book logic
                    // 1. Ask user to provide necessary book data.
                    // 2. Create a new Book element.
                    // 3. Pass this book as argument to Library Service.
                    System.out.println("Enter the book title: ");
                    String bookTitle = scanner.nextLine();

                    System.out.println("Enter book author: ");
                    String bookAuthor = scanner.nextLine();

                    System.out.println("Enter book year: ");
                    int bookYear = scanner.nextInt();

                    Book tempBook = new Book(bookTitle, bookAuthor, bookYear);

                    libraryService.addNewBook(tempBook);

                    break;
                }
                case "2" : {
                    System.out.println("Enter new user name: ");
                    String userName = scanner.nextLine();

                    System.out.println("Enter new user password: ");
                    String pass = scanner.nextLine();

                    User tempUser = new User(userName, pass);

                    libraryService.registerNewUser(tempUser);
                  break;
                }
                case "3": {
                    System.out.println("Enter user name: ");
                    String userName = scanner.nextLine();

                    System.out.println("Enter user password: ");
                    String pass = scanner.nextLine();

                    libraryService.login(userName, pass);
                    break;
                }
                case "11": {
                    libraryService.displayCurrentUserName();
                    break;
                }
                case "0": {
                    System.out.println("Shutting down...");
                    System.out.println("Thanks for using our Library App!");

                    isRunning = false;
                    break;
                }
                default: {
                    System.err.println("Error: please choose a valid option.");
                    break;
                }
            }

            scanner.nextLine();
        }
    }
}
