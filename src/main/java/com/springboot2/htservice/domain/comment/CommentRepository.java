package com.springboot2.htservice.domain.comment;

import com.springboot2.htservice.domain.board.Board;
import com.springboot2.htservice.domain.user.User;
import com.springboot2.htservice.web.dto.commentdto.CommentListResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c where c.board.id = :id order by c.id desc")
    List<Comment> findAllByBoardOrderByIdDesc(@Param("id") Long id); // 특정 게시글에 해당하는 댓글들 가져오기

    List<Comment> findAllByUserOrderByIdDesc(User user); // 특정 유저가 작성한 댓글 모음 가져오기
}
