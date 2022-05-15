package com.codeus.config.jwt;

import com.codeus.config.exception.MethodNotAllowException;
import com.codeus.config.exception.TokenExpiredException;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenService {
    // jwt를 생성하고 검증하는 컴포넌트
    // jwt에는 토큰 만료시간이나 회원 권한 정보등을 저장할 수 있다.
    private String secretKey= "codeus";
    private int EXPIRATION_TIME=24*60*60*1000;

    //객체 초기화, secretKey를 Base64로 인코딩한다.
    @PostConstruct
    protected void init(){
        secretKey= Base64.getEncoder().encodeToString(secretKey.getBytes());
    }


    public String createToken(String userId){
        Claims claims = Jwts.claims().setSubject(userId);   //Jwt payload에 저장되는 정보 단위
        claims.put("role","ROLE_USER");  //권한설정 key/value 쌍으로 저장된다.
        Date now = new Date();
        String acToken = Jwts.builder()
                .setClaims(claims)  //정보 저장
                .setIssuedAt(now)   //토큰 발행시간 정보
                .setExpiration(new Date(now.getTime()+EXPIRATION_TIME))  //setExpire Time
                .signWith(SignatureAlgorithm.HS256, secretKey)  //사용할 암호화 알고리즘과, signature에 들어갈 secret값 세팅
                .compact();
        return acToken;
    }

    //토큰에서 회원 정보 추출
    public String getUserPk(String token){
        try{
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        }catch (NullPointerException e){
            log.info(e.getMessage());
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        }
    }

    //Request의 Header에서 token값을 가져옵니다. "X-AUTH-TOKEN":"TOKEN값" =>refresh token은 DB에 저장
    public String resolveToken(){
        try{
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            return request.getHeader("X-AUTH-TOKEN");
        }catch (Exception e){
            throw new MethodNotAllowException("can not resolve Token");
        }
    }

    //토큰의 유효성 + 만료 일자 확인
    public boolean validateToken(String jwtToken){
        try{
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        }catch (SecurityException e) {
            log.info("Invalid JWT signature.");
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token.");
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token. at validation Token");
            throw new TokenExpiredException("token Expired");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
        }catch (Exception e) {
            return false;
        }
        return false;
    }

    public String getIdByToken(){
        String token = resolveToken();
        if(validateToken(token))
            return getUserPk(token);
        else throw new TokenExpiredException("token Expired");
    }

    public String regDate(){
        LocalDateTime now = LocalDateTime.now().plusNanos(EXPIRATION_TIME*100000);
        log.info(now+"");
        String formatNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return formatNow;
    }

}
