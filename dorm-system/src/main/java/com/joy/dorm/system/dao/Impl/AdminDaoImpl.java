package com.joy.dorm.system.dao.Impl;

import com.joy.dorm.system.dao.AdminDao;
import com.joy.dorm.system.model.Admin;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

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
            update.set("password",admin.getPassword());
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
        return mongoTemplate.findAll(Admin.class);
    }

    @Override
    public Admin getByIdAdmin(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query,Admin.class);
    }

    @Override
    public Admin loadUserByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        return mongoTemplate.findOne(query,Admin.class);
    }
}
