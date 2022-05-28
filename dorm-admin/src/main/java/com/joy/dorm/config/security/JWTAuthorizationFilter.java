package com.joy.dorm.config.security;

import com.joy.dorm.common.utils.JwtTokenUtils;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//验证令牌过滤器
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public static final Logger logger =  LoggerFactory.getLogger(JWTAuthorizationFilter.class);

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            String jwtToken = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
            if (jwtToken==null){
                logger.warn("未携带令牌");
                chain.doFilter(request,response);
                return;
            }
            if (JwtTokenUtils.isExpiration(jwtToken)){
                logger.warn("令牌已失效");
                chain.doFilter(request,response);
                return;
            }
            jwtToken = jwtToken.replace(JwtTokenUtils.TOKEN_PREFIX,"");
            Claims claims = Jwts.parser().setSigningKey(JwtTokenUtils.SECRET).parseClaimsJws(jwtToken).getBody();
            if (claims==null){
                logger.warn("令牌解析异常");
                chain.doFilter(request,response);
                return;
            }
            logger.info("令牌解析成功");

            String username = claims.getSubject();
            Integer id = (Integer) claims.get("id");
            logger.info("当前用户角色信息："+(String) claims.get("authorities")+"  _id："+id);
            List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(token);
            super.doFilterInternal(request,response,chain);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("解析token出现错误"+e.getMessage());
            chain.doFilter(request,response);
        }
    }
}
