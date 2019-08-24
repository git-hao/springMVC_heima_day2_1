package com.hao.controller;

import com.hao.domain.User;
import com.hao.exception.MyException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

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

    @RequestMapping("/fileUpload1")
    public String fileUpload1(HttpServletRequest request) throws Exception{
        //使用fileUpload组件上传文件
        //上传位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        //判断路径是否存在
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        System.out.println(path);
        //解析request对象，获取上传文件
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解析request
        List<FileItem> items = upload.parseRequest(request);
        for (FileItem item:items) {
            //判断是否是上传文件
            if(item.isFormField()){
                //说明是普通表单项
            }else {
                // 说明是上传文件
                // 获取并修改唯一文件名
                String filename = item.getName();
                String uuid = UUID.randomUUID().toString().replace("-", "");
                filename = uuid+"_"+filename;
                // 完成上传
                item.write(new File(path,filename));
                //删除临时文件
                item.delete();
            }
        }
        return "success";
    }

    /**
     * springMVC方式上传文件
     * @param request
     * @param upload   此参数名，必须和前端form表单name一致,或者注解绑定
     * @return
     * @throws Exception
     */
    @RequestMapping("/fileUpload2")
    public String fileUpload2(HttpServletRequest request, @RequestParam(value = "file") MultipartFile upload) throws Exception{
        //使用fileUpload组件上传文件
        //上传位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        //判断路径是否存在
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        System.out.println(path);
        String filename = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid+"_"+filename;
        //完成上传
        upload.transferTo(new File(path,filename));
        return "success";
    }

    @RequestMapping("/fileUpload3")
    public String fileUpload3(@RequestParam(value = "file") MultipartFile upload) throws Exception{
        //文件服务器路径
        String path = "http://localhost:9090/uploads/";

        String filename = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid+"_"+filename;
        //创建客户端对象
        Client client = Client.create();
        //获取服务器连接
        WebResource webResource = client.resource(path + upload);
        //文件上传,字节码形式
        webResource.put(upload.getBytes());
        return "success";
    }

    @RequestMapping("/exception")
    public String exception() throws MyException{
        System.out.println("异常执行");

        try {
            int i =1/0;
        } catch (Exception e) {
            e.printStackTrace();
            //使用自定义异常类
            throw new MyException("喵内异常啦");
        }
        return "success";
    }

    @RequestMapping("/testInterceptor")
    public String testInterceptor(){
        System.out.println("被拦截器的controller执行啦。。");
        return "success";
    }
}
