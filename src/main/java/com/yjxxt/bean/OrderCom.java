package com.yjxxt.bean;

public class OrderCom {
    private Integer id;

    private Integer orderId;

    private Integer comId;

    private Double comPrice;

    private String comName;

    public OrderCom() {
    }

    @Override
    public String toString() {
        return "OrderCom{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", comId=" + comId +
                ", comPrice=" + comPrice +
                ", comName='" + comName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public Double getComPrice() {
        return comPrice;
    }

    public void setComPrice(Double comPrice) {
        this.comPrice = comPrice;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }
}