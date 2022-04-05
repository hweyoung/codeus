package com.codeus.service;

import com.codeus.domain.User;
import com.codeus.dto.request.CreateUser;
import com.codeus.dto.request.UpdateUser;
import com.codeus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User getUser(Long userSeq){
        User user = userRepository.findById(userSeq).orElseThrow(()->new IllegalArgumentException("user를 찾을 수 없습니다."));
        return user;
    }

    @Transactional
    public User createUser(CreateUser createuser){
//        User userDetail = User.builder().id(user.getId()).pwd(user.getPwd()).build();
        User userDetail = createuser.toEntity();
        User newUser = userRepository.save(userDetail);
        return newUser;
    }

    @Transactional
    public User updateUser(Long userSeq, UpdateUser updateUser){
        User user = userRepository.findById(userSeq).orElseThrow(()->new IllegalArgumentException("user를 찾을 수 없습니다."));
        user.setId(updateUser.getId());
        user.setPwd(updateUser.getPwd());
        return user;
    }

    @Transactional
    public void deleteUser(Long userSeq){
        User user = userRepository.findById(userSeq).orElseThrow(()->new IllegalArgumentException("user를 찾을 수 없습니다."));
        userRepository.delete(user);
    }


    @Transactional
    public List<User> findAll(){//추가함
        List<User> users  = userRepository.findAll();
        return users;
    }
}
