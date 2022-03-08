package com.yjxxt.query;

import com.yjxxt.base.BaseQuery;

public class OrderQuery extends BaseQuery {

    private Integer orderNum;

    public OrderQuery() {
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "OrderQuery{" +
                "orderNum='" + orderNum + '\'' +
                '}';
    }
}
