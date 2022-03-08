package com.yjxxt.controller;


import com.yjxxt.base.BaseController;
import com.yjxxt.service.ComService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("buy")
public class BuyController extends BaseController {


    @Resource
    private ComService comService;


    @RequestMapping("index")
    public String com(){
        return "buy/buy";
    }


    @RequestMapping("myOrder")
    public String myOrder(){
        return "buy/myOrder";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> selectAllCom(){
        return comService.selectAllCom();
    }


    @RequestMapping("openChooseBurden")
    public String openChooseBurden(Integer id, Model model){
        model.addAttribute("id",id);
        return "buy/choose_burden";
    }
}
