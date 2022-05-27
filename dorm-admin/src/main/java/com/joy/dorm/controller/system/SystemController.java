package com.joy.dorm.controller.system;

import com.joy.dorm.common.utils.RespResult;
import com.joy.dorm.system.model.Admin;
import com.joy.dorm.system.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Admin> getAllAdmin(String keywords){
        return null;
    }

    @ApiOperation("添加管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Admin",value = "Admin类")
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
            @ApiImplicitParam(name = "id",value = "管理员id"),
            @ApiImplicitParam(name = "Admin",value = "Admin类"),
    })
    @PutMapping("/")
    public RespResult getAllAdmin(Admin admin){
        return null;
    }


    @ApiOperation("删除管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "管理员id")
    })
    @DeleteMapping("/{id}")
    public RespResult deleteAdmin(@PathVariable Integer id){
        return null;
    }

}
