package com.springboot2.htservice.domain.board;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springboot2.htservice.domain.BaseTimeEntity;
import com.springboot2.htservice.domain.comment.Comment;
import com.springboot2.htservice.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "board")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Board extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "B_ID")
    private Long id;

    @Column(length = 500, nullable = false, name = "B_TITLE")
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false, name = "B_CONTENT")
    private String content;

    @Column(name = "B_BOARDTYPE")
    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @Column(name = "B_VIEWCOUNT")
    private Long viewCount = 0L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "U_ID")
    private User user;

    @OneToMany(mappedBy = "board")
    private List<Comment> comment = new ArrayList<>();

    @Builder
    public Board(String title, String content, BoardType boardType, User user) {
        this.title = title;
        this.content = content;
        this.boardType = boardType;
        this.user = user;
    }

    public void update(String title, String content, BoardType boardType) {
        this.title = title;
        this.content = content;
        this.boardType = boardType;
    }

}
