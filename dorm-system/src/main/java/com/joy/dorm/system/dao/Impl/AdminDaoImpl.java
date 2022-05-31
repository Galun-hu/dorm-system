package com.joy.dorm.system.dao.Impl;

import com.alibaba.fastjson.JSON;
import com.joy.dorm.system.dao.AdminDao;
import com.joy.dorm.system.model.Admin;
import com.joy.dorm.system.model.Role;
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

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Repository
public class AdminDaoImpl implements AdminDao {

    @Resource
    MongoTemplate mongoTemplate;

    @Override
    public int insert(Admin admin) {
        try {
            mongoTemplate.insert(admin);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(Admin admin) {
        Query query = new Query(Criteria.where("id").is(admin.getId()));
        Update update = new Update();
        if (StringUtils.hasText(admin.getUsername())){
            update.set("username",admin.getUsername());
        }
        if (StringUtils.hasText(admin.getName())){
            update.set("name",admin.getName());
        }
        if (StringUtils.hasText(admin.getPassword())){
            update.set("password",admin.getPassword());
        }
        if (StringUtils.hasText(admin.getSex())){
            update.set("sex",admin.getSex());
        }
        if (StringUtils.hasText(admin.getPhone())){
            update.set("phone",admin.getPhone());
        }
        if (StringUtils.hasText(admin.getCompany())){
            update.set("company",admin.getCompany());
        }
        if (admin.getEnabled()!=null){
            update.set("enabled",admin.getEnabled());
        }
        if (StringUtils.hasText(admin.getRemark())){
            update.set("remark",admin.getRemark());
        }
        try {
            mongoTemplate.updateFirst(query,update,Admin.class);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        try {
            mongoTemplate.remove(query,Admin.class);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Admin> getAllAdmin(String keywords,Integer id,long pageNumNew,long pageSize) {

        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("role")
                .localField("roleId")
                .foreignField("role_id")
                .as("adminRole");

        Criteria criteria = new Criteria();
        if (id!=null){
            criteria.and("id").ne(id);
        }
        if (StringUtils.hasText(keywords)){
            Pattern pattern= Pattern.compile("^.*"+keywords+".*$", Pattern.CASE_INSENSITIVE);
            criteria.and("name").regex(pattern);
        }
        //创建分页
        //Sort sort = Sort.by(Sort.Direction.ASC, "createTime");
        ProjectionOperation project  = Aggregation.project("id", "username", "name", "sex", "phone", "company", "enabled", "roleId", "createTime")
                .and("adminRole").as("adminRole");
        //,Aggregation.sort(sort)
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(criteria),Aggregation.skip(pageNumNew),Aggregation.limit(pageSize),lookupOperation,project,Aggregation.unwind("adminRole"));
        List<Map> results = mongoTemplate.aggregate(aggregation, "admin", Map.class).getMappedResults();
        List<Admin> admins = new ArrayList<>();
        for (Map result : results) {
            String str = JSON.toJSONString(result);
            String str2 = JSON.toJSONString(result.get("adminRole"));
            Admin admin = JSON.parseObject(str, Admin.class);
            admin.setRole(JSON.parseObject(str2, Role.class));
            admins.add(admin);
        }
        return admins;
    }

    @Override
    public Admin getByIdAdmin(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query,Admin.class);
    }

    @Override
    public Admin loadUserByUsername(String username) {
        try {
            Query query = new Query(Criteria.where("username").is(username));
            return mongoTemplate.findOne(query,Admin.class);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("该用户名不存在");
            return null;
        }
    }

    @Override
    public Long getAdminCount(String keywords, Integer id) {
        Criteria criteria = new Criteria();
        if (id!=null){
            criteria.and("id").ne(id);
        }
        if (StringUtils.hasText(keywords)){
            Pattern pattern= Pattern.compile("^.*"+keywords+".*$", Pattern.CASE_INSENSITIVE);
            criteria.and("name").regex(pattern);
        }
        Query query = new Query(criteria);
        return mongoTemplate.count(query,Admin.class);
    }
}
