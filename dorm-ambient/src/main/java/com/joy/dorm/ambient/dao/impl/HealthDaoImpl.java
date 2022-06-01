package com.joy.dorm.ambient.dao.impl;

import com.joy.dorm.ambient.dao.IHealthDao;
import com.joy.dorm.ambient.model.Health;
import com.joy.dorm.common.utils.Tool;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
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

import java.util.List;
import java.util.regex.Pattern;

@Repository
public class HealthDaoImpl implements IHealthDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private Tool myTool;

    @Override
    public List<Health> findHealths(Integer building_id, String keywords,String building_type, Integer pageNum, Integer pageSize){
//        Criteria criteria = new Criteria();
//        if (building_id != null){
//            criteria.and("Building_id").is(building_id);
//        }
//        if (StringUtils.hasText(keywords)){
//            Pattern pattern= Pattern.compile("^.*"+keywords+".*$", Pattern.CASE_INSENSITIVE);
//            criteria.and("rome_id").regex(pattern);
//        }
//        PageRequest pageRequest = PageRequest.of(pageNum,pageSize);
//        Sort sort = Sort.by(Sort.Direction.DESC, "create_time");
//        Query query = new Query().addCriteria(criteria).with(pageRequest).with(sort);
//        return mongoTemplate.find(query,Health.class);

        LookupOperation lookup = LookupOperation.newLookup()
                .from("t_building")
                .localField("building_id")
                .foreignField("id")
                .as("t_b");
        ProjectionOperation project = Aggregation.project("_id","id","building_id","rome_id","floor",
                        "health_level","create_time").and("t_b.name").as("building_name")
                        .and("t_b.type").as("building_type");
        Criteria criteria = new Criteria();
        if (building_id != null){
            criteria.and("building_id").is(building_id);
        }
        if (StringUtils.hasText(keywords)){
            Pattern pattern= Pattern.compile("^.*"+keywords+".*$", Pattern.CASE_INSENSITIVE);
            criteria.and("building_name").regex(pattern);
        }
        if (StringUtils.hasText(building_type)){
            criteria.and("building_type").is(building_type);
        }

        Aggregation aggregation = Aggregation.newAggregation(lookup,project,
                Aggregation.match(criteria),
                Aggregation.skip(pageNum*pageSize),
                Aggregation.limit(pageSize),
                Aggregation.unwind("building_name"),
                Aggregation.unwind("building_type"));
        List<Health> healths = mongoTemplate.aggregate(aggregation,"t_health",Health.class).getMappedResults();
        return healths;
    }

    @Override
    public Long acountHealths(String building_type,String keywords,Integer building_id){
//        Pattern pattern= Pattern.compile("^.*"+keywords+".*$", Pattern.CASE_INSENSITIVE);
//        Criteria criteria = new Criteria().and("building_name").regex(pattern);
//        Query query = new Query().addCriteria(criteria);
        LookupOperation lookup = LookupOperation.newLookup()
                .from("t_building")
                .localField("building_id")
                .foreignField("id")
                .as("t_b");
        ProjectionOperation project = Aggregation.project("_id","id","building_id","rome_id","floor",
                        "health_level","create_time").and("t_b.name").as("building_name")
                .and("t_b.type").as("building_type");
        Criteria criteria = new Criteria();
        if (building_id != null){
            criteria.and("building_id").is(building_id);
        }
        if (StringUtils.hasText(keywords)){
            Pattern pattern= Pattern.compile("^.*"+keywords+".*$", Pattern.CASE_INSENSITIVE);
            criteria.and("building_name").regex(pattern);
        }
        if (StringUtils.hasText(building_type)){
            criteria.and("building_type").is(building_type);
        }

        Aggregation aggregation = Aggregation.newAggregation(lookup,project,
                Aggregation.match(criteria),
                Aggregation.unwind("building_name"),
                Aggregation.unwind("building_type"));
        List<Health> healths = mongoTemplate.aggregate(aggregation,"t_health",Health.class).getMappedResults();
//        return healths;
        return Long.valueOf(healths.size());
    }

    @Override
    public Integer insertHealth(Health health) {
        Health health1 = mongoTemplate.insert(health);
        if (health1 != null) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public long updateHealth(Health health){
        Update update = myTool.updateFields(health);
        Query query = new Query(Criteria.where("id").is(health.getId()));
        UpdateResult result = mongoTemplate.updateFirst(query,update,Health.class);
        return result.getModifiedCount();
    }

    @Override
    public long deleteHealth(Integer id){
        Query query = new Query(Criteria.where("id").is(id));
        DeleteResult result = mongoTemplate.remove(query,Health.class);
        return result.getDeletedCount();
    }
}
