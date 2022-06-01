package com.joy.dorm.controller.ambient;

import com.joy.dorm.ambient.model.Health;
import com.joy.dorm.ambient.service.IHealthService;
import com.joy.dorm.common.utils.RespPage;
import com.joy.dorm.common.utils.RespResult;
import com.joy.dorm.dormitory.model.Building;
import com.joy.dorm.outLate.model.Outlate;
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
@Api(tags = "c 卫生管理 ------宿舍管理员")
@RequestMapping("/system/dorm/ambient")
public class AambientController {

    @Autowired
    private IHealthService healthService;

    @Autowired
    private DormitoryTool dormitoryTool;


    @ApiOperation("获取卫生信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "building_id",value = "宿舍id"),
            @ApiImplicitParam(name = "building_type",value = "宿舍类型"),
            @ApiImplicitParam(name = "keywords",value = "搜索关键词"),
            @ApiImplicitParam(name = "pageNum",value = "第几页（默认第1页）"),
            @ApiImplicitParam(name = "pagheSize",value = "一页多少条数据（默认10条）")})
    @GetMapping("/")
    public RespPage getHealths(HttpServletRequest request,
                               @RequestParam(defaultValue = "") Integer building_id,
                               @RequestParam(defaultValue = "") String building_type,
                               @RequestParam(defaultValue = "") String keywords,
                               @RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "10") Integer pageSize){
        Map<String, Object> map = RequestJwt.getIdByJwtToken(request);
        Integer id = (Integer) map.get("id");
        String role = (String)map.get("role");
        if (!role.equals("ROLE_admin")){
            Building building = dormitoryTool.getBuildWithAdminId(id);
            building_id = building.getId();
        }
        int pageNumNew = pageNum-1;
        if (pageNumNew < 0){
            pageNumNew = 0;
        }
        List<Health> healths = healthService.getHealths(building_id,keywords,building_type,pageNumNew,pageSize);
        RespPage page = new RespPage();
        Long count = healthService.getHealthsCount(building_type,keywords,building_id);
        page.setTotal(count);
        page.setData(healths);
        return page;
    }



    @ApiOperation("添加卫生信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Health",value = "Health  接口文档Models有对应该类描述"),
            @ApiImplicitParam(name = "id",value = "卫生信息id"),
            @ApiImplicitParam(name = "rome_id",value = "房间号"),
            @ApiImplicitParam(name = "floor",value = "楼层"),
            @ApiImplicitParam(name = "health_level",value = "卫生等级"),
            @ApiImplicitParam(name = "create_time",value = "创建时间")})
    @PostMapping("/")
    public RespResult addHealth(@RequestBody Health health){
        Integer result = healthService.addHealth(health);
        if (result > 0){
            return RespResult.ok("成功");
        }else {
            return RespResult.error("失败");}
    }




    @ApiOperation("修改卫生信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Health",value = "Health  接口文档Models有对应该类描述"),
            @ApiImplicitParam(name = "id",value = "卫生信息id"),
            @ApiImplicitParam(name = "rome_id",value = "房间号"),
            @ApiImplicitParam(name = "floor",value = "楼层"),
            @ApiImplicitParam(name = "health_level",value = "卫生等级"),
            @ApiImplicitParam(name = "create_time",value = "创建时间")
    })
    @PutMapping("/")
    public RespResult updateHealth(@RequestBody Health health){
        long result = healthService.updateHealth(health);
        if (result == -1){
            return RespResult.error("缺少卫生信息id");
        }else if (result > 0){
            return RespResult.ok("成功");
        }else{
            return RespResult.error("失败");
        }
    }




    @ApiOperation("根据id删除卫生信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "id",value = "卫生信息id")})
    @DeleteMapping("/{id}")
    public RespResult deleteHealth(@PathVariable Integer id){
        long result = healthService.deleteHealth(id);
        if (result > 0){
            return RespResult.ok("成功");
        }else {
            return RespResult.error("失败");
        }
    }
}
