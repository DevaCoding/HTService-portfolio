package com.springboot2.htservice.web.dto.userdto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String name;
    private String nickName;
    private String picture;

    public UserUpdateRequestDto(String name, String nickName, String picture) {
        this.name = name;
        this.nickName = nickName;
        this.picture = picture;
    }
}
