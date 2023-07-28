package com.pekao.projektpekao.controller.Book;

import com.pekao.projektpekao.controller.Author.AuthorDtoMapper;
import com.pekao.projektpekao.controller.Comments.CommentDtoMapper;
import com.pekao.projektpekao.domain.book.Book;
import com.pekao.projektpekao.domain.book.BookParams;

import java.util.List;

public final class BookDtoMapper {

    private BookDtoMapper() {
    }

    public static BookDto toBookDto(BookParams bookParams) {
        return BookDto.builder()
                .id(bookParams.getId())
                .authorDto(bookParams.getAuthorDto())
                .title(bookParams.getTitle())
//                .commentDtoList(bookParams.getCommentDtoList())
                .publisher(bookParams.getPublisher())
                .build();

    }

    public static List<BookDto> toBookDtos(List<Book> bookList) {
        return bookList.stream()
                .map(BookDtoMapper::toBookDto)
                .toList();
    }

    public static BookDto toBookDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .authorDto(AuthorDtoMapper.toAuthorDto(book.getAuthor()))
                .title(book.getTitle())
//                .commentDtoList(CommentDtoMapper.toCommentsDto(book.getCommentList()))
                .publisher(book.getPublisher())
                .bookPhoto(book.getBookPhoto())
                .build();

    }
}
