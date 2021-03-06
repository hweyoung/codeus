package com.codeus.controller;

import com.codeus.domain.User;
import com.codeus.dto.JsonResponse;
import com.codeus.dto.user.request.*;
import com.codeus.dto.user.response.ChangePwdResponse;
import com.codeus.dto.user.response.GetUserResponse;
import com.codeus.dto.user.response.LoginUserResponse;
import com.codeus.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;



    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<JsonResponse> createUser(@RequestBody CreateUser createUser){
        User user = userService.createUser(createUser);
        return ResponseEntity.ok(new JsonResponse("createUser","success",user));
    }

    @GetMapping("/admin") //추가함
    public ResponseEntity<List<User>> getUserList() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{userId}")
    @ResponseBody
    public ResponseEntity<JsonResponse> getUser(@PathVariable String userId){
        GetUserResponse response = userService.getUser(userId);
        return ResponseEntity.ok(new JsonResponse("getUser","success",response));
    }

    @PostMapping("/changePwd")
    public ResponseEntity<JsonResponse> changePwd(@RequestBody ChangePwd changePwd){
        String token = userService.changePwd(changePwd);
        ChangePwdResponse response = new ChangePwdResponse(changePwd.getId(), token);
        return ResponseEntity.ok(new JsonResponse("changePwd","success", response));
    }

    //password, question
    @PutMapping ("/{userId}")
    public ResponseEntity<JsonResponse> updateUser(@PathVariable String userId,@RequestBody UpdateUser updateUser)throws IOException {
        userService.updateUser(userId, updateUser);
        return ResponseEntity.ok(new JsonResponse("updatePwd","success",null));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<JsonResponse> updateUserImg(@PathVariable String userId, MultipartHttpServletRequest file)throws IOException {
        userService.updateUserImg(userId, file);
        return ResponseEntity.ok(new JsonResponse("updateUserImg","success",null));
    }



    @DeleteMapping("")
    public ResponseEntity<JsonResponse> deleteUser(){
        userService.deleteUser();
        return ResponseEntity.ok(new JsonResponse("deleteUser","success",null));
    }

    @PostMapping("/checkId")
    public ResponseEntity<JsonResponse> checkId(@RequestBody CheckId checkId){
        Long countId = userService.checkId(checkId.getId());
        return ResponseEntity.ok(new JsonResponse("checkId","success", countId));
    }

    @PostMapping("/login")
    public ResponseEntity<JsonResponse> loginUser(@RequestBody LoginUser loginUser) throws Throwable {
        LoginUserResponse response = userService.loginUser(loginUser);
        return ResponseEntity.ok(new JsonResponse("loginUser","success",response));
    }

}
