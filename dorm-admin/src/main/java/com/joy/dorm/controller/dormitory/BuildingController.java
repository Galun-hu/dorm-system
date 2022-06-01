package com.joy.dorm.controller.dormitory;

import com.joy.dorm.common.utils.RespPage;
import com.joy.dorm.common.utils.RespResult;
import com.joy.dorm.dormitory.model.Building;
import com.joy.dorm.dormitory.service.IBuildingService;
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
@Api(tags = "a 宿舍楼信息 ------系统管理员")
@RequestMapping("/system/admin/dormitory")
public class BuildingController {

    @Autowired
    private IBuildingService buildingService;

    @ApiOperation("获取所有宿舍楼")
    @ApiImplicitParams({@ApiImplicitParam(name = "id",value = "宿舍id"),
                        @ApiImplicitParam(name = "keywords",value = "搜索关键词"),
                        @ApiImplicitParam(name = "pageNum",value = "第几页（默认第1页）"),
                        @ApiImplicitParam(name = "pagheSize",value = "一页多少条数据（默认10条）")})
    @GetMapping("/")
    public RespPage getAllBuilding(@RequestParam(defaultValue = "") Integer id,
                                   @RequestParam(defaultValue = "") String keywords,
                                   @RequestParam(defaultValue = "1") int pageNum,
                                   @RequestParam(defaultValue = "10") int pageSize){
        int pageNumNew = pageNum-1;
        if (pageNumNew < 0){
            pageNumNew = 0;
        }
        List<Building> buildings = buildingService.getBuildings(keywords,id,pageNumNew,pageSize);
        RespPage page = new RespPage();
//        if (id != null){
//            page.setTotal(Long.valueOf(1));
//        }else {
//            Long count = buildingService.getBuildingsCount(keywords);
//            page.setTotal(count);
//        }
        Long count = buildingService.getBuildingsCount(keywords);
        page.setTotal(count);
        page.setData(buildings);
        return page;
    }





    @ApiOperation("获取所有宿舍楼名称")
    @GetMapping("/name")
    public RespResult getAllBuildingName(){
        List<Building> buildings = buildingService.getNames();
        if (buildings.size() > 0){
            return RespResult.ok("成功",buildings);
        }else {
            return RespResult.error("失败");
        }
    }




//    @ApiOperation("根据宿舍楼栋号获取宿舍信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id",value = "宿舍楼id")
//    })
//    @GetMapping("/{id}")
//    public RespResult getBuildingWithId(@PathVariable int id){
//        Building building = buildingService.getBuildingWithId(id);
//        if (building != null) {
//            return RespResult.ok("成功",building);
//        }else {
//            return RespResult.error("失败，检查宿舍楼id");
//        }
//    }




//    @ApiOperation("根据管理员姓名获取宿舍信息")
//    @ApiImplicitParams({@ApiImplicitParam(name = "name",value = "管理员名字")})
//    @GetMapping("/name/{name}")
//    public RespResult getBuildingWithName(@PathVariable String name){
//        Building building = buildingService.getBuildingWithAdministrator(name);
//        if (building != null) {
//            return RespResult.ok("成功",building);
//        }else {
//            return RespResult.error("失败,无此管理员或此管理员未关联宿舍");
//        }
//    }



    @ApiOperation("添加宿舍楼栋信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "_id",value = "宿舍楼_id（不用填）"),
            @ApiImplicitParam(name = "id",value = "宿舍楼id（不用填）"),
            @ApiImplicitParam(name = "created",value = "创建时间（不用填）"),
            @ApiImplicitParam(name = "modified",value = "修改时间（不用填）")
    })
    @PostMapping("/")
    public RespResult addBuilding(@RequestBody Building building){
        Integer result = buildingService.addBuilding(building);
        if (result > 0){
            return RespResult.ok("成功");
        }else {
            return RespResult.error("失败");
        }
    }



    @ApiOperation("更新宿舍信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "宿舍楼id（必填）"),
            @ApiImplicitParam(name = "created",value = "创建时间（不用填）"),
            @ApiImplicitParam(name = "modified",value = "修改时间（不用填）")
    })
    @PutMapping("/")
    public RespResult updateBuilding(@RequestBody Building building){
        long result = buildingService.updateBuilding(building);
        if (result > 0){
            return RespResult.ok("成功");
        }else {
            return RespResult.error("失败");
        }
    }



    @ApiOperation("根据_id删除宿舍信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "id",value = "宿舍楼id")})
    @DeleteMapping("/{id}")
    public RespResult deleteBuilding(@PathVariable Integer id){
        long result = buildingService.deleteBuilding(id);
        if (result > 0){
            return RespResult.ok("成功");
        }else {
            return RespResult.error("失败");
        }

    }



}
