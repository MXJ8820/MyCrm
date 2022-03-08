package com.yjxxt.mapper;

import com.yjxxt.base.BaseMapper;
import com.yjxxt.bean.OrderCom;

import java.util.List;

public interface OrderComMapper extends BaseMapper<OrderCom,Integer> {


    List<OrderCom> selectComByOrder(Integer id);
}