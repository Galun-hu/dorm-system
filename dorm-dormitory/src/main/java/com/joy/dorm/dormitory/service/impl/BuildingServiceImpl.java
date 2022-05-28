package com.joy.dorm.dormitory.service.impl;

import com.joy.dorm.dormitory.dao.IAdministretorDao;
import com.joy.dorm.dormitory.dao.IBuildingDao;
import com.joy.dorm.dormitory.model.Administrator;
import com.joy.dorm.dormitory.model.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joy.dorm.dormitory.service.IBuildingService;

import java.util.Date;
import java.util.List;

@Service
public class BuildingServiceImpl implements IBuildingService {

    @Autowired
    private IBuildingDao buildingDao;

    @Autowired
    private IAdministretorDao administretorDao;

    @Override
    public List<Building> getBuildings(){
        return buildingDao.findBuildings();
    }

    @Override
    public Building getBuildingWithId(int id){
        return buildingDao.findBuildingById(id);
    }

    @Override
    public Building getBuildingWithAdminId(Integer id){
        Integer building_id = administretorDao.findBuildingIdByDormAdminId(id);
        return buildingDao.findBuildingById(building_id);
    }

    @Override
    public Building getBuildingWithAdministrator(String name){
        Administrator administrator = administretorDao.findAdministratorByName(name);
        if (administrator != null) {
            Integer build_id = administretorDao.findBuildingIdByDormAdminId(administrator.getId());
            if (build_id != null){
                return buildingDao.findBuildingById(build_id);
            }else {
                return null;
            }
        }else {
            return null;
        }
    }

    @Override
    public Integer addBuilding(Building building){
        building.setCreated(new Date().toString());
        building.setId(null);
        building.set_id(null);
        building.setModified(new Date().toString());
        return buildingDao.insertBuilding(building);
    }

    @Override
    public long updateBuilding(Building building){
        if (building.get_id() == null || building.get_id() == ""){
            return 0;
        }else {
            building.setModified(new Date().toString());
            return buildingDao.updateBuilding(building);
        }
    }

    @Override
    public long deleteBuilding(String _id){
        return buildingDao.deleteBuildingBy_id(_id);
    }
}
