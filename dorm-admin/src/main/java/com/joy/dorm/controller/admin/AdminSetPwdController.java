package com.joy.dorm.controller.admin;

import com.joy.dorm.common.utils.RespResult;
import com.joy.dorm.system.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Api(tags = "h 修改管理员密码 ------宿舍/系统管理员")
@RequestMapping("/update/password")
public class AdminSetPwdController {

    @Autowired
    AdminService adminService;

    @ApiOperation("修改管理员密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldPassword",value = "旧密码"),
            @ApiImplicitParam(name = "newPassword",value = "新密码"),
            @ApiImplicitParam(name = "id",value = "管理员id"),
    })
    @PutMapping("/")
    public RespResult updatePassword(@RequestBody Map<String,Object> map){
        String oldPassword = (String) map.get("oldPassword");
        String newPassword = (String) map.get("newPassword");
        Integer id = (Integer) map.get("id");
        if (adminService.updatePassWord(oldPassword,newPassword,id)){
            return RespResult.ok("修改管理员密码成功！");
        }
        return RespResult.error("修改管理员密码失败！");
    }

}
