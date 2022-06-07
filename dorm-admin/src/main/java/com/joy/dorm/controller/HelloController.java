package com.joy.dorm.controller;

import com.joy.dorm.dormitory.model.Building;
import com.joy.dorm.dormitory.service.IBuildingService;
import com.joy.dorm.utils.DormitoryTool;
import com.joy.dorm.utils.RequestJwt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Api(tags = "z 测试接口")
@RestController
public class HelloController {

    @Autowired
    DormitoryTool dormitoryTool;

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

    @ApiOperation("楼栋信息 参数管理员id")
    @GetMapping("/dorm/building")
    public Building dorm(HttpServletRequest request){
        Map<String, Object> map = RequestJwt.getIdByJwtToken(request);
        Integer id = (Integer) map.get("id");
        return dormitoryTool.getBuildWithAdminId(id);
    }
}
