import lib.MyArrayList;
import lib.MyList;
import model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.BookRepository;


/**
 * Created by Volodymyr Sh on 31.10.2023
 * project name: Library
 */
public class BookRepositoryTest {
    private BookRepository bookRepository;

    public BookRepositoryTest() {
        this.bookRepository = new BookRepository();
    }

    @BeforeEach
    private void init() {
        bookRepository.addAllBook(
                new Book("To Kill a Mockingbird", "Harper Lee", 1960),
                new Book("1984" , "George Orwell", 1949),
                new Book("The Great Gatsby" , "F. Scott Fitzgerald", 1925),
                new Book("Pride and Prejudice" , "Jane Austen", 1813),
                new Book("Moby-Dick" , "Herman Melville", 1851),
                new Book("The Catcher in the Rye" , " J.D. Salinger", 1951),
                new Book("One Hundred Years of Solitude" , "Gabriel Garcia Marquez", 1967),
                new Book("Brave New World" , "Aldous Huxley", 1932),
                new Book("War and Peace" , "Leo Tolstoy", 1869),
                new Book("The Lord of the Rings" , "J.R.R. Tolkien",  1954)
        );

        System.out.println(bookRepository);
    }

    @Test
    void test(){
        System.out.println(bookRepository.getBookList());
    }


    @Test
    void testGetBookByPredicate() {
        MyList<Book> books = bookRepository.getBookByPredicate(book -> book.getAuthor().equals("Herman Melville"));
        System.out.println(books);

    }


}
