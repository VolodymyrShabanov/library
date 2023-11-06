
import model.User;
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
    private User userTest;

    public UserRepositoryTest() {
        this.userRepository = new UserRepository();
    }

    @BeforeEach
    public void setUp() {
        userTest = new User("Vlad", "qwerty");
    }

    @Test
    void testAddNewUserIsExist() {
        userRepository.addNewUser(this.userTest);
        String userNameFromRepository = userRepository.getUserByName(this.userTest.getUserName()).getUserName();
        assertEquals("Vlad", userNameFromRepository);
    }

    @Test
    void testUserByNameNotExist(){
        assertNull(userRepository.getUserByName(this.userTest.getUserName()));
    }

    @Test
    void testUserExistsIsTrue(){
        userRepository.addNewUser(this.userTest);
        assertTrue(userRepository.userExists(this.userTest.getUserName()));
    }
    @Test
    void testUserExistsIsFalse(){
        assertFalse(userRepository.userExists(this.userTest.getUserName()));
    }

}
