package com.joy.dorm.controller.visitor;

import com.joy.dorm.common.utils.RespResult;
import com.joy.dorm.dormitory.model.Building;
import com.joy.dorm.dormitory.model.BuildingAdmin;
import com.joy.dorm.system.model.Admin;
import com.joy.dorm.system.service.AdminService;
import com.joy.dorm.utils.DormitoryTool;
import com.joy.dorm.utils.RequestJwt;
import com.joy.dorm.visitor.model.Visitor;
import com.joy.dorm.visitor.service.VisitorService;
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
@Api(tags = "访客管理")
@RequestMapping("/system/dorm/visitor")
public class VisitorController {

    @Autowired
    VisitorService visitorService;

    @Autowired
    DormitoryTool dormitoryTool;


    @ApiOperation("获取所有到访记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keywords",value = "根据到访者姓名关键词")
    })
    @GetMapping("/")
    public List<Visitor> getAllVisitor(String keywords, HttpServletRequest request){
        Map<String, Object> map = RequestJwt.getIdByJwtToken(request);
        Integer id = (Integer) map.get("id");
        Building building = dormitoryTool.getBuildWithAdminId(id);
        return visitorService.getAllVisitor(keywords,building.getId());
    }

    @ApiOperation("添加到访人员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Visitor",value = "Visitor类"),
            @ApiImplicitParam(name = "name",value = "姓名"),
            @ApiImplicitParam(name = "sex",value = "性别"),
            @ApiImplicitParam(name = "phone",value = "手机号"),
            @ApiImplicitParam(name = "remark",value = "到访原因"),
            @ApiImplicitParam(name = "createTime",value = "到访时间")
    })
    @PostMapping("/")
    public RespResult addVisitor(@RequestBody Visitor visitor, HttpServletRequest request){
        Map<String, Object> map = RequestJwt.getIdByJwtToken(request);
        Integer id = (Integer) map.get("id");
        Building building = dormitoryTool.getBuildWithAdminId(id);
        visitor.setDormId(id);
        visitor.setBuildingId(building.getId());
        if (visitorService.addVisitor(visitor)==1){
            return RespResult.ok("添加到访人员成功!");
        }
        return RespResult.error("添加到访人员失败!");
    }


    @ApiOperation("修改到访记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Visitor",value = "Visitor类"),
            @ApiImplicitParam(name = "id",value = "到访的id"),
            @ApiImplicitParam(name = "name",value = "姓名"),
            @ApiImplicitParam(name = "sex",value = "性别"),
            @ApiImplicitParam(name = "phone",value = "手机号"),
            @ApiImplicitParam(name = "remark",value = "到访原因"),
            @ApiImplicitParam(name = "createTime",value = "到访时间")
    })
    @PutMapping("/")
    public RespResult updateVisitor(@RequestBody Visitor visitor){
        if (visitorService.updateVisitor(visitor)==1){
            return RespResult.ok("修改到访记录成功！");
        }
        return RespResult.error("修改到访记录失败！");
    }


    @ApiOperation("删除到访记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "到访的id")
    })
    @DeleteMapping("/{id}")
    public RespResult deleteVisitor(@PathVariable Integer id){
        if (visitorService.deleteVisitor(id)==1){
            return RespResult.ok("删除到访记录成功！");
        }
        return RespResult.error("删除到访记录失败！");
    }


}
