package com.joy.dorm.controller.system;

import com.joy.dorm.common.utils.RespResult;
import com.joy.dorm.system.model.Admin;
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
@Api(tags = "管理员信息")
@RequestMapping("/system/admin")
public class SystemController {

    @Autowired
    AdminService adminService;

    @ApiOperation("获取所有管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keywords",value = "关键词")
    })
    @GetMapping("/")
    public List<Admin> getAllAdmin(String keywords, HttpServletRequest request){
        Map<String, Object> map = RequestJwt.getIdByJwtToken(request);
        Integer id = (Integer) map.get("id");
        return adminService.getAllAdmin(keywords,id);
    }

    @ApiOperation("添加管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Admin",value = "Admin类"),
            @ApiImplicitParam(name = "username",value = "账号"),
            @ApiImplicitParam(name = "password",value = "密码"),
            @ApiImplicitParam(name = "name",value = "姓名"),
            @ApiImplicitParam(name = "sex",value = "性别"),
            @ApiImplicitParam(name = "phone",value = "手机号"),
            @ApiImplicitParam(name = "company",value = "学生处、宿舍管理中心，前端这两个直接写死 后端不提供"),
            @ApiImplicitParam(name = "remark",value = "备注"),
            @ApiImplicitParam(name = "enabled",value = "是否启用不用传递 后端自动启用"),
    })
    @PostMapping("/")
    public RespResult addAdmin(@RequestBody Admin admin){
        if (adminService.insert(admin)==1){
            return RespResult.ok("添加管理员成功!");
        }
        return RespResult.error("添加管理员失败!");
    }


    @ApiOperation("修改管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Admin",value = "Admin类"),
            @ApiImplicitParam(name = "id",value = "管理员id"),
            @ApiImplicitParam(name = "name",value = "姓名"),
            @ApiImplicitParam(name = "sex",value = "性别"),
            @ApiImplicitParam(name = "phone",value = "手机号"),
            @ApiImplicitParam(name = "company",value = "学生处、宿舍管理中心，前端这两个直接写死 后端不提供"),
            @ApiImplicitParam(name = "enabled",value = "是否启用 Boolean类型"),
    })
    @PutMapping("/")
    public RespResult updateAdmin(@RequestBody Admin admin){
        if (adminService.update(admin)==1){
            return RespResult.ok("修改管理员信息成功！");
        }
        return RespResult.error("修改管理员信息失败！");
    }


    @ApiOperation("删除管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "管理员id")
    })
    @DeleteMapping("/{id}")
    public RespResult deleteAdmin(@PathVariable Integer id){
        if (adminService.deleteAdmin(id)==1){
            return RespResult.ok("删除管理员成功！");
        }
        return RespResult.error("删除管理员失败！");
    }


    @ApiOperation("修改管理员密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldPassword",value = "旧密码"),
            @ApiImplicitParam(name = "newPassword",value = "新密码"),
            @ApiImplicitParam(name = "id",value = "管理员id"),
    })
    @PutMapping("/pwd")
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
