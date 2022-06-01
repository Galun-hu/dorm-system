package com.joy.dorm.dormitory.service;

import com.joy.dorm.dormitory.model.Administrator;
import com.joy.dorm.system.model.Admin;

import java.util.List;

public interface IAdministratorService {

    // 获取所有宿舍管理员信息
    List<Administrator> getDormAdmins(String keywords,Integer building_id,int pageNum,int pageSize);

    // 根据building_id获取所有宿舍管理员信息
    List<Administrator> getDormAdminsWithBuildingId(String keywords,Integer building_id,Integer pageNum,Integer pageSize);

    // 统计模糊查询结果的数量
    Long getAdministratorsCount(String keywords,Integer building_id);

    // 获取宿舍管理员名称
    List<Administrator> getNames();

//    // 根据宿舍管理员名字获取宿舍管理员信息
//    Administrator getDormAdminWithName(String name);
//
//    // 根据宿舍管理员id获取宿舍管理员信息
//    Administrator getDromAdminWithId(Integer id);


    // 添加管理员并绑定宿舍楼
    Integer insertDormAdminToBuilding(Administrator administrator);

    // 修改宿舍管理员信息
    Long updateDormAdminToBuilding(Administrator administrator);


    // 为宿舍楼移除管理员
    long removeDormAdminToBuilding(Integer admin_id);

    // 为宿舍楼移除所有管理员
    long removeAllDormAdminToBuilding(Integer building_id);

}
