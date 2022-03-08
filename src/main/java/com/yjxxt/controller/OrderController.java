package com.yjxxt.controller;


import com.yjxxt.base.BaseController;
import com.yjxxt.base.ResultInfo;
import com.yjxxt.bean.Order;
import com.yjxxt.bean.OrderCom;
import com.yjxxt.mapper.OrderComMapper;
import com.yjxxt.query.OrderQuery;
import com.yjxxt.service.OrderService;
import com.yjxxt.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("order")
public class OrderController extends BaseController {


    @Resource
    private OrderService orderService;


    @Resource
    private HttpSession session;


    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> list(HttpServletRequest req){
        return orderService.list(LoginUserUtil.releaseUserIdFromCookie(req));
//        return orderService.list(101);
    }


    @RequestMapping("selectComByOrder")
    @ResponseBody
    public Map<String,Object> selectComByOrder(){
        return orderService.selectComByOrder((Integer) session.getAttribute("id"));
    }


    @RequestMapping("selectIngredientsByOrderComId")
    @ResponseBody
    public Map<String,Object> selectIngredientsByOrderComId(){
        return orderService.selectIngredientsByOrderComId((Integer) session.getAttribute("ingredientsId"));
    }


    @RequestMapping("openOrderIncludeCom")
    public String openOrderIncludeCom(Integer id, HttpSession session){
        session.setAttribute("id",id);
        return "buy/openOrderIncludeCom";
    }


    @RequestMapping("openOrderIncludeIngredients")
    public String openOrderIncludeIngredients(Integer id, HttpSession session){
        session.setAttribute("ingredientsId",id);
        return "buy/openOrderIncludeIngredients";
    }


    @RequestMapping("deleteOrder")
    @ResponseBody
    public ResultInfo deleteOrder(Integer id){
        orderService.deleteOrder(id);

        return success("退单成功");
    }


    //沈康纯
    //查询订单列表
    @RequestMapping("queryAllOrders")
    @ResponseBody
    public Map<String,Object> queryAllOrders(OrderQuery orderQuery){

        return orderService.queryAllOrders(orderQuery);

    }

    @RequestMapping("index")
    public String index(){return "order/order";}

    @RequestMapping("list1")
    @ResponseBody
    public Map<String,Object> orderList(OrderQuery orderQuery){
        return orderService.queryAllOrders(orderQuery);
    }

    /**
     * 修改
     * @param id
     * @param ordState
     * @param ordRemark
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateOrder(String id, String ordState,String ordRemark){
        System.out.println(id);
        System.out.println(ordState);
        System.out.println(ordRemark);
        String[] split = id.split(",");
        String idStr = "";
        for (String s:split) {
            idStr += s;
        }

        Order order = new Order();
        order.setId(Integer.parseInt(idStr));
        order.setOrdState(Integer.parseInt(ordState));
        order.setOrdRemark(ordRemark);

        orderService.updateOrder(order);
        return success("订单记录修改成功");
    }

    @RequestMapping("addOrUpdateOrderPage")
    public String addUserPage(Integer id, Model model){
        if(null !=id){
            model.addAttribute("order",orderService.selectByPrimaryKey(id));
        }
        return "order/add.update";
    }

}
