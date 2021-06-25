package com.springboot2.htservice.domain.trainerboard;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainerBoardRepository extends JpaRepository<TrainerBoard, Long> {

    //findall -> permit이 된 트레이너 보드만 사용자가 볼 수 있는 쿼리문.
    @Query("SELECT t FROM TrainerBoard t where t.permit = true")
    Page<TrainerBoard> findAllBypermitted(Pageable pageable);

    @Query("select t from TrainerBoard t order by t.id DESC")
    List<TrainerBoard> findAllDesc();

    //쿼리문 수정해야 함. permit 된 애들만 보여주고 카테고리/주소로 sort 하는 방식으로 수정해야함
    /*@Query("SELECT t FROM TrainerBoard t where t.address like %:address1% and t.address like %:address2% " +
            "and t.category = :category")
    List<TrainerBoard> findByKeyword(String address1, String address2, String category);*/

    List<TrainerBoard> findByAddressContainingAndCategoryContainingAndPermitTrue(String address, String category, Pageable pageable);

    List<TrainerBoard> findByCategoryContainingAndPermitTrue(String category, Pageable pageable);

    List<TrainerBoard> findByAddressContainingAndPermitTrue(String address, Pageable pageable);

}
