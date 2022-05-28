package com.joy.dorm.utils;

import com.joy.dorm.dormitory.dao.IAdministretorDao;
import com.joy.dorm.dormitory.dao.IBuildingDao;
import com.joy.dorm.dormitory.model.Administrator;
import com.joy.dorm.dormitory.model.Building;
import com.joy.dorm.dormitory.model.BuildingAdmin;
import com.joy.dorm.dormitory.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DormitoryTool {

    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private IAdministretorDao administretorDao;

    /**
     * 根据管理员id获取宿舍楼信息
     * @return
     */
    public Building getBuildWithAdminId(Integer id){
        return buildingService.getBuildingWithAdminId(id);
    }

    /**
     * 根据宿舍楼id获取管理该宿舍楼的所有管理员信息
     * @param building_id
     * @return
     */
    public List<BuildingAdmin> getAllAdminWithBuildingId(Integer building_id){
        return administretorDao.findAllAdmintratorIdByBuildId(building_id);
    }
}
