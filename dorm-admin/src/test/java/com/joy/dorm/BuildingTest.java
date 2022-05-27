package com.joy.dorm;

import com.joy.dorm.dormitory.dao.IBuildingDao;
import com.joy.dorm.dormitory.model.Administrator;
import com.joy.dorm.dormitory.model.Building;
import com.joy.dorm.dormitory.service.IBuildingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

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
        building.setPerson_num(800);
        buildingDao.updateBuilding(building);
    }

    @Test
    public void insertTest(){
        Building building = new Building();
        building.setId(2);
        building.setPerson_num(80);
        building.setModified("2022.5.58");
        building.setType("女生宿舍");
        building.setCreated("2022-5-58");
        buildingDao.insertBuilding(building);
    }

    @Test
    public void deleteTest(){
        long result = buildingDao.deleteBuildingBy_id("6290ccf1ac591137bb0cfcf5");
        System.out.println("删除"+result+"条");
    }

    @Autowired
    private MongoTemplate mongoTemplate;
    @Test
    public void aa(){
        Query query = new Query(Criteria.where("id").is(1));
        Administrator administrator = mongoTemplate.findOne(query,Administrator.class,"t_administrator");
        System.out.println(administrator);
    }

}
