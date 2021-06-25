package com.springboot2.htservice.web;


import com.springboot2.htservice.config.auth.LoginUser;
import com.springboot2.htservice.domain.user.User;
import com.springboot2.htservice.service.OrderService;
import com.springboot2.htservice.web.dto.orderdto.OrderSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrderApiController {
    private final OrderService orderService;

    @PostMapping("/api/v1/order/{id}")
    public Long save(@RequestBody OrderSaveRequestDto requestDto, @LoginUser User user, @PathVariable Long id){
        requestDto.setUser(user);
        return orderService.save(id, requestDto);
    }
}
