package com.yjxxt.MvcConfig;

import com.yjxxt.interceptors.NoLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 新建 config 包，添加拦截器生效的配置类
 * */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     *配置拦截器对象
     **/
    @Bean
    public NoLoginInterceptor noLoginInterceptor(){

        return new NoLoginInterceptor();

    }

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        /**指定拦截器对象
         * 需要一个实现HandlerInterceptor接口的拦截器实例，这里使用的是NoLoginInterceptor
         * */
        registry.addInterceptor(noLoginInterceptor())
                /*拦截路径
                 * 用于设置拦截器的过滤路径规则
                 */
                .addPathPatterns("/**")
                /*放行路径
                * 用于设置不需要拦截的过滤规则
                */
                .excludePathPatterns("/index","/user/login","/js/**","/css/**","/images/**","/lib/**","/toRegPage","/email/sendEmail","/user/reg_addUser");

    }
}
