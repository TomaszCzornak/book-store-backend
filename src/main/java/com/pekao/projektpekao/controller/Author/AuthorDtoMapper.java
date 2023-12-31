package com.pekao.projektpekao.controller.Author;

import com.pekao.projektpekao.domain.Author.Author;

import java.util.List;

public class AuthorDtoMapper {
    private AuthorDtoMapper(){}

    public static AuthorDto toAuthorDto(Author author) {
        return AuthorDto.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .authorPhotoUrl(author.getAuthorPhotoUrl())
                .build();
    }

    public static List<AuthorDto> toAuthorDtoList(List<Author> authorList) {
        return authorList.stream()
                .map(AuthorDtoMapper::toAuthorDto)
                .toList();
    }
}
