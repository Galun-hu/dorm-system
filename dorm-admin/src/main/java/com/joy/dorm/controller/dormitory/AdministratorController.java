package com.joy.dorm.controller.dormitory;

import com.joy.dorm.common.utils.RespResult;
import com.joy.dorm.dormitory.model.Administrator;
import com.joy.dorm.dormitory.service.IAdministratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "宿舍管理员信息")
@RestController
@RequestMapping("/system/admin/administrator/")
public class AdministratorController {

    @Autowired
    private IAdministratorService administratorService;

    @ApiOperation("获取所有宿舍管理员信息")
    @GetMapping("/all")
    public RespResult getAllAdministrator(){
        List<Administrator> administrators = administratorService.getDormAdmins();
        if (administrators == null || administrators.size() == 0){
            return RespResult.ok("成功",administrators);
        }else {
            return RespResult.error("获取失败");
        }
    }


//    @ApiOperation("为宿舍楼添加管理员")
//    @PostMapping("/add/{building_id}/{admin_id}")
//    public RespResult addDormAdminInBuilding(@PathVariable int building_id,@PathVariable int admin_id){
//
//    }

}
