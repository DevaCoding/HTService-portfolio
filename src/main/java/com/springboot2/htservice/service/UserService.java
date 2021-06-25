package com.springboot2.htservice.service;

import com.springboot2.htservice.domain.user.User;
import com.springboot2.htservice.domain.user.UserRepository;
import com.springboot2.htservice.web.dto.userdto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;


    @Transactional
    public Page<User> getUserList(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Transactional
    public User getUser(Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 id가 존재하지 않습니다 id=" + id));
    }

    @Transactional
    public Long update(Long id, UserUpdateRequestDto requestDto){
        User user = userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 id가 존재하지 않습니다 id=" + id));
        user.update(requestDto.getNickName());

        return id;
    }

    @Transactional
    public void delete(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 id가 존재하지 않습니다 id=" + id));
        userRepository.delete(user);
    }

    @Transactional
    public Long roleUpdate(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 id가 존재하지 않습니다 id=" + id));
        user.roleUpdate();
        return id;
    }

    @Transactional
    public boolean getListCheck(Pageable pageable){
        Page<User> saved = getUserList(pageable);
        return saved.hasNext();
    }

}
