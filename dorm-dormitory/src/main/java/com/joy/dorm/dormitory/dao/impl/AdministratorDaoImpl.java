package com.joy.dorm.dormitory.dao.impl;

import com.joy.dorm.dormitory.dao.IAdministretorDao;
import com.joy.dorm.dormitory.model.Administrator;
import com.joy.dorm.dormitory.model.BuildingAdmin;
import com.joy.dorm.system.dao.RoleDao;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdministratorDaoImpl implements IAdministretorDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Administrator> findAdministrators(){
        ProjectionOperation project = Aggregation.project("_id","id","name","sex","phone",
                "company","createTime","roleId");
        Criteria criteria = new Criteria().and("roleId").is(2);
        Aggregation aggregation = Aggregation.newAggregation(project,Aggregation.match(criteria));
        List<Administrator> administrators = mongoTemplate.aggregate(aggregation,"admin",Administrator.class).getMappedResults();
        return administrators;
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

    @Override
    public Administrator findAdministratorByName(String name){
        Query query = new Query(Criteria.where("name").is(name));
        Administrator administrator = mongoTemplate.findOne(query,Administrator.class);
//        ProjectionOperation project = Aggregation.project("_id","id","name","sex","phone",
//                "company","createTime");
//        Criteria criteria = new Criteria().and("name").is(name);
//        Aggregation aggregation = Aggregation.newAggregation(project,Aggregation.match(criteria));
//        Administrator administrator = mongoTemplate.aggregate(aggregation,"admin",Administrator.class).getUniqueMappedResult();
        return administrator;
    }

    @Override
    public Administrator findAdministratorById(Integer id){
        Query query = new Query(Criteria.where("id").is(id));
        Administrator administrator = mongoTemplate.findOne(query,Administrator.class);
        return administrator;
    }

    @Override
    public Integer insertDormAdminToBuilding(Integer building_id,String admin_id){
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
