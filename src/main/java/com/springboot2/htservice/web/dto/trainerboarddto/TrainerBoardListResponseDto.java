package com.springboot2.htservice.web.dto.trainerboarddto;

import com.springboot2.htservice.domain.trainerboard.TrainerBoard;
import com.springboot2.htservice.domain.user.User;
import lombok.Getter;

@Getter
public class TrainerBoardListResponseDto {

    private String thumbnailName;
    private String thumbnailPath;
    private String address;
    private User user;
    private String category;

    public TrainerBoardListResponseDto(TrainerBoard entity) {
        this.thumbnailName= entity.getThumbnailName();
        this.thumbnailPath = entity.getThumbnailPath();
        this.address = entity.getAddress();
        this.user = entity.getUser();
        this.category = entity.getCategory();

    }
}
