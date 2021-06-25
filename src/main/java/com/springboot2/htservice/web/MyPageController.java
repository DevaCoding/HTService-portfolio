package com.springboot2.htservice.web;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.springboot2.htservice.config.auth.LoginUser;
import com.springboot2.htservice.domain.user.User;
import com.springboot2.htservice.service.BoardService;
import com.springboot2.htservice.service.CommentService;
import com.springboot2.htservice.service.OrderService;
import com.springboot2.htservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MyPageController {

    private final UserService userService;
    private final BoardService boardService;
    private final OrderService orderService;
    private final CommentService commentService;

    @GetMapping("/mypage/info")
    public String myPage(Model model, @LoginUser User user){
        if (user != null) {
            model.addAttribute("userInfo", userService.getUser(user.getId()));
        }
        return "mypage-info"; //프론트페이지에서 바로 update로 갈 수 있게 url 설정 해 두기
    }

    @GetMapping("/mypage/myboard")
    public String myPageBoard (Model model, @LoginUser User user,
                               @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        if (user != null) {
            model.addAttribute("userName", user.getName());
            model.addAttribute("userPicture", user.getPicture());
        }
        model.addAttribute("boardList",boardService.getBoardListByUser(pageable,user));
        model.addAttribute("prev",pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next",pageable.next().getPageNumber());
        model.addAttribute("check",boardService.getListCheck(pageable));
        return "mypage-myboard";
    }

    @GetMapping("/mypage/order")
    public String myOrder(Model model, @LoginUser User user,
                          @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        if (user != null) {
            model.addAttribute("userInfo", userService.getUser(user.getId()));
        }
        model.addAttribute("orderList", orderService.getOrderListByUser(pageable,user));

        return "mypage-order";
    }

    @GetMapping("/mypage/mycomment")
    public String myComment(Model model, @LoginUser User user,
                           @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        if (user != null) {
            model.addAttribute("userInfo", userService.getUser(user.getId()));
        }
        model.addAttribute("commentList", commentService.getCommentListByUser(pageable, user));

        return "mypage-mycomment";
    }

}
