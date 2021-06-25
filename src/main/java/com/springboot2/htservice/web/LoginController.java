package com.springboot2.htservice.web;


import com.springboot2.htservice.config.auth.LoginUser;
import com.springboot2.htservice.domain.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@LoginUser User user, Model model) {
        if(user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "login";
    }
}
