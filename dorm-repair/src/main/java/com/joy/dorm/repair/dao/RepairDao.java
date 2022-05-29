package com.joy.dorm.repair.dao;

import com.joy.dorm.repair.model.Repair;

import java.util.List;

public interface RepairDao {

    //获取所有维修记录
    List<Repair> getAllRepair(String keywords, Integer id,int pageNumNew,int pageSize);

    //添加报修
    int addRepair(Repair repair);

    //修改报修
    int updateRepair(Repair repair);

    //删除维修记录
    int deleteRepair(Integer id);

    //获取总记录数
    Long getRepairCount(String keywords, Integer id);
}
