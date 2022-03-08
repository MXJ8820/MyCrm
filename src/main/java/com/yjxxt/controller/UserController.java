package com.yjxxt.controller;

import com.yjxxt.annotation.RequiredPermission;
import com.yjxxt.base.BaseController;
import com.yjxxt.base.ResultInfo;
import com.yjxxt.bean.User;
import com.yjxxt.model.UserModel;
import com.yjxxt.query.UserQuery;
import com.yjxxt.service.UserService;
import com.yjxxt.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;


    @RequestMapping("reg_addUser")
    @ResponseBody
    public ResultInfo addUser(User user, HttpServletRequest request){


        userService.addUser(user,request);

        return success("注册成功");
    }

    /**
     *
     * @param userName 用户名
     * @param userPwd  密码
     * @return resultInfo
     */
    @RequestMapping("login")
    @ResponseBody
    public ResultInfo login(String userName,String userPwd){

       UserModel userModel = userService.login(userName,userPwd);

        return success("登录成功",userModel);
    }

    //用户登录功能实现

    /**
     * 修改用户基本信息页面跳转
     * 在 layuimini (main.ftl)布局页面，通过点击"基本资料" ，请求后端的 user/toSettingPage 接口
     * */
    @RequestMapping("toSettingPage")
    public String setting(HttpServletRequest req){

        //从Cookie获取用户的ID
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(req);
        //调用方法查询用户信息
        User user = userService.selectByPrimaryKey(userId);
        //存储数据
        req.setAttribute("user",user);
        //转发
        return "user/setting";

    }

    /**
     * 用户基本信息修改
     * @param user
     * */
    @RequestMapping("updateUser")
    @ResponseBody
    public ResultInfo updateUser(User user){

        //用户基本信息修改
        Integer x = userService.updateByPrimaryKeySelective(user);
        //返回目标对象
        return success("修改成功了");

    }

    //修改用户基本信息功能实现

    /**
     * 修改密码页面跳转
     * 在 layuimini (main.ftl)布局页面，通过点击"修改密码" ，请求后端的 user/toPasswordPage 接口
     * */
    @RequestMapping("toPasswordPage")
    public String toPasswordPage(){

        return "user/password";
    }

    /**
     * 用户密码修改
     * @param req
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     * @return
     */
    @RequestMapping("updatePassword")
    @ResponseBody
    public ResultInfo updateUserPassword(HttpServletRequest req,String oldPassword,String newPassword,String confirmPassword){

        //实例化返回值对象
        ResultInfo resultInfo = new ResultInfo();
        //获取当前用户的ID
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(req);
        // 调用Service层的密码修改方法
        userService.updateUserPassword(userId,oldPassword,newPassword,confirmPassword);
        //返回目标对象
        return resultInfo;

    }

    //修改密码功能实现

    /**
     * 下面是用户管理功能实现
     * */

    /**
     * 进入用户页面
     * @return
     */
    @RequestMapping("index")
    @RequiredPermission(code = "3010")
    public String index(){
        return "user/user";
    }

    /**
     * 多条件查询用户数据
     * @param userQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryUserByParams(UserQuery userQuery) {
        return userService.queryUserByParams(userQuery);
    }

    /**
     * 查询所有的销售人员
     * @return
     */
    @RequestMapping("queryAllSales")
    @ResponseBody
    public List<Map<String, Object>> queryAllSales() {
        return userService.queryAllSales();
    }

    /**
     * 进入用户添加或更新页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("addOrUpdateUserPage")
    public String addUserPage(Integer id, Model model){

        if(null != id){
            model.addAttribute("user",userService.selectByPrimaryKey(id));
        }
        return "user/add_update";

    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo saveUser(User user) {
        userService.saveUser(user);
        return success("用户添加成功！");
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateUser1(User user) {
        userService.updateUser(user);
        return success("用户更新成功！");
    }



    /**
     * 删除用户
     * @param ids
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteUser(Integer[] ids){
        userService.deleteUser(ids);
        return success("用户记录删除成功");
    }


}
