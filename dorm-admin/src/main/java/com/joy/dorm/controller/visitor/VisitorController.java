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

//声明只返回json数据
@RestController
@Api(tags = "f 访客管理 ------宿舍管理员")
//统一路径前缀
@RequestMapping("/system/dorm/visitor")
public class VisitorController {

    //注入访客
    @Autowired
    VisitorService visitorService;

    //注入工具包 用来实现查询当前管理员的管理楼栋
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
        //获取token
        Map<String, Object> map = RequestJwt.getIdByJwtToken(request);
        //解析token里面的id
        Integer id = (Integer) map.get("id");
        //解析token里面的角色
        String role = (String)map.get("role");
        //获取当前管理员所管理的楼栋
        Building building = dormitoryTool.getBuildWithAdminId(id);
        //页码减1 因为mongo下标从0开始
        long pageNumNew = pageNum-1;
        //如果小于0 那么都统一等于从0开始拿
        if (pageNumNew < 0){
            pageNumNew = 0;
        }
        //是页码从哪里开始
        if (pageNumNew > 0){
            //当前页码*一页的数量
            pageNumNew *= pageSize;
        }
        //创建一个封装的返回分页的对象
        RespPage respPage = new RespPage();
        //判断是否是系统管理员
        if (role.equals("ROLE_admin")){
            //拿总数量 分页使用 keyword是模糊查询使用 buildingId是根据宿舍楼id查
            respPage.setTotal(visitorService.getVisitorAdminCount(keywords,buildingId));
            //拿当前分页数据
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
        //添加宿舍管理员id
        visitor.setDormId(id);
        //如果等于空说明这是宿舍管理员添加 不等于空则是系统管理员添加 用来区分是哪个角色的操作
        if (visitor.getBuildingId()==null){
            visitor.setBuildingId(building.getId());
        }
        //调用service层 添加成功返回1
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
