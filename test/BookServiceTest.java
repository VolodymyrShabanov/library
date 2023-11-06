
import lib.MyArrayList;
import model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import service.BookService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Volodymyr Sh on 01.11.2023
 * project name: Library
 */
public class BookServiceTest {
    private final BookService bookService;
    private String newUserName;
    private Book book;

    public BookServiceTest() {
        this.bookService = new BookService();
    }

    @BeforeEach
    public void setUp() {
        this.book = new Book("One", "Alon", 1998);
        this.newUserName = "Jaguar";
    }

    @Test
    void testAddNewBookIsNull() {
        /*  Создается новый поток вывода который будет захватывать все,
            что выводится в System.err*/
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        //  Сохраняется стандартный поток ошибок в переменной
        PrintStream standardStreamErr = System.err;

        /*  Стандартный поток ошибок System.err
            заменяется на новый поток вывода*/
        System.setErr(new PrintStream(outputStream));

        /*  Вызывается метод addNewBook с null в качестве аргумента.
            Поскольку в методе addNewBook проверяется, что аргумент book
            не является null, метод выводит сообщение
            "Error: book can't be null." в System.err.*/
        bookService.addNewBook(null);

        /*  Фактический вывод перехватывается в переменную
            с использованием outputStream.toString()
            + используем trim() для удаления лишних пробелов
            и символов новой строки*/
        String strActual = outputStream.toString().trim();

        // Выполняется утверждение assertEquals
        assertEquals("Error: book can't be null.", strActual);

        // Восстанавливается стандартный поток ошибок
        System.setErr(standardStreamErr);
    }

    @Test
    void testAddNewBookTitleEmpty() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamErr = System.err;
        System.setErr(new PrintStream(outputStream));

        this.book.setTitle("");
        bookService.addNewBook(this.book);
        String expected = "Error: book data doesn't match the format.";

        assertEquals(expected, outputStream.toString().trim());
        System.setErr(standardStreamErr);
    }

    @Test
    void testAddNewBookTitleNotEmptyAndNotNull() {
        bookService.addNewBook(this.book);

        assertNotNull(bookService.borrowBook(this.book.getId(), "").getTitle());
        assertFalse(bookService.borrowBook(this.book.getId(), "").getTitle().isEmpty());
    }

    @Test
    void testAddNewBookAuthorEmpty() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamErr = System.err;
        System.setErr(new PrintStream(outputStream));

        this.book.setAuthor("");
        bookService.addNewBook(this.book);
        String expected = "Error: book data doesn't match the format.";

        assertEquals(expected, outputStream.toString().trim());
        System.setErr(standardStreamErr);
    }

    @Test
    void testAddNewBookAutorNotEmptyAndNotNull() {
        bookService.addNewBook(this.book);

        assertNotNull(bookService.borrowBook(this.book.getId(), "").getAuthor());
        assertNotEquals("", bookService.borrowBook(this.book.getId(), "").getAuthor());

    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 12, 123, 12345, -1234})
    void testAddNewBookYearLengthNotFourCharacters(int year) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamErr = System.err;
        System.setErr(new PrintStream(outputStream));

        this.book.setYear(year);
        bookService.addNewBook(this.book);
        String expected = "Error: book data doesn't match the format.";

        assertEquals(expected, outputStream.toString().trim());
        System.setErr(standardStreamErr);
    }

    @Test
    void testAddNewBookYearLengthFourCharacters() {
        bookService.addNewBook(this.book);
        Book bookFromRepository = bookService.borrowBook(this.book.getId(), "");
        int yearLength = Integer.toString(bookFromRepository.getYear()).length();
        assertEquals(4, yearLength);
    }

    @Test
    void testDisplayAllBooksListEmpty() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamOut = System.out;
        System.setOut(new PrintStream(outputStream));

        bookService.displayAllBooks();
        String expected = "This list is empty.";

        assertEquals(expected, outputStream.toString().trim());
        System.setOut(standardStreamOut);
    }

    @Test
    void testDisplayAllBooksListNotEmpty() {
        bookService.addNewBook(this.book);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamOut = System.out;
        System.setOut(new PrintStream(outputStream));

        bookService.displayAllBooks();
        String expected = "Book list:";

        assertTrue(outputStream.toString().trim().startsWith(expected));
        System.setOut(standardStreamOut);
    }

    @Test
    void testBorrowBookNotExist() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamErr = System.err;
        System.setErr(new PrintStream(outputStream));

        bookService.borrowBook(-1, "");
        String expected = "Error: no books with this identifier are registered.";

        assertEquals(expected, outputStream.toString().trim());
        System.setErr(standardStreamErr);

        assertNull(bookService.borrowBook(-1, ""));
    }

    @Test
    void testBorrowBookAlreadyBorrowed() {
        this.book.setCurrentBookHolder(newUserName);
        bookService.addNewBook(this.book);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamErr = System.err;
        System.setErr(new PrintStream(outputStream));

        bookService.borrowBook(this.book.getId(), newUserName);
        String expected = "Error: this book is already borrowed by another user.";

        assertEquals(expected, outputStream.toString().trim());
        System.setErr(standardStreamErr);

        assertNull(bookService.borrowBook(this.book.getId(), newUserName));
    }

    @Test
    void testBorrowBookIsBorrowed() {
        bookService.addNewBook(this.book);
        bookService.borrowBook(this.book.getId(), this.newUserName);
        assertEquals(book.getCurrentBookHolder(), this.newUserName);
        assertEquals(book.getBorrowDate(), LocalDate.now());
    }

    @Test
    void testReturnBookNotExist() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamErr = System.err;
        System.setErr(new PrintStream(outputStream));

        bookService.returnBook(-1, "");
        String expected = "Error: no books with this identifier are registered.";

        assertEquals(expected, outputStream.toString().trim());
        System.setErr(standardStreamErr);

        assertNull(bookService.returnBook(-1, ""));
    }

    @Test
    void testReturnBookNotBorrowed() {
        bookService.addNewBook(this.book);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamErr = System.err;
        System.setErr(new PrintStream(outputStream));

        bookService.returnBook(this.book.getId(), newUserName);
        String expected = "Error: this book wasn't borrowed.";

        assertEquals(expected, outputStream.toString().trim());
        System.setErr(standardStreamErr);

        assertNull(bookService.returnBook(this.book.getId(), newUserName));
    }

    @Test
    void testReturnBookBorrowedAnotherUser() {
        this.book.setCurrentBookHolder("Vlad");
        bookService.addNewBook(this.book);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamErr = System.err;
        System.setErr(new PrintStream(outputStream));

        bookService.returnBook(this.book.getId(), newUserName);
        String expected = "Error: this book is borrowed by another user.";

        assertEquals(expected, outputStream.toString().trim());
        System.setErr(standardStreamErr);

        assertNull(bookService.returnBook(this.book.getId(), newUserName));
    }

    @Test
    void testReturnBookIsReturned() {
        this.book.setCurrentBookHolder(newUserName);
        this.book.setBorrowDate(LocalDate.of(2023, 1, 1));
        bookService.addNewBook(this.book);

        bookService.returnBook(this.book.getId(), this.newUserName);
        assertEquals(book.getCurrentBookHolder(), "");
        assertNull(book.getBorrowDate());
    }

    @Test
    void testDisplayUnborrowedBookListEmpty() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamOut = System.out;
        System.setOut(new PrintStream(outputStream));

        bookService.displayUnborrowedBookList();
        String expected = "This list is empty.";

        assertEquals(expected, outputStream.toString().trim());
        System.setOut(standardStreamOut);
    }

    @Test
    void testDisplayUnborrowedBookListNotEmpty() {
        bookService.addNewBook(this.book);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamOut = System.out;
        System.setOut(new PrintStream(outputStream));

        bookService.displayUnborrowedBookList();
        String expected = "Book list:";

        assertTrue(outputStream.toString().trim().startsWith(expected));
        System.setOut(standardStreamOut);
    }

    @Test
    void testDisplayBorrowedBookListEmpty() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamOut = System.out;
        System.setOut(new PrintStream(outputStream));

        bookService.displayBorrowedBookList();
        String expected = "This list is empty.";

        assertEquals(expected, outputStream.toString().trim());
        System.setOut(standardStreamOut);
    }

    @Test
    void testDisplayBorrowedBookListNotEmpty() {
        this.book.setCurrentBookHolder(newUserName);
        bookService.addNewBook(this.book);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamOut = System.out;
        System.setOut(new PrintStream(outputStream));

        bookService.displayBorrowedBookList();
        String expected = "Book list:";

        assertTrue(outputStream.toString().trim().startsWith(expected));
        System.setOut(standardStreamOut);
    }


    @Test
    void testDisplayBooksByAuthorEmpty() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamOut = System.out;
        System.setOut(new PrintStream(outputStream));

        bookService.displayBooksByAuthor(this.book.getAuthor());
        String expected = "This list is empty.";

        assertEquals(expected, outputStream.toString().trim());
        System.setOut(standardStreamOut);
    }

    @Test
    void testDisplayBooksByAuthorNotEmpty() {
        this.book.setCurrentBookHolder(newUserName);
        bookService.addNewBook(this.book);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamOut = System.out;
        System.setOut(new PrintStream(outputStream));

        bookService.displayBooksByAuthor(this.book.getAuthor());
        String expected = "Book list:";

        assertTrue(outputStream.toString().trim().startsWith(expected));
        System.setOut(standardStreamOut);
    }

    @Test
    void testDisplayBooksByTitleEmpty() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamOut = System.out;
        System.setOut(new PrintStream(outputStream));

        bookService.displayBooksByTitle(this.book.getTitle());
        String expected = "This list is empty.";

        assertEquals(expected, outputStream.toString().trim());
        System.setOut(standardStreamOut);
    }

    @Test
    void testDisplayBooksByTitleNotEmpty() {
        this.book.setCurrentBookHolder(newUserName);
        bookService.addNewBook(this.book);


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamOut = System.out;
        System.setOut(new PrintStream(outputStream));

        bookService.displayBooksByTitle(this.book.getTitle());
        String expected = "Book list:";

        assertTrue(outputStream.toString().trim().startsWith(expected));
        System.setOut(standardStreamOut);
    }

    @Test
    void testPrintBookListEmpty() {
        MyArrayList<Book> listEmpty = new MyArrayList<>();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamOut = System.out;
        System.setOut(new PrintStream(outputStream));

        bookService.printBookList(listEmpty);
        String expected = "This list is empty.";

        assertEquals(expected, outputStream.toString().trim());
        System.setOut(standardStreamOut);
    }

    @Test
    void testPrintBookListNotEmpty() {
        MyArrayList<Book> listNotEmpty = new MyArrayList<>();
        listNotEmpty.add(this.book);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream standardStreamOut = System.out;
        System.setOut(new PrintStream(outputStream));

        bookService.printBookList(listNotEmpty);
        String expected = "Book list:";

        assertTrue(outputStream.toString().trim().startsWith(expected));
        System.setOut(standardStreamOut);
    }

    @Test
    void testBookRentalPeriodBookNotBorrowed() {
        bookService.addNewBook(this.book);
        assertEquals(-1, bookService.getBookRentalPeriod(this.book.getId()));
    }

    @ParameterizedTest
    @MethodSource("generateDatePeriodTest")
    void testBookRentalPeriod(LocalDate date) {
        this.book.setBorrowDate(date);
        bookService.addNewBook(this.book);
        assertTrue(bookService.getBookRentalPeriod(book.getId()) >= 0);
    }

    static Stream<LocalDate> generateDatePeriodTest() {
        return Stream.of(
                LocalDate.of(2023, 3, 12),
                LocalDate.of(2012, 11, 2),
                LocalDate.now()
        );
    }

}
