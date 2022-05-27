package com.joy.dorm.common.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 存储当前id数据的数据表
 */
@Document("t_inc")
public class incId {
    @Id
    private String _id;

    @Field
    private String cillName; //需要自增id的集合名称

    @Field
    private String incKey;  // 需要自增的字段名

    @Field
    private Integer incId; // 该字段名当前自增id值

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCillName() {
        return cillName;
    }

    public void setCillName(String cillName) {
        this.cillName = cillName;
    }

    public String getIncKey() {
        return incKey;
    }

    public void setIncKey(String incKey) {
        this.incKey = incKey;
    }

    public Integer getIncId() {
        return incId;
    }

    public void setIncId(Integer incId) {
        this.incId = incId;
    }

    @Override
    public String toString() {
        return "incId{" +
                "_id='" + _id + '\'' +
                ", cillName='" + cillName + '\'' +
                ", incKey='" + incKey + '\'' +
                ", incId=" + incId +
                '}';
    }
}
