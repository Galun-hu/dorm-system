package com.joy.dorm.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joy.dorm.common.utils.JwtTokenUtils;
import com.joy.dorm.common.utils.RespResult;
import com.joy.dorm.dormitory.dao.impl.AdministratorDaoImpl;
import com.joy.dorm.dormitory.service.impl.BuildingServiceImpl;
import com.joy.dorm.system.model.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;

//登录过滤器
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Admin admin = null;
        try {
            admin = new ObjectMapper().readValue(request.getInputStream(), Admin.class);
            System.out.println("登录的："+admin);
            logger.info("开始登录");
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(admin.getUsername(),admin.getPassword(),null));
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("没有使用json的方式提交："+e.getMessage());
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        StringBuffer stringBuffer = new StringBuffer();
        for (GrantedAuthority authority : authorities) {
            stringBuffer.append(authority.getAuthority()).append(",");
        }
        //获取登录信息
        Admin admin = (Admin) authResult.getPrincipal();
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        admin.setPassword(null); //建议使用
        HashMap<String,Object> map = new HashMap<>();
        String token = JwtTokenUtils.JwtToken(admin.getId(),admin.getUsername(),stringBuffer);
        logger.info("生成用户token："+token);
        map.put("admin",admin);
        map.put("token",token);
        RespResult ok = RespResult.ok("登录成功", map);
        String s = objectMapper.writeValueAsString(ok);
        out.write(s);
        out.flush();
        out.close();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        RespResult respResult = RespResult.error("登录失败");
        if (exception instanceof LockedException) {
            respResult.setMsg("账户被锁定，请联系管理员！");
        } else if (exception instanceof CredentialsExpiredException) {
            respResult.setMsg("密码过期，请联系管理员！");
        } else if (exception instanceof AccountExpiredException) {
            respResult.setMsg("账户过期，请联系管理员！");
        } else if (exception instanceof DisabledException) {
            respResult.setMsg("账户被禁用，请联系管理员！");
        } else if (exception instanceof BadCredentialsException) {
            respResult.setMsg("用户名或者密码输入错误，请重新输入！");
        }
        logger.info("登录失败："+respResult.getMsg());
        ObjectMapper objectMapper = new ObjectMapper();
        out.write(objectMapper.writeValueAsString(respResult));
        out.flush();
        out.close();
    }
}
