package com.joy.dorm.ambient.dao;

import com.joy.dorm.ambient.model.Health;

import java.util.List;

public interface IHealthDao {
    // 获取卫生信息
    List<Health> findHealths(Integer building_id, String keywords,String building_type, Integer pageNum, Integer pageSize);

    // 统计模糊查询结果的数量
    Long acountHealths(String keywords);

    // 添加卫生信息
    Integer insertHealth(Health health);

    // 更新卫生信息
    long updateHealth(Health health);

    // 删除卫生信息
    long deleteHealth(Integer id);
}
