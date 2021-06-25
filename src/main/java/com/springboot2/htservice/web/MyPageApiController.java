package com.springboot2.htservice.web;

import com.springboot2.htservice.config.auth.LoginUser;
import com.springboot2.htservice.domain.user.User;
import com.springboot2.htservice.service.UserService;
import com.springboot2.htservice.web.dto.userdto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MyPageApiController {
    private final UserService userService;

    @PutMapping("/api/v1/userUpdate")
    public Long update(@LoginUser User user, @RequestBody UserUpdateRequestDto requestDto) {
        return userService.update(user.getId(),requestDto); //프론트페이지에서 바로 update로 갈 수 있게 url 설정 해 두기
    }


}
