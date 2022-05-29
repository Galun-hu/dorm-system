package com.joy.dorm.visitor.dao.Impl;

import com.joy.dorm.visitor.dao.VisitorDao;
import com.joy.dorm.visitor.model.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

@Component
public class VisitorDaoImpl implements VisitorDao {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Visitor> getAllVisitor(String keywords, Integer id) {
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
        if (visitor.getName()!=null){
            update.set("name",visitor.getName());
        }
        if (visitor.getSex()!=null){
            update.set("sex",visitor.getSex());
        }
        if (visitor.getPhone()!=null){
            update.set("phone",visitor.getPhone());
        }
        if (visitor.getRemark()!=null){
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
}
