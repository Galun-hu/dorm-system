package com.joy.dorm.system.dao.Impl;

import com.joy.dorm.system.dao.RoleDao;
import com.joy.dorm.system.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public int insert(Role role) {
        try {
            mongoTemplate.insert(role);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(Role role) {
        Query query = new Query(Criteria.where("id").is(role.get_id()));
        Update update = new Update();

        if (role.getName()!=null){
            update.set("name",role.getName());
        }
        if (role.getNameZh()!=null){
            update.set("nameZh",role.getNameZh());
        }
        try {
            mongoTemplate.updateFirst(query,update, Role.class);
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
            mongoTemplate.remove(query,Role.class);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Role> getAllRoles() {
        return mongoTemplate.findAll(Role.class);
    }

    @Override
    public Role getByIdRole(Integer id) {
        Query query = new Query(Criteria.where("role_id").is(id));
        return mongoTemplate.findOne(query,Role.class);
    }
}
