package com.joy.dorm.repair.service;

import com.joy.dorm.repair.dao.Impl.RepairDaoImpl;
import com.joy.dorm.repair.model.Repair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairService {

    @Autowired
    RepairDaoImpl repairDao;

    public List<Repair> getAllRepair(String keywords, Integer id) {
        return repairDao.getAllRepair(keywords,id);
    }

    public int addRepair(Repair repair) {
        return repairDao.addRepair(repair);
    }

    public int updateRepair(Repair repair) {
        return repairDao.updateRepair(repair);
    }

    public int deleteRepair(Integer id) {
        return repairDao.deleteRepair(id);
    }
}
