package com.joy.dorm.visitor.service;

import com.joy.dorm.visitor.dao.Impl.VisitorDaoImpl;
import com.joy.dorm.visitor.model.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VisitorService {

    @Autowired
    VisitorDaoImpl visitorDao;

    public List<Visitor> getAllVisitor(String keywords, Integer id,long pageNumNew,long pageSize) {
        return visitorDao.getAllVisitor(keywords,id,pageNumNew,pageSize);
    }

    public int addVisitor(Visitor visitor) {
        visitor.setCreateTime(new Date());
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

    public Long getVisitorAdminCount(String keywords,Integer buildingId) {
        return visitorDao.getVisitorAdminCount(keywords,buildingId);
    }

    public List<Visitor> getAllVisitorAdmin(String keywords,long pageNumNew,long pageSize,Integer buildingId){
        return visitorDao.getAllVisitorAdmin(keywords,pageNumNew,pageSize,buildingId);
    }
}
