package com.springboot2.htservice.web;

import com.springboot2.htservice.config.auth.LoginUser;
import com.springboot2.htservice.domain.user.User;
import com.springboot2.htservice.service.PhotoService;
import com.springboot2.htservice.service.TrainerBoardService;
import com.springboot2.htservice.web.dto.photodto.PhotoResponseDto;
import com.springboot2.htservice.web.dto.trainerboarddto.TrainerBoardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class TrainerBoardController {

    private final TrainerBoardService trainerBoardService;
    private final PhotoService photoService;

    @GetMapping("/trainerboard")
    public String trainerboard(Model model, @LoginUser User user, @PageableDefault(size = 12, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        if (user != null) {
            model.addAttribute("userName", user.getName());
            model.addAttribute("userPicture", user.getPicture());
        }
        model.addAttribute("trainerboard", trainerBoardService.findAllByPermitted(pageable));
        model.addAttribute("prev", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("check", trainerBoardService.getListCheck(pageable));
        return "trainerboard";
    }



    @GetMapping("/trainerboard/save")
    public String trainerboardSave(@LoginUser User user, Model model) {
        if (user != null) {
            model.addAttribute("userName", user.getName());
            model.addAttribute("userPicture", user.getPicture());
            return "trainerboard-save";
        }
        else{
            return "redirect:/login";

        }

    }

    @GetMapping("/trainerboard/{id}")
    public String trainerboardDetail(@PathVariable Long id, @LoginUser User user, Model model) {
        TrainerBoardResponseDto trainerBoardResponseDto = trainerBoardService.findById(id);
        model.addAttribute("trainerboard", trainerBoardResponseDto);

        List<PhotoResponseDto> photoResponseDtoList = photoService.getPhotosByTrainerBoardId(id);
        model.addAttribute("photoList", photoResponseDtoList);

        if (user != null) {
            model.addAttribute("userName", user.getName());
            model.addAttribute("userPicture", user.getPicture());
            model.addAttribute("isSame", user.getId().equals(trainerBoardResponseDto.getUser().getId()));
        }

        return "trainerboard-detail";
    }

    @GetMapping("/trainerboard/update/{id}")
    public String trainerboardUpdate(@PathVariable Long id, @LoginUser User user, Model model) {
        if (user != null) {
            model.addAttribute("userName", user.getName());
            model.addAttribute("userPicture", user.getPicture());
        }
        TrainerBoardResponseDto dto = trainerBoardService.findById(id);
        model.addAttribute("trainerboard", dto);
        return "trainerboard-update";
    }

    @GetMapping("/trainerboard/{id}/order")
    public String trainerboardOrder(@PathVariable Long id, @LoginUser User user, Model model){
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("userPicture", user.getPicture());
        }
        TrainerBoardResponseDto dto = trainerBoardService.findById(id);
        model.addAttribute("trainerboard", dto);
        return "trainerboard-order";
    }

    @GetMapping("/trainerboard/search")
    public String search(Model model, @LoginUser User user, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam(required = false) String address1,
                         @RequestParam(required = false) String address2,
                         @RequestParam(required = false) String category) {
        String address = address1 + " " + address2;
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        if (address != null && category.equals("전체")) {
            model.addAttribute("trainerboard-searchList", trainerBoardService.findByAddress(address, pageable));
        } else if (category != null && address.equals("전체 ")) {
            model.addAttribute("trainerboard-searchList", trainerBoardService.findByCategory(category, pageable));
        } else {
            model.addAttribute("trainerboard-searchList", trainerBoardService.findByAddressAndCategory(address, category, pageable));
        }
        model.addAttribute("prev", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("check", trainerBoardService.getListCheck(pageable));


        return "trainerboard-searchPage";
    }

}
