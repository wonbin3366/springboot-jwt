package shop.mtcoding.jwtstudy.config.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JwtProvider {

    private static final String SUBJECT = "jwtstudy";
    private static final int EXP = 1000*60*60;
    private static final String TOKEN_PREFIX = "Bearer "; // 스페이스 필요함
    private static final String HEADER = "Authorization";
    private static final String SECRET = "메타코딩";

    public static String create(){
        String jwt = JWT.create()
                .withSubject("토큰제목")
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*24*7))
                .withClaim("id", 1)
                .withClaim("role", "guest")
                .sign(Algorithm.HMAC512("메타코딩"));
        return jwt;
    }

    public static DecodedJWT verify(String jwt) throws SignatureVerificationException, TokenExpiredException{

        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512("메타코딩"))
                    .build().verify(jwt);
//        int id = decodedJWT.getClaim("id").asInt();String role = decodedJWT.getClaim("role").asString();
//        System.out.println(id);
//        System.out.println(role);
        return decodedJWT;
    }
}
