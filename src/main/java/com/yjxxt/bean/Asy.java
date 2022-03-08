package com.yjxxt.bean;

public class Asy {
    private Integer id;

    private String asyName;

    private Integer asyPrice;

    private String asyRemark;

    public Asy() {
    }

    @Override
    public String toString() {
        return "Asy{" +
                "id=" + id +
                ", asyName='" + asyName + '\'' +
                ", asyPrice=" + asyPrice +
                ", asyRemark='" + asyRemark + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAsyName() {
        return asyName;
    }

    public void setAsyName(String asyName) {
        this.asyName = asyName;
    }

    public Integer getAsyPrice() {
        return asyPrice;
    }

    public void setAsyPrice(Integer asyPrice) {
        this.asyPrice = asyPrice;
    }

    public String getAsyRemark() {
        return asyRemark;
    }

    public void setAsyRemark(String asyRemark) {
        this.asyRemark = asyRemark;
    }
}