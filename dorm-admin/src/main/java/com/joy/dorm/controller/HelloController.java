package com.joy.dorm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "z 测试接口")
@RestController
public class HelloController {

    @ApiOperation("登录就能访问")
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }


    @ApiOperation("需要admin角色")
    @GetMapping("/system/admin/hello")
    public String admin(){
        return "admin";
    }

    @ApiOperation("需要dorm角色")
    @GetMapping("/system/dorm/hello")
    public String dorm(){
        return "dorm";
    }
}
