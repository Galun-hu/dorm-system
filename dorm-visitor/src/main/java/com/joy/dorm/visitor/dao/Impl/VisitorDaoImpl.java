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

        //多集合查询
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("t_building")
                .localField("buildingId")
                .foreignField("id")
                .as("building");

        //创建查询对象
        Criteria criteria = new Criteria();
        if (id!=null){
            //根据宿舍楼查找
            criteria.and("buildingId").is(id);
        }
        //判断是否为空
        if (StringUtils.hasText(keywords)){
            //根据姓名模糊查询
            Pattern pattern= Pattern.compile("^.*"+keywords+".*$", Pattern.CASE_INSENSITIVE);
            criteria.and("name").regex(pattern);
        }
        //筛选查询成功需要返回的数据
        ProjectionOperation project  = Aggregation.project("id", "name", "sex","floor","phone", "buildingId", "dormId", "remark","createTime")
                .and("building").as("building");
        //创建查询
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(criteria),Aggregation.skip(pageNumNew),Aggregation.limit(pageSize),lookupOperation,project,Aggregation.unwind("building"));
        //拿到返回对象
        List<Map> results = mongoTemplate.aggregate(aggregation, "visitor", Map.class).getMappedResults();
        //创建维修对象
        List<Visitor> visitors = new ArrayList<>();
        //遍历封装返回的数据
        for (Map result : results) {
            //获取到里面的宿舍楼
            Map<String,Object> building = JSONObject.parseObject(JSONObject.toJSONString(result.get("building")));
            //将result变成字符串在转成Visitor对象
            String str = JSON.toJSONString(result);
            Visitor visitor = JSON.parseObject(str, Visitor.class);
            //添加宿舍楼名字
            visitor.setBuiName((String) building.get("name"));
            //添加宿舍楼类型
            visitor.setType((String) building.get("type"));
            //放入数组打包返回
            visitors.add(visitor);
        }
        return visitors;
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
        //创建查询条件 根据维修id修改
        Query query = new Query(Criteria.where("id").is(visitor.getId()));
        //创建修改对象
        Update update = new Update();
        //判断是否为空 不为空则进行添加到修改
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
        if (visitor.getBuildingId()!=null){
            update.set("buildingId",visitor.getBuildingId());
        }
        try {
            //修改第一条记录
            mongoTemplate.updateFirst(query,update,Visitor.class);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int deleteVisitor(Integer id) {
        //根据维修id查询
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

        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("t_building")
                .localField("buildingId")
                .foreignField("id")
                .as("building");

        Criteria criteria = new Criteria();
        if (id!=null){
            //根据宿舍楼查找
            criteria.and("buildingId").is(id);
        }
        if (StringUtils.hasText(keywords)){
            Pattern pattern= Pattern.compile("^.*"+keywords+".*$", Pattern.CASE_INSENSITIVE);
            criteria.and("name").regex(pattern);
        }
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(criteria),lookupOperation);
        List<Map> results = mongoTemplate.aggregate(aggregation, "visitor", Map.class).getMappedResults();
        int num = results.size();
        return (long)num;
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
        ProjectionOperation project  = Aggregation.project("id", "name", "sex","floor","phone", "buildingId", "dormId", "remark","createTime")
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
