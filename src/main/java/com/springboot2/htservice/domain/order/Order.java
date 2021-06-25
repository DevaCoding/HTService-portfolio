package com.springboot2.htservice.domain.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springboot2.htservice.domain.BaseTimeEntity;
import com.springboot2.htservice.domain.trainerboard.TrainerBoard;
import com.springboot2.htservice.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "orderlist")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="O_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="U_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="T_ID")
    private TrainerBoard trainerBoard;

    @Column(name = "O_PRICE")
    private String price;

    @Builder
    public Order(Long id, User user, TrainerBoard trainerBoard, String price) {
        this.id = id;
        this.user = user;
        this.trainerBoard = trainerBoard;
        this.price = price;
    }
}
