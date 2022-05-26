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
}
