package com.pekao.projektpekao.controller.Book;

import com.pekao.projektpekao.controller.Author.AuthorDto;
import com.pekao.projektpekao.domain.book.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicLong;

@Getter
@Setter
public class BookDto {
    private static final AtomicLong count = new AtomicLong(0);
    private Long id;
    private String title;
    private AuthorDto authorDto;
//    private List<CommentDto> commentDtoList;
    private Book.Publisher publisher;
    private String bookPhoto;


    protected BookDto() {
    }

    public BookDto(Long id, String title, AuthorDto authorDto, String bookPhoto,
//                   List<CommentDto> commentDtoList,
                   Book.Publisher publisher) {
        this.id = count.incrementAndGet();
        this.title = title;
        this.authorDto = authorDto;
//        this.commentDtoList = commentDtoList;
        this.publisher = publisher;
        this.bookPhoto = bookPhoto;
    }

    public static final class Builder {
        private Long id;
        private String title;
        private AuthorDto authorDto;
        private Book.Publisher publisher;
        private String bookPhoto;

        private Builder() {
        }



        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder authorDto(AuthorDto authorDto) {
            this.authorDto = authorDto;
            return this;
        }

        public Builder publisher(Book.Publisher publisher) {
            this.publisher = publisher;
            return this;
        }

        public Builder bookPhoto(String bookPhoto) {
            this.bookPhoto = bookPhoto;
            return this;
        }

        public BookDto build() {
            BookDto bookDto = new BookDto();
            bookDto.setId(id);
            bookDto.setTitle(title);
            bookDto.setAuthorDto(authorDto);
            bookDto.setPublisher(publisher);
            bookDto.setBookPhoto(bookPhoto);
            return bookDto;
        }
    }
    public static Builder builder() {
        return new Builder();
    }
}

