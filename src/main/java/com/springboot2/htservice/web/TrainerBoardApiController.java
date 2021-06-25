package com.springboot2.htservice.web;

import com.springboot2.htservice.config.auth.LoginUser;
import com.springboot2.htservice.domain.photo.Photo;
import com.springboot2.htservice.domain.user.User;
import com.springboot2.htservice.service.S3Uploader;
import com.springboot2.htservice.service.TrainerBoardService;
import com.springboot2.htservice.web.dto.photodto.PhotoSaveRequestDto;
import com.springboot2.htservice.web.dto.trainerboarddto.TrainerBoardSaveRequestDto;
import com.springboot2.htservice.web.dto.trainerboarddto.TrainerBoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class TrainerBoardApiController {

    private final TrainerBoardService trainerBoardService;
    private final S3Uploader s3Uploader;

    @PostMapping("/api/v1/trainerboard")
    public Long save(@LoginUser User user, @RequestPart(value = "key") TrainerBoardSaveRequestDto requestDto,
                     @RequestPart(value = "files") List<MultipartFile> files,
                     @RequestPart(value = "thumbnail") MultipartFile thumbNail) throws IOException {
        if (thumbNail != null) {
            String uploadThumbnailUrl = s3Uploader.upload(thumbNail,"thumbnail");
            requestDto.addThumbnailPath(uploadThumbnailUrl);
        }
        List<PhotoSaveRequestDto> requestDtoList = null;
        if (files != null) {
            requestDtoList = new ArrayList<>();
            for (MultipartFile file : files) {
                String uploadPhotoUrl = s3Uploader.upload(file, "photo");
                PhotoSaveRequestDto photoSaveRequestDto = new PhotoSaveRequestDto(file.getOriginalFilename(), uploadPhotoUrl);
                requestDtoList.add(photoSaveRequestDto);
            }
        }

        requestDto.setUser(user);


        return trainerBoardService.save(requestDto, requestDtoList);
    }

    @PutMapping("/api/v1/trainerboard/{id}")
    public Long update(@PathVariable Long id, TrainerBoardUpdateRequestDto requestDto) {
        return trainerBoardService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/trainerboard/{id}")
    public Long delete(@PathVariable Long id) {
        trainerBoardService.delete(id);
        return id;
    }


}
