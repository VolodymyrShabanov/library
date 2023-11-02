import lib.MyArrayList;
import model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.BookRepository;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by Volodymyr Sh on 31.10.2023
 * project name: Library
 */
public class BookRepositoryTest {
    private BookRepository bookRepository;

    public BookRepositoryTest() {
        this.bookRepository = new BookRepository();
    }

    public void addAllBook(Book... books) {
        bookRepository.getBookList().addAll(books);
    }

    @BeforeEach
    private void init() {
        addAllBook(
                new Book("To Kill a Mockingbird", "Harper Lee", 1960),
                new Book("1984", "George Orwell", 1949),
                new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925),
                new Book("Pride and Prejudice", "Jane Austen", 1813),
                new Book("Moby-Dick", "Herman Melville", 1851),
                new Book("The Catcher in the Rye", " J.D. Salinger", 1951),
                new Book("One Hundred Years of Solitude", "Gabriel Garcia Marquez", 1967),
                new Book("Brave New World", "Aldous Huxley", 1932),
                new Book("War and Peace", "Leo Tolstoy", 1869),
                new Book("The Lord of the Rings", "J.R.R. Tolkien", 1954)
        );
        //System.out.println(bookRepository);
    }


    @Test
    void testAddBookIsNull() { // ну не наю как суда может попасть null
        int previousListLength = bookRepository.getBookList().size();
        Book newBook = null;
        bookRepository.addBook(newBook);
        int nextListLength = bookRepository.getBookList().size();
        //System.out.println(previousListLength + " || " + nextListLength);
        assertTrue(previousListLength == nextListLength);
    }

    @Test
    void testAddBook() {
        int previousListLength = bookRepository.getBookList().size();
        Book book = new Book("1984", "George Orwell", 1950);
        bookRepository.addBook(book);
        int nextListLength = bookRepository.getBookList().size();
        //System.out.println(previousListLength + " || " + nextListLength);
        assertTrue(previousListLength < nextListLength);
    }

    @Test
        // нужно ли его тестировать
    void testBookListIsNotNull() {
        assertNotNull(bookRepository.getBookList());
    }

    @Test
        // не понимаю ка его можно протестировать
    void testBookByPredicateIsNotNull() {
        assertNotNull(bookRepository.getBookByPredicate(book -> book.getYear() > 2000));
        assertNotNull(bookRepository.getBookByPredicate(book -> book.getYear() < 0));
    }

    @Test
    void testUnborrowedBookListIsNotNull() {
        assertNotNull(bookRepository.getUnborrowedBookList());
    }

    @Test
    void testBorrowedBookListIsNotNull() {
        assertNotNull(bookRepository.getBorrowedBookList());
    }

    @Test
    void testBookListByAuthorIsNotNull() {
        assertNotNull(bookRepository.getBookListByAuthor("Harper Lee"));
        assertNotNull(bookRepository.getBookListByAuthor(""));
    }

    @Test
    void testBookListByTitleIsNotNull() {
        assertNotNull(bookRepository.getBookListByTitle("1984"));
        assertNotNull(bookRepository.getBookListByTitle(""));
    }

    @Test
    void testBookByIdIsNotExist() {
        int bookId = -1;
        assertNull(bookRepository.getBookById(bookId));
    }

    @Test
    void testBookByIdIsExist() {
        int bookId = bookRepository.getBookList().size() - 1;
        assertNotNull(bookRepository.getBookById(bookId));
    }

}
