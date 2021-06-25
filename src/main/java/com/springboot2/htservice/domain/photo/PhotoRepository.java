package com.springboot2.htservice.domain.photo;

import com.springboot2.htservice.web.dto.photodto.PhotoResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    @Query("select p from Photo p where p.trainerBoard.id = :id")
    List<PhotoResponseDto> getPhotosByTrainerBoardId(@Param("id")Long id);
}
