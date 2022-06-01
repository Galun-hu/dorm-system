package com.joy.dorm.controller.admin;

import com.joy.dorm.common.utils.RespResult;
import com.joy.dorm.dormitory.model.Administrator;
import com.joy.dorm.dormitory.model.Building;
import com.joy.dorm.dormitory.service.IAdministratorService;
import com.joy.dorm.dormitory.service.IBuildingService;
import com.joy.dorm.system.service.AdminService;
import com.joy.dorm.utils.RequestJwt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "h 修改管理员密码 ------宿舍/系统管理员")
@RequestMapping("/update/password")
public class AdminSetPwdController {

    @Autowired
    AdminService adminService;

    @Autowired
    IAdministratorService administratorService;

    @Autowired
    IBuildingService buildingService;

    @ApiOperation("修改管理员密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldPassword",value = "旧密码"),
            @ApiImplicitParam(name = "newPassword",value = "新密码"),
    })
    @PutMapping("/")
    public RespResult updatePassword(@RequestBody Map<String,Object> map, HttpServletRequest request){
        String oldPassword = (String) map.get("oldPassword");
        String newPassword = (String) map.get("newPassword");
        Map<String, Object> map2 = RequestJwt.getIdByJwtToken(request);
        Integer id = (Integer) map2.get("id");
        if (adminService.updatePassWord(oldPassword,newPassword,id)){
            return RespResult.ok("修改管理员密码成功！");
        }
        return RespResult.error("修改管理员密码失败！");
    }



}
