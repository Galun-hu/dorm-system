package com.joy.dorm.system.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.joy.dorm.common.utils.AutoIncKey;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@ApiModel(description = "管理员")
@Document(collection = "admin")
//空值不返回
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Admin implements UserDetails {

    public static final String tableName = "admin";

    @Id
    @ApiModelProperty("id")
    private String _id;
    @ApiModelProperty("管理员id")
    @Indexed
    @AutoIncKey
    private Integer id;

    @Indexed(unique = true)
    @ApiModelProperty("账号")
    private String username;
    @ApiModelProperty("密码")
    private String password;

    @Indexed
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
    @Indexed
    @ApiModelProperty("角色id")
    private Integer roleId;
    @ApiModelProperty("角色")
    private Role role;
    @ApiModelProperty("创建时间")
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

    public String getUsername() {
        return username;
    }

    //账号是否没有过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //账号是否没有锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //登录是否没有过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //账号是否启用
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getName()));
        return authorities;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}
