package com.yjxxt;

import com.alibaba.fastjson.JSON;
import com.yjxxt.base.ResultInfo;
import com.yjxxt.exceptions.NoLoginException;
import com.yjxxt.exceptions.ParamsException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 全局异常统一处理
 * 实现 HandlerExceptionResolver 接口 ，处理应用程序异常信息
 */
@Component
public class GlobalException implements HandlerExceptionResolver {

    /**
     * 方法返回值类型
     * 视图
     * JSON
     * 如何判断方法的返回类型：
     * 如果方法级别配置了 @ResponseBody 注解，表示方法返回的是JSON；
     * 反之，返回的是视图页面
     * @param req
     * @param resp
     * @param handler
     * @param ex
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse resp, Object handler, Exception ex) {

        /**
         * 判断异常类型
         * 如果是未登录异常，则先执行相关的拦截操作
         */
        if(ex instanceof NoLoginException){
            // 如果捕获的是未登录异常，则重定向到登录页面
            ModelAndView mav = new ModelAndView("redirect:/index");
//            ModelAndView mav = new ModelAndView();
            return mav;
        }

        // 设置默认异常处理
        ModelAndView mav = new ModelAndView();
        //默认视图
        mav.setViewName("");
        mav.addObject("code",400);
        mav.addObject("msg","系统异常，稍后访问...");

        //判断入参类型是否是HandlerMethod
        if(handler instanceof HandlerMethod){

            // 类型转换
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取方法上的 ResponseBody 注解
            ResponseBody responseBody = handlerMethod.getMethod().getDeclaredAnnotation(ResponseBody.class);
            // 判断 ResponseBody 注解是否存在 (如果不存在，表示返回的是视图;如果存在，表示返回的是JSON)

            if(responseBody==null){
                //方法返回视图
                if(ex instanceof ParamsException){
                    ParamsException pe = (ParamsException) ex;
                    mav.addObject("code",pe.getCode());
                    mav.addObject("msg",pe.getMsg());
                }
                return mav;
            }else {
                //方法返回的json数据
                ResultInfo resultInfo = new ResultInfo();
                resultInfo.setMsg("系统异常");
                resultInfo.setCode(400);

                // 如果捕获的是自定义异常
                if(ex instanceof ParamsException){
                    ParamsException pe = (ParamsException) ex;
                    resultInfo.setCode(pe.getCode());
                    resultInfo.setMsg(pe.getMsg());
                }

                // 设置响应类型和编码格式 （响应JSON格式）
                resp.setContentType("application/json;charset=utf-8");
                //获取输出流
                PrintWriter out = null;
                try{
                    out = resp.getWriter();
                    //将对象转换成JSON格式，通过输出流输出 响应给请求的前台
                    out.write(JSON.toJSONString(resultInfo));
                }catch (IOException ioException){
                    ioException.printStackTrace();
                }finally {
                    if(out != null){
                        out.flush();
                        out.close();
                    }
                }
                return null;
            }

        }

        return mav;
    }

}
