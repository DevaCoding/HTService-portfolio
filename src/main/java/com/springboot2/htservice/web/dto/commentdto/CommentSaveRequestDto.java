package com.springboot2.htservice.web.dto.commentdto;

import com.springboot2.htservice.domain.board.Board;
import com.springboot2.htservice.domain.comment.Comment;
import com.springboot2.htservice.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentSaveRequestDto {

    private Long boardId;
    private Board board;
    private User user;
    private String content;

    public void setUser(User user){
        this.user = user;
    }
    public void setBoard(Board board){
        this.board = board;
    }

    @Builder
    public CommentSaveRequestDto(Board board, User user, String content){
        this.board = board;
        this.user = user;
        this.content = content;
    }

    public Comment toEntity() {
        return Comment.builder()
                .board(board)
                .user(user)
                .content(content)
                .build();
    }
}
