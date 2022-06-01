package com.joy.dorm.dormitory.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.joy.dorm.common.utils.AutoIncKey;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@ApiModel(description = "宿舍楼")
@Document("t_building")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Building {
    private static final String tableName = "t_building";

    @Id
    @ApiModelProperty("宿舍楼_id")
    private String _id;

    @AutoIncKey
    @ApiModelProperty("宿舍楼id")
    private Integer id;
    @ApiModelProperty("宿舍楼名称")
    private String name;
    @ApiModelProperty("男/女生宿舍")
    private String type;
    @ApiModelProperty("人数")
    private Integer person_num;
    @ApiModelProperty("宿舍管理员")
    private List<Administrator> administrators;
    @ApiModelProperty("创建时间")
    private Date created;
    @ApiModelProperty("修改时间")
    private Date modified;

    private List<Integer> admin_id;

    public List<Integer> getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(List<Integer> admin_id) {
        this.admin_id = admin_id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPerson_num() {
        return person_num;
    }

    public void setPerson_num(Integer person_num) {
        this.person_num = person_num;
    }

    public List<Administrator> getAdministrators() {
        return administrators;
    }

    public void setAdministrators(List<Administrator> administrators) {
        this.administrators = administrators;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return "Building{" +
                "_id='" + _id + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", person_num=" + person_num +
                ", administrators=" + administrators +
                ", created=" + created +
                ", modified=" + modified +
                ", admin_id=" + admin_id +
                '}';
    }
}
