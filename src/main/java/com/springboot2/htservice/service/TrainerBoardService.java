package com.springboot2.htservice.service;

import com.springboot2.htservice.domain.photo.PhotoRepository;
import com.springboot2.htservice.domain.trainerboard.TrainerBoard;
import com.springboot2.htservice.domain.trainerboard.TrainerBoardRepository;
import com.springboot2.htservice.web.dto.photodto.PhotoSaveRequestDto;
import com.springboot2.htservice.web.dto.trainerboarddto.TrainerBoardListResponseDto;
import com.springboot2.htservice.web.dto.trainerboarddto.TrainerBoardResponseDto;
import com.springboot2.htservice.web.dto.trainerboarddto.TrainerBoardSaveRequestDto;
import com.springboot2.htservice.web.dto.trainerboarddto.TrainerBoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TrainerBoardService {

    private final TrainerBoardRepository trainerBoardRepository;
    private final PhotoService photoService;

    @Transactional
    public Long save(TrainerBoardSaveRequestDto requestDto, List<PhotoSaveRequestDto> requestDtoList) {
        Long id = trainerBoardRepository.save(requestDto.toEntity()).getId();
        TrainerBoard trainerBoard = trainerBoardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해상 사용자 없음" + id));
        if(requestDtoList != null){
            for(PhotoSaveRequestDto photoSaveRequestDto : requestDtoList){
                photoSaveRequestDto.setTrainerBoard(trainerBoard);
                photoService.savePhoto(photoSaveRequestDto);
            }
        }

        return id;
    }

    @Transactional
    public Long update(Long id, TrainerBoardUpdateRequestDto requestDto) {

        TrainerBoard trainerBoard = trainerBoardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해상 사용자 없음" + id));
        /*trainerBoard.permitDown();*/
        trainerBoard.update(requestDto.getThumbnailName(), requestDto.getThumbnailPath(), requestDto.getIntroduce(),
                requestDto.getProgram(), requestDto.getPrice10(),requestDto.getPrice20(), requestDto.getPrice30(), requestDto.getAddress(),
                requestDto.getCategory());

        return id;
    }

    @Transactional
    public void delete(Long id) {
        TrainerBoard trainerBoard = trainerBoardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해상 사용자 없음" + id));
        trainerBoardRepository.delete(trainerBoard);
    }

    @Transactional(readOnly = true)
    public TrainerBoardResponseDto findById(Long id) {
        TrainerBoard trainerBoard = trainerBoardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해상 사용자 없음" + id));

        return new TrainerBoardResponseDto(trainerBoard);
    }

    @Transactional(readOnly = true)
    public List<TrainerBoardListResponseDto> findAll() {
        return trainerBoardRepository.findAll().stream()
                .map(TrainerBoardListResponseDto::new)
                .collect(Collectors.toList());
    }

    public Page<TrainerBoard> findAllByPermitted(Pageable pageable) {
        return trainerBoardRepository.findAllBypermitted(pageable);
    }

    @Transactional
    public Page<TrainerBoard> getTrainerBoardList(Pageable pageable) {
        return trainerBoardRepository.findAll(pageable);
    }


    /*@Transactional(readOnly = true)
    public List<TrainerBoardListResponseDto> findByKeyword(String address1, String address2, String category){
        return trainerBoardRepository.findByKeyword(address1, address2, category).stream()
                .map(TrainerBoardListResponseDto :: new)
                .collect(Collectors.toList());
    }*/

    @Transactional(readOnly = true)
    public List<TrainerBoard> findByAddress(String address, Pageable pageable) {
        System.out.println("여기 오셨나요?1");
        return trainerBoardRepository.findByAddressContainingAndPermitTrue(address, pageable);
    }

    @Transactional(readOnly = true)
    public List<TrainerBoard> findByCategory(String category, Pageable pageable) {
        System.out.println("여기 오셨나요?2");
        return trainerBoardRepository.findByCategoryContainingAndPermitTrue(category, pageable);
    }

    @Transactional(readOnly = true)
    public List<TrainerBoard> findByAddressAndCategory(String address, String category, Pageable pageable) {
        System.out.println("여기 오셨나요?3");
        return trainerBoardRepository.findByAddressContainingAndCategoryContainingAndPermitTrue(address, category, pageable);
    }

    @Transactional
    public boolean getListCheck(Pageable pageable) {
        Page<TrainerBoard> saved = getTrainerBoardList(pageable);
        return saved.hasNext();
    }

    @Transactional
    public Long permitRoleUpdate(Long id) {
        TrainerBoard trainerBoard = trainerBoardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해상 사용자 없음" + id));
        trainerBoard.permitUpdate();
        trainerBoard.getUser().roleUpdate();
        return id;
    }


}
