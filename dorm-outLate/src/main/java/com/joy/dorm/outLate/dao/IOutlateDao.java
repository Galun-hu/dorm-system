package com.joy.dorm.outLate.dao;

import com.joy.dorm.outLate.model.Outlate;

import java.util.List;

public interface IOutlateDao {

    // 获取所有晚归信息
    List<Outlate> findOutlate(Integer building_id,String keywords,String building_type,Integer pageNum,Integer pageSize);

//    // 根据building_id获取所有晚归信息
//    List<Outlate> findAllOutlateByBuildingId(Integer building_id);
//
//    // 根据building_id和building_type获取所有晚归信息
//    List<Outlate> findAllOutlateByBuildingIdAdndBuildingType(Integer building_id,String building_type);

    // 统计模糊查询结果的数量
    Long acountOutlate(String keywords);

    // 添加晚归信息
    Integer insertOutlate(Outlate outlate);

    // 修改晚归信息
    long updateOutlate(Outlate outlate);

    // 根据id删除晚归信息
    long deleteOutlateById(Integer id);
}
