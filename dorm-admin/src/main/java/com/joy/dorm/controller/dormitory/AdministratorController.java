package com.joy.dorm.controller.dormitory;

import com.joy.dorm.common.utils.RespPage;
import com.joy.dorm.common.utils.RespResult;
import com.joy.dorm.dormitory.model.Administrator;
import com.joy.dorm.dormitory.model.BuildingAdmin;
import com.joy.dorm.dormitory.service.IAdministratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "b 宿舍管理员信息 ------系统管理员")
@RestController
@RequestMapping("/system/admin/administrator/")
public class AdministratorController {

    @Autowired
    private IAdministratorService administratorService;

    @ApiOperation("获取所有宿舍管理员信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "id",value = "宿舍楼id"),
            @ApiImplicitParam(name = "keywords",value = "搜索关键词"),
            @ApiImplicitParam(name = "pageNum",value = "第几页（默认第1页）"),
            @ApiImplicitParam(name = "pagheSize",value = "一页多少条数据（默认10条）")})
    @GetMapping("/")
    public RespPage getAllAdministrator(@RequestParam(defaultValue = "") String keywords,
                                        @RequestParam(defaultValue = "1") int pageNum,
                                        @RequestParam(defaultValue = "10") int pageSize){
        int pageNumNew = pageNum-1;
        if (pageNumNew < 0){
            pageNumNew = 0;
        }
        List<Administrator> administrators = administratorService.getDormAdmins(keywords,pageNumNew,pageSize);
        RespPage page = new RespPage();
        if (administrators.size() == 1){
            page.setTotal(Long.valueOf(1));
        }else {
            Long count = administratorService.getAdministratorsCount(keywords);
            page.setTotal(count);
        }
        page.setData(administrators);
        return page;
    }


//    @ApiOperation("根据宿舍管理员名字获取宿舍管理员信息")
//    @ApiImplicitParams({@ApiImplicitParam(name = "name",value = "管理员名字")})
//    @GetMapping("/name/{name}")
//    public RespResult getAdminWithName(@PathVariable String name){
//        Administrator administrator = administratorService.getDormAdminWithName(name);
//        if (administrator != null){
//            return RespResult.ok("查询成功",administrator);
//        }else {
//            return RespResult.error("查询失败");
//        }
//    }
//
//    @ApiOperation("根据宿舍管理员id获取宿舍管理员信息")
//    @ApiImplicitParams({@ApiImplicitParam(name = "admin_id",value = "管理员id")})
//    @GetMapping("/{admin_id}")
//    public RespResult getDormAdminWithId(@PathVariable Integer admin_id){
//        Administrator administrator = administratorService.getDromAdminWithId(admin_id);
//        if (administrator != null){
//            return RespResult.ok("查询成功",administrator);
//        }else {
//            return RespResult.error("查询失败");
//
//        }
//    }


    @ApiOperation("为宿舍楼添加管理员")
    @ApiImplicitParams({@ApiImplicitParam(name = "buildingAdmin",value = "宿舍楼管理员关联实体类")})
    @PostMapping("/")
    public RespResult addDormAdminToBuilding(@RequestBody BuildingAdmin buildingAdmin){
        if (buildingAdmin.getBuilding_id() != null && buildingAdmin.getAdmin_id() != null){
            Integer result = administratorService.insertDormAdminToBuilding(buildingAdmin
                    .getBuilding_id(),buildingAdmin.getAdmin_id());
            if (result > 0){
                return RespResult.ok("添加成功");
            }else {return RespResult.error("添加失败");}
        }else {return RespResult.error("添加失败，缺少building_id或admin_id");}

    }



    @ApiOperation("带admin_id参数表示移除指定管理员，带building_id参数参数表示移除指定宿舍楼的所有管理员")
    @ApiImplicitParams({@ApiImplicitParam(name = "admin_id",value = "管理员id"),
                        @ApiImplicitParam(name = "building_id",value = "宿舍楼id")})
    @DeleteMapping("/")
    public RespResult deleteDormAdmin(@RequestParam(defaultValue = "") Integer admin_id,
                                      @RequestParam(defaultValue = "") Integer building_id){
        long result = -1;
        if (building_id != null){
            result = administratorService.removeAllDormAdminToBuilding(building_id);
        }else if (admin_id != null){
            result = administratorService.removeDormAdminToBuilding(admin_id);
        }
        if (result > 0){
            return RespResult.ok("移除成功");
        }else {
            return RespResult.error("一移除失败");
        }
    }



//    @ApiOperation("为宿舍楼移除所有管理员")
//    @PostMapping("/delete/all/{building_id}")
//    public RespResult deleteAllDormAdmin(@PathVariable int building_id){
//        long result = administratorService.removeAllDormAdminToBuilding(building_id);
//        if (result > 0){
//            return RespResult.ok("移除成功");
//        }else {return RespResult.error("移除失败");}
//    }

}
