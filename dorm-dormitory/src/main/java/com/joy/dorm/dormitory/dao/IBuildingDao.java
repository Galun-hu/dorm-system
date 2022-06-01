package com.joy.dorm.dormitory.dao;


import com.joy.dorm.dormitory.model.Administrator;
import com.joy.dorm.dormitory.model.Building;
import com.joy.dorm.dormitory.model.BuildingAdmin;

import java.util.List;

public interface IBuildingDao {


    /**
     * 查询所有宿舍信息
     */
    List<Building> findBuildings(String keywords,Integer id,int pageNum,int pageSize);

    /**
     * 统计模糊查询结果数量
     * @param keywords
     * @return
     */
    Long acountBuildings(String keywords);

    /**
     * 获取所有宿舍楼的名称
     * @return
     */
    List<Building> findNames();

    /**
     * 根据宿舍楼栋号查询宿舍信息
     * @param id
     * @return
     */
    Building findBuildingById(int id);

//    /**
//     * 根据_id获取宿舍楼id
//     * @param String
//     * @return
//     */
//    Building findBuildingBy_id(String String);




    /**
     * 更新宿舍信息数据
     * @param building
     */
    long updateBuilding(Building building);

    /**
     * 添加宿舍信息
     */
    Integer insertBuilding(Building building);

    /**
     * 根据_id删除宿舍信息
     * @param _id
     */
    long deleteBuildingBy_id(Integer _id);


    // 查询所有宿舍楼对应的管理员
    List<Building> getAllBuildingAndAdmin(String keywords,Integer id,int pageNum,int pageSize);
}
