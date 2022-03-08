package com.yjxxt.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Order {
    private Integer id;

    private Integer orderNum;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date orderDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;

    private Integer userId;

    private Integer comSumPrice;

    private Integer isValid;

    private Integer ordState;

    private String ordRemark;


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNum=" + orderNum +
                ", orderDate=" + orderDate +
                ", updateDate=" + updateDate +
                ", userId=" + userId +
                ", comSumPrice=" + comSumPrice +
                ", isValid=" + isValid +
                ", ordState=" + ordState +
                ", ordRemark='" + ordRemark + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getComSumPrice() {
        return comSumPrice;
    }

    public void setComSumPrice(Integer comSumPrice) {
        this.comSumPrice = comSumPrice;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Integer getOrdState() {
        return ordState;
    }

    public void setOrdState(Integer ordState) {
        this.ordState = ordState;
    }

    public String getOrdRemark() {
        return ordRemark;
    }

    public void setOrdRemark(String ordRemark) {
        this.ordRemark = ordRemark;
    }
}