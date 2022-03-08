package com.yjxxt.controller;

import com.yjxxt.base.BaseController;
import com.yjxxt.base.ResultInfo;
import com.yjxxt.service.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("email")
public class EmailController extends BaseController {

    @Resource
    private EmailService emailService;

    @RequestMapping("sendEmail")
    @ResponseBody
    public ResultInfo sendEmail(String toEmail,HttpServletRequest request){

       if (emailService.sendMail(toEmail,request)){
           return success("发送成功");
       }else {
           ResultInfo resultInfo = new ResultInfo();
           resultInfo.setCode(300);
           resultInfo.setMsg("发送失败");
           return resultInfo;
       }

    }
}
