package com.joy.dorm.visitor.dao.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.joy.dorm.visitor.dao.VisitorDao;
import com.joy.dorm.visitor.model.Visitor;
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
public class VisitorDaoImpl implements VisitorDao {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Visitor> getAllVisitor(String keywords, Integer id,long pageNumNew,long pageSize) {
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
        PageRequest pageRequest = PageRequest.of((int)pageNumNew,(int)pageSize);
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Query query = new Query();
        query.with(pageRequest);
        query.with(sort);
        query.addCriteria(criteria);
        return mongoTemplate.find(query,Visitor.class);
    }

    @Override
    public int addVisitor(Visitor visitor) {
        try {
            mongoTemplate.insert(visitor);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateVisitor(Visitor visitor) {
        Query query = new Query(Criteria.where("id").is(visitor.getId()));
        Update update = new Update();
        if (StringUtils.hasText(visitor.getName())){
            update.set("name",visitor.getName());
        }
        if (StringUtils.hasText(visitor.getSex())){
            update.set("sex",visitor.getSex());
        }
        if (StringUtils.hasText(visitor.getPhone())){
            update.set("phone",visitor.getPhone());
        }
        if (StringUtils.hasText(visitor.getFloor())){
            update.set("floor",visitor.getFloor());
        }
        if (StringUtils.hasText(visitor.getRemark())){
            update.set("remark",visitor.getRemark());
        }
        if (visitor.getCreateTime()!=null){
            update.set("createTime",visitor.getCreateTime());
        }
        try {
            mongoTemplate.updateFirst(query,update,Visitor.class);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int deleteVisitor(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        try {
            mongoTemplate.remove(query,Visitor.class);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Long getVisitorCount(String keywords, Integer id) {
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
        return mongoTemplate.count(query,Visitor.class);
    }

    @Override
    public Long getVisitorAdminCount(String keywords,Integer buildingId) {

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
            flag=false;
        }
        if (buildingId!=null&&flag){
            criteria.and("buildingId").is(buildingId);
        }
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(criteria),lookupOperation);
        List<Map> results = mongoTemplate.aggregate(aggregation, "visitor", Map.class).getMappedResults();
        int num = results.size();
        return (long)num;
    }

    @Override
    public List<Visitor> getAllVisitorAdmin(String keywords, long pageNumNew, long pageSize, Integer buildingId) {
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
        ProjectionOperation project  = Aggregation.project("id", "name", "sex", "phone", "buildingId", "dormId", "remark","createTime")
                .and("building").as("building");
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(criteria),Aggregation.skip(pageNumNew),Aggregation.limit(pageSize),lookupOperation,project,Aggregation.unwind("building"));
        List<Map> results = mongoTemplate.aggregate(aggregation, "visitor", Map.class).getMappedResults();
        List<Visitor> visitors = new ArrayList<>();
        for (Map result : results) {
            Map<String,Object> building = JSONObject.parseObject(JSONObject.toJSONString(result.get("building")));
            String str = JSON.toJSONString(result);
            Visitor visitor = JSON.parseObject(str, Visitor.class);
            visitor.setBuiName((String) building.get("name"));
            visitor.setType((String) building.get("type"));
            visitors.add(visitor);
        }
        return visitors;
    }
}
