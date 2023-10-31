package interfaces;

import lib.MyArrayList;
import model.User;

public interface UserRepositoryInterface {
    public void addNewUser(User newUser);
    public User getUserByName(String userName);
}
