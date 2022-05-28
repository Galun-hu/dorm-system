package com.joy.dorm.dormitory.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.joy.dorm.common.utils.AutoIncKey;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@ApiModel("宿舍管理员")
@Document("admin")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Administrator {

    @Id
    @ApiModelProperty("宿舍管理员_id")
    private String _id;
    @ApiModelProperty("宿舍管理员id")
    private Integer id;
    @ApiModelProperty("名字")
    private String name;
    @ApiModelProperty("性别")
    private String sex;
    @ApiModelProperty("手机号码")
    private String phone;
    @ApiModelProperty("单位")
    private String company;
    @ApiModelProperty("角色id")
    private Integer roleId;
    @ApiModelProperty("创建时间")
    private Date createTime;

    private Integer building_id;


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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(Integer building_id) {
        this.building_id = building_id;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "_id='" + _id + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", company='" + company + '\'' +
                ", roleId='" + roleId + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
