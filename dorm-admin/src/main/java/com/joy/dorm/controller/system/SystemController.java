package com.joy.dorm.controller.system;

import com.joy.dorm.common.utils.RespPage;
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
import java.util.Map;

@RestController
@Api(tags = "g 管理员信息 ------系统管理员")
@RequestMapping("/system/admin")
public class SystemController {

    @Autowired
    AdminService adminService;

    @ApiOperation("获取所有管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keywords",value = "关键词"),
            @ApiImplicitParam(name = "pageNum",value = "第几页 默认第一页"),
            @ApiImplicitParam(name = "pageSize",value = "拿多少条数据 默认10条")
    })
    @GetMapping("/")
    public RespPage getAllAdmin(String keywords, HttpServletRequest request,
                                   @RequestParam(defaultValue = "1") long pageNum,
                                   @RequestParam(defaultValue = "10") long pageSize){
        Map<String, Object> map = RequestJwt.getIdByJwtToken(request);
        Integer id = (Integer) map.get("id");
        long pageNumNew = pageNum-1;
        if (pageNumNew < 0){
            pageNumNew = 0;
        }
        if (pageNumNew>0){
            pageNumNew*=pageSize;
        }
        Long total = adminService.getAdminCount(keywords,id);
        RespPage respPage = new RespPage();
        respPage.setTotal(total);
        respPage.setData(adminService.getAllAdmin(keywords,id,pageNumNew,pageSize));
        return respPage;
    }

    @ApiOperation("添加管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Admin",value = "Admin类 接口文档Models有对应该类描述"),
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
        Admin username = adminService.getUsername(admin.getUsername());

        if (username!=null){
            return RespResult.error("该账号名已被使用!");
        }

        if (adminService.insert(admin)==1){
            return RespResult.ok("添加管理员成功!");
        }
        return RespResult.error("添加管理员失败!");
    }


    @ApiOperation("修改管理员，系统管理员个人中心修改的也是这个接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Admin",value = "Admin类"),
            @ApiImplicitParam(name = "id",value = "管理员id"),
            @ApiImplicitParam(name = "name",value = "姓名"),
            @ApiImplicitParam(name = "sex",value = "性别"),
            @ApiImplicitParam(name = "phone",value = "手机号"),
            @ApiImplicitParam(name = "company",value = "学生处、宿舍管理中心，前端这两个直接写死 后端不提供"),
            @ApiImplicitParam(name = "enabled",value = "是否启用 Boolean类型 个人中心的修改不显示这个"),
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

}
