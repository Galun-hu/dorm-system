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
    public Integer insertDormAdminToBuilding(Integer building_id,String admin_id){
        return administretorDao.insertDormAdminToBuilding(building_id,admin_id);
    }


    public void removeDormAdminToBuilding(String admin_id){
        administretorDao.deleteDromAdminToBuilding(admin_id);
    }

    @Override
    public void removeAllDormAdminToBuilding(Integer building_id){
        administretorDao.deleteAllDromAdminToBuilding(building_id);
    }
}
