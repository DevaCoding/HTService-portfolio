package com.springboot2.htservice.web;

import com.springboot2.htservice.service.BoardService;
import com.springboot2.htservice.service.TrainerBoardService;
import com.springboot2.htservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class AdminApiController {

    private final UserService userService;
    private final BoardService boardService;
    private final TrainerBoardService trainerBoardService;


    @DeleteMapping("/api/v1/admin/user/{id}")
    public Long deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return id;
    }
    @DeleteMapping("/api/v1/admin/board/{id}")
    public Long deleteBoard(@PathVariable Long id){
        boardService.delete(id);
        return id;
    }
    @DeleteMapping("/api/v1/admin/trainerboard/{id}")
    public Long deleteTrainerBoard(@PathVariable Long id) {
        trainerBoardService.delete(id);
        return id;
    }

   /* @GetMapping("/api/v1/admin/user/{id}")
    public Long roleUpdate (@PathVariable Long id){
        return userService.roleUpdate(id);
    }*/

    @GetMapping("/api/v1/admin/trainerboard/{id}")
    public Long permitRoleUpdate(@PathVariable Long id){

        return trainerBoardService.permitRoleUpdate(id);
    }

}
