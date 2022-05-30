package com.joy.dorm.visitor.dao;

import com.joy.dorm.visitor.model.Visitor;

import java.util.List;

public interface VisitorDao {

    //获取所有访客记录
    List<Visitor> getAllVisitor(String keywords, Integer id,int pageNumNew,int pageSize);

    //添加到访人员
    int addVisitor(Visitor visitor);

    //修改到访记录
    int updateVisitor(Visitor visitor);

    //删除到访记录
    int deleteVisitor(Integer id);

    //获取总记录数
    Long getVisitorCount(String keywords, Integer id);

    //获取属于该楼栋的访客总记录数
    Long getVisitorAdminCount(String keywords,Integer buildingId,String buildingType);

    //获取属于该楼栋的所有访客
    List<Visitor> getAllVisitorAdmin(String keywords, Integer pageNumNew, Integer pageSize, Integer buildingId, String buildingType);
}
