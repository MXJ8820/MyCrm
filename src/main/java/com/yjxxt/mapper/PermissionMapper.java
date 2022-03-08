package com.yjxxt.mapper;

import com.yjxxt.base.BaseMapper;
import com.yjxxt.bean.Permission;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission,Integer> {

    //统计角色用有的资源id数量
    int countPermissionByRoleId(Integer roleId);

    //删除当前角色用有的资源信息
    Integer deletePermissionsByRoleId(Integer roleId);

    List<String> queryUserHasRolesHasPermissions(Integer userId);

    List<Integer> queryRoleHasAllModuleIdsByRoleId(Integer roleId);

}