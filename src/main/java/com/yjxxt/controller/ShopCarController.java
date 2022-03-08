package com.yjxxt.controller;


import com.alibaba.fastjson.JSONArray;
import com.yjxxt.base.BaseController;
import com.yjxxt.base.ResultInfo;
import com.yjxxt.dto.Dto;
import com.yjxxt.bean.ShopCar;
import com.yjxxt.service.ShopCarService;
import com.yjxxt.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("shopCar")
public class ShopCarController extends BaseController {


    @Resource
    private ShopCarService shopCarService;

    @RequestMapping("addToShopCar")
    public ResultInfo addToShopCar(Integer comId, String asys, HttpServletRequest req){
        //json字符串转为对象
        List<Dto> dtos = JSONArray.parseArray(asys, Dto.class);

        Integer userId = LoginUserUtil.releaseUserIdFromCookie(req);


        shopCarService.addToShopCar(comId,dtos,userId);

        return success("已添加至购物车");

    }


    @RequestMapping("openShopCar")
    public String openShopCar(){
        return "buy/shopCar";
    }


    @RequestMapping("selectAllInShopCar")
    @ResponseBody
    public Map<String,Object> selectAllInShopCar(HttpServletRequest req){
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(req);

        return shopCarService.selectAllInShopCar(userId);
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo delete(Integer markOrderNum){
        shopCarService.deleteShopCarById(markOrderNum);

        return success("删除成功");
    }


    @RequestMapping("addOrderFromShopCar")
    @ResponseBody
    public ResultInfo addOrderFromShopCar(String data){
        //json字符串转为对象
        List<ShopCar> shop = JSONArray.parseArray(data, ShopCar.class);

        shopCarService.addOrderFromShopCar(shop);
        return success("下单成功");
    }

}
