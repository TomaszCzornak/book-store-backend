package com.pekao.projektpekao.domain.book;

import com.pekao.projektpekao.controller.Author.AuthorDto;
import com.pekao.projektpekao.controller.Comments.CommentDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Builder
@Setter
@Getter
public class BookParams {
    private static final AtomicLong count = new AtomicLong(10);
    private final Long id;
    private final String title;
    private final AuthorDto authorDto;
//    private final List<CommentDto> commentDtoList;
    private final Book.Publisher publisher;
    private final String bookPhoto;

    public BookParams(Long id, String title, AuthorDto authorDto,
//                      List<CommentDto> commentDtoList,
                      Book.Publisher publisher, String bookPhoto) {
        this.id = count.incrementAndGet();;
        this.title = title;
        this.authorDto = authorDto;
//        this.commentDtoList = commentDtoList;
        this.publisher = publisher;
        this.bookPhoto = bookPhoto;
    }


}
