package com.yjxxt.mapper;

import com.yjxxt.base.BaseMapper;
import com.yjxxt.bean.UserRole;

public interface UserRoleMapper extends BaseMapper<UserRole,Integer> {

    //统计用户的角色
    int countUserRoleByUserId(Integer userId);

    //删除用户的角色
    int deleteUserRoleByUserId(Integer userId);

}