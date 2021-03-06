package com.joy.dorm.dormitory.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.joy.dorm.common.utils.AutoIncKey;
import com.joy.dorm.system.model.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@ApiModel("宿舍管理员")
@Document("admin")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Administrator {
    public static final String tableName = "admin";

    @Id
    @ApiModelProperty("id")
    private String _id;
    @ApiModelProperty("管理员id")
    @AutoIncKey
    private Integer id;
    @ApiModelProperty("账号")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("性别")
    private String sex;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("单位")
    private String company;
    @ApiModelProperty("是否启用")
    private Boolean enabled;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("角色id")
    private Integer roleId;
    @ApiModelProperty("角色")
    private Role role;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("宿舍楼id")
    private Integer building_id;
    @ApiModelProperty("宿舍楼名称")
    private String building_name;

    public String getBuilding_name() {
        return building_name;
    }

    public void setBuilding_name(String building_name) {
        this.building_name = building_name;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", company='" + company + '\'' +
                ", enabled=" + enabled +
                ", remark='" + remark + '\'' +
                ", roleId=" + roleId +
                ", role=" + role +
                ", createTime=" + createTime +
                ", building_id=" + building_id +
                ", building_name='" + building_name + '\'' +
                '}';
    }
}
