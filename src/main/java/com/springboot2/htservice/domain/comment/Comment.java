package com.springboot2.htservice.domain.comment;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springboot2.htservice.domain.BaseTimeEntity;
import com.springboot2.htservice.domain.board.Board;
import com.springboot2.htservice.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name = "comment")
public class Comment extends BaseTimeEntity implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Lob
    private String content;


    public void update(String comment){
        this.content = comment;
    }

    @Builder
    public Comment(Board board, User user, String content){
        this.board = board;
        this.user = user;
        this.content = content;
    }
}
