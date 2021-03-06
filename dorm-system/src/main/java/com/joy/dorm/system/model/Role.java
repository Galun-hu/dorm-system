package com.joy.dorm.system.model;

import com.joy.dorm.common.utils.AutoIncKey;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@ApiModel(description = "角色")
@Document(collection = "role")
public class Role {

    public static final String tableName = "role";

    @Id
    @ApiModelProperty("管理员id")
    private String _id;
    @ApiModelProperty("管理员id")
    @Indexed
    @AutoIncKey
    private Integer role_id;
    @ApiModelProperty("角色英文名")
    private String name;
    @ApiModelProperty("角色中文名")
    private String nameZh;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "Role{" +
                "_id='" + _id + '\'' +
                ", role_id=" + role_id +
                ", name='" + name + '\'' +
                ", nameZh='" + nameZh + '\'' +
                '}';
    }
}
