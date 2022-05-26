package service.impl;

import dao.IBuildingDao;
import model.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.IBuildingService;

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
