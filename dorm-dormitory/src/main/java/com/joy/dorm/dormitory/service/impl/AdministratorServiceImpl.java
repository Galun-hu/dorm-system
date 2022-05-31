package com.joy.dorm.dormitory.service.impl;

import com.joy.dorm.dormitory.dao.IAdministretorDao;
import com.joy.dorm.dormitory.model.Administrator;
import com.joy.dorm.dormitory.service.IAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class AdministratorServiceImpl implements IAdministratorService {

    @Autowired
    private IAdministretorDao administretorDao;

    @Override
    public List<Administrator> getDormAdmins(String keywords,Integer building_id,int pageNum,int pageSize){
        return administretorDao.findAdministrators(keywords,building_id,pageNum,pageSize);
    }

    @Override
    public List<Administrator> getDormAdminsWithBuildingId(String keywords,Integer building_id,int pageNum,int pageSize){
        return  administretorDao.findAdministratorsWithBuildingId(keywords,building_id,pageNum,pageSize);
    }

    @Override
    public Long getAdministratorsCount(String keywords){
        return administretorDao.acountAdministrators(keywords);
    }

//    @Override
//    public Administrator getDormAdminWithName(String name){
//        return administretorDao.findAdministratorByName(name);
//    }

//    @Override
//    public Administrator getDromAdminWithId(Integer id){
//        return administretorDao.findAdministratorById(id);
//    }


    @Override
    public Integer insertDormAdminToBuilding(Integer building_id,Integer admin_id){
        return administretorDao.insertDormAdminToBuilding(building_id,admin_id);
    }


    public long removeDormAdminToBuilding(Integer admin_id){
        return administretorDao.deleteDromAdminToBuilding(admin_id);
    }

    @Override
    public long removeAllDormAdminToBuilding(Integer building_id){
        return administretorDao.deleteAllDromAdminToBuilding(building_id);
    }
}
