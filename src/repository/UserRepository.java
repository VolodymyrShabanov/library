package repository;

import lib.MyArrayList;
import model.User;

public class UserRepository {
    private MyArrayList<User> userList;

    public UserRepository() {
        userList = new MyArrayList<>();
    }

    public void addNewUser(User newUser) {
        userList.add(newUser);
    }

    public User getUserByName(String userName) {
        for (User user : userList)
            if (user.getUserName().equals(userName))
                return user;

        return null;
    }

    public boolean userExists(String userName) {
        for (User user : userList)
            if (user.getUserName().equals(userName))
                return true;

        return false;
    }
}
