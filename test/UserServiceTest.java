
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.UserService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Volodymyr Sh on 01.11.2023
 * project name: Library
 */
public class UserServiceTest {
    private final UserService userService;
    private User userTest;


    public UserServiceTest() {
        this.userService = new UserService();
    }

    @BeforeEach
    public void setUp() {
        this.userTest = new User("Vlad", "qwerty");
    }

    @Test
    void testCreateNewUserIsNull() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamErr = System.err;
        System.setErr(new PrintStream(outputStream));

        userService.createNewUser(null);
        String expected = "Error: user can't be null.";

        assertEquals(expected, outputStream.toString().trim());
        System.setErr(standardStreamErr);
    }

    @Test
    void testCreateNewUserNameIsBlank() {
        User newUser = new User("", "qwerty");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamErr = System.err;
        System.setErr(new PrintStream(outputStream));

        userService.createNewUser(newUser);
        String expected = "Error: user data doesn't match the format.";

        assertEquals(expected, outputStream.toString().trim());
        System.setErr(standardStreamErr);
    }

    @Test
    void testCreateNewUserNameAlreadyExist() {
        userService.createNewUser(userTest);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamErr = System.err;
        System.setErr(new PrintStream(outputStream));

        userService.createNewUser(userTest);
        String expected = "Error: user with this name already exists.";

        assertEquals(expected, outputStream.toString().trim());
        System.setErr(standardStreamErr);
    }


    @Test
    void testLoginUserNameIsBlank() {
        User newUser = new User("", "qwerty");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamErr = System.err;
        System.setErr(new PrintStream(outputStream));

        userService.login(newUser.getUserName(), "qwerty");
        String expected = "Error: login data doesn't match the format.";

        assertEquals(expected, outputStream.toString().trim());
        System.setErr(standardStreamErr);
        assertNotEquals(this.userTest, userService.getCurrentUser());

    }


    @Test
    void testLoginCorrectUser() {
        userService.createNewUser(userTest);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamOut = System.out;
        System.setOut(new PrintStream(outputStream));

        userService.login(this.userTest.getUserName(), "qwerty");
        String expected = "Login success!";

        assertTrue(outputStream.toString().trim().startsWith(expected));
        System.setOut(standardStreamOut);
        assertEquals(this.userTest, userService.getCurrentUser());
    }

    @Test
    void testLoginUserWrongPassword() {
        userService.createNewUser(userTest);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamErr = System.err;
        System.setErr(new PrintStream(outputStream));

        userService.login(this.userTest.getUserName(), "");
        String expected = "Error: wrong password.";

        assertEquals(expected, outputStream.toString().trim());
        System.setErr(standardStreamErr);

        assertNotEquals(this.userTest, userService.getCurrentUser());
    }

    @Test
    void testLoginUserNotExist() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamErr = System.err;
        System.setErr(new PrintStream(outputStream));

        userService.login(this.userTest.getUserName(), "qwerty");
        String expected = "Error: this user doesn't exist.";

        assertEquals(expected, outputStream.toString().trim());
        System.setErr(standardStreamErr);

        assertNotEquals(this.userTest, userService.getCurrentUser());
    }

    @Test
    void testCurrentUserNameExist() {
        userService.createNewUser(this.userTest);
        userService.login(this.userTest.getUserName(), "qwerty");
        assertEquals(userService.getCurrentUserName(), this.userTest.getUserName());
    }


    @Test
    void testCurrentUserIsNull() {
        assertNull(userService.getCurrentUser());
    }

    @Test
    void testCurrentUserNotNull() {
        userService.createNewUser(this.userTest);
        userService.login(this.userTest.getUserName(), "qwerty");
        assertEquals(this.userTest, userService.getCurrentUser());
    }

}
