package com.pekao.projektpekao.domain.Author;

import com.pekao.projektpekao.controller.Author.AuthorDto;

import java.util.List;

public class AuthorParamsMapper {

        private AuthorParamsMapper() {
        }

        public static AuthorParams toAuthorParams(AuthorDto authorDto) {
            return AuthorParams.builder()
                    .id(authorDto.getId())
                    .firstName(authorDto.getFirstName())
                    .lastName(authorDto.getLastName())
                    .build();
        }

        public static List<AuthorParams> toCreateAuthorParamsList(List<AuthorDto> authorDtoList) {
            return authorDtoList.stream()
                    .map(AuthorParamsMapper::toAuthorParams)
                    .toList();
        }
}
