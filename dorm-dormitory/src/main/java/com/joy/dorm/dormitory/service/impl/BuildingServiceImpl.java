package com.joy.dorm.dormitory.service.impl;

import com.joy.dorm.dormitory.dao.IAdministretorDao;
import com.joy.dorm.dormitory.dao.IBuildingDao;
import com.joy.dorm.dormitory.model.Administrator;
import com.joy.dorm.dormitory.model.Building;
import com.joy.dorm.dormitory.model.BuildingAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joy.dorm.dormitory.service.IBuildingService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BuildingServiceImpl implements IBuildingService {

    @Autowired
    private IBuildingDao buildingDao;

    @Autowired
    private IAdministretorDao administretorDao;

    @Override
    public List<Building> getBuildings(String keywords,Integer id,int pageNum,int pageSize){
        List<Building> buildings = buildingDao.findBuildings(keywords,id,pageNum,pageSize);
        return setAdminstrator(buildings);
    }


    @Override
    public Long getBuildingsCount(String keywords){
        return buildingDao.acountBuildings(keywords);
    }

    @Override
    public List<Building> getNames(){
        return buildingDao.findNames();
    }

    @Override
    public Building getBuildingWithId(int id){
        Building building = buildingDao.findBuildingById(id);
        return setAdminstrator(building);
    }

    @Override
    public Building getBuildingWithAdminId(Integer id){
        Integer building_id = administretorDao.findBuildingIdByDormAdminId(id);
        Building building = buildingDao.findBuildingById(building_id);
        return setAdminstrator(building);
    }

//    @Override
//    public Building getBuildingWithAdministrator(String name){
//        Administrator administrator = administretorDao.findAdministratorByName(name);
//        if (administrator != null) {
//            Integer build_id = administretorDao.findBuildingIdByDormAdminId(administrator.getId());
//            if (build_id != null){
//                Building building = buildingDao.findBuildingById(build_id);
//                return setAdminstrator(building);
//            }else {
//                return null;
//            }
//        }else {
//            return null;
//        }
//    }

    @Override
    public Integer addBuilding(Building building){
        building.setCreated(new Date());
        building.setId(null);
        building.set_id(null);
        building.setModified(new Date());
        return buildingDao.insertBuilding(building);
    }

    @Override
    public long updateBuilding(Building building){
        if (building.getId() == null){
            return 0;
        }else {
            building.setModified(new Date());
            return buildingDao.updateBuilding(building);
        }
    }

    @Override
    public long deleteBuilding(Integer id){
        return buildingDao.deleteBuildingBy_id(id);
    }

    @Override
    public List<Building> setAdminstrator(List<Building> buildings){
        for (int i = 0;i<buildings.size();i++){
            Building building = buildings.get(i);
            List<BuildingAdmin> buildingAdmins = administretorDao.findAllAdmintratorIdByBuildId(building.getId());
            if (buildingAdmins != null){
                List<Administrator> administrators = new ArrayList<>();
                for (int index = 0;index < buildingAdmins.size();index++){
                    administrators.add(administretorDao.findAdministratorById(buildingAdmins.get(index).getAdmin_id()));
                }
                building.setAdministrators(administrators);
            }

        }
        return buildings;
    }

    @Override
    public Building setAdminstrator(Building building){
        List<BuildingAdmin> buildingAdmins = administretorDao.findAllAdmintratorIdByBuildId(building.getId());
        if (buildingAdmins != null){
            List<Administrator> administrators = new ArrayList<>();
            for (int i = 0;i < buildingAdmins.size();i++){
                administrators.add(administretorDao.findAdministratorById(buildingAdmins.get(i).getAdmin_id()));
            }
            building.setAdministrators(administrators);
        }
        return building;
    }
}
