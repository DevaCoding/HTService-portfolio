package com.springboot2.htservice.web.dto.boarddto;

import com.springboot2.htservice.domain.board.Board;
import com.springboot2.htservice.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private Long viewCount;
    private User user;
    private LocalDateTime createdDate;

    public BoardResponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.viewCount = entity.getViewCount();
        this.user = entity.getUser();
        this.createdDate = entity.getCreatedDate();
    }
}
