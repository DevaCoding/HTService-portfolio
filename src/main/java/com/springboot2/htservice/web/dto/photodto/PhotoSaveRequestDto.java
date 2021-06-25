package com.springboot2.htservice.web.dto.photodto;

import com.springboot2.htservice.domain.photo.Photo;
import com.springboot2.htservice.domain.trainerboard.TrainerBoard;
import lombok.Builder;

public class PhotoSaveRequestDto {

    private String origPhotoName;
    private String photoPath;
    private TrainerBoard trainerBoard;

    public void setTrainerBoard(TrainerBoard trainerBoard) {
        this.trainerBoard = trainerBoard;
    }

    @Builder
    public PhotoSaveRequestDto(String origPhotoName, String photoPath) {
        this.origPhotoName = origPhotoName;
        this.photoPath = photoPath;
    }

    public Photo toEntity() {
        return Photo.builder()
                .origPhotoName(origPhotoName)
                .photoPath(photoPath)
                .trainerBoard(trainerBoard)
                .build();
    }
}
