package service;

import interfaces.UserServiceInterface;
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
}
