package com.joy.dorm.system.dao;

import com.joy.dorm.system.model.Admin;

import java.util.List;

public interface AdminDao {

    //添加管理员
    int insert(Admin admin);

    //修改管理员
    int update(Admin admin);

    //删除管理员
    int delete(Integer id);

    //查询所有管理员
    List<Admin> getAllAdmin(String keywords,Integer id,int pageNumNew,int pageSize);

    //根据id查管理员
    Admin getByIdAdmin(Integer id);

    //根据用户名查询管理员
    Admin loadUserByUsername(String username);

    //获取总记录数
    Long getAdminCount(String keywords, Integer id);
}
