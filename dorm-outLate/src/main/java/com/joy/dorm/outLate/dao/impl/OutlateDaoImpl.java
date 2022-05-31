package com.joy.dorm.outLate.dao.impl;

import com.joy.dorm.common.utils.Tool;
import com.joy.dorm.outLate.dao.IOutlateDao;
import com.joy.dorm.outLate.model.Outlate;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
public class OutlateDaoImpl implements IOutlateDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private Tool myTool;

    @Override
    public List<Outlate> findOutlate(Integer building_id,String keywords,String building_type,Integer pageNum,Integer pageSize){
        LookupOperation lookup = LookupOperation.newLookup()
                .from("t_building")
                .localField("building_id")
                .foreignField("id")
                .as("t_b");
        ProjectionOperation project = Aggregation.project("_id","id","student_id","name","phone","building_id",
                "rome_id","outlate_time").and("t_b.name").as("building_name")
                .and("t_b.type").as("building_type");
        Criteria criteria = new Criteria();
        if (building_id != null){
            criteria.and("building_id").is(building_id);
        }
        if (StringUtils.hasText(keywords)){
            Pattern pattern= Pattern.compile("^.*"+keywords+".*$", Pattern.CASE_INSENSITIVE);
            criteria.and("name").regex(pattern);
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
        List<Outlate> outlates = mongoTemplate.aggregate(aggregation,"t_outlate",Outlate.class).getMappedResults();
        return outlates;
    }

    @Override
    public Long acountOutlate(String keywords){
        Pattern pattern= Pattern.compile("^.*"+keywords+".*$", Pattern.CASE_INSENSITIVE);
        Criteria criteria = new Criteria().and("name").regex(pattern);
        Query query = new Query(criteria);
        return Long.valueOf(mongoTemplate.find(query,Outlate.class).size());
    }

//    @Override
//    public List<Outlate> findAllOutlateByBuildingId(Integer building_id){
//        LookupOperation lookup = LookupOperation.newLookup()
//                .from("t_building")
//                .localField("building_id")
//                .foreignField("id")
//                .as("t_b");
//        ProjectionOperation project = Aggregation.project("_id","id","student_id","name","phone","building_id",
//                        "rome_id","outlate_time").and("t_b.name").as("building_name")
//                .and("t_b.type").as("building_type");
//        Criteria criteria = new Criteria().and("building_id").is(building_id);
//        Aggregation aggregation = Aggregation.newAggregation(lookup,project,Aggregation.match(criteria),
//                Aggregation.unwind("building_name"),
//                Aggregation.unwind("building_type"));
//        List<Outlate> outlates = mongoTemplate.aggregate(aggregation,"t_outlate",Outlate.class).getMappedResults();
//        return outlates;
//    }
//
//    @Override
//    public List<Outlate> findAllOutlateByBuildingIdAdndBuildingType(Integer building_id,String building_type){
//        LookupOperation lookup = LookupOperation.newLookup()
//                .from("t_building")
//                .localField("building_id")
//                .foreignField("id")
//                .as("t_b");
//        ProjectionOperation project = Aggregation.project("_id","id","student_id","name","phone","building_id",
//                        "rome_id","outlate_time").and("t_b.name").as("building_name")
//                .and("t_b.type").as("building_type");
//        Criteria criteria = new Criteria().and("building_id").is(building_id).and("building_type").is(building_type);
//        Aggregation aggregation = Aggregation.newAggregation(lookup,project,Aggregation.match(criteria),
//                Aggregation.unwind("building_name"),
//                Aggregation.unwind("building_type"));
//        List<Outlate> outlates = mongoTemplate.aggregate(aggregation,"t_outlate",Outlate.class).getMappedResults();
//        return outlates;
//    }

    // 添加晚归信息
    public Integer insertOutlate(Outlate outlate){
        outlate.setId(null);
        Outlate outlateResult = mongoTemplate.insert(outlate);
        if (outlateResult != null){
            return 1;
        }else {return 0;}
    }

    // 修改晚归信息
    @Override
    public long updateOutlate(Outlate outlate){
        Update update = myTool.updateFields(outlate);
        Query query = new Query(Criteria.where("id").is(outlate.getId()));
        UpdateResult result = mongoTemplate.updateFirst(query,update,Outlate.class);
        return result.getModifiedCount();
    }

    @Override
    public long deleteOutlateById(Integer id){
        Query query = new Query(Criteria.where("id").is(id));
        DeleteResult result = mongoTemplate.remove(query,Outlate.class);
        return result.getDeletedCount();
    }
}
