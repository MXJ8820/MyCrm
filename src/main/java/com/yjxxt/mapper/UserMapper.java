package com.yjxxt.mapper;

import com.yjxxt.base.BaseMapper;
import com.yjxxt.bean.User;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User,Integer> {


    User selectByUserName(String userName);

    public User queryUserByUserName(String UserName);

    // 查询所有的销售人员
    @MapKey("")
    public List<Map<String, Object>> queryAllSales();

}