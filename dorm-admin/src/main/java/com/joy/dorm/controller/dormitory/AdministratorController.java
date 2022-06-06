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
@RequestMapping("/system/admin/administrator")
public class AdministratorController {

    @Autowired
    private IAdministratorService administratorService;

    @ApiOperation("获取所有宿舍管理员信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "building_id",value = "宿舍楼id"),
            @ApiImplicitParam(name = "keywords",value = "搜索关键词"),
            @ApiImplicitParam(name = "pageNum",value = "第几页（默认第1页）"),
            @ApiImplicitParam(name = "pagheSize",value = "一页多少条数据（默认10条）")})
    @GetMapping("/")
    public RespPage getAllAdministrator(@RequestParam(defaultValue = "") Integer building_id,
                                        @RequestParam(defaultValue = "") String keywords,
                                        @RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10") Integer pageSize){
        int pageNumNew = pageNum-1;
        if (pageNumNew < 0){
            pageNumNew = 0;
        }

        RespPage page = new RespPage();
//        if (building_id != null){
//            List<Administrator> administrators = administratorService.getDormAdminsWithBuildingId(keywords,building_id,pageNumNew,pageSize);
//            Long count = administratorService.getAdministratorsCount(keywords,building_id);
//            page.setTotal(count);
//            page.setData(administrators);
//            return page;
//        }else {
//            List<Administrator> administrators = administratorService.getDormAdmins(keywords,building_id,pageNumNew,pageSize);
//            Long count = administratorService.getAdministratorsCount(keywords,building_id);
//            page.setTotal(count);
//            page.setData(administrators);
//            return page;
//        }
        List<Administrator> administrators = administratorService.getDormAdminsWithBuildingId(keywords,building_id,pageNumNew,pageSize);
//        List<Administrator> administrators = administratorService.getDormAdmins(keywords,building_id,pageNumNew,pageSize);
        Long count = administratorService.getAdministratorsCount(keywords,building_id);
        page.setTotal(count);
        page.setData(administrators);
        return page;
//        page.setData(administrators);
//        return page;
    }




    @ApiOperation("获取所有宿舍管理员名字和id")
    @GetMapping("/name")
    public RespResult getAllAdministratorNames(){
        List<Administrator> administrators = administratorService.getNames();
        if (administrators.size() > 0){
            return RespResult.ok("成功",administrators);
        }else {
            return RespResult.error("失败");
        }
    }


//    @ApiOperation("根据宿舍管理员名字获取宿舍管理员信息")
//    @ApiImplicitParams({@ApiImplicitParam(name = "name",va
//    lue = "管理员名字")})
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


    @ApiOperation("为宿舍楼添加管理员并绑定宿舍楼id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Administrator",value = "Administrator类 接口文档Models有对应该类描述"),
            @ApiImplicitParam(name = "username",value = "账号"),
            @ApiImplicitParam(name = "password",value = "密码"),
            @ApiImplicitParam(name = "name",value = "姓名"),
            @ApiImplicitParam(name = "sex",value = "性别"),
            @ApiImplicitParam(name = "phone",value = "手机号"),
            @ApiImplicitParam(name = "company",value = "学生处、宿舍管理中心，前端这两个直接写死 后端不提供"),
            @ApiImplicitParam(name = "remark",value = "备注"),
            @ApiImplicitParam(name = "enabled",value = "是否启用不用传递 后端自动启用"),
            @ApiImplicitParam(name = "building_id",value = "宿舍楼id")
    })
    @PostMapping("/")
    public RespResult addDormAdminToBuilding(@RequestBody Administrator administrator){
        if (administrator.getBuilding_id() != null) {
            Integer result = administratorService.insertDormAdminToBuilding(administrator);
            if (result > 0){
                return RespResult.ok("成功");
            }else if(result == -2){
                return RespResult.error("失败，用户名已被使用");
            }else {return RespResult.error("失败");}
        }else {return RespResult.error("失败，缺少building_id");}

    }



    @ApiOperation("修改宿舍管理员信息或修改管理员绑定的宿舍楼id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Administrator",value = "Administrator类 接口文档Models有对应该类描述"),
            @ApiImplicitParam(name = "username",value = "账号"),
            @ApiImplicitParam(name = "password",value = "密码"),
            @ApiImplicitParam(name = "name",value = "姓名"),
            @ApiImplicitParam(name = "sex",value = "性别"),
            @ApiImplicitParam(name = "phone",value = "手机号"),
            @ApiImplicitParam(name = "company",value = "学生处、宿舍管理中心，前端这两个直接写死 后端不提供"),
            @ApiImplicitParam(name = "remark",value = "备注"),
            @ApiImplicitParam(name = "enabled",value = "是否启用不用传递 后端自动启用"),
            @ApiImplicitParam(name = "building_id",value = "宿舍楼id")
    })
    @PutMapping("/")
    public RespResult update(@RequestBody Administrator administrator){
        Long result = administratorService.updateDormAdminToBuilding(administrator);
        System.out.println("\n\n\n\n\n\n\n"+result+"\n\n\n\n\n");
        if (result == Long.valueOf(-1)){
            return RespResult.error("失败，缺少管理员id");
        }else if (result == Long.valueOf(-2)){
            return RespResult.error("修改绑定的宿舍失败");
        }else if (result == Long.valueOf(-3)){
            return RespResult.error("修改绑定的宿舍成功，修改管理员信息失败");
        }else if (result > Long.valueOf(0)){
            return RespResult.ok("成功");
        }else {
            return RespResult.error("失败");
        }
    }





    @ApiOperation("带id参数删除指定管理员并解除绑定宿舍id")
    @ApiImplicitParams({@ApiImplicitParam(name = "admin_id",value = "管理员id")})
//                        @ApiImplicitParam(name = "building_id",value = "宿舍楼id")})
    @DeleteMapping("/")
    public RespResult deleteDormAdmin(@RequestParam(defaultValue = "") Integer id){
//                                      @RequestParam(defaultValue = "") Integer building_id){
        long result = -1;
//        if (building_id != null){
//            result = administratorService.removeAllDormAdminToBuilding(building_id);
//        }else if (admin_id != null){
        if (id != null){
            result = administratorService.removeDormAdminToBuilding(id);
        }
        if (result > 0){
            return RespResult.ok("成功");
        }
        else if (result == -1){
            return RespResult.error("删除管理员失败");
        }else {
            return RespResult.error("接除管理员绑定的宿舍楼id失败");
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
