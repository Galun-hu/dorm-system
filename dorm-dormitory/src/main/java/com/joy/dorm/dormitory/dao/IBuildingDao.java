package com.joy.dorm.dormitory.dao;


import com.joy.dorm.dormitory.model.Building;

import java.util.List;

public interface IBuildingDao {


    /**
     * 查询所有宿舍信息
     */
    List<Building> findBuildings();

    /**
     * 根据宿舍楼栋号查询宿舍信息
     * @param id
     * @return
     */
    Building findBuildingById(int id);

    /**
     * 根据_id获取宿舍楼id
     * @param String
     * @return
     */
    Building findBuildingBy_id(String String);

//    /**
//     * 根据管理员姓名查询宿舍信息
//     * @param name
//     * @return
//     */
//    Building findBuildingByAdministrator(String name);

    /**
     * 更新宿舍信息数据
     * @param building
     */
    long updateBuilding(Building building);

    /**
     * 添加宿舍信息
     */
    void insertBuilding(Building building);

    /**
     * 根据_id删除宿舍信息
     * @param _id
     */
    long deleteBuildingBy_id(String _id);
}
