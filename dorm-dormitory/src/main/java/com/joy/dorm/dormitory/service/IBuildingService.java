package com.joy.dorm.dormitory.service;

import com.joy.dorm.dormitory.model.Building;

import java.util.List;

public interface IBuildingService {
    // 获取所有宿舍楼栋信息
    List<Building> getBuildings();

    // 根据宿舍楼栋号获取宿舍信息
    Building getBuildingWithId(int id);

    // 根据管理员姓名获取宿舍信息
    Building getBuildingWithAdministrator(String name);

    // 添加宿舍楼栋信息
    Integer addBuilding(Building building);

    // 更新宿舍信息
    long updateBuilding(Building building);

    // 根据_id删除宿舍信息
    long deleteBuilding(String _id);
}
