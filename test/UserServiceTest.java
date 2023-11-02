import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.UserService;

/**
 * Created by Volodymyr Sh on 01.11.2023
 * project name: Library
 */
public class UserServiceTest {
    private UserService userService;
    private User user;

    public UserServiceTest() {
        this.userService = new UserService();
    }

    @BeforeEach
    private void init () {
        User user = new User("Vlad", "qwerty");
    }

    @Test
    void testCreateNewUser(){
        userService.createNewUser(user);
    }

}
