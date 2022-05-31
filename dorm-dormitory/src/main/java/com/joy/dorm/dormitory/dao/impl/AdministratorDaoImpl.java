package com.joy.dorm.dormitory.dao.impl;

import com.joy.dorm.dormitory.dao.IAdministretorDao;
import com.joy.dorm.dormitory.model.Administrator;
import com.joy.dorm.dormitory.model.Building;
import com.joy.dorm.dormitory.model.BuildingAdmin;
import com.joy.dorm.system.dao.RoleDao;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

@Repository
public class AdministratorDaoImpl implements IAdministretorDao {
    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public List<Administrator> findAdministrators(String keywords,Integer building_id,int pageNum,int pageSize){
        Criteria criteria = new Criteria();
        if (StringUtils.hasText(keywords)){
            Pattern pattern= Pattern.compile("^.*"+keywords+".*$", Pattern.CASE_INSENSITIVE);
            criteria.and("name").regex(pattern);
        }
        PageRequest pageRequest = PageRequest.of(pageNum,pageSize);
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Query query = new Query().addCriteria(criteria).with(sort).with(pageRequest);
        return mongoTemplate.find(query, Administrator.class);
    }

    @Override
    public List<Administrator> findAdministratorsWithBuildingId(String keywords,Integer building_id,int pageNum,int pageSize){
        LookupOperation lookup = LookupOperation.newLookup()
                .from("t_building_admin")
                .localField("id")
                .foreignField("admin_id")
                .as("t_b_a");
        ProjectionOperation project = Aggregation.project("_id","id","name","sex","age","phone",
                "created","modified").and("t_b_a.building_id").as("building_id");
        Criteria criteria = new Criteria();
        if (building_id != null){
            criteria.and("building_id").is(building_id);
        }
        if (StringUtils.hasText(keywords)){
            Pattern pattern= Pattern.compile("^.*"+keywords+".*$", Pattern.CASE_INSENSITIVE);
            criteria.and("name").regex(pattern);
        }

        Aggregation aggregation = Aggregation.newAggregation(lookup,project,
                Aggregation.match(criteria),
                Aggregation.skip(pageNum*pageSize),
                Aggregation.limit(pageSize),
                Aggregation.unwind("building_id"));
        List<Administrator> administrators = mongoTemplate.aggregate(aggregation,"admin",Administrator.class).getMappedResults();
        return administrators;
    }

    @Override
    public Long acountAdministrators(String keywords){
        Pattern pattern= Pattern.compile("^.*"+keywords+".*$", Pattern.CASE_INSENSITIVE);
        Criteria criteria = new Criteria().and("name").regex(pattern);
        Query query = new Query(criteria);
        return Long.valueOf(mongoTemplate.find(query,Administrator.class).size());
    }

    @Override
    public Integer findBuildingIdByDormAdminId(int id){
        LookupOperation lookup = LookupOperation.newLookup()
                .from("admin")
                .localField("admin_id")
                .foreignField("id")
                .as("a");
        ProjectionOperation project = Aggregation.project("building_id","admin_id");
        Criteria criteria = new Criteria().and("admin_id").is(id);
        Aggregation aggregation = Aggregation.newAggregation(lookup,project,Aggregation.match(criteria));
        BuildingAdmin buildingAdmin = mongoTemplate.aggregate(aggregation, "t_building_admin", BuildingAdmin.class).getUniqueMappedResult();
        if (buildingAdmin != null) {
            return buildingAdmin.getBuilding_id();
        }else {
            return null;
        }
    }
//
//    @Override
//    public Administrator findAdministratorByName(String name){
//        Query query = new Query(Criteria.where("name").is(name));
//        Administrator administrator = mongoTemplate.findOne(query,Administrator.class);
////        ProjectionOperation project = Aggregation.project("_id","id","name","sex","phone",
////                "company","createTime");
////        Criteria criteria = new Criteria().and("name").is(name);
////        Aggregation aggregation = Aggregation.newAggregation(project,Aggregation.match(criteria));
////        Administrator administrator = mongoTemplate.aggregate(aggregation,"admin",Administrator.class).getUniqueMappedResult();
//        return administrator;
//    }

    @Override
    public Administrator findAdministratorById(Integer id){
        Query query = new Query(Criteria.where("id").is(id));
        Administrator administrator = mongoTemplate.findOne(query,Administrator.class);
        return administrator;
    }

    @Override
    public List<BuildingAdmin> findAllAdmintratorIdByBuildId(Integer id){
        Query query = new Query(Criteria.where("building_id").is(id));
        List<BuildingAdmin> buildingAdmins = mongoTemplate.find(query,BuildingAdmin.class);
        return buildingAdmins;
    }

    @Override
    public Integer insertDormAdminToBuilding(Integer building_id,Integer admin_id){
        BuildingAdmin buildingAdmin = new BuildingAdmin();
        buildingAdmin.setBuilding_id(building_id);
        buildingAdmin.setAdmin_id(admin_id);
        BuildingAdmin buildingAdmin1 = mongoTemplate.insert(buildingAdmin);
        if (buildingAdmin != null){
            return 1;
        }else {return 0;}
    }

    @Override
    public long deleteDromAdminToBuilding(Integer admin_id){
        Query query = new Query(Criteria.where("admin_id").is(admin_id));
        DeleteResult result = mongoTemplate.remove(query,BuildingAdmin.class);
        return result.getDeletedCount();
    }

    @Override
    public long deleteAllDromAdminToBuilding(Integer building_id){
        Query query = new Query(Criteria.where("building_id").is(building_id));
        List<BuildingAdmin> result = mongoTemplate.findAllAndRemove(query,BuildingAdmin.class);
        return result.size();
    }
}
