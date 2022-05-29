package com.joy.dorm.visitor.model;

import com.joy.dorm.common.utils.AutoIncKey;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@ApiModel(description = "访客")
@Document(collection = "visitor")
public class Visitor {

    public static final String tableName = "visitor";

    @Id
    @ApiModelProperty("mongoId")
    private String _id;
    @ApiModelProperty("访客id")
    @AutoIncKey
    private Integer id;
    @ApiModelProperty("访客姓名")
    private String name;
    @ApiModelProperty("访客性别")
    private String sex;
    @ApiModelProperty("访客手机号")
    private String phone;
    @ApiModelProperty("访问的宿舍楼id")
    private Integer buildingId;
    @ApiModelProperty("接待的舍管id")
    private Integer dormId;
    @ApiModelProperty("到访原因")
    private String remark;
    @ApiModelProperty("到访时间")
    private Date createTime;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public Integer getDormId() {
        return dormId;
    }

    public void setDormId(Integer dormId) {
        this.dormId = dormId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "_id='" + _id + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", buildingId=" + buildingId +
                ", dormId=" + dormId +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
