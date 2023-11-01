package service;

import interfaces.UserServiceInterface;
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
        if(!userRepository.userExists(newUser.getUserName())) {
            userRepository.addNewUser(newUser);
        } else {
            System.err.println("Error: user with this name already exists.");
        }
    }

    public void login(String userName, String password) {
        User tempUser = userRepository.getUserByName(userName);

        if(tempUser != null) {
            if(tempUser.checkPassword(password)) {
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
        if (currentUser == null) {
            return;
        }

        currentUser.borrowBook(book);
    }

    public void displayUserBooks() {

        if(currentUser == null) {
            System.err.println("Error: user not set.");
            return;
        }

        MyArrayList<Book> bookList = currentUser.getUserBooks();

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
