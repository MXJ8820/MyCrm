package com.yjxxt.service;

import com.yjxxt.base.BaseService;
import com.yjxxt.bean.*;
import com.yjxxt.dto.Dto;
import com.yjxxt.exceptions.ParamsException;
import com.yjxxt.mapper.*;
import com.yjxxt.utils.AssertUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ShopCarService extends BaseService<ShopCar, Integer> {


    @Resource
    private ShopCarMapper shopCarMapper;


    @Resource
    private OrderMapper orderMapper;


    @Resource
    private OcaMapper ocaMapper;


    @Resource
    private OrderComMapper orderComMapper;


    @Resource
    private ComMapper comMapper;


    @Resource
    private ComService comService;

    public void addToShopCar(Integer comId, List<Dto> dtos, Integer userId) {
        Com com = comService.selectByPrimaryKey(comId);


        Integer sumPrice = com.getComPrice();

        for (Dto dto : dtos) {
            sumPrice += dto.getPrice();
        }

        List<Integer> ids = new ArrayList<>();
        Integer markId = null;

        for (Dto dto : dtos) {
            ShopCar shopCar = new ShopCar();

            shopCar.setComId(com.getId());
            shopCar.setAsyId(Integer.parseInt(dto.getValue()));
            shopCar.setComPrice(com.getComPrice());
            shopCar.setAsyPrice(dto.getPrice());
            shopCar.setSumPrice(sumPrice);
            shopCar.setUserId(userId);
            shopCar.setAsyName(dto.getTitle());
            shopCar.setComName(com.getComName());

            AssertUtil.isTrue(insertHasKey(shopCar) < 1, "添加购物车失败");

            ids.add(shopCar.getId());
            markId = shopCar.getId();
        }

        for (Integer id : ids) {
            shopCarMapper.setMarkNumById(id, markId);
        }

    }

    public Map<String, Object> selectAllInShopCar(Integer userId) {
        List<ShopCar> shopCars = shopCarMapper.selectAllInShopCar(userId);
        Map<String, Object> map = new HashMap();
        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", shopCars.size());
        map.put("data", shopCars);

        return map;
    }

    public void deleteShopCarById(Integer markOrderNum) {
        AssertUtil.isTrue(markOrderNum == null, "待删除记录不存在");
        AssertUtil.isTrue(shopCarMapper.deleteBycomNameAndSumPrice(markOrderNum) < 1, "删除失败");
    }


    public void addOrderFromShopCar(List<ShopCar> shopCar) {
        AssertUtil.isTrue(shopCar == null || shopCar.size() == 0, "请选择数据");

        Order order = new Order();
        Integer comSumPrice = 0;

        for (ShopCar shop : shopCar) {
            //插入order表
            comSumPrice += shop.getSumPrice();
            order.setUserId(shop.getUserId());
        }
        order.setIsValid(1);
        order.setOrdState(0);
        order.setOrdRemark("待完成");
        order.setOrderDate(new Date());
        order.setOrderNum(String.valueOf(System.currentTimeMillis()).hashCode() > 0 ? String.valueOf(System.currentTimeMillis()).hashCode() : -String.valueOf(System.currentTimeMillis()).hashCode());
        order.setUpdateDate(new Date());

        order.setComSumPrice(comSumPrice);

        String[] comName = new String[shopCar.size()];

        int i = 0;
        for (ShopCar sc : shopCar) {
            comName[i] = sc.getComName();
            i++;
        }

        Arrays.sort(comName);
        int count = 1;
        for (int x = 0; x < comName.length; x++) {
            if (x != comName.length - 1) {
                if (!comName[x].equals(comName[x + 1])) {
                    //查库存 作比较
                    Com com = comMapper.selectByComName(comName[x]);
                    if (com.getComHousenum() < count) {
                        throw new ParamsException("商品库存不够");
                    }
                    count = 1;
                } else {
                    //累加
                    count++;
                }
            } else {
                //查库存 作比较
                Com com = comMapper.selectByComName(comName[x]);
                if (com.getComHousenum() < 1) {
                    throw new ParamsException("商品库存不够");
                }
            }
        }


        AssertUtil.isTrue(orderMapper.insertSelective(order) < 1, "下单失败");


        for (ShopCar shop : shopCar) {
            //插入订单商品表
            List<ShopCar> comInOrder = shopCarMapper.selectShopCarByMarkOrderNum(shop.getMarkOrderNum());
            for (ShopCar shopcar : comInOrder) {
                OrderCom orderCom = new OrderCom();
                orderCom.setOrderId(order.getId());
                orderCom.setComName(shop.getComName());
                orderCom.setComPrice((double) shopcar.getComPrice());
                orderCom.setComId(shopcar.getComId());

                Com com = comMapper.selectByComName(shop.getComName());

                if (com.getComHousenum() > 0) {
                    com.setComHousenum(com.getComHousenum() - 1);
                    AssertUtil.isTrue(comMapper.updateByPrimaryKeySelective(com) < 1, "商品减库存失败");

                } else {
                    throw new ParamsException("商品库存不够");
                }
                AssertUtil.isTrue(orderComMapper.insertSelective(orderCom) < 1, "插入商品失败");


                //插入配料表
                List<String> asys = shopCarMapper.selectAsyIdFromShopCarByMarkOrderNum(shop.getMarkOrderNum());
                for (String asy : asys) {
                    Oca oca = new Oca();
                    oca.setAsyId(Integer.parseInt(asy));
                    oca.setOrderComId(orderCom.getId());
                    AssertUtil.isTrue(ocaMapper.insertSelective(oca) < 1, "插入配料失败");
                }
            }
        }

        //删除购物车
        for (ShopCar sc : shopCar) {
            AssertUtil.isTrue(shopCarMapper.deleteByMarkOrderNum(sc.getMarkOrderNum()) < 1, "购物车删除失败");
        }
    }
}
