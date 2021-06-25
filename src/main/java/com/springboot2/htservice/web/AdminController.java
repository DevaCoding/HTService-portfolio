package com.springboot2.htservice.web;

import com.springboot2.htservice.config.auth.LoginUser;
import com.springboot2.htservice.domain.user.Role;
import com.springboot2.htservice.domain.user.User;
import com.springboot2.htservice.service.BoardService;
import com.springboot2.htservice.service.PhotoService;
import com.springboot2.htservice.service.TrainerBoardService;
import com.springboot2.htservice.service.UserService;
import com.springboot2.htservice.web.dto.photodto.PhotoResponseDto;
import com.springboot2.htservice.web.dto.trainerboarddto.TrainerBoardResponseDto;
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
public class AdminController {

    private final UserService userService;
    private final BoardService boardService;
    private final TrainerBoardService trainerBoardService;
    private final PhotoService photoService;

    @GetMapping("/admin/user")
    public String getUserList(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, Model model, @LoginUser User user) {
        if (user.getRole() == Role.ADMIN) {
            model.addAttribute("userList", userService.getUserList(pageable));
            model.addAttribute("prev", pageable.previousOrFirst().getPageNumber());
            model.addAttribute("next", pageable.next().getPageNumber());
            model.addAttribute("check", boardService.getListCheck(pageable));
            return "admin-user";
        } else {
            return "redirect:/";
        }
    }


    @GetMapping("/admin")
    public String admin(@LoginUser User user) {
        if(user.getRole()==Role.ADMIN) {
            return "admin";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/admin/board")
    public String board(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, @LoginUser User user) {
        if(user.getRole() == Role.ADMIN) {
            model.addAttribute("board", boardService.getBoardList(pageable));
            model.addAttribute("prev", pageable.previousOrFirst().getPageNumber());
            model.addAttribute("next", pageable.next().getPageNumber());
            model.addAttribute("check", boardService.getListCheck(pageable));
            return "admin-board";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/admin/trainerboard")
    public String trainerBoard(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, @LoginUser User user) {
        if(user.getRole() == Role.ADMIN) {
            model.addAttribute("trainerboard", trainerBoardService.getTrainerBoardList(pageable));
            model.addAttribute("prev", pageable.previousOrFirst().getPageNumber());
            model.addAttribute("next", pageable.next().getPageNumber());
            model.addAttribute("check", trainerBoardService.getListCheck(pageable));
            return "admin-trainerboard";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/admin/trainerboard/{id}")
    public String trainerBoardDetail(@PathVariable Long id, Model model, @LoginUser User user) {
        if (user.getRole() == Role.ADMIN) {
            TrainerBoardResponseDto dto = trainerBoardService.findById(id);
            model.addAttribute("trainerboard", dto);
            List<PhotoResponseDto> photoResponseDtoList = photoService.getPhotosByTrainerBoardId(id);
            model.addAttribute("photoList", photoResponseDtoList);
            return "admin-trainerboard-detail";
        } else {
            return "redirect:/";
        }
    }
}
