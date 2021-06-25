package com.springboot2.htservice.config.auth.dto;

import com.springboot2.htservice.domain.board.Board;
import com.springboot2.htservice.domain.order.Order;
import com.springboot2.htservice.domain.trainerboard.TrainerBoard;
import com.springboot2.htservice.domain.user.Role;
import com.springboot2.htservice.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String nickName;
    private String email;
    private String picture;
    private List<Board> boardList;
    private List<Order> orderList;
    private TrainerBoard trainerBoard;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String nickName, String email, String picture
            , List<Board> boardList, List<Order> orderList, TrainerBoard trainerBoard) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.nickName = name;
        this.email = email;
        this.picture = picture;
        this.boardList = boardList;
        this.orderList = orderList;
        this.trainerBoard = trainerBoard;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
        if("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }


    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes){
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .nickName((String) attributes.get("nickName"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity(){
        return User.builder()
                .name(name)
                .nickName(nickName)
                .email(email)
                .picture(picture)
                .boardList(boardList)
                .orderList(orderList)
                .trainerBoard(trainerBoard)
                .role(Role.USER)
                .build();
    }
}