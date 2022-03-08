package com.yjxxt.mapper;

import com.yjxxt.base.BaseMapper;
import com.yjxxt.bean.Role;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface RoleMapper extends BaseMapper<Role,Integer> {

    //查询所有角色
    @MapKey("")
    public List<Map<String, Object>> queryAllRoles(Integer userId);

    //根据名称查询角色对象
    Role queryRoleByRoleName(String roleName);
}