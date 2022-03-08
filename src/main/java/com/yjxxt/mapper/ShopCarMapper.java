package com.yjxxt.mapper;

import com.yjxxt.base.BaseMapper;
import com.yjxxt.bean.ShopCar;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopCarMapper extends BaseMapper<ShopCar,Integer> {


    List<ShopCar> selectAllInShopCar(Integer userId);

    Integer  deleteBycomNameAndSumPrice(Integer markOrderNum);

    Integer setMarkNumById(@Param("id")Integer id,@Param("markId") Integer markId);

    List<ShopCar> selectShopCarByMarkOrderNum(Integer markOrderNum);

    List<String> selectAsyIdFromShopCarByMarkOrderNum(Integer markOrderNum);

    Integer deleteByMarkOrderNum(Integer markOrderNum);

}