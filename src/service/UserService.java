package service;

import lib.MyArrayList;
import model.Book;
import model.User;
import repository.UserRepository;

public class UserService {
    private User currentUser = null;

    private UserRepository userRepository;

    public UserService() {
        userRepository = new UserRepository();
    }

    public void createNewUser(User newUser) {
        if (newUser == null) {
            System.err.println("Error: user can't be null.");
            return;
        }

        if (newUser.getUserName().isBlank()) {
            System.err.println("Error: user data doesn't match the format.");
            return;
        }

        if (!userRepository.userExists(newUser.getUserName())) {
            userRepository.addNewUser(newUser);
        } else {
            System.err.println("Error: user with this name already exists.");
        }
    }

    public void login(String userName, String password) {
        if (userName.isBlank()) {
            System.err.println("Error: login data doesn't match the format.");
            return;
        }
        User tempUser = userRepository.getUserByName(userName);

        if (tempUser != null) {
            if (tempUser.checkPassword(password)) {
                currentUser = tempUser;

                System.out.println("Login success!");
                System.out.printf("Welcome %s!\n", currentUser.getUserName());
            } else {
                System.err.println("Error: wrong password.");
            }
        } else {
            System.err.println("Error: this user doesn't exist.");
        }
    }

    public String getCurrentUserName() {
        return currentUser.getUserName();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void borrowBook(Book book) {
        if (currentUser == null) return;

        currentUser.borrowBook(book);
    }

    public void returnBook(Book book) {
        if (currentUser == null) return;

        currentUser.returnBook(book);
    }

    public void displayUserBooks() {
        if (currentUser == null) {
            System.err.println("Error: user is not logged in.");
            return;
        }

        MyArrayList<Book> bookList = currentUser.getUserBooks();

        if (bookList.isEmpty()) {
            System.out.println("This list is empty.");
        } else {
            System.out.println("Book list:");

            for (Book book : bookList)
                if (book != null) System.out.println(book);
        }
    }

    public void displayCurrentUserName() {
        if (currentUser != null)
            System.out.printf("Current user is: '%s'\n", currentUser.getUserName());
        else
            System.err.println("Error: user is not logged in.");
    }
}
