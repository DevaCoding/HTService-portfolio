package com.springboot2.htservice.web.dto.commentdto;

import com.springboot2.htservice.domain.board.Board;
import com.springboot2.htservice.domain.user.User;
import lombok.Getter;

@Getter
public class CommentListResponseDto {

    private Board board;
    private User user;
    private String content;

    public CommentListResponseDto(Board board, User user, String content) {
        this.board = board;
        this.user = user;
        this.content = content;
    }
}
