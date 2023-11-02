import model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.BookRepository;
import service.BookService;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Volodymyr Sh on 01.11.2023
 * project name: Library
 */
public class BookServiceTest {
    private BookService bookService;
    private Book book;

    public BookServiceTest() {
        this.bookService = new BookService();
    }

    @BeforeEach
    private void init(){
        this.book = new Book("One", "Alon", 1);
    }

    @Test
    void testAddNewIsNull() { // что можно здесь придумать??
        Book newBook = null;
        bookService.addNewBook(newBook);
    }

    @Test
    void testAddNewBookByTitle(){ // ну такое )))
        bookService.addNewBook(this.book);

        assertNotNull(bookService.borrowBook(this.book.getId(),"").getTitle());
        assertFalse(bookService.borrowBook(this.book.getId(),"").getTitle().equals(""));
    }

    @Test
    void testAddNewBookByAutor() { // ну такое )))
        bookService.addNewBook(this.book);

        assertNotNull(bookService.borrowBook(this.book.getId(),"").getAuthor());
        assertFalse(bookService.borrowBook(this.book.getId(),"").getAuthor().equals(""));

    }

    @Test
    void testAddNewBookByYear() { // ну такое )))
        bookService.addNewBook(this.book);

        assertNotNull(bookService.borrowBook(this.book.getId(),"").getYear());
        assertTrue(bookService.borrowBook(this.book.getId(),"").getYear() > 0);

    }
    @Test
    void testBorrowBookIsNotNull(){
        bookService.addNewBook(this.book);
        assertNotNull(bookService.borrowBook(this.book.getId(), ""));
    }

    @Test
    void testBorrowBookIsNotExist(){
        int bookId = 999;
        assertNull(bookService.borrowBook(bookId, ""));
    }

    @Test
    void testBorrowBookByHolder() {
        bookService.addNewBook(this.book);
        this.book.setCurrentBookHolder("Nord");
        assertNull(bookService.borrowBook(this.book.getId(), ""));
    }


    @Test
    void testReturnBookIsNotNull(){

    }


    @Test
    void testDisplayAllBooks(){
        bookService.addNewBook(this.book);
        bookService.displayAllBooks();
    }


}
