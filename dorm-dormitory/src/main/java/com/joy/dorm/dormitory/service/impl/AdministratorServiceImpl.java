package com.joy.dorm.dormitory.service.impl;

import com.joy.dorm.dormitory.dao.IAdministretorDao;
import com.joy.dorm.dormitory.model.Administrator;
import com.joy.dorm.dormitory.service.IAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorServiceImpl implements IAdministratorService {

    @Autowired
    private IAdministretorDao administretorDao;

    @Override
    public List<Administrator> getDormAdmins(){
        return administretorDao.findAdministrators();
    }

    @Override
    public Integer insertDormAdminInBuilding(Integer building_id,String admin_id){
        return administretorDao.insertDormAdminInBuilding(building_id,admin_id);
    }


    public void removeDormAdminInBuilding(String admin_id){
        administretorDao.deleteDromAdminInBuilding(admin_id);
    }

    @Override
    public void removeDormAdminInBuilding(Integer building_id){
        administretorDao.deleteAllDromAdminInBuilding(building_id);
    }
}
