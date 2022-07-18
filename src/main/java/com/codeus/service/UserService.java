package com.codeus.service;

import com.codeus.config.exception.BadRequestException;
import com.codeus.config.exception.GlobalException;
import com.codeus.config.exception.MethodNotAllowException;
import com.codeus.config.exception.NotFoundException;
import com.codeus.config.jwt.JwtTokenService;
import com.codeus.domain.User;
import com.codeus.dto.user.request.ChangePwd;
import com.codeus.dto.user.request.CreateUser;
import com.codeus.dto.user.request.LoginUser;
import com.codeus.dto.user.request.UpdatePwd;
import com.codeus.dto.user.response.GetUserResponse;
import com.codeus.dto.user.response.LoginUserResponse;
import com.codeus.repository.UserRepository;
import com.codeus.utils.FileUtils;
import com.codeus.utils.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
//    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenService jwtTokenService;
    private final FileUtils fileUtils;


    @Transactional
    public GetUserResponse getUser(String userId){
        String getUserId = jwtTokenService.getIdByToken();
        if(!getUserId.equals(userId))throw new BadRequestException("userId 불일치");
        User user = userRepository.findById(userId).orElseThrow(()->new NotFoundException("id를 찾을 수 없음"));
        return new GetUserResponse(user.getId(),user.getImg());
    }

    @Transactional
    public User createUser(CreateUser createuser){
        User user = createuser.toEntity();
        user.setPwd(SHA256.encrypt(user.getPwd()));
        return userRepository.save(user);
    }

    @Transactional
    public String changePwd(ChangePwd changePwd) {
        User user = userRepository.findById(changePwd.getId()).orElseThrow(()->new NotFoundException("id를 찾을 수 없음"));
        if(!user.getQuestion().equals(changePwd.getQuestion())) throw new NotFoundException("Wrong answer");
        return jwtTokenService.createToken(user.getId());
    }

    @Transactional
    public User updatePwd(String userId, UpdatePwd updatePwd){
        String id = jwtTokenService.getIdByToken();

        if(!userId.equals(id))throw new MethodNotAllowException("잘못된 경로 요청");

        User user = userRepository.findById(id).orElseThrow(()->new NotFoundException("id를 찾을 수 없음"));
        user.setId(userId);

        if(updatePwd.getNewPwd()==null&&updatePwd.getPwd()==null) throw new MethodNotAllowException("잘못된 요청");

        String oldPwd = SHA256.encrypt(updatePwd.getPwd());
        String newPwd = SHA256.encrypt(updatePwd.getNewPwd());

        if(user.getPwd().equals(oldPwd)){
            user.setPwd(newPwd);
            userRepository.save(user);
            return user;
        }else{
            throw new BadRequestException("비밀번호가 일치하지 않음");
        }

    }
    public User updateUserImg(String userId, MultipartHttpServletRequest file) throws IOException {
        String id = jwtTokenService.getIdByToken();
        if(!userId.equals(id))throw new MethodNotAllowException("잘못된 경로 요청");
        User user = userRepository.findById(id).orElseThrow(()->new NotFoundException("id를 찾을 수 없음"));
        String url = fileUtils.parseFileInfo("user",id,file);
        user.setImg(url);
        userRepository.save(user);
        return user;
    }

    @Transactional
    public void deleteUser(){
        String userId = jwtTokenService.getIdByToken();
        User user = userRepository.findById(userId).orElseThrow(()->new NotFoundException("id를 찾을 수 없음"));
        userRepository.delete(user);
    }


    @Transactional
    public List<User> findAll(){//추가함
        List<User> users  = userRepository.findAll();
        return users;
    }

    public LoginUserResponse loginUser(LoginUser loginUser) throws Throwable {
        User user = userRepository.findById(loginUser.getId()).orElseThrow(()-> new NotFoundException("id를 찾을 수 없음"));
        if(!SHA256.encrypt(loginUser.getPwd()).equals(user.getPwd())){
            throw new NotFoundException("password가 일치하지 않음");
        }
        String token = jwtTokenService.createToken(user.getId());
        //TODO : DB에서 regDate update => Expiration time이 적용 안됨
        return new LoginUserResponse(token,jwtTokenService.regDate());

    }

    public Long checkId(String userId) {
        Long checkId = userRepository.countById(userId).orElseThrow(()-> new GlobalException("sql Exception"));
        System.out.println(checkId);
        return checkId;
    }



}
