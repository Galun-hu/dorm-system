package com.joy.dorm.visitor.service;

import com.joy.dorm.visitor.dao.Impl.VisitorDaoImpl;
import com.joy.dorm.visitor.model.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorService {

    @Autowired
    VisitorDaoImpl visitorDao;

    public List<Visitor> getAllVisitor(String keywords, Integer id,int pageNumNew,int pageSize) {
        return visitorDao.getAllVisitor(keywords,id,pageNumNew,pageSize);
    }

    public int addVisitor(Visitor visitor) {
        return visitorDao.addVisitor(visitor);
    }

    public int updateVisitor(Visitor visitor) {
        return visitorDao.updateVisitor(visitor);
    }

    public int deleteVisitor(Integer id) {
        return visitorDao.deleteVisitor(id);
    }

    public Long getVisitorCount(String keywords, Integer id) {
        return visitorDao.getVisitorCount(keywords,id);
    }
}
