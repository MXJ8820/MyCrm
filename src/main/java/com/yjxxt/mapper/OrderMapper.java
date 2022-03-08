package com.yjxxt.mapper;

import com.yjxxt.base.BaseMapper;
import com.yjxxt.bean.Order;
import com.yjxxt.query.OrderQuery;

import java.util.List;

public interface OrderMapper extends BaseMapper<Order,Integer> {

    List<Order> list(Integer userId);

    Integer tuiOrder(Integer id);


    //沈康纯
    List<Order> queryAllOrders(OrderQuery orderQuery);

}