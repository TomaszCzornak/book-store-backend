package com.pekao.projektpekao.controller.Book;

import com.pekao.projektpekao.domain.book.Book;

import java.util.List;

public class BookAuthorDtoMapper {

    private BookAuthorDtoMapper(){

    }
    public static BookAuthorDto toBookAuthorDto(Book book) {
        return BookAuthorDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .bookPhoto(book.getBookPhoto())
                .build();
    }

    public static List<BookAuthorDto> toBookAuthorDtoList(List<Book> bookAuthorDtoList) {
        return bookAuthorDtoList.stream()
                .map(BookAuthorDtoMapper::toBookAuthorDto)
                .toList();
    }
}
