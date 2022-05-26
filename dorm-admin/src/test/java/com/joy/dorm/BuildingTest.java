package com.joy.dorm;

import com.joy.dorm.dormitory.service.IBuildingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BuildingTest {


    @Autowired
    private IBuildingService buildingService;
//
    @Test
    public void findBuildingsTest(){
        System.out.println(buildingService.getBuildings());
    }

}
