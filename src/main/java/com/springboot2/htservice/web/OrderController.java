package com.springboot2.htservice.web;

import com.springboot2.htservice.config.auth.LoginUser;
import com.springboot2.htservice.domain.user.User;
import com.springboot2.htservice.service.TrainerBoardService;
import com.springboot2.htservice.web.dto.trainerboarddto.TrainerBoardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class OrderController {
    private final TrainerBoardService trainerBoardService;

    @GetMapping("/order/{id}")
    public String order(@LoginUser User user, Model model, @PathVariable Long id){
        if (user !=null) {
            model.addAttribute("userName", user.getName());
        }
        TrainerBoardResponseDto dto = trainerBoardService.findById(id);
        model.addAttribute("trainerboard",dto);
        return "trainerboard-order";
    }

}
