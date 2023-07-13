package com.pekao.projektpekao.controller.Comments;

import com.pekao.projektpekao.controller.Book.BookForCommentEntityMapper;
import com.pekao.projektpekao.controller.Users.UserEntityMapper;
import com.pekao.projektpekao.domain.Comment.Comment;

import java.util.List;
import java.util.stream.Collectors;

public final class CommentEntityMapper {

    private CommentEntityMapper() {}

    public static Comment toCommentEntity(com.pekao.projektpekao.controller.Comments.CommentDto commentDto) {
        return Comment.builder()
                .id(commentDto.getId())
                .content(commentDto.getContent())
                .user(UserEntityMapper.toUserEntity(commentDto.getUserDto()))
                .book(BookForCommentEntityMapper.toBookEntity(commentDto.getBookForCommentDto()))
                .build();
    }

    public static List<Comment> toCommentListEntity(List<com.pekao.projektpekao.controller.Comments.CommentDto> commentList) {
        return commentList.stream()
                .map(CommentEntityMapper::toCommentEntity)
                .collect(Collectors.toList());
    }

}
