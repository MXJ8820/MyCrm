package com.yjxxt.bean;

public class ShopCar {
    private Integer id;

    private Integer userId;

    private Integer comId;

    private Integer asyId;

    private Integer comPrice;

    private Integer asyPrice;

    private Integer sumPrice;

    private String asyName;

    private String comName;

    private Integer markOrderNum;

    public Integer getMarkOrderNum() {
        return markOrderNum;
    }

    public void setMarkOrderNum(Integer markOrderNum) {
        this.markOrderNum = markOrderNum;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public ShopCar() {
    }


    public String getAsyName() {
        return asyName;
    }

    public void setAsyName(String asyName) {
        this.asyName = asyName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public Integer getAsyId() {
        return asyId;
    }

    public void setAsyId(Integer asyId) {
        this.asyId = asyId;
    }

    public Integer getComPrice() {
        return comPrice;
    }

    public void setComPrice(Integer comPrice) {
        this.comPrice = comPrice;
    }

    public Integer getAsyPrice() {
        return asyPrice;
    }

    public void setAsyPrice(Integer asyPrice) {
        this.asyPrice = asyPrice;
    }

    public Integer getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(Integer sumPrice) {
        this.sumPrice = sumPrice;
    }

    @Override
    public String toString() {
        return "ShopCar{" +
                "id=" + id +
                ", userId=" + userId +
                ", comId=" + comId +
                ", asyId=" + asyId +
                ", comPrice=" + comPrice +
                ", asyPrice=" + asyPrice +
                ", sumPrice=" + sumPrice +
                ", asyName='" + asyName + '\'' +
                ", comName='" + comName + '\'' +
                ", markOrderNum=" + markOrderNum +
                '}';
    }
}