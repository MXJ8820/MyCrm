package com.yjxxt.controller;

import com.yjxxt.base.BaseController;
import com.yjxxt.base.ResultInfo;
import com.yjxxt.bean.User;
import com.yjxxt.service.PermissionService;
import com.yjxxt.service.UserService;
import com.yjxxt.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController extends BaseController {

    @Resource
    private UserService userService;

    @Resource
    private PermissionService permissionService;


    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("welcome")
    public String welcome(){
        return "welcome";
    }

    /**
     * 后端管理主页面
     * @return
     */
    @RequestMapping("main")
    public String main(HttpServletRequest request){

        // 通过工具类，从cookie中获取userId
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);

        // 调用对应Service层的方法，通过userId主键查询用户对象
        User currentUser = userService.selectByPrimaryKey(userId);
        // 将用户对象设置到request作用域中
        request.setAttribute("user",currentUser);

        //统计用户拥有的权限码
        List<String> permisssions=permissionService.queryUserHasRolesHasPermissions(userId);
        //存储
        request.getSession().setAttribute("permissions",permisssions);

        return "main";
    }

    @RequestMapping("toRegPage")
    public String toRegPage(){
        return "reg";
    }


}
