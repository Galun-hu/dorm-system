package com.joy.dorm.utils;

import com.joy.dorm.dormitory.model.Building;
import com.joy.dorm.dormitory.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DormitoryTool {

    @Autowired
    private IBuildingService buildingService;

    /**
     * 根据管理员id获取宿舍楼信息
     * @return
     */
    public Building getBuildWithAdminId(Integer id){
        return buildingService.getBuildingWithAdminId(id);
    }
}
