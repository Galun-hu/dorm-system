package com.joy.dorm.controller.repair;

import com.joy.dorm.common.utils.RespResult;
import com.joy.dorm.dormitory.model.Building;
import com.joy.dorm.repair.model.Repair;
import com.joy.dorm.repair.service.RepairService;
import com.joy.dorm.utils.DormitoryTool;
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
@Api(tags = "访客管理")
@RequestMapping("/system/dorm/visitor")
public class RepairController {

    @Autowired
    RepairService repairService;

    @Autowired
    DormitoryTool dormitoryTool;


    @ApiOperation("获取所有维修记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keywords",value = "根据申报人姓名关键词")
    })
    @GetMapping("/")
    public List<Repair> getAllRepair(String keywords, HttpServletRequest request){
        Map<String, Object> map = RequestJwt.getIdByJwtToken(request);
        Integer id = (Integer) map.get("id");
        Building building = dormitoryTool.getBuildWithAdminId(id);
        return repairService.getAllRepair(keywords,building.getId());
    }

    @ApiOperation("添加报修")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Repair",value = "Repair类"),
            @ApiImplicitParam(name = "number",value = "宿舍号 类型string"),
            @ApiImplicitParam(name = "name",value = "申报人姓名"),
            @ApiImplicitParam(name = "phone",value = "申报人手机号"),
            @ApiImplicitParam(name = "content",value = "维修内容"),
            @ApiImplicitParam(name = "remark",value = "备注"),
            @ApiImplicitParam(name = "enabled",value = "是否完成 有两种状态 false：维修中 true：已修好 类型布尔"),
            @ApiImplicitParam(name = "createTime",value = "申报时间"),
            @ApiImplicitParam(name = "goodsTime",value = "修好时间"),
    })
    @PostMapping("/")
    public RespResult addRepair(@RequestBody Repair repair, HttpServletRequest request){
        Map<String, Object> map = RequestJwt.getIdByJwtToken(request);
        Integer id = (Integer) map.get("id");
        Building building = dormitoryTool.getBuildWithAdminId(id);
        repair.setDormId(id);
        repair.setBuildingId(building.getId());
        if (repairService.addRepair(repair)==1){
            return RespResult.ok("添加报修成功!");
        }
        return RespResult.error("添加报修失败!");
    }


    @ApiOperation("修改维修记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Repair",value = "Repair类"),
            @ApiImplicitParam(name = "id",value = "维修的id"),
            @ApiImplicitParam(name = "number",value = "宿舍号 类型string"),
            @ApiImplicitParam(name = "name",value = "申报人姓名"),
            @ApiImplicitParam(name = "phone",value = "申报人手机号"),
            @ApiImplicitParam(name = "content",value = "维修内容"),
            @ApiImplicitParam(name = "remark",value = "备注"),
            @ApiImplicitParam(name = "enabled",value = "是否完成 有两种状态 false：维修中 true：已修好 类型布尔"),
            @ApiImplicitParam(name = "createTime",value = "申报时间"),
            @ApiImplicitParam(name = "goodsTime",value = "修好时间"),
    })
    @PutMapping("/")
    public RespResult updateRepair(@RequestBody Repair repair){
        if (repairService.updateRepair(repair)==1){
            return RespResult.ok("修改维修记录成功！");
        }
        return RespResult.error("修改维修记录失败！");
    }


    @ApiOperation("修改维修状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "维修的id"),
            @ApiImplicitParam(name = "enabled",value = "是否完成 有两种状态 false：维修中 true：已修好 类型布尔")
    })
    @PutMapping("/enabled")
    public RespResult updateRepairEnabled(@RequestBody Repair repair){
        if (repairService.updateRepair(repair)==1){
            return RespResult.ok("修改维修状态成功！");
        }
        return RespResult.error("修改维修状态失败！");
    }


    @ApiOperation("删除维修记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "维修的id")
    })
    @DeleteMapping("/{id}")
    public RespResult deleteRepair(@PathVariable Integer id){
        if (repairService.deleteRepair(id)==1){
            return RespResult.ok("删除维修记录成功！");
        }
        return RespResult.error("删除维修记录失败！");
    }


}
