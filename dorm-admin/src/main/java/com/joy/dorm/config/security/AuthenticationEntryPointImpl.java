package com.joy.dorm.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joy.dorm.common.utils.RespResult;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//处理未登录或者未携带token
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(401);
        PrintWriter out = response.getWriter();
        RespResult build = RespResult.build();
        build.setStatus(401);
        build.setMsg("无效凭证，访问失败");
        build.setObj(null);
        if (authException instanceof InsufficientAuthenticationException) {
            build.setMsg("未授权，访问失败！");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        out.write(objectMapper.writeValueAsString(build));
        out.flush();
        out.close();
    }
}
