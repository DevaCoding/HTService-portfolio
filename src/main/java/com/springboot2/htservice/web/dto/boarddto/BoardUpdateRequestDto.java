package com.springboot2.htservice.web.dto.boarddto;


import com.springboot2.htservice.domain.board.Board;
import com.springboot2.htservice.domain.board.BoardType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardUpdateRequestDto {

    private BoardType boardType;
    private String title;
    private String content;

    @Builder
    public BoardUpdateRequestDto (BoardType boardType, String title,
                                  String content){
        this.boardType = boardType;
        this.title = title;
        this.content = content;
    }

    public Board toEntity() {
        return Board.builder()
                .boardType(boardType)
                .title(title)
                .content(content)
                .build();
    }
}
