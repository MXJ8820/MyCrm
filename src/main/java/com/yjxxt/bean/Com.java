package com.yjxxt.bean;

public class Com {
    private Integer id;

    private String comName;

    private Integer comPrice;

    private Integer comHousenum;

    private String comRemark;

    private String imgAddr;

    private Integer comState;

    public Integer getComState() {
        return comState;
    }

    public void setComState(Integer comState) {
        this.comState = comState;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName == null ? null : comName.trim();
    }

    public Integer getComPrice() {
        return comPrice;
    }

    public void setComPrice(Integer comPrice) {
        this.comPrice = comPrice;
    }

    public Integer getComHousenum() {
        return comHousenum;
    }

    public void setComHousenum(Integer comHousenum) {
        this.comHousenum = comHousenum;
    }

    public String getComRemark() {
        return comRemark;
    }

    public void setComRemark(String comRemark) {
        this.comRemark = comRemark == null ? null : comRemark.trim();
    }

    public String getImgAddr() {
        return imgAddr;
    }

    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr;
    }
}