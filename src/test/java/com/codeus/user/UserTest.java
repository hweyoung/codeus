package com.codeus.user;

import com.codeus.domain.User;
import com.codeus.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class UserTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void BaseTimeEntity_등록(){
        LocalDateTime now = LocalDateTime.of(2019, 12, 6, 0, 0, 0);
        User user = User.builder().id("user1").pwd("1234").question("1 질문").build();
        userRepository.save(user);

        List<User> userList = userRepository.findAll();

        System.out.println("userList"+userList.get(0));
    }
}
