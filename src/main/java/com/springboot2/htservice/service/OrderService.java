package com.springboot2.htservice.service;


import com.springboot2.htservice.domain.order.Order;
import com.springboot2.htservice.domain.order.OrderRepository;
import com.springboot2.htservice.domain.trainerboard.TrainerBoard;
import com.springboot2.htservice.domain.trainerboard.TrainerBoardRepository;
import com.springboot2.htservice.domain.user.User;
import com.springboot2.htservice.web.dto.orderdto.OrderSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final TrainerBoardRepository trainerBoardRepository;

    @Transactional
    public Long save(Long id, OrderSaveRequestDto requestDto){
        TrainerBoard trainerBoard = trainerBoardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자 없음 id= " + id));
        requestDto.setTrainerBoard(trainerBoard);
        return orderRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Page<Order> getOrderListByUser(Pageable pageable, User user){
        return orderRepository.findAllByUserOrderByIdDesc(user.getId(), pageable);
    }
}
