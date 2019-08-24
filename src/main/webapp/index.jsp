<%--
  Created by IntelliJ IDEA.
  User: sakura
  Date: 2019/8/20
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h5>喵内·首页index</h5>
    <a href="response.jsp">喵内去response页面</a>
    <hr>
    <b>喵内传统方式文件上传</b>
    <form action="/user/fileUpload1" method="post" enctype="multipart/form-data">
        选择文件：<input type="file"/><br>
        <input type="submit"value="上传">
    </form>
    <hr>
    <b>喵内springMVC方式文件上传</b>
    <form action="/user/fileUpload2" method="post" enctype="multipart/form-data">
        选择文件：<input type="file" name="file"/><br>
        <input type="submit"value="上传">
    </form>
    <hr>
    <b>喵内springMVC跨服务器方式文件上传</b>
    <form action="/user/fileUpload3" method="post" enctype="multipart/form-data">
        选择文件：<input type="file" name="file"/><br>
        <input type="submit"value="上传">
    </form>
    <hr>
    <a href="/user/exception">喵内异常惹</a>
    <hr>
    <a href="/user/testInterceptor">喵内拦截器惹</a>
</body>
</html>
