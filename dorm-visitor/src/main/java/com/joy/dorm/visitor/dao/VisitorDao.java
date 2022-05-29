package com.joy.dorm.visitor.dao;

import com.joy.dorm.visitor.model.Visitor;

import java.util.List;

public interface VisitorDao {

    //获取所有访客记录
    List<Visitor> getAllVisitor(String keywords, Integer id);

    //添加到访人员
    int addVisitor(Visitor visitor);

    //修改到访记录
    int updateVisitor(Visitor visitor);

    //删除到访记录
    int deleteVisitor(Integer id);
}
