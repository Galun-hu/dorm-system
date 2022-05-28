package com.joy.dorm.dormitory.service;

import com.joy.dorm.dormitory.model.Administrator;
import com.joy.dorm.system.model.Admin;

import java.util.List;

public interface IAdministratorService {

    // 获取所有宿舍管理员信息
    List<Administrator> getDormAdmins();

    // 为宿舍楼添加管理员
    Integer insertDormAdminToBuilding(Integer building_id,String admin_id);

    // 为宿舍楼移除管理员
    void removeDormAdminToBuilding(String admin_id);

    // 为宿舍楼移除所有管理员
    void removeAllDormAdminToBuilding(Integer building_id);
}
