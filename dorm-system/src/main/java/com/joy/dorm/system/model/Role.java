package com.joy.dorm.system.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@ApiModel(description = "角色")
@Document(collection = "role")
public class Role {

    @Id
    @ApiModelProperty("管理员id")
    private String id;
    @ApiModelProperty("角色英文名")
    private String name;
    @ApiModelProperty("角色中文名")
    private String nameZh;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
