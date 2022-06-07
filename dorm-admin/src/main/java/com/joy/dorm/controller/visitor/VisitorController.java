package com.joy.dorm.controller.visitor;

import com.joy.dorm.common.utils.RespPage;
import com.joy.dorm.common.utils.RespResult;
import com.joy.dorm.dormitory.model.Building;
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
import java.util.Map;

@RestController
@Api(tags = "f 访客管理 ------宿舍管理员")
@RequestMapping("/system/dorm/visitor")
public class VisitorController {

    @Autowired
    VisitorService visitorService;

    @Autowired
    DormitoryTool dormitoryTool;


    @ApiOperation("获取所有到访记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keywords",value = "根据到访者姓名关键词"),
            @ApiImplicitParam(name = "pageNum",value = "第几页 默认第一页"),
            @ApiImplicitParam(name = "pageSize",value = "拿多少条数据 默认10条"),
            @ApiImplicitParam(name = "buildingId",value = "宿舍楼id 默认拿第一栋的信息 *这是系统管理员的才有"),
            //@ApiImplicitParam(name = "buildingType",value = "宿舍楼类别 默认拿男生宿舍的信息 *这是系统管理员的才有"),
    })
    @GetMapping("/")
    public RespPage getAllVisitor(String keywords, HttpServletRequest request,
                                  @RequestParam(defaultValue = "1") long pageNum,
                                  @RequestParam(defaultValue = "10") long pageSize, Integer buildingId
    ){
        Map<String, Object> map = RequestJwt.getIdByJwtToken(request);
        Integer id = (Integer) map.get("id");
        String role = (String)map.get("role");
        Building building = dormitoryTool.getBuildWithAdminId(id);
        long pageNumNew = pageNum-1;
        if (pageNumNew < 0){
            pageNumNew = 0;
        }
        if (pageNumNew > 0){
            pageNumNew *= pageSize;
        }
        RespPage respPage = new RespPage();
        if (role.equals("ROLE_admin")){
            respPage.setTotal(visitorService.getVisitorAdminCount(keywords,buildingId));
            respPage.setData(visitorService.getAllVisitorAdmin(keywords,pageNumNew,pageSize,buildingId));
        }else{
            respPage.setTotal(visitorService.getVisitorCount(keywords,building.getId()));
            respPage.setData(visitorService.getAllVisitor(keywords,building.getId(),pageNumNew,pageSize));
        }
        return respPage;
    }

    @ApiOperation("添加到访人员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Visitor",value = "Visitor类  接口文档Models有对应该类描述"),
            @ApiImplicitParam(name = "buildingId",value = "宿舍楼id，宿舍管理员不需要带此项"),
            @ApiImplicitParam(name = "name",value = "姓名"),
            @ApiImplicitParam(name = "sex",value = "性别"),
            @ApiImplicitParam(name = "phone",value = "手机号"),
            @ApiImplicitParam(name = "remark",value = "到访原因")
    })
    @PostMapping("/")
    public RespResult addVisitor(@RequestBody Visitor visitor, HttpServletRequest request){
        Map<String, Object> map = RequestJwt.getIdByJwtToken(request);
        Integer id = (Integer) map.get("id");
        Building building = dormitoryTool.getBuildWithAdminId(id);
        visitor.setDormId(id);
        if (visitor.getBuildingId()==null){
            visitor.setBuildingId(building.getId());
        }
        if (visitorService.addVisitor(visitor)==1){
            return RespResult.ok("添加到访人员成功!");
        }
        return RespResult.error("添加到访人员失败!");
    }


    @ApiOperation("修改到访记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Visitor",value = "Visitor类  接口文档Models有对应该类描述"),
            @ApiImplicitParam(name = "id",value = "到访的id"),
            @ApiImplicitParam(name = "buildingId",value = "宿舍楼id，宿舍管理员不需要带此项"),
            @ApiImplicitParam(name = "name",value = "姓名"),
            @ApiImplicitParam(name = "sex",value = "性别"),
            @ApiImplicitParam(name = "phone",value = "手机号"),
            @ApiImplicitParam(name = "remark",value = "到访原因"),
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
