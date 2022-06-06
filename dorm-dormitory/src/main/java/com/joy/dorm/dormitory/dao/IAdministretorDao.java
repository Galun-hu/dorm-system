package com.joy.dorm.dormitory.dao;

import com.joy.dorm.dormitory.model.Administrator;
import com.joy.dorm.dormitory.model.BuildingAdmin;
import com.joy.dorm.system.model.Admin;

import java.util.List;
import java.util.Map;

public interface IAdministretorDao {

    // 获取所有宿舍管理员信息
    List<Administrator> findAdministrators(String keywords,Integer building_id,int pageNum,int pageSize);

    // 根据building_id获取所有宿舍管理员信息
    List<Administrator> findAdministratorsWithBuildingId(String keywords, Integer building_id, Integer pageNum, Integer pageSize);

    Long acountAdministrators(String keywords,Integer building_id);

    // 根据宿舍管理员id获取宿舍楼id
    Integer findBuildingIdByDormAdminId(int id);

    // 获取宿舍管理员名称
    List<Administrator> findNames();

    // 根据宿舍管理员名字获取宿舍管理员信息
    Administrator findAdministratorByUserName(String username);

    // 根据管理员id获取管理员信息
    Administrator findAdministratorById(Integer id);

    // 根据宿舍楼id获取所有管理此宿舍楼的管理员信息
    List<BuildingAdmin> findAllAdmintratorIdByBuildId(Integer id);

    // 系统管理员关联指定宿舍楼
    Integer insertDormAdminToBuilding(Integer building_id,Integer admin_id);

    // 添加宿舍管理员
    Administrator insertAdministrator(Administrator administrator);

    // 修改宿舍管理员绑定的宿舍楼id
    Long updateAdministratorToBuilding(BuildingAdmin buildingAdmin);

    // 修改宿舍管理员
    Long updateAdministrator(Administrator administrator);

    // 删除宿舍楼关联的指定管理员
    long deleteDromAdminToBuilding(Integer admin_id);

    // 删除宿舍楼关联的所有管理员
    long deleteAllDromAdminToBuilding(Integer building_id);


}
