package com.joy.dorm;

import com.joy.dorm.dormitory.dao.IBuildingDao;
import com.joy.dorm.dormitory.model.Building;
import com.joy.dorm.dormitory.service.IBuildingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BuildingTest {


    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private IBuildingDao buildingDao;

    @Test
    public void findBuildingsTest(){
        System.out.println(buildingService.getBuildings());
    }

    @Test
    public void findBuildingByIdTest(){
        System.out.println(buildingDao.findBuildingById(1));
    }

    @Test
    public void updateTest(){
        Building building = buildingDao.findBuildingById(1);
        building.setPerson_num(700);
        buildingDao.updateBuilding(building);
    }
}
