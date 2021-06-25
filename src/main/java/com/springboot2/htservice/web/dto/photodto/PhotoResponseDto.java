package com.springboot2.htservice.web.dto.photodto;

import com.springboot2.htservice.domain.photo.Photo;
import com.springboot2.htservice.domain.trainerboard.TrainerBoard;

public class PhotoResponseDto {

    private Long id;
    private String origPhotoName;
    private String photoPath;
    private TrainerBoard trainerBoard;

    public PhotoResponseDto(Photo entity) {
        this.id = entity.getId();
        this.origPhotoName = entity.getOrigPhotoName();
        this.photoPath = entity.getPhotoPath();
        this.trainerBoard = entity.getTrainerBoard();
    }
}
