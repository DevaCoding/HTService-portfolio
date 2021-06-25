package com.springboot2.htservice.web;


import com.springboot2.htservice.config.auth.LoginUser;
import com.springboot2.htservice.domain.comment.Comment;
import com.springboot2.htservice.domain.user.User;
import com.springboot2.htservice.service.BoardService;
import com.springboot2.htservice.service.CommentService;
import com.springboot2.htservice.web.dto.boarddto.BoardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;

    @GetMapping("/board")
    public String board(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, @LoginUser User user){
        model.addAttribute("boardList", boardService.getBoardList(pageable));
        model.addAttribute("prev",pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next",pageable.next().getPageNumber());
        model.addAttribute("check",boardService.getListCheck(pageable));
        if (user != null) {
            model.addAttribute("userName", user.getName());
            model.addAttribute("userPicture", user.getPicture());
        }

        return "board.html";
    }

    @GetMapping("/board/save")
    public String boardSave(@LoginUser User user, Model model) {
        if (user != null) {
            model.addAttribute("userNickName", user.getNickName());
            model.addAttribute("userName", user.getName());
            model.addAttribute("userPicture", user.getPicture());
        }
        return "board-save";
    }

    @GetMapping("/board/{id}") //@pathVariable: 경로의 특정위치값이 고정되지 않고 달라질때 사용함
    public String boardDetail(@LoginUser User user, @PathVariable Long id, Model model) {
        boardService.updateView(id);
        BoardResponseDto boardResponseDto = boardService.findById(id);
        model.addAttribute("board", boardResponseDto);
        List<Comment> commentList = commentService.getCommentList(id);
        model.addAttribute("commentList",commentList);

        if(user != null) {
            model.addAttribute("userName", user.getName());
            model.addAttribute("userNickName", user.getNickName());
            model.addAttribute("userPicture", user.getPicture());
            model.addAttribute("userId",user.getId());
            model.addAttribute("isUpdate",user.getId().equals(boardResponseDto.getUser().getId()));
        }

        return "board-detail.html";
    }

    @GetMapping("/board/update/{id}")
    public String boardUpdate(@LoginUser User user, @PathVariable Long id, Model model) {
        if(user != null) {
            model.addAttribute("userName", user.getName());
            model.addAttribute("userPicture", user.getPicture());
        }
        BoardResponseDto dto = boardService.findById(id);
        model.addAttribute("board", dto);
        return "board-update";
    }

    @GetMapping("/board/search")
    public String search(String keyword, Model model, @LoginUser User user,
                         @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        if(user != null) {
            model.addAttribute("userName", user.getName());
            model.addAttribute("userPicture", user.getPicture());
        }
        model.addAttribute("searchList", boardService.search(keyword, pageable));
        model.addAttribute("prev",pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next",pageable.next().getPageNumber());
        model.addAttribute("check",boardService.getListCheck(pageable));
        return "board-searchPage";
    }
}
