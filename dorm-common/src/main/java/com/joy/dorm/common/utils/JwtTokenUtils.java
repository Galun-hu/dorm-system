package com.joy.dorm.common.utils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtTokenUtils {

    //请求头
    public static final String TOKEN_HEADER = "Authorization";
    //令牌前缀
    public static final String TOKEN_PREFIX = "Bearer ";
    //签名密钥
    public static final String SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHOToken";
    //jwt签发者
    public static final String ISS = "joy";
    // 过期时间 单位毫秒 过期时间4小时
    public static final long EXPIRATION = 14400L;


    //创建token
    public static String JwtToken(Integer id,String name,StringBuffer authorities){
        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS512")
                .setSubject(name)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION*1000))
                .claim("id",id)
                .claim("authorities",authorities)
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();
    }

    public static boolean isExpiration(String jwtToken) {
        boolean before=false;
        try {
            before = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwtToken.replace(TOKEN_PREFIX, "")).getBody().getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
        return before;
    }


}
