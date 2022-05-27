package com.joy.dorm.dormitory.service.impl;

import com.joy.dorm.dormitory.dao.IBuildingDao;
import com.joy.dorm.dormitory.model.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joy.dorm.dormitory.service.IBuildingService;

import java.util.List;

@Service
public class BuildingServiceImpl implements IBuildingService {

    @Autowired
    private IBuildingDao buildingDao;

    @Override
    public List<Building> getBuildings(){
        return buildingDao.findBuildings();
    }

    @Override
    public Building getBuildingWithId(int id){
        return buildingDao.findBuildingById(id);
    }

//    @Override
//    public Building getBuildingWithAdministrator(String name){
//
//    }

    @Override
    public void addBuilding(Building building){
        buildingDao.insertBuilding(building);
    }

    @Override
    public void updateBuilding(Building building){
        buildingDao.updateBuilding(building);
    }

    @Override
    public void deleteBuilding(String _id){
        buildingDao.deleteBuildingBy_id(_id);
    }
}
