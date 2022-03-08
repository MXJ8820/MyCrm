package com.yjxxt.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.base.BaseService;
import com.yjxxt.bean.User;
import com.yjxxt.bean.UserRole;
import com.yjxxt.mapper.UserMapper;
import com.yjxxt.mapper.UserRoleMapper;
import com.yjxxt.model.UserModel;
import com.yjxxt.query.UserQuery;
import com.yjxxt.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class UserService extends BaseService<User,Integer> {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    public void  addUser(User user,HttpServletRequest request){

        // 参数校验
        checkParams(user);

        // 验证码要正确
        AssertUtil.isTrue(!(user.getCode().equals(request.getSession().getAttribute("code"))),"验证码不正确");

        //密码加密
        user.setUserPwd(Md5Util.encode(user.getUserPwd()));
        //初始化信息
        user.setUpdateDate(new Date());
        user.setCreateDate(new Date());
        user.setIsValid(1);

        //添加用户
        AssertUtil.isTrue(userMapper.insertSelective(user) < 1,"注册失败");
    }

    private void checkParams(User user) {
        // 1.用户是否存在
        User temp = userMapper.selectByUserName(user.getUserName());
        AssertUtil.isTrue(temp != null,"用户已存在");
        // 非空校验
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserName()),"用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserPwd()),"密码不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(user.getEmail()),"邮箱不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(user.getPhone()),"手机号不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(user.getCode()),"验证码不能为空");

        // 手机号格式
        AssertUtil.isTrue(!PhoneUtil.isMobile(user.getPhone()),"手机号格式不正确");


    }

    public UserModel login(String userName,String userPwd){


        // 参数校验
        checkUserNamePwd(userName,userPwd);

        // 查询用户是否存在
        User user = userMapper.selectByUserName(userName);

        AssertUtil.isTrue(user == null,"用户不存在");

        // 检验密码是否正确
        checkPwd(userPwd,user.getUserPwd());


        return bulidUserModel(user);
    }

    /**
     * 登录
     * 检查传入参数是否为空
     * @param userName
     * @param userPwd
     */
    private void checkUserNamePwd(String userName, String userPwd) {

        // 用户名
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名为空");
        AssertUtil.isTrue(StringUtils.isBlank(userPwd),"用户名为空");

    }

    /**
     * 检查密码是否正确
     * @param userPwd
     * @param userPwd1
     */
    private void checkPwd(String userPwd, String userPwd1) {

        AssertUtil.isTrue(!Md5Util.encode(userPwd).equals(userPwd1),"密码不正确");
    }

    /**
     * 构建cookie信息模板类
     * @param user
     * @return
     */
    private UserModel bulidUserModel(User user) {
        UserModel userModel = new UserModel();

        // 加密cookie存储的用户id
        userModel.setIdStr(UserIDBase64.encoderUserID(user.getId()));
        userModel.setUserName(user.getUserName());
        userModel.setTrueName(user.getTrueName());
        return userModel;
    }

    //用户登录结束


    /**
     * 用户密码修改功能实现
     * 1. 参数校验
     * 用户ID：userId 非空 用户对象必须存在
     * 原始密码：oldPassword 非空 与数据库中密文密码保持一致
     * 新密码：newPassword 非空 与原始密码不能相同
     * 确认密码：confirmPassword 非空 与新密码保持一致
     * 2. 设置用户新密码
     * 新密码进行加密处理
     * 3. 执行更新操作
     * 受影响的行数小于1，则表示修改失败
     *
     * 注：在对应的更新方法上，添加事务控制
     * @param userId
     * @param oldPassword 原始密码
     * @param newPassword 新密码
     * @param confirmPwd 确认密码
     * */
    public void updateUserPassword(Integer userId,String oldPassword,String newPassword,String confirmPwd){

        // 通过userId获取用户对象
        User user = userMapper.selectByPrimaryKey(userId);
        // 1. 参数校验
        checkUserPasswordParams(user,oldPassword,newPassword,confirmPwd);
        // 2. 加密设置用户新密码
        user.setUserPwd(Md5Util.encode(newPassword));
        //执行更新操作,判断修改是否成功
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user)<1,"您的密码修改失败!");

    }

    /**
     *验证用户的密码修改参数
     *      用户ID：userId 非空 用户对象必须存在
     *      原始密码：oldPassword 非空 与数据库中密文密码保持一致
     *      新密码：newPassword 非空 与原始密码不能相同
     *      确认密码：confirmPassword 非空 与新密码保持一致
     * @param user
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     * */
    private void checkUserPasswordParams(User user, String oldPassword, String newPassword, String confirmPassword) {

        // user对象 非空验证
        AssertUtil.isTrue(user==null,"用户未登录或者已注销!");
        // 原始密码 非空验证
        AssertUtil.isTrue(StringUtils.isBlank(oldPassword),"请输入原始密码");
        // 原始密码要与数据库中的密文密码（加密）保持一致
        AssertUtil.isTrue(!user.getUserPwd().equals(Md5Util.encode(oldPassword)),"原始密码不正确 ");
        // 新密码 非空校验
        AssertUtil.isTrue(StringUtils.isBlank(newPassword),"请输入新密码");
        //新密码和原始密码不能相同
        AssertUtil.isTrue(oldPassword.equals(newPassword),"新密码不能与原始密码相同");
        //确认密码，非空校验
        AssertUtil.isTrue(StringUtils.isBlank(confirmPassword),"请输入确认密码");
        //确认密码要与新密码保持一致
        AssertUtil.isTrue(!confirmPassword.equals(newPassword),"确认密码和新密码不一致");

    }

    /**
     * 查询所有的销售人员
     * @return
     */
    public List<Map<String, Object>> queryAllSales() {
        return userMapper.queryAllSales();
    }

    /**
     * 多条件分页查询用户数据
     * @param query
     * @return
     */
    public Map<String, Object> queryUserByParams (UserQuery query) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(query.getPage(), query.getLimit());
        PageInfo<User> pageInfo = new PageInfo<>(userMapper.selectByParams(query));
        map.put("code",0);
        map.put("msg", "");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    /**
     * 添加用户
     * 1. 参数校验
     * 用户名 非空 唯一性
     * 邮箱 非空
     * 手机号 非空 格式合法
     * 2. 设置默认参数
     * isValid 1
     * creteDate 当前时间
     * updateDate 当前时间
     * userPwd 123456 -> md5加密
     * 3. 执行添加，判断结果
     */
    /**
     * 添加用户
     * @param user
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveUser(User user) {
        // 1. 参数校验
        checkParams(user.getUserName(), user.getEmail(), user.getPhone());
        // 2. 设置默认参数
        user.setIsValid(1);
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        user.setUserPwd(Md5Util.encode("123456"));
        // 3. 执行添加，判断结果
        AssertUtil.isTrue(userMapper.insertSelective(user) == null, "用户添加失败！");

        /**
         * 用户角色分配
         * userId
         * roleIds
         */
        relaionUserRole(user.getId(), user.getRoleIds());

    }

    /**
     * 添加参数校验
     * @param userName
     * @param email
     * @param phone
     */
    private void checkParams(String userName, String email, String phone) {
        AssertUtil.isTrue(StringUtils.isBlank(userName), "用户名不能为空！");
        // 验证用户名是否存在
        User temp = userMapper.queryUserByUserName(userName);
//        User temp = userMapper.selectByUserName(userName);
        AssertUtil.isTrue(null != temp, "该用户已存在！");
        AssertUtil.isTrue(StringUtils.isBlank(email), "请输入邮箱地址！");
        AssertUtil.isTrue(!PhoneUtil.isMobile(phone), "手机号码格式不正确！");

    }

    /**
     * 用户角色分配
     * 原始角色不存在 添加新的角色记录
     * 原始角色存在 添加新的角色记录
     * 原始角色存在 清空所有角色
     * 原始角色存在 移除部分角色
     * 如何进行角色分配???
     * 如果用户原始角色存在 首先清空原始所有角色 添加新的角色记录到用户角色表
     */
    private void relaionUserRole(int useId, String roleIds) {

        int count = userRoleMapper.countUserRoleByUserId(useId);
        if (count > 0) {
            AssertUtil.isTrue(userRoleMapper.deleteUserRoleByUserId(useId) != count, "用户角色分配失败!");
        }
        if (StringUtils.isNotBlank(roleIds)) {
            //重新添加新的角色，实例化容器list
            List<UserRole> userRoles = new ArrayList<UserRole>();
            //分割"1,2,14..."
            for (String s : roleIds.split(",")) {
                //实例化对象
                UserRole userRole = new UserRole();
                userRole.setUserId(useId);
                userRole.setRoleId(Integer.parseInt(s));
                userRole.setCreateDate(new Date());
                userRole.setUpdateDate(new Date());
                //将对象存储到容器
                userRoles.add(userRole);
            }
            //循环添加用户角色
            AssertUtil.isTrue(userRoleMapper.insertBatch(userRoles) < userRoles.size(), "用户角色分配失败!");
        }
    }


    /**
     * 更新用户
     * 1. 参数校验
     * id 非空 记录必须存在
     * 用户名 非空 唯一性
     * email 非空
     * 手机号 非空 格式合法
     * 2. 设置默认参数
     * updateDate
     * 3. 执行更新，判断结果
     * @param user
     */
    /**
     * 更新用户
     * @param user
     */
    @Transactional(propagation = Propagation.REQUIRED)//事物的使用
    public void updateUser(User user) {
        // 1. 参数校验
        // 通过id查询用户对象
        User temp = userMapper.selectByPrimaryKey(user.getId());
        // 判断对象是否存在
        AssertUtil.isTrue(temp == null, "待更新记录不存在！");
        // 验证参数
        checkParams1(user.getUserName(),user.getEmail(),user.getPhone());
        // 2. 设置默认参数
        temp.setUpdateDate(new Date());
        // 3. 执行更新，判断结果
        AssertUtil.isTrue(userMapper.updateByPrimaryKeySelective(user) < 1, "用户更新失败！");


        /**
         * 用户角色分配
         * userId
         * roleIds
         */
        Integer userId = userMapper.queryUserByUserName(user.getUserName()).getId();
//        Integer userId = userMapper.selectByUserName(user.getUserName()).getId();
        relaionUserRole(userId, user.getRoleIds());

    }

    /**
     * 更新参数校验
     * @param userName
     * @param email
     * @param phone
     */
    private void checkParams1(String userName, String email, String phone) {
        AssertUtil.isTrue(StringUtils.isBlank(userName), "用户名不能为空！");
        AssertUtil.isTrue(StringUtils.isBlank(email), "请输入邮箱地址！");
        AssertUtil.isTrue(!PhoneUtil.isMobile(phone), "手机号码格式不正确！");
    }

    /**
     * 删除用户
     * @param
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUser(Integer[] ids) {
        //验证
        AssertUtil.isTrue(ids==null || ids.length==0,"选择数据");
        //删除是否成功
        AssertUtil.isTrue(userMapper.deleteBatch(ids)!=ids.length,"删除异常");
        //遍历
        for (Integer userId: ids ) {
            int count=userRoleMapper.countUserRoleByUserId(userId);
            if(count>0){
                //删除原来的角色
                AssertUtil.isTrue( userRoleMapper.deleteUserRoleByUserId(userId)!=count,"角色删除失败");
            }
        }
    }


}
