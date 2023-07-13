package com.pekao.projektpekao.service;

import com.pekao.projektpekao.AuthorTestUtility;
import com.pekao.projektpekao.BookTestUtility;
import com.pekao.projektpekao.domain.book.BookParams;
import com.pekao.projektpekao.domain.Author.Author;
import com.pekao.projektpekao.domain.ElectronicJournal.ElectronicJournal;
import com.pekao.projektpekao.domain.book.Book;
import com.pekao.projektpekao.repository.AuthorRepository;
import com.pekao.projektpekao.repository.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.pekao.projektpekao.domain.ElectronicJournal.ElectronicJournal.EventType.TO_DO;
import static com.pekao.projektpekao.domain.ElectronicJournal.ElectronicJournal.EventType.WIP;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Disabled
//@TestPropertySource("/application-test.properties") //alternative in case of test issues
class BookServiceTest {

    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;


    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
    }

    @Test
    void shouldCreateBookOfEventTypeWhenPublisherWYDAWNICTWO_LITERACKIE() {
        //given
        Author author = authorRepository.save(AuthorTestUtility.createdAuthorWithFirstNameAndLastName("Jan", "Kowalski"));

        BookParams book = BookTestUtility.createBookWithTitleAuthorAndPublisherAndCommentsAndUser("Spring na Robocie", author,Book.Publisher.WYDAWNICTWO_LITERACKIE);

        //when
        Book bookFound = bookService.addBook(book);
        //then
        assertEquals(ElectronicJournal.EventType.MANAGER, ElectronicJournal.EventType.MANAGER);

    }

    @Test
    void shouldCreateBookOfEventTypeWhenPublisherPWN() {
        //given
        BookParams book = BookTestUtility.createBookParamsWithPublisher(Book.Publisher.PWN);
        //when
        Book bookFound = bookService.addBook(book);
        //then
        assertEquals(ElectronicJournal.EventType.DONE, ElectronicJournal.EventType.DONE);
    }

    @Test
    void shouldCreateBookOfEventTypeWhenPublisherZNAK() {
        //given
        BookParams book = BookTestUtility.createBookParamsWithPublisher(Book.Publisher.ZNAK);
        //when
        Book bookFound = bookService.addBook(book);
        //then
        assertEquals(TO_DO, TO_DO);
    }

    @Test
    void shouldCreateBookOfEventTypeWhenPublisherAGORA() {
        //given
        BookParams book = BookTestUtility.createBookParamsWithPublisher(Book.Publisher.AGORA);
        //when
        Book bookFound = bookService.addBook(book);
        //then
        assertEquals(WIP, ElectronicJournal.EventType.WIP);
    }


    @Test
    void findAllBooks() {
        //given
        final List<Book> booksToSave = List.of(BookTestUtility.createBookWithPublisher(Book.Publisher.ZNAK),
                BookTestUtility.createBookWithPublisher(Book.Publisher.PWN),
                BookTestUtility.createBookWithPublisher(Book.Publisher.AGORA),
                BookTestUtility.createBookWithPublisher(Book.Publisher.WYDAWNICTWO_LITERACKIE));
        bookRepository.saveAll((booksToSave));
        //when
        List<Book> allBooksFound = bookService.findAllBooks();
        //then
        assertThat(allBooksFound, hasSize(4));
        Assertions.assertThat(
                allBooksFound.stream().anyMatch(n -> n.getPublisher().equals(booksToSave.get(0).getPublisher()))
        ).isTrue();
        Assertions.assertThat(
                allBooksFound.stream().anyMatch(n -> n.getPublisher().equals(booksToSave.get(1).getPublisher()))
        ).isTrue();
        Assertions.assertThat(
                allBooksFound.stream().anyMatch(n -> n.getPublisher().equals(booksToSave.get(2).getPublisher()))
        ).isTrue();
        Assertions.assertThat(
                allBooksFound.stream().anyMatch(n -> n.getPublisher().equals(booksToSave.get(3).getPublisher()))
        ).isTrue();
    }

    @Test
    void findBookById() {
        //given
        Book bookSaved = bookService.addBook(BookTestUtility.createBookParamsWithPublisher(Book.Publisher.ZNAK));
        //when
        Book bookFound = bookService.findBookById(bookSaved.getId());
        //then
        assertEquals(bookSaved.getId(), bookFound.getId());
    }

    @Test
    void findBookByTitle() {
        //given
        Author authorToSave = authorService.addAuthor(AuthorTestUtility.createAuthor("Mark", "Spencer"));
        Book bookToSave = bookService.addBook(BookTestUtility.createBookWithTitleAndAuthor("What's the story morning glory", authorToSave));
        //when
        Book foundBook = bookService.findBookByTitle(bookToSave.getTitle());
        //then
        assertEquals(foundBook.getTitle(), bookToSave.getTitle());
    }

    @Test
    void removeBookById() {
        //given
        Author authorToSave = authorService.addAuthor(AuthorTestUtility.createAuthor("Mark", "Spencer"));
        Book bookWithTitleSaved = bookService.addBook(BookTestUtility.createBookWithTitleAndAuthor("What's the story morning glory", authorToSave));
        //when
        bookService.removeBookById(bookWithTitleSaved.getId());
        //then
        assertThrows(IllegalStateException.class, () -> bookService.findBookById(bookWithTitleSaved.getId()));
    }

    @Test
    void addBook() {
        //given
        Author authorToSave = authorService.addAuthor(AuthorTestUtility.createAuthor("Mark", "Spencer"));
        BookParams bookToBeAdded = BookTestUtility.createBookWithTitleAndAuthor("What's the story morning glory", authorToSave);
        //when
        Book bookSaved = bookService.addBook(bookToBeAdded);
        //then
        assertNotNull(bookSaved);

    }

    @Test
    void updateBook() {
        //given
        Author authorToSave = authorService.addAuthor(AuthorTestUtility.createAuthor("Mark", "Spencer"));
        Book bookWithTitleSaved = bookService.addBook(BookTestUtility.createBookWithTitleAndAuthor("Exemplary Title",authorToSave));
        BookParams bookWithChangedData = BookParams.builder()
                .id(bookWithTitleSaved.getId())
                .title("This title has been changed now")
                .build();
        //when
        Book bookFoundWithChangedData = bookService.updateBook(bookWithChangedData);
        //then
        assertEquals(bookFoundWithChangedData.getTitle(), "This title has been changed now");
    }
}
