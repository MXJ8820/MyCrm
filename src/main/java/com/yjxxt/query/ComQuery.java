package com.yjxxt.query;

import com.yjxxt.base.BaseQuery;

public class ComQuery extends BaseQuery {
    private String comName;

    private Integer comPrice;

    private Integer comState;

    public Integer getComState() {
        return comState;
    }

    public void setComState(Integer comState) {
        this.comState = comState;
    }

    public ComQuery() {
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public Integer getComPrice() {
        return comPrice;
    }

    public void setComPrice(Integer comPrice) {
        this.comPrice = comPrice;
    }
}
