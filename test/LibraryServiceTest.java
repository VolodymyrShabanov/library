import model.Book;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.LibraryService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceTest {
    private final LibraryService libraryService;
    private Book bookTest;
    private User userTest;

    public LibraryServiceTest() {
        this.libraryService = new LibraryService();
    }

    @BeforeEach
    void setUp() {
        this.bookTest = new Book("Second", "Bes", 1666);
        this.userTest = new User("Haribo", "qwerty");
    }

    @Test
    void testAddNewBookIsNull() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamErr = System.err;
        System.setErr(new PrintStream(outputStream));

        libraryService.addNewBook(null);
        String expected = "Error: book can't be null.";

        assertEquals(expected, outputStream.toString().trim());
        System.setErr(standardStreamErr);


    }

    @Test
    void testRegisterNewUserIsNull() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamErr = System.err;
        System.setErr(new PrintStream(outputStream));

        libraryService.registerNewUser(null);

        String expected = "Error: user can't be null.";

        assertEquals(expected, outputStream.toString().trim());
        System.setErr(standardStreamErr);
    }

    @Test
    void testLoginUserNameIsBlank() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamErr = System.err;
        System.setErr(new PrintStream(outputStream));

        libraryService.login("", "");

        String expected = "Error: login data doesn't match the format.";

        assertEquals(expected, outputStream.toString().trim());
        System.setErr(standardStreamErr);
    }

    @Test
    void testBorrowBookUserNotLogged() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamErr = System.err;
        System.setErr(new PrintStream(outputStream));

        libraryService.borrowBook(this.bookTest.getId());
        String expected = "Error: user is not logged in.";

        assertEquals(expected, outputStream.toString().trim());
        System.setErr(standardStreamErr);

    }

    @Test
    void testBorrowBookIsNull() {
        libraryService.registerNewUser(this.userTest);
        libraryService.login(this.userTest.getUserName(), "qwerty");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamErr = System.err;
        System.setErr(new PrintStream(outputStream));

        libraryService.borrowBook(0);
        String expected = "Error: no books with this identifier are registered.";

        assertEquals(expected, outputStream.toString().trim());
        System.setErr(standardStreamErr);
    }

    @Test
    void testBorrowBookBorrowed() {
        libraryService.addNewBook(this.bookTest);
        libraryService.registerNewUser(this.userTest);
        libraryService.login(this.userTest.getUserName(), "qwerty");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamOut = System.out;
        System.setOut(new PrintStream(outputStream));

        libraryService.borrowBook(this.bookTest.getId());
        libraryService.displayUserBooks();
        String expected = "Book list:";

        assertTrue(outputStream.toString().trim().startsWith(expected));
        System.setOut(standardStreamOut);
    }

    @Test
    void testReturnBookUserNotLogged() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamErr = System.err;
        System.setErr(new PrintStream(outputStream));

        libraryService.returnBook(this.bookTest.getId());
        String expected = "Error: user is not logged in.";

        assertEquals(expected, outputStream.toString().trim());
        System.setErr(standardStreamErr);

    }

    @Test
    void testReturnBookIsNull() {
        libraryService.registerNewUser(this.userTest);
        libraryService.login(this.userTest.getUserName(), "qwerty");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamErr = System.err;
        System.setErr(new PrintStream(outputStream));

        libraryService.returnBook(0);
        String expected = "Error: no books with this identifier are registered.";

        assertEquals(expected, outputStream.toString().trim());
        System.setErr(standardStreamErr);
    }

    @Test
    void testReturnBookReturned() {
        libraryService.addNewBook(this.bookTest);
        libraryService.registerNewUser(this.userTest);
        libraryService.login(this.userTest.getUserName(), "qwerty");
        libraryService.borrowBook(this.bookTest.getId());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamOut = System.out;
        System.setOut(new PrintStream(outputStream));

        libraryService.returnBook(this.bookTest.getId());
        libraryService.displayUserBooks();
        String expected = "This list is empty.";

        assertEquals(expected, outputStream.toString().trim());
        System.setOut(standardStreamOut);
    }


    @Test
    void testDisplayUserBooks() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamOut = System.err;
        System.setErr(new PrintStream(outputStream));

        libraryService.displayUserBooks();
        String expected = "Error: user is not logged in.";

        assertEquals(expected, outputStream.toString().trim());
        System.setErr(standardStreamOut);
    }

    @Test
    void testDisplayBookRentalPeriodBookNotBorrowed() {
        libraryService.addNewBook(this.bookTest);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamErr = System.err;
        System.setErr(new PrintStream(outputStream));

        libraryService.displayBookRentalPeriod(this.bookTest.getId());
        String expected = "Error: this book is not borrowed.";

        assertEquals(expected, outputStream.toString().trim());
        System.setErr(standardStreamErr);

    }

    @Test
    void testDisplayBookRentalPeriodIsGreaterThanZero() {
        this.bookTest.setBorrowDate(LocalDate.of(2023, 1, 1));
        libraryService.addNewBook(this.bookTest);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamOut = System.out;
        System.setOut(new PrintStream(outputStream));

        libraryService.displayBookRentalPeriod(this.bookTest.getId());
        String expected = "This book was borrowed";

        assertTrue(outputStream.toString().trim().startsWith(expected));
        System.setOut(standardStreamOut);

    }

    @Test
    void testDisplayBookListIsEmpty() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamOut = System.out;
        System.setOut(new PrintStream(outputStream));

        libraryService.displayBookList();
        String expected = "This list is empty.";

        assertEquals(expected, outputStream.toString().trim());
        System.setOut(standardStreamOut);
    }

    @Test
    void testDisplayUnborrowedBooksIsEmpty() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamOut = System.out;
        System.setOut(new PrintStream(outputStream));

        libraryService.displayUnborrowedBooks();
        String expected = "This list is empty.";

        assertEquals(expected, outputStream.toString().trim());
        System.setOut(standardStreamOut);
    }

    @Test
    void testDisplayBorrowedBooksIsEmpty() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamOut = System.out;
        System.setOut(new PrintStream(outputStream));

        libraryService.displayBorrowedBooks();
        String expected = "This list is empty.";

        assertEquals(expected, outputStream.toString().trim());
        System.setOut(standardStreamOut);
    }

    @Test
    void testDisplayBooksByTitleIsEmpty() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamOut = System.out;
        System.setOut(new PrintStream(outputStream));

        libraryService.displayBooksByTitle("");
        String expected = "This list is empty.";

        assertEquals(expected, outputStream.toString().trim());
        System.setOut(standardStreamOut);
    }

    @Test
    void testDisplayBooksByAuthorIsEmpty() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamOut = System.out;
        System.setOut(new PrintStream(outputStream));

        libraryService.displayBooksByAuthor("");
        String expected = "This list is empty.";

        assertEquals(expected, outputStream.toString().trim());
        System.setOut(standardStreamOut);
    }

    @Test
    void testDisplayCurrentUserNameIsNotLogged() {
        //libraryService.registerNewUser(this.userTest);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamOut = System.err;
        System.setErr(new PrintStream(outputStream));

        libraryService.displayCurrentUserName();
        String expected = "Error: user is not logged in.";

        assertEquals(expected, outputStream.toString().trim());
        System.setErr(standardStreamOut);
    }

    @Test
    void testDisplayCurrentUserNameIsLogged() {
        libraryService.registerNewUser(this.userTest);
        libraryService.login(this.userTest.getUserName(), "qwerty");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamOut = System.out;
        System.setOut(new PrintStream(outputStream));

        libraryService.displayCurrentUserName();
        String expected = "Current user is: 'Haribo'";

        assertEquals(expected, outputStream.toString().trim());
        System.setOut(standardStreamOut);
    }


}