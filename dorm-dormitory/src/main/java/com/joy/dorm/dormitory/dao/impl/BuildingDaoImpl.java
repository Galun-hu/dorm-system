package com.joy.dorm.dormitory.dao.impl;

import com.joy.dorm.common.utils.Tool;
import com.joy.dorm.dormitory.dao.IBuildingDao;
import com.joy.dorm.dormitory.model.Administrator;
import com.joy.dorm.dormitory.model.Building;
import com.joy.dorm.dormitory.model.BuildingAdmin;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

@Repository
public class BuildingDaoImpl implements IBuildingDao {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Autowired
    private Tool myTool;

    @Override
    public List<Building> findBuildings(String keywords,int pageNum,int pageSize){
        Criteria criteria = new Criteria();
        if (StringUtils.hasText(keywords)){
            Pattern pattern= Pattern.compile("^.*"+keywords+".*$", Pattern.CASE_INSENSITIVE);
            criteria.and("name").regex(pattern);
        }
        PageRequest pageRequest = PageRequest.of(pageNum,pageSize);
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Query query = new Query().addCriteria(criteria).with(sort).with(pageRequest);
        return mongoTemplate.find(query,Building.class);
    }

    @Override
    public Long acountBuildings(String keywords){
        Pattern pattern= Pattern.compile("^.*"+keywords+".*$", Pattern.CASE_INSENSITIVE);
        Criteria criteria = new Criteria().and("name").regex(pattern);
        Query query = new Query().addCriteria(criteria);
        return Long.valueOf(mongoTemplate.find(query,Building.class).size());
    }

    @Override
    public Building findBuildingById(int id){
        Query query = new Query(Criteria.where("id").is(id));
        Building building = mongoTemplate.findOne(query,Building.class);
        return building;
    }

//    @Override
//    public Building findBuildingBy_id(String _id){
//        Query query = new Query(Criteria.where("_id").is(_id));
//        return mongoTemplate.findOne(query,Building.class);
//    }



    @Override
    public long updateBuilding(Building building){
        Update update = myTool.updateFields(building);
        Query query = new Query().addCriteria(Criteria.where("id").is(building.getId()));
        UpdateResult result = mongoTemplate.updateFirst(query,update,Building.class);
        return result.getModifiedCount();
    }

    @Override
    public Integer insertBuilding(Building building){
        Building result = mongoTemplate.insert(building);
        if (result != null){
            return 1;
        }else {
            return null;
        }
    }

    @Override
    public long deleteBuildingBy_id(Integer id){
        Query query = new Query(Criteria.where("id").is(id));
        Query query1 = new Query(Criteria.where("building_id").is(id));
        DeleteResult result = mongoTemplate.remove(query,Building.class);
        mongoTemplate.findAllAndRemove(query1,"t_building_administrator");

        return result.getDeletedCount();
    }
}
