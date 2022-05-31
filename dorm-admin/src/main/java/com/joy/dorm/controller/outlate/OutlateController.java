package com.joy.dorm.controller.outlate;

import com.joy.dorm.common.utils.RespResult;
import com.joy.dorm.outLate.model.Outlate;
import com.joy.dorm.outLate.service.IOutlateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "d 晚归管理 ------宿舍管理员")
@RequestMapping("/system/admin/outlate")
public class OutlateController {

    @Autowired
    private IOutlateService outlateService;


    @ApiOperation("获取所有晚归信息")
    @GetMapping("/all")
    public RespResult getAllOutlate(){
        List<Outlate> outlates = outlateService.getAllOutlate();
        if (outlates != null){
            return RespResult.ok("成功",outlates);
        }else {
            return RespResult.error("获取失败");
        }
    }


//    @ApiOperation("根据building_id获取所有晚归信息")
//    @GetMapping("/")
//    public RespResult getAllOutlateWithBuildingId(@RequestParam(defaultValue = "1") int building_id){
//        List<Outlate> outlates = outlateService.getAllOutlateWithBuildingId(building_id);
//        if (outlates != null){
//            return RespResult.ok("成功",outlates);
//        }else {
//            return RespResult.error("获取失败");
//        }
//    }


    @ApiOperation("根据building_id和building_type获取所有晚归信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "building_id",value = "宿舍楼id"),
                        @ApiImplicitParam(name = "building_type",value = "宿舍楼类型")})
    @GetMapping("/")
    public RespResult getAllOutlateWithBuildingIdAndBuildingType(@RequestParam int building_id,
                                                                 @RequestParam String building_type){
        List<Outlate> outlates = outlateService.getAllOutlateWithBuildingIdAndBuildingType(building_id,building_type);
        if (outlates != null){
            return RespResult.ok("成功",outlates);
        }else {
            return RespResult.error("获取失败");
        }
    }




    @ApiOperation("添加晚归信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Outlate",value = "Outlate类  接口文档Models有对应该类描述"),
            @ApiImplicitParam(name = "student_id",value = "学号"),
            @ApiImplicitParam(name = "name",value = "姓名"),
            @ApiImplicitParam(name = "phone",value = "手机号"),
            @ApiImplicitParam(name = "building_id",value = "宿舍楼id"),
            @ApiImplicitParam(name = "rome_id",value = "房间号")})
    @PostMapping("/")
    public RespResult addOutlate(@RequestBody Outlate outlate){
        Integer result = outlateService.addOutlate(outlate);
        if (result > 0){
            return RespResult.ok("数据添加成功");
        }else if (result == -1) {
            return RespResult.error("缺少学号或宿舍楼id或房间号");
        }else {
            return RespResult.error("数据添失败");}
    }

    @ApiOperation("修改晚归信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Outlate",value = "Outlate类  接口文档Models有对应该类描述"),
            @ApiImplicitParam(name = "student_id",value = "学号"),
            @ApiImplicitParam(name = "name",value = "姓名"),
            @ApiImplicitParam(name = "phone",value = "手机号"),
            @ApiImplicitParam(name = "building_id",value = "宿舍楼id"),
            @ApiImplicitParam(name = "rome_id",value = "房间号")
    })
    @PutMapping("/")
    public RespResult updateOutlate(@RequestBody Outlate outlate){
        long result = outlateService.updateOutlate(outlate);
        if (result == -1){
            return RespResult.error("缺少晚归信息id");
        }else if (result > 0){
            return RespResult.ok("更新成功");
        }else{
            return RespResult.error("更新失败");
        }
    }


    @ApiOperation("根据id删除晚归信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "id",value = "晚归信息id")})
    @DeleteMapping("/{id}")
    public RespResult deleteOutlate(@PathVariable Integer id){
        long result = outlateService.deleteOutlateWithId(id);
        if (result > 0){
            return RespResult.ok("删除成功");
        }else {
            return RespResult.error("删除失败");
        }
    }
}
