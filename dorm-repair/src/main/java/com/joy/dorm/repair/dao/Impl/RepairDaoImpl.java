package com.joy.dorm.repair.dao.Impl;

import com.alibaba.fastjson.JSON;
import com.joy.dorm.repair.dao.RepairDao;
import com.joy.dorm.repair.model.Repair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Repository
public class RepairDaoImpl implements RepairDao {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Repair> getAllRepair(String keywords, Integer id,int pageNumNew,int pageSize) {
        Criteria criteria = new Criteria();
        if (id!=null){
            //根据宿舍楼查找
            criteria.and("buildingId").is(id);
        }
        if (StringUtils.hasText(keywords)){
            Pattern pattern= Pattern.compile("^.*"+keywords+".*$", Pattern.CASE_INSENSITIVE);
            criteria.and("name").regex(pattern);
        }
        //创建分页
        PageRequest pageRequest = PageRequest.of(pageNumNew,pageSize);
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Query query = new Query();
        query.with(pageRequest);
        query.with(sort);
        query.addCriteria(criteria);
        return mongoTemplate.find(query,Repair.class);
    }

    @Override
    public int addRepair(Repair repair) {
        try {
            mongoTemplate.insert(repair);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateRepair(Repair repair) {
        Query query = new Query(Criteria.where("id").is(repair.getId()));
        Update update = new Update();
        if (repair.getNumber()!=null){
            update.set("number",repair.getNumber());
        }
        if (repair.getName()!=null){
            update.set("name",repair.getName());
        }
        if (repair.getPhone()!=null){
            update.set("phone",repair.getPhone());
        }
        if (repair.getContent()!=null){
            update.set("content",repair.getContent());
        }
        if (repair.getRemark()!=null){
            update.set("remark",repair.getRemark());
        }
        if (repair.getEnabled()!=null){
            update.set("enabled",repair.getEnabled());
        }
        if (repair.getCreateTime()!=null){
            update.set("createTime",repair.getCreateTime());
        }
        if (repair.getGoodsTime()!=null){
            update.set("goodsTime",repair.getGoodsTime());
        }
        try {
            mongoTemplate.updateFirst(query,update,Repair.class);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int deleteRepair(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        try {
            mongoTemplate.remove(query,Repair.class);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Long getRepairCount(String keywords, Integer id) {
        Criteria criteria = new Criteria();
        if (id!=null){
            //根据宿舍楼查找
            criteria.and("buildingId").is(id);
        }
        if (StringUtils.hasText(keywords)){
            Pattern pattern= Pattern.compile("^.*"+keywords+".*$", Pattern.CASE_INSENSITIVE);
            criteria.and("name").regex(pattern);
        }
        Query query = new Query(criteria);
        return mongoTemplate.count(query,Repair.class);
    }

    @Override
    public Long getRepairAdminCount(String keywords, Integer buildingId) {
        LookupOperation lookup= LookupOperation.newLookup()
                .from("t_building")
                .localField("buildingId")
                .foreignField("id")
                .as("building");

        Criteria criteria = new Criteria();
        boolean flag = true;
        if (StringUtils.hasText(keywords)){
            Pattern pattern= Pattern.compile("^.*"+keywords+".*$", Pattern.CASE_INSENSITIVE);
            criteria.and("name").regex(pattern);
            flag = false;
        }
        if (buildingId!=null&&flag){
            criteria.and("buildingId").is(buildingId);
        }
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(criteria),lookup);
        List<Map> results = mongoTemplate.aggregate(aggregation, "repair", Map.class).getMappedResults();
        int num = results.size();
        return (long)num;
    }

    @Override
    public List<Repair> getAllRepairAdmin(String keywords, int pageNumNew, int pageSize, Integer buildingId) {
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("t_building")
                .localField("buildingId")
                .foreignField("id")
                .as("building");

        Criteria criteria = new Criteria();
        boolean flag = true;
        if (StringUtils.hasText(keywords)){
            Pattern pattern= Pattern.compile("^.*"+keywords+".*$", Pattern.CASE_INSENSITIVE);
            criteria.and("name").regex(pattern);
            flag = false;
        }
        if (buildingId!=null&&flag){
            criteria.and("buildingId").is(buildingId);
        }
        ProjectionOperation project  = Aggregation.project("id", "dormId", "buildingId", "number", "name", "phone","content","enabled", "remark","createTime")
                .and("building").as("building");
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(criteria),Aggregation.skip(pageNumNew),Aggregation.limit(pageSize),lookupOperation);
        List<Map> results = mongoTemplate.aggregate(aggregation, "repair", Map.class).getMappedResults();
        List<Repair> repairs = new ArrayList<>();
        for (Map result : results) {
            Integer id = (Integer)result.get("building.id");
            String str = JSON.toJSONString(result);
            Repair repair = JSON.parseObject(str, Repair.class);
            repairs.add(repair);
        }
        return repairs;
    }


}
