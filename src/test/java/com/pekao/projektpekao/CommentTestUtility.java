package com.pekao.projektpekao;

import com.pekao.projektpekao.domain.Comment.Comment;
import com.pekao.projektpekao.domain.User.User;
import com.pekao.projektpekao.domain.book.Book;

public class CommentTestUtility {

    public static Comment createComment() {
        return Comment.builder()
                .content("Komentarz do Książki")
                .buildNewEntity();

    }
    public static Comment createCommentWithBookAndUser(Book book, User user) {
        return Comment.builder()
                .content("Komnentarz do testu")
                .book(book)
                .user(user)
                .build()
        ;

    }


}
