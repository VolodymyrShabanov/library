package interfaces;

import model.User;

import java.time.LocalDate;

/**
 * Created by Volodymyr Sh on 30.10.2023
 * project name: Library
 */
public interface BookInterface {
    public String getTitle();
    public String getDescription();
    public LocalDate getPublishingDate();
    public LocalDate getBorrowDate();
    public User getCurrentBookHolder();
}
