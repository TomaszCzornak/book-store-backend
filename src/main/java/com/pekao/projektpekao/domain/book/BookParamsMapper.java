package com.pekao.projektpekao.domain.book;

import com.pekao.projektpekao.controller.Book.BookDto;

import java.util.List;

public class BookParamsMapper {

    private BookParamsMapper() {
    }

    public static BookParams toCreateBookParams(BookDto bookDto) {
        return BookParams.builder()
                .id(bookDto.getId())
                .title(bookDto.getTitle())
                .authorDto(bookDto.getAuthorDto())
//                .commentDtoList(bookDto.getCommentDtoList())
                .publisher(bookDto.getPublisher())
                .bookPhoto(bookDto.getBookPhoto())
                .build();
    }

    public static List<BookParams> toCreateBookParamsList(List<BookDto> bookDtoList) {
        return bookDtoList.stream()
                .map(BookParamsMapper::toCreateBookParams)
                .toList();
    }

}
