package com.springboot2.htservice.web.dto.trainerboarddto;

import com.springboot2.htservice.domain.photo.Photo;
import com.springboot2.htservice.domain.trainerboard.TrainerBoard;
import com.springboot2.htservice.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class TrainerBoardResponseDto {

    private Long id;
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
    private boolean permit;

    public TrainerBoardResponseDto(TrainerBoard entity) {
        this.id = entity.getId();
        this.user = entity.getUser();
        this.thumbnailName = entity.getThumbnailName();
        this.thumbnailPath = entity.getThumbnailPath();
        this.introduce = entity.getIntroduce();
        this.program = entity.getProgram();
        this.price10 = entity.getPrice10();
        this.price20 = entity.getPrice20();
        this.price30 = entity.getPrice30();
        this.address = entity.getAddress();
        this.category = entity.getCategory();
        this.permit = entity.isPermit();

    }
}
