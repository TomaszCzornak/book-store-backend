package com.pekao.projektpekao.domain.User;

import com.pekao.projektpekao.controller.Users.UserDto;

import java.time.LocalDate;
import java.util.List;

public class UserParamsMapper {

    private UserParamsMapper() {
    }

    public static UserParams toUserParams(UserDto userDto) {
        return UserParams.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .createdAt(LocalDate.now().toString())
                .build();
    }

    public static List<UserParams> toUserParamsList(List<UserDto> userDtoList) {
        return userDtoList.stream()
                .map(UserParamsMapper::toUserParams)
                .toList();
    }

}
