package com.codeus.controller;

import com.codeus.domain.User;
import com.codeus.dto.request.CreateUser;
import com.codeus.dto.request.UpdateUser;
import com.codeus.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;



    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/{userSeq}")
    @ResponseBody
    public ResponseEntity<User> getUser(@PathVariable long userSeq){
        User user = userService.getUser(userSeq);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/{userSeq}")
    @ResponseBody
    public ResponseEntity<User> createUser(@RequestBody CreateUser createUser){
        User newUser = userService.createUser(createUser);
        return ResponseEntity.ok(newUser);
    }

    @PutMapping("/{userSeq}")
    @ResponseBody
    public ResponseEntity<User> updateUser(@PathVariable Long userSeq, @RequestBody UpdateUser updateUser){
        User newUser = userService.updateUser(userSeq,updateUser);
        return ResponseEntity.ok(newUser);
    }

    @DeleteMapping("/{userSeq}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userSeq){
        userService.deleteUser(userSeq);
        return ResponseEntity.ok("유저가 삭제되었습니다.");
    }

}
