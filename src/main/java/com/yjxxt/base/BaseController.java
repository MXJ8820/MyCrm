package com.yjxxt.base;


import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

public class BaseController {


    @ModelAttribute
    public void preHandler(HttpServletRequest request){
        request.setAttribute("ctx", request.getContextPath());

        StringBuffer requestURL = request.getRequestURL();

        String requestURI = request.getServletPath();

        String substring = requestURL.substring(0, requestURL.length() - requestURI.length());

        String concat = substring.concat("/images/");

        request.setAttribute("imagePath",concat);
        request.setAttribute("host",request.getServerName());
    }


    public ResultInfo success(){
        return new ResultInfo();
    }

    public ResultInfo success(String msg){
        ResultInfo resultInfo= new ResultInfo();
        resultInfo.setMsg(msg);
        return resultInfo;
    }

    public ResultInfo success(String msg,Object result){
        ResultInfo resultInfo= new ResultInfo();
        resultInfo.setMsg(msg);
        resultInfo.setResult(result);
        return resultInfo;
    }

}
