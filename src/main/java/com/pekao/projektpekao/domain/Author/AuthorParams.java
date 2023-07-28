package com.pekao.projektpekao.domain.Author;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Setter
@Getter
@Builder
public class AuthorParams {

    private static final AtomicLong count = new AtomicLong(10);
    private final Long id;
    private final String firstName;
    private final String lastName;



    public AuthorParams(Long id, String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            throw new IllegalStateException("On of required values is null: [%s]".formatted(List.of(firstName, lastName)));
        }

        this.id = count.incrementAndGet();
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
