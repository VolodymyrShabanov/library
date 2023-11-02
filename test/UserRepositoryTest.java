import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Volodymyr Sh on 01.11.2023
 * project name: Library
 */
public class UserRepositoryTest {
    private UserRepository userRepository;
    private User user;

    public UserRepositoryTest() {
        this.userRepository = new UserRepository();
    }

    @BeforeEach
    private void init () {
        User user = new User("Vlad", "qwerty");
    }

    @Test
    void testAddNewUser(){
        userRepository.addNewUser(this.user);
        User newUser = userRepository.getUserByName("Vlad");
//        assertEquals(this.user, newUser);
    }


}
