package com.yjxxt.controller;


import com.yjxxt.base.BaseController;
import com.yjxxt.dto.Dto;
import com.yjxxt.service.AsyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("asy")
public class AsyController extends BaseController {


    @Resource
    private AsyService asyService;


    @RequestMapping("list")
    @ResponseBody
    public List<Dto> all(){
        return asyService.all();
    }
}
