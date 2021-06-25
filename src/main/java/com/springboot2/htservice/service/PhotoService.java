package com.springboot2.htservice.service;

import com.springboot2.htservice.domain.photo.Photo;
import com.springboot2.htservice.domain.photo.PhotoRepository;
import com.springboot2.htservice.web.dto.photodto.PhotoResponseDto;
import com.springboot2.htservice.web.dto.photodto.PhotoSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PhotoService {

    private final PhotoRepository photoRepository;

    @Transactional
    public Long savePhoto(PhotoSaveRequestDto requestDto){
        return photoRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public List<PhotoResponseDto> getPhotosByTrainerBoardId(Long id){
//        Photo entity = photoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자 없음 id= " + id));
        return photoRepository.getPhotosByTrainerBoardId(id);
    }

}
