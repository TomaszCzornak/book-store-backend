package com.pekao.projektpekao;

import com.pekao.projektpekao.controller.Author.AuthorDtoMapper;
import com.pekao.projektpekao.controller.Comments.CommentDto;
import com.pekao.projektpekao.controller.Users.UserDtoMapper;
import com.pekao.projektpekao.domain.Author.Author;
import com.pekao.projektpekao.domain.User.User;
import com.pekao.projektpekao.domain.book.Book;
import com.pekao.projektpekao.domain.book.BookParams;

import java.util.List;

public class BookTestUtility {

    public static BookParams createBookParamsWithPublisher(Book.Publisher publisher) {
        return BookParams.builder()
                .title("Spring w Akcji")
                .publisher(publisher)
                .build();
    }

    public static BookParams createBookWithTitleAndAuthor(String title, Author author) {
        return BookParams.builder()
                .title(title)
                .authorDto(AuthorDtoMapper.toAuthorDto(author))
                .publisher(Book.Publisher.AGORA)
                .build();
    }

    public static Book createBookWithPublisher(Book.Publisher publisher) {
        return Book.builder()
                .title("Spring w Akcji")
                .publisher(publisher)
                .build();
    }

    public static BookParams createBookWithTitleAuthorAndPublisherAndCommentsAndUser(String title, Author author, Book.Publisher publisher) {
        return BookParams.builder()
                .title(title)
                .authorDto(AuthorDtoMapper.toAuthorDto(author))
                .publisher(publisher)
                .commentDtoList(
                        List.of(CommentDto.builder()
                                        .content("Testowy Komentarz 1")
                                        .userDto(UserDtoMapper.toUserDto(User.builder().firstName("Ziomek").lastName("Poziomek").build()))
                                        .build(),
                                CommentDto.builder()
                                        .content("Testowy Komentarz 2")
                                        .userDto(UserDtoMapper.toUserDto(User.builder().firstName("Marian").lastName("Kowalski").build()))
                                        .build()))
                .build();


    }

}
