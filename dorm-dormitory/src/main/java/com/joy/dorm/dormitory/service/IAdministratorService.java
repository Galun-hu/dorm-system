package com.joy.dorm.dormitory.service;

import com.joy.dorm.dormitory.model.Administrator;
import com.joy.dorm.system.model.Admin;

import java.util.List;

public interface IAdministratorService {

    // 获取所有宿舍管理员信息
    List<Administrator> getDormAdmins(String keywords,int pageNum,int pageSize);

    // 统计模糊查询结果的数量
    Long getAdministratorsCount(String keywords);

//    // 根据宿舍管理员名字获取宿舍管理员信息
//    Administrator getDormAdminWithName(String name);
//
//    // 根据宿舍管理员id获取宿舍管理员信息
//    Administrator getDromAdminWithId(Integer id);


    // 为宿舍楼添加管理员
    Integer insertDormAdminToBuilding(Integer building_id,Integer admin_id);

    // 为宿舍楼移除管理员
    long removeDormAdminToBuilding(Integer admin_id);

    // 为宿舍楼移除所有管理员
    long removeAllDormAdminToBuilding(Integer building_id);

}
