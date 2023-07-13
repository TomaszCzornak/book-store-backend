package com.pekao.projektpekao.controller.Book;

import com.pekao.projektpekao.controller.Author.AuthorDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
@Getter
@Setter
public class BookDto {

    private Long id;
    private String title;
    private AuthorDto authorDto;
//    private List<CommentDto> commentDtoList;
//    private Book.Publisher publisher;
    private String bookPhoto;


    protected BookDto() {
    }

    public BookDto(Long id, String title, AuthorDto authorDto, String bookPhoto) {
//                   List<CommentDto> commentDtoList, Book.Publisher publisher
        this.id = id;
        this.title = title;
        this.authorDto = authorDto;
//        this.commentDtoList = commentDtoList;
//        this.publisher = publisher;
        this.bookPhoto = bookPhoto;
    }
}

