package com.springboot2.htservice.service;

import com.springboot2.htservice.domain.board.Board;
import com.springboot2.htservice.domain.board.BoardRepository;
import com.springboot2.htservice.domain.user.User;
import com.springboot2.htservice.web.dto.boarddto.BoardResponseDto;
import com.springboot2.htservice.web.dto.boarddto.BoardSaveRequestDto;
import com.springboot2.htservice.web.dto.boarddto.BoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardSaveRequestDto requestDto) {
        return boardRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, BoardUpdateRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자 없음 id= " + id));
        board.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getBoardType());

        return id;
    }

    @Transactional
    public void delete(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자 없음 id= " + id));
        boardRepository.delete(board);
    }

    @Transactional(readOnly = true)
    public BoardResponseDto findById(long id) {
        Board entity = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자 없음 id= " + id));
        return new BoardResponseDto(entity);
    }


    @Transactional
    public void updateView(Long id) {
        boardRepository.updateView(id);
    }

    @Transactional
    public Page<Board> getBoardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional
    public Page<Board> getBoardListByUser(Pageable pageable, User user) {
        return boardRepository.findAllByUserOrderByIdDesc(user.getId(), pageable);
    }

    @Transactional
    public boolean getListCheck(Pageable pageable) {
        Page<Board> saved = getBoardList(pageable);
        return saved.hasNext();
    }

    /* @Transactional
    public LocalDateTime getDate(Pageable pageable) {
        Page<Board> saved = getBoardList(pageable);
        saved.
     }*/

    @Transactional
    public List<Board> search(String keyword, Pageable pageable) {
        return boardRepository.findByTitleContaining(keyword, pageable);
    }
}
