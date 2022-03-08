package com.yjxxt.mapper;

import com.yjxxt.base.BaseMapper;
import com.yjxxt.bean.Com;
import com.yjxxt.query.ComQuery;

import java.util.List;

public interface ComMapper extends BaseMapper<Com,Integer> {

    List<Com> selectAllComs(ComQuery comQuery);

    Integer selectComState(Integer id);

    Com selectComByName(String comName);

    //何林杰BuyMapper
    List<Com> selectAllCom();

    Com selectByComName(String comName);

}