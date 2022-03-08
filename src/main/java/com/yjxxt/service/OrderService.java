package com.yjxxt.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.base.BaseService;
import com.yjxxt.bean.Asy;
import com.yjxxt.bean.Oca;
import com.yjxxt.bean.Order;
import com.yjxxt.bean.OrderCom;
import com.yjxxt.mapper.AsyMapper;
import com.yjxxt.mapper.OcaMapper;
import com.yjxxt.mapper.OrderComMapper;
import com.yjxxt.mapper.OrderMapper;
import com.yjxxt.query.OrderQuery;
import com.yjxxt.utils.AssertUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class OrderService extends BaseService<Order,Integer> {


    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderComMapper orderComMapper;


    @Resource
    private AsyMapper asyMapper;


    @Resource
    private OcaMapper ocaMapper;


    public void addOrderFromShopCar(Order order){

        AssertUtil.isTrue(order.getOrderNum()==null,"订单号不能为空");
        AssertUtil.isTrue(order.getUserId()==null,"用户编号不能为空");
        AssertUtil.isTrue(order.getIsValid()==null,"订单状态不能为空");

        AssertUtil.isTrue(orderMapper.insertSelective(order)<1,"插入订单失败");
    }

    public Map<String, Object> list(Integer userId) {

        List<Order> list = orderMapper.list(userId);
        Map<String,Object> map = new HashMap();

        map.put("code",0);
        map.put("msg","success");
        map.put("count",list.size());
        map.put("data",list);
        return map;
    }

    public Map<String, Object> selectComByOrder(Integer id) {

        List<OrderCom> list = orderComMapper.selectComByOrder(id);
        Map<String,Object> map = new HashMap();

        map.put("code",0);
        map.put("msg","success");
        map.put("count",list.size());
        map.put("data",list);
        return map;
    }

    public void deleteOrder(Integer id) {

        //查状态
        Order order = orderMapper.selectByPrimaryKey(id);
        AssertUtil.isTrue(order.getOrdState() == 1,"订单已完成，不能退单");

        AssertUtil.isTrue(orderMapper.tuiOrder(id)<1,"退单失败");
    }

    public Map<String, Object> selectIngredientsByOrderComId(Integer ingredientsId) {
        List<Oca> list = ocaMapper.selectComByOrder(ingredientsId);
        List<Asy> asy = new ArrayList<>();
        for (Oca oca:list) {
            asy.add(asyMapper.selectByPrimaryKey(oca.getAsyId()));
        }

        Map<String,Object> map = new HashMap();
        map.put("code",0);
        map.put("msg","success");
        map.put("count",asy.size());
        map.put("data",asy);
        return map;
    }


    //沈康纯
    /**
     * 查询角色列表
     * @return
     */
    public Map<String, Object> queryAllOrders(OrderQuery orderQuery){
        //返回目标集合对象
//        return orderMapper.queryAllOrders(id);
        //Map<String,Object> result = new HashMap<String,Object>();
        PageHelper.startPage(orderQuery.getPage(),orderQuery.getLimit());
        PageInfo pageInfo = new PageInfo(orderMapper.queryAllOrders(orderQuery));
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }

    /**
     * 订单的修改
     * @param order
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateOrder(Order order){
        AssertUtil.isTrue(null==order.getId()||null==selectByPrimaryKey(order.getId()),"待修改的订单不存在!");
//        Order temp = orderMapper.queryAllOrders();
//        AssertUtil.isTrue(null !=temp && !(temp.getId().equals(order.getId())),"该订单已存在!");
        order.setUpdateDate(new Date());
        AssertUtil.isTrue(updateByPrimaryKeySelective(order)<1,"订单记录更新失败!");
    }


}
