package com.joy.dorm.utils;

import com.joy.dorm.common.utils.JwtTokenUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class RequestJwt {

    public static Map<String,Object> getIdByJwtToken(HttpServletRequest request){
        String token = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        Claims claims = Jwts.parser().setSigningKey(JwtTokenUtils.SECRET).parseClaimsJws(token.replace(JwtTokenUtils.TOKEN_PREFIX,"")).getBody();
        Map<String,Object> map = new HashMap<>();
        map.put("id",(Integer)claims.get("id"));
        map.put("name",claims.getSubject());
        String role = (String)claims.get("authorities");
        map.put("role",role.replace(",",""));
        return map;
    }

}
