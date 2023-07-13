package com.pekao.projektpekao.controller.Book;

import com.pekao.projektpekao.controller.Author.AuthorDto;
import com.pekao.projektpekao.controller.Comments.CommentEntityMapper;
import com.pekao.projektpekao.domain.Author.Author;
import com.pekao.projektpekao.domain.book.Book;
import com.pekao.projektpekao.domain.book.BookFactory;
import com.pekao.projektpekao.domain.book.BookParams;
import com.pekao.projektpekao.domain.book.CreateBookFactoryParams;

import java.util.List;


public class BookEntityMapper {

    private BookEntityMapper() {
    }

    public static Book toBookEntity(BookDto bookDto) {
        return Book.builder()
                .id(bookDto.getId())
                .title(bookDto.getTitle())
                .author(Author.builder()
                        .withFirstName(bookDto.getAuthorDto().getFirstName())
                        .withLastName(bookDto.getAuthorDto().getLastName())
                        .buildNewEntity())
//                .publisher(bookDto.getPublisher())
//                .commentList(CommentEntityMapper.toCommentListEntity(bookDto.getCommentDtoList()))
                .build();
    }

    public static List<Book> toBooksEntity(List<BookDto> bookDtoList) {
        return bookDtoList.stream()
                .map(BookEntityMapper::toBookEntity)
                .toList();
    }
    public static Book toBookEntity(BookParams bookParams) {
        return Book.builder()
                .id(bookParams.getId())
                .title(bookParams.getTitle())
                .author(Author.builder()
                        .withFirstName(bookParams.getAuthorDto().getFirstName())
                        .withLastName(bookParams.getAuthorDto().getLastName())
                        .buildNewEntity())
                .publisher(bookParams.getPublisher())
                .commentList(CommentEntityMapper.toCommentListEntity(bookParams.getCommentDtoList()))
                .build();

    }
    public static List<Book> toBookEntityList(List<BookParams> bookParamsList) {
        return bookParamsList.stream()
                .map(BookEntityMapper::toBookEntity)
                .toList();
    }
}
