package com.springboot2.htservice.domain.board;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Modifying
    @Query("update Board b set b.viewCount = b.viewCount +1 where b.id= :id")
    void updateView(@Param("id") Long id);

    @Query("select b from Board b where b.user.id = :id order by b.id desc")
    Page<Board> findAllByUserOrderByIdDesc(@Param("id") Long id, Pageable pageable);

    List<Board> findByTitleContaining(String keyword, Pageable pageable);
}
