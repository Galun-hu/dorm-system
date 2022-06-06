package com.joy.dorm.dormitory.service.impl;

import com.joy.dorm.dormitory.dao.IAdministretorDao;
import com.joy.dorm.dormitory.model.Administrator;
import com.joy.dorm.dormitory.model.BuildingAdmin;
import com.joy.dorm.dormitory.service.IAdministratorService;
import com.joy.dorm.system.dao.AdminDao;
import com.joy.dorm.system.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class AdministratorServiceImpl implements IAdministratorService {

    @Autowired
    private IAdministretorDao administretorDao;


    @Autowired
    private AdminDao adminDao;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public List<Administrator> getDormAdmins(String keywords,Integer building_id,int pageNum,int pageSize){
        return administretorDao.findAdministrators(keywords,building_id,pageNum,pageSize);
    }

    @Override
    public List<Administrator> getDormAdminsWithBuildingId(String keywords,Integer building_id,Integer pageNum,Integer pageSize){
        return  administretorDao.findAdministratorsWithBuildingId(keywords,building_id,pageNum,pageSize);
//        return null;
    }

    @Override
    public Long getAdministratorsCount(String keywords,Integer building_id){
        return administretorDao.acountAdministrators(keywords,building_id);
    }


    @Override
    public List<Administrator> getNames(){
        return administretorDao.findNames();
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
    public Integer insertDormAdminToBuilding(Administrator administrator){
        if (administretorDao.findAdministratorByUserName(administrator.getUsername()) != null){
            return -2;
        }
        //在这里添加都是宿舍管理员
        administrator.setRoleId(2);
        administrator.setEnabled(true);
        administrator.setBuilding_name(null);
        if (StringUtils.hasText(administrator.getPassword())){
            administrator.setPassword(encoder.encode(administrator.getPassword()));
        }
        if (!StringUtils.hasText(administrator.getRemark())){
            administrator.setRemark("");
        }
        Integer building_id = administrator.getBuilding_id();
        administrator.setBuilding_id(null);
        administrator.setCreateTime(new Date());
        Administrator administrator1 = administretorDao.insertAdministrator(administrator);
        if (administrator1 != null){
            return administretorDao.insertDormAdminToBuilding(building_id,administrator1.getId());
        }else {
            return -1;
        }

    }

    @Override
    public Long updateDormAdminToBuilding(Administrator administrator) {
        if (administrator.getId() == null) {
            return Long.valueOf(-1);
        }
        administrator.setBuilding_name(null);
        if (administrator.getBuilding_id() != null) {
            Integer building_id = administretorDao.findBuildingIdByDormAdminId(administrator.getId());
            if (building_id == null || building_id != administrator.getBuilding_id()) {
                BuildingAdmin buildingAdmin = new BuildingAdmin();
                buildingAdmin.setAdmin_id(administrator.getId());
                buildingAdmin.setBuilding_id(administrator.getBuilding_id());
                Long result = administretorDao.updateAdministratorToBuilding(buildingAdmin);
                if (result < 1) {
                    return Long.valueOf(-2);
                }
                administrator.setBuilding_id(null);
                Long result1 = administretorDao.updateAdministrator(administrator);
                if (result1 == 0) {
                    return Long.valueOf(-3);
                } else {
                    return result1;
                }
            }else {
                administrator.setBuilding_id(null);
                return administretorDao.updateAdministrator(administrator);
            }
        } else {
            administrator.setBuilding_id(null);
            return administretorDao.updateAdministrator(administrator);
        }
    }



    public long removeDormAdminToBuilding(Integer admin_id){
        Integer result = adminDao.delete(admin_id);
        if (result == 0){
            return -1;
        }
        return administretorDao.deleteDromAdminToBuilding(admin_id);
    }

    @Override
    public long removeAllDormAdminToBuilding(Integer building_id){
        return administretorDao.deleteAllDromAdminToBuilding(building_id);
    }
}
