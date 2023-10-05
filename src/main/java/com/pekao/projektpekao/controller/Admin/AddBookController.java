package com.pekao.projektpekao.controller.Admin;

import com.pekao.projektpekao.controller.Book.BookDto;
import com.pekao.projektpekao.controller.Book.BookDtoMapper;
import com.pekao.projektpekao.controller.Book.BookResponse;
import com.pekao.projektpekao.domain.book.Book;
import com.pekao.projektpekao.domain.book.BookParams;
import com.pekao.projektpekao.domain.book.BookParamsMapper;
import com.pekao.projektpekao.service.AuthorService;
import com.pekao.projektpekao.service.BookService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(allowedHeaders = "Content-type")
public class AddBookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public AddBookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @PostMapping("/add")
    @CrossOrigin(origins = "http://localhost:4200")
    public BookResponse addBook(@RequestBody BookDto bookDto) {
        BookParams bookParams = BookParamsMapper.toCreateBookParams(bookDto);
        Book bookSaved = bookService.addBook(bookParams);
        BookDto bookDto1 = BookDtoMapper.toBookDto(bookSaved);
        return BookResponse.builder()
                .bookDtoResponse(bookDto1)
                .build();

    }


}

