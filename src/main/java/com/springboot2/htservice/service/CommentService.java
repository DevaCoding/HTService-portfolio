package com.springboot2.htservice.service;

import com.springboot2.htservice.domain.board.Board;
import com.springboot2.htservice.domain.board.BoardRepository;
import com.springboot2.htservice.domain.comment.Comment;
import com.springboot2.htservice.domain.comment.CommentRepository;
import com.springboot2.htservice.domain.user.User;
import com.springboot2.htservice.web.dto.commentdto.CommentListResponseDto;
import com.springboot2.htservice.web.dto.commentdto.CommentSaveRequestDto;
import com.springboot2.htservice.web.dto.commentdto.CommentUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Long save(CommentSaveRequestDto requestDto){
        Board board = boardRepository.findById(requestDto.getBoardId()).orElseThrow(
                ()-> new IllegalArgumentException("해당 사용자 없음"+ requestDto.getBoard().getId()));
        requestDto.setBoard(board);
        return commentRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, CommentUpdateRequestDto requestDto){
        Board board = boardRepository.findById(requestDto.getBoardId()).orElseThrow(
                ()-> new IllegalArgumentException("해당 사용자 없음"+ requestDto.getBoard().getId()));
        requestDto.setBoard(board);
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자 없음 id= " + id));
        comment.update(requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당사용자 없음" + id));
        commentRepository.delete(comment);
    }

    @Transactional
    public List<Comment> getCommentList(Long id){
        return commentRepository.findAllByBoardOrderByIdDesc(id);
    }

    @Transactional
    public List<Comment> getCommentListByUser(Pageable pageable, User user){
        return commentRepository.findAllByUserOrderByIdDesc(user);
    }


}
