package com.joy.dorm.system.dao.Impl;

import com.alibaba.fastjson.JSON;
import com.joy.dorm.system.dao.AdminDao;
import com.joy.dorm.system.model.Admin;
import com.joy.dorm.system.model.Role;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        if (!admin.getName().isEmpty()){
            update.set("name",admin.getName());
        }
        if (!admin.getPassword().isEmpty()){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            update.set("password",encoder.encode(admin.getPassword()));
        }
        if (!admin.getSex().isEmpty()){
            update.set("sex",admin.getSex());
        }
        if (!admin.getPhone().isEmpty()){
            update.set("phone",admin.getPhone());
        }
        if (!admin.getCompany().isEmpty()){
            update.set("company",admin.getCompany());
        }
        if (admin.getEnabled()!=null){
            update.set("enabled",admin.getEnabled());
        }
        if (!admin.getRemark().isEmpty()){
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
    public int delete(String id) {
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
    public List<Admin> getAllAdmin() {

        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("role")
                .localField("roleId")
                .foreignField("role_id")
                .as("adminRole");

        Aggregation aggregation = Aggregation.newAggregation(lookupOperation);
        List<Map> results = mongoTemplate.aggregate(aggregation, "admin", Map.class).getMappedResults();
        System.out.println(results);
        List<Admin> admins = new ArrayList<>();
        for (Map result : results) {
            List list = (List) result.get("adminRole");
            result.remove("adminRole");
            String str = JSON.toJSONString(result);
            String str2 = JSON.toJSONString(list.get(0));
            Admin admin = JSON.parseObject(str, Admin.class);
            admin.setRole(JSON.parseObject(str2, Role.class));
            admins.add(admin);
        }
        return admins;
    }

    @Override
    public Admin getByIdAdmin(String id) {
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
}
