package com.joy.dorm.ambient.service;

import com.joy.dorm.ambient.model.Health;

import java.util.List;

public interface IHealthService {

    // 获取卫生信息(模糊分页查询)
    List<Health> getHealths(Integer building_id,String keywords,String building_type,Integer pageNum,Integer pageSize);

    // 统计模糊查询的结果数量
    Long getHealthsCount(String building_type,String keywords,Integer building_id);

    // 添加卫生信息
    Integer addHealth(Health health);

    // 更新卫生信息
    long updateHealth(Health health);

    // 删除卫生信息
    long deleteHealth(Integer id);


}
