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
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUserName().equals(userName))
                return userList.get(i);
        }

        return null;
    }

    public boolean userExists(String userName) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUserName().equals(userName))
                return true;
        }

        return false;
    }
}
