package dao.impl;

import dao.IBuildingDao;
import model.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BuildingDaoImpl implements IBuildingDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Building> findBuildings(){
        return mongoTemplate.findAll(Building.class);
    }

}
