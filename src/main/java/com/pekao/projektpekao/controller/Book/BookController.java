package com.pekao.projektpekao.controller.Book;

import com.pekao.projektpekao.domain.book.Book;
import com.pekao.projektpekao.domain.book.BookParams;
import com.pekao.projektpekao.domain.book.BookParamsMapper;
import com.pekao.projektpekao.service.BookService;
import com.pekao.projektpekao.service.ElectronicJournalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(allowedHeaders = "Content-type")
@RequestMapping("/api/book")
public class BookController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;
    private final ElectronicJournalService electronicJournalService;

    public BookController(BookService bookService, ElectronicJournalService electronicJournalService) {
        this.bookService = bookService;
        this.electronicJournalService = electronicJournalService;
    }

//    testowy endpoint do odbioru na froncie
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public BooksResponse getAllBooks() {
        LOGGER.info("Printing all books");
        List<BookDto> bookDtoList = BookDtoMapper.toBookDtos(bookService.findAllBooks());
        return BooksResponse.builder()
                .bookResponseList(bookDtoList)
                .build();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all/list")
    public List<BookDto> getListOfBooks() {
        return BookDtoMapper.toBookDtos(bookService.findAllBooks());
    }


    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public BookResponse getBookById(@PathVariable("id") Long id) {
        BookDto singleBook = BookDtoMapper.toBookDto(bookService.findBookById(id));
        return BookResponse.builder()
                .bookDtoResponse(singleBook)
                .build();
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable("id") Long id) {
        bookService.removeBookById(id);
    }

    @PostMapping()
    public BookResponse postBook(@RequestBody BookDto bookDto) {
        // DTO -> PARAMSY
        BookParams bookParams = BookParamsMapper.toCreateBookParams(bookDto);
        Book bookSaved = bookService.addBook(bookParams);
        BookDto bookDto1 = BookDtoMapper.toBookDto(bookSaved);
        return BookResponse.builder()
                .bookDtoResponse(bookDto1)
                .build();
    }

    @PutMapping("/{id}")
    public void putBook(@PathVariable("id") Long id, @RequestBody BookDto bookDto) {
        if (!Objects.equals(id, bookService.findBookById(id).getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id does not match");
        }
        BookParams bookToPut = BookParamsMapper.toCreateBookParams(bookDto);
        bookService.updateBook(bookToPut);
    }
}
