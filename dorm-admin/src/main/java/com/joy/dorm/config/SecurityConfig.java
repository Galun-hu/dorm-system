package com.joy.dorm.config;

import com.joy.dorm.config.security.AccessDeniedHandlerImpl;
import com.joy.dorm.config.security.AuthenticationEntryPointImpl;
import com.joy.dorm.config.security.JWTAuthenticationFilter;
import com.joy.dorm.config.security.JWTAuthorizationFilter;
import com.joy.dorm.system.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AdminService adminService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminService);
    }

    //角色继承admin也能访问dorm的权限

    @Bean
    RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_admin > ROLE_dorm";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/swagger-ui.html")
                .antMatchers("/v2/**")
                .antMatchers("/webjars/**")
                .antMatchers("/swagger-resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //权限处理
                .antMatchers("/system/admin/**").hasRole("admin")
                .antMatchers("/system/dorm/**").hasRole("dorm")
                .antMatchers(HttpMethod.POST,"/login").permitAll()
                .antMatchers(HttpMethod.GET,"/login").authenticated()
                .anyRequest().authenticated()
                .and()
                //登录过滤器
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                //token认证过滤器
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                //关闭session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //关闭跨域保护
                .cors().and().csrf().disable()
                .exceptionHandling()
                //权限失败处理
                .accessDeniedHandler(new AccessDeniedHandlerImpl())
                //登录异常处理器
                .authenticationEntryPoint(new AuthenticationEntryPointImpl());
    }

}
