package com.hao.controller;

import com.hao.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Describe com.hao.controller
 * @Auther wenhao chen
 * @CreateDate 2019/8/20
 * @Version 1.0
 */
@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping("/testString")
    public String testString(Model model){
        //模拟改动对象
        User user = new User();
        user.setName("kiana");
        user.setAge(17);
        // 将对象存入request域
        model.addAttribute("user",user);
        return "success";
    }

    /**
     * 使用servlet对象，手动跳转页面
     */
    @RequestMapping("/testVoid")
    public void testVoid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 转发，手动跳转，不经过视图解析器，手写完整路径
        //request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request,response);

        //重定向,不能进入pages里的页面
        //response.sendRedirect(request.getContextPath()+"/success.jsp");

        //直接响应到页面
            //设置中文编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print("喵内噶");
    }

    /**
     * 返回String，的底层也是通过该方法
     * @return
     */
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(){
        // 创建 ModelAndView 对象
        ModelAndView mv = new ModelAndView();
        // 模拟操作对象
        User user = new User();
        user.setName("mei");
        user.setAge(18);
        // user对象存入mv对象，也会存入request对象
        mv.addObject("user",user);
        // 控制跳转页面，会经过视图解析器，不需要写路径
        mv.setViewName("success");
        return mv;
    }

    @RequestMapping("/testForwardOrRedirect")
    public String testForwardOrRedirect(){
        System.out.println("testForwardOrRedirect执行了--==");
        //使用关键字，写法固定
        //return "forward:/WEB-INF/pages/success.jsp";

        //重定向，只能到根目录下，底层自动添加项目路径
        return "redirect:/index.jsp";
    }

    @RequestMapping("/testAjax")
    public @ResponseBody User testAjax(@RequestBody User user){
        // 客户端json字符串，jackson jar包自动封装到user对象
        System.out.println(user);
        // 模拟操作数据库
        user.setName("teriri");
        user.setAge(13);
        // ResponseBody 自动将对象转为json返回客户端
        return user;
    }
}
