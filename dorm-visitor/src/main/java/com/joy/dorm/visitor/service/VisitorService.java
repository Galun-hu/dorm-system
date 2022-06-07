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

    //获取宿舍管理员角色维修记录分页数据
    public List<Visitor> getAllVisitor(String keywords, Integer id,long pageNumNew,long pageSize) {
        return visitorDao.getAllVisitor(keywords,id,pageNumNew,pageSize);
    }

    //添加维修
    public int addVisitor(Visitor visitor) {
        visitor.setCreateTime(new Date());
        return visitorDao.addVisitor(visitor);
    }

    //修改报修
    public int updateVisitor(Visitor visitor) {
        return visitorDao.updateVisitor(visitor);
    }

    //删除报修
    public int deleteVisitor(Integer id) {
        return visitorDao.deleteVisitor(id);
    }

    //获取分页总记录数
    public Long getVisitorCount(String keywords, Integer id) {
        return visitorDao.getVisitorCount(keywords,id);
    }

    //获取系统管理员维修分页总记录数
    public Long getVisitorAdminCount(String keywords,Integer buildingId) {
        return visitorDao.getVisitorAdminCount(keywords,buildingId);
    }

    //获取系统管理员维修分页数据
    public List<Visitor> getAllVisitorAdmin(String keywords,long pageNumNew,long pageSize,Integer buildingId){
        return visitorDao.getAllVisitorAdmin(keywords,pageNumNew,pageSize,buildingId);
    }
}
