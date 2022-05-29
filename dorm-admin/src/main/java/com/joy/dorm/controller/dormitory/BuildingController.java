package com.joy.dorm.controller.dormitory;

import com.joy.dorm.common.utils.RespResult;
import com.joy.dorm.dormitory.model.Building;
import com.joy.dorm.dormitory.service.IBuildingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "a 宿舍楼信息 ------系统管理员")
@RequestMapping("/system/admin/dormitory")
public class BuildingController {

    @Autowired
    private IBuildingService buildingService;

    @ApiOperation("获取所有宿舍楼")
    @GetMapping("/all")
    public RespResult getAllBuilding(){
        List<Building> buildings = buildingService.getBuildings();
        if (buildings != null || buildings.size() != 0){
            return RespResult.ok("成功",buildings);
        }else {
            return RespResult.error("获取失败");
        }
    }




    @ApiOperation("根据宿舍楼栋号获取宿舍信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "宿舍楼id")
    })
    @GetMapping("/{id}")
    public RespResult getBuildingWithId(@PathVariable int id){
        Building building = buildingService.getBuildingWithId(id);
        if (building != null) {
            return RespResult.ok("成功",building);
        }else {
            return RespResult.error("失败，检查宿舍楼id");
        }
    }




    @ApiOperation("根据管理员姓名获取宿舍信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "name",value = "管理员名字")})
    @GetMapping("/name/{name}")
    public RespResult getBuildingWithName(@PathVariable String name){
        Building building = buildingService.getBuildingWithAdministrator(name);
        if (building != null) {
            return RespResult.ok("成功",building);
        }else {
            return RespResult.error("失败,无此管理员或此管理员未关联宿舍");
        }
    }



    @ApiOperation("添加宿舍楼栋信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "_id",value = "宿舍楼_id（不用填）"),
            @ApiImplicitParam(name = "id",value = "宿舍楼id（不用填）"),
            @ApiImplicitParam(name = "created",value = "创建时间（不用填）"),
            @ApiImplicitParam(name = "modified",value = "修改时间（不用填）")
    })
    @PostMapping("/addBuilding")
    public RespResult addBuilding(@RequestBody Building building){
        Integer result = buildingService.addBuilding(building);
        if (result > 0){
            return RespResult.ok("数据成功插入："+result+"条");
        }else {
            return RespResult.error("数据插入失败");
        }
    }


    @ApiOperation("更新宿舍信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "_id",value = "宿舍楼_id（必填）"),
            @ApiImplicitParam(name = "created",value = "创建时间（不用填）"),
            @ApiImplicitParam(name = "modified",value = "修改时间（不用填）")
    })
    @PostMapping("/update")
    public RespResult updateBuilding(@RequestBody Building building){
        long result = buildingService.updateBuilding(building);
        if (result > 0){
            return RespResult.ok("成功更新数据"+result+"条");
        }else {
            return RespResult.error("更新数据失败");
        }
    }



    @ApiOperation("根据_id删除宿舍信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "_id",value = "宿舍楼_id")})
    @PostMapping("/delete/{_id}")
    public RespResult deleteBuilding(@PathVariable String _id){
        long result = buildingService.deleteBuilding(_id);
        if (result > 0){
            return RespResult.ok("成功删除"+result+"条数据");
        }else {
            return RespResult.error("删除失败，该_id不存在");
        }

    }



}
