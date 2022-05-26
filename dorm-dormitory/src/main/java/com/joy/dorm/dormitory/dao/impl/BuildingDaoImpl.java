package com.joy.dorm.dormitory.dao.impl;

import com.joy.dorm.common.utils.Tool;
import com.joy.dorm.dormitory.dao.IBuildingDao;
import com.joy.dorm.dormitory.model.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BuildingDaoImpl implements IBuildingDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Bean
    private Tool myTool(){
        return new Tool();
    }

    @Override
    public List<Building> findBuildings(){
        return mongoTemplate.findAll(Building.class);
    }

    @Override
    public Building findBuildingById(int id){
        Query query = new Query(Criteria.where("id").is(id));
        Building building = mongoTemplate.findOne(query,Building.class);
        return building;
    }

//    @Override
//    public Building findBuildingByAdministrator(String name){
//        Query query = new Query(Criteria.where("administrator").is(name));
//        Building building = mongoTemplate.findOne(query,Building.class);
//        return building;
//    }

    @Override
    public void updateBuilding(Building building){
        Update update = myTool().updateFields(building);
        Query query = new Query().addCriteria(Criteria.where("_id").is(building.get_id()));
        mongoTemplate.updateFirst(query,update,Building.class);
    }

}
