package com.springboot2.htservice.web.dto.orderdto;

import com.springboot2.htservice.domain.order.Order;
import com.springboot2.htservice.domain.trainerboard.TrainerBoard;
import com.springboot2.htservice.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderSaveRequestDto {
    private User user;
    private TrainerBoard trainerBoard;
    private String price;

    public void setUser(User user){
        this.user = user;
    }

    public void setTrainerBoard(TrainerBoard trainerBoard){
        this.trainerBoard = trainerBoard;
    }

    @Builder
    public OrderSaveRequestDto(User user, TrainerBoard trainerBoard, String price) {
        this.user = user;
        this.trainerBoard = trainerBoard;
        this.price = price;
    }

    public Order toEntity(){
        return Order.builder()
                .user(user)
                .trainerBoard(trainerBoard)
                .price(price)
                .build();
    }
}
