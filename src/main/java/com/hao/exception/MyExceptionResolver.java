package com.hao.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Describe com.hao.exception
 * @Auther wenhao chen
 * @CreateDate 2019/8/22
 * @Version 1.0
 * 异常处理类
 */
public class MyExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        //获取异常
        MyException ex = null;
        if(e instanceof MyException){
            ex = (MyException)e;
        }else {
            ex = new MyException("喵内的异常。。");
        }
        // 使用ModelAndView
        ModelAndView mv = new ModelAndView();
        mv.addObject("errorMessage",ex.getMessage());
        mv.setViewName("error");
        return mv;
    }
}
