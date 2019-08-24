package com.hao.controller;


import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Describe com.hao.controller
 * @Auther wenhao chen
 * @CreateDate 2019/8/24
 * @Version 1.0
 */
public class MyInterceptor1 implements HandlerInterceptor {
    /**
     * 预处理，controller方法前执行
     * @param request
     * @param response
     * @param handler
     * @return
     *      true：放行，到下一个拦截器，没有下一个拦截器就去执行controller
     *      false：不放行，通过request，response对象跳转别的页面
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入拦截器啦。。11");
        return true;
        //request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request,response);
        //return false;
    }

    //后处理，controller方法后执行，跳转页面前执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
        System.out.println("离开拦截器啦。。11");
        //跳转到别的页面，但是seccess页面也会执行
        request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request,response);
    }

    //最后执行，在success.jso执行后执行，不能再控制跳转页面
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("拦截器最后的执行啦。。11");
    }
}
