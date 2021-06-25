package com.springboot2.htservice.web.dto.trainerboarddto;

import com.springboot2.htservice.domain.photo.Photo;
import com.springboot2.htservice.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class TrainerBoardUpdateRequestDto {
    private User user;
    private String thumbnailName;
    private String thumbnailPath;
    private String introduce;
    private String program;
    private String price10;
    private String price20;
    private String price30;
    private String address;
    private String category;

    @Builder
    public TrainerBoardUpdateRequestDto(User user, String thumbnailName, String thumbnailPath, String introduce, String program,
                                        String price10, String price20, String price30, String address, String category) {
        this.user = user;
        this.thumbnailName = thumbnailName;
        this.thumbnailPath = thumbnailPath;
        this.introduce = introduce;
        this.program = program;
        this.price10 = price10;
        this.price20 = price20;
        this.price30 = price30;
        this.address = address;
        this.category = category;
    }
}
