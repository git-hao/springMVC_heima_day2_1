<%--
  Created by IntelliJ IDEA.
  User: sakura
  Date: 2019/8/20
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="js/jquery.min.js"></script>
    <script>
        $(function () {
            $("#btn").click(function () {
                $.ajax({
                    url:"user/testAjax",
                    type:"post",
                    data:'{"name":"fuhua","age":500000}',
                    contentType:"application/json;charset=utf-8",
                    dataType:"json",
                    success:(function (data) {
                        alert(data.name+"***"+data.age);

                    })
                })
            })
        })
    </script>

    <title>喵~内</title>

</head>
<body>

    <a href="user/testString">喵内测试返回字符串</a>
    <hr>
    <a href="user/testVoid">喵内测试没有返回值</a>
    <hr>
    <a href="user/testModelAndView">喵内测试modelAndView返回值</a>
    <hr>
    <a href="user/testForwardOrRedirect">喵内测试关键字转发或重定向</a>
    <hr>
    <button id="btn">喵内ajax</button>
</body>
</html>
