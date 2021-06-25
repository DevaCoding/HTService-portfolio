package com.springboot2.htservice.web.dto.boarddto;

import com.springboot2.htservice.domain.board.Board;
import com.springboot2.htservice.domain.board.BoardType;
import com.springboot2.htservice.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardSaveRequestDto {
    private BoardType boardType;
    private String title;
    private User user;
    private String content;

    public void setUser(User user) {
        this.user = user;
    }

    @Builder
    public BoardSaveRequestDto(BoardType boardType, String title, User user, String content) {
        this.boardType = boardType;
        this.title = title;
        this.user = user;
        this.content = content;
    }

    public Board toEntity() {
        return Board.builder()
                .boardType(BoardType.FREE)
                .title(title)
                .user(user)
                .content(content)
                .build();
    }
}