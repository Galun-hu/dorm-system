package com.joy.dorm.outLate.service;


import com.joy.dorm.outLate.model.Outlate;

import java.util.List;

public interface IOutlateService {

    // 获取所有晚归信息
    List<Outlate> getAllOutlate(Integer building_id,String keywords,String building_type,Integer pageNum,Integer pageSize);

    // 统计模糊查询结果的数量
    Long getOutlateCount(Integer building_id,String keywords,String building_type);

//    // 根据building_id获取所有晚归信息
//    List<Outlate> getAllOutlateWithBuildingId(Integer building_id);
//
//    // 根据building_id和building_type获取所有晚归信息
//    List<Outlate> getAllOutlateWithBuildingIdAndBuildingType(Integer building_id,String building_type);


    // 添加晚归信息
    Integer addOutlate(Outlate outlate);

    // 修改晚归信息
    long updateOutlate(Outlate outlate);

    // 根据id删除晚归信息
    long deleteOutlateWithId(Integer id);
}
