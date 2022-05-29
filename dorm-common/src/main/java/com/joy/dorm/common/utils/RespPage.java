package com.joy.dorm.common.utils;

import java.util.List;

public class RespPage {

    //总记录数
    private Long total;

    //查询了多少条
    private List<?> data;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
