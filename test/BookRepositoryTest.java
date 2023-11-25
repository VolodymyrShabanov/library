
import model.Book;
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
    public void init() {
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
    }

    @Test
    void testAddBookIsNull() {
        int listLength = bookRepository.getBookList().size();
        Book newBook = null;
        bookRepository.addBook(newBook);
        assertEquals(listLength + 1, bookRepository.getBookList().size());
    }

    @Test
    void testAddBook() {
        int listLength = bookRepository.getBookList().size();
        Book book = new Book("1984", "George Orwell", 1950);
        bookRepository.addBook(book);
        assertEquals(listLength + 1, bookRepository.getBookList().size());

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
        Book newBook = new Book("Three", "Baccogan", 1749);
        bookRepository.addBook(newBook);
        assertEquals(newBook, bookRepository.getBookById(newBook.getId()));
        assertNotNull(bookRepository.getBookById(newBook.getId()));
    }

}
