package com.joy.dorm.dormitory.dao;

import com.joy.dorm.dormitory.model.Administrator;
import com.joy.dorm.system.model.Admin;

import java.util.List;

public interface IAdministretorDao {

    // 获取所有宿舍管理员信息
    List<Administrator> findAdministrators();

    // 根据宿舍管理员id获取宿舍楼id
    Integer findBuildingIdByDormAdminId(int id);

    // 根据宿舍管理员名字获取宿舍管理员信息
    Administrator findAdministratorByName(String name);

    // 系统管理员关联指定宿舍楼
    Integer insertDormAdminInBuilding(Integer building_id,String admin_id);

    // 删除宿舍楼关联的指定管理员
    void deleteDromAdminInBuilding(String admin_id);

    // 删除宿舍楼关联的所有管理员
    void deleteAllDromAdminInBuilding(Integer building_id);

}
