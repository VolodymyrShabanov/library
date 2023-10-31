package interfaces;

import lib.MyArrayList;
import model.Book;

public interface UserInterface {
    public String getUserName();
    public boolean checkPassword(String password);
    public MyArrayList<Book> getUserBooks();
}
