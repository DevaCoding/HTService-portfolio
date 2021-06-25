package com.springboot2.htservice.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springboot2.htservice.domain.BaseTimeEntity;
import com.springboot2.htservice.domain.board.Board;
import com.springboot2.htservice.domain.order.Order;
import com.springboot2.htservice.domain.trainerboard.TrainerBoard;
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
@Table(name = "user")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "U_ID")
    private Long id;

    @Column(nullable = false, name = "U_NAME")
    private String name;

    @Column(name = "U_NICKNAME")
    private String nickName;

    @Column(nullable = false, name = "U_EMAIL")
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // trainer, user, admin

    @OneToMany(mappedBy = "user")
    private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Order> orderList = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private TrainerBoard trainerBoard;

    @Builder
    public User(String name, String nickName, String email, String picture, Role role, List<Board> boardList, List<Order> orderList, TrainerBoard trainerBoard) {
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.boardList = boardList;
        this.orderList = orderList;
        this.trainerBoard = trainerBoard;
    }


    public User update(String nickName) {
        this.nickName = nickName;

        return this;
    }

    public User saveOrUpdate(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    public void roleUpdate() {
        this.role = Role.TRAINER;
    }

}
