package interfaces;

import model.User;

public interface UserServiceInterface {
    public String getCurrentUserName();
    public User getCurrentUser();
    public void login(String userName, String password);
}
