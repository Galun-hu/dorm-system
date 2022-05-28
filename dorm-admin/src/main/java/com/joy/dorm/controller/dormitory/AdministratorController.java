package com.joy.dorm.controller.dormitory;

import com.joy.dorm.common.utils.RespResult;
import com.joy.dorm.dormitory.model.Administrator;
import com.joy.dorm.dormitory.model.BuildingAdmin;
import com.joy.dorm.dormitory.service.IAdministratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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


    @ApiOperation("为宿舍楼添加管理员")
    @ApiImplicitParams({@ApiImplicitParam(name = "buildingAdmin",value = "宿舍楼管理员关联实体类")})
    @PostMapping("/add")
    public RespResult addDormAdminToBuilding(@RequestBody BuildingAdmin buildingAdmin){
        if (buildingAdmin.getBuilding_id() != null && buildingAdmin.getAdmin_id() != null){
            Integer result = administratorService.insertDormAdminToBuilding(buildingAdmin
                    .getBuilding_id(),buildingAdmin.getAdmin_id());
            if (result > 0){
                return RespResult.ok("添加成功");
            }else {return RespResult.error("添加失败");}
        }else {return RespResult.error("添加失败，缺少building_id或admin_id");}

    }



//    @ApiOperation("为宿舍楼移除管理员")
//    @PostMapping("/delete/{admin_id}")
//    public RespResult deleteDormAdmin(@PathVariable int admin_id){
//        administratorService.re
//    }

}
