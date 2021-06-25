package com.springboot2.htservice.web;

import com.springboot2.htservice.config.auth.LoginUser;
import com.springboot2.htservice.domain.user.Role;
import com.springboot2.htservice.domain.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(@LoginUser User user, Model model) {
        if(user != null) {
            model.addAttribute("userName", user.getName());
            model.addAttribute("userPicture", user.getPicture());
        }
        return "index";
    }
    @GetMapping("/order")
    public String order(@LoginUser User user, Model model) {
        if(user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "trainerboard-order";
    }
}
