package com.springboot2.htservice.web;

import com.springboot2.htservice.config.auth.LoginUser;
import com.springboot2.htservice.domain.user.User;
import com.springboot2.htservice.service.CommentService;
import com.springboot2.htservice.web.dto.commentdto.CommentSaveRequestDto;
import com.springboot2.htservice.web.dto.commentdto.CommentUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/api/v1/comment")
    public Long save(@RequestBody CommentSaveRequestDto requestDto, @LoginUser User user){
        requestDto.setUser(user);
        return commentService.save(requestDto);
    }

    @PutMapping("/api/v1/comment/{commentId}")
    public Long update(@PathVariable Long commentId, @RequestBody CommentUpdateRequestDto requestDto, @LoginUser User user){
        requestDto.setUser(user);
        return  commentService.update(commentId,requestDto);
    }

    @DeleteMapping("/api/v1/comment/{id}")
    public Long delete(@PathVariable Long id){
        commentService.delete(id);
        return id;
    }
}
