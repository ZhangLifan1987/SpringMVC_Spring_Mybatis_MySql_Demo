<%--
  Created by IntelliJ IDEA.
  User: ZhangLifan
  Date: 2017/10/25
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<base href="<%=basePath%>">
<head>
    <title>Login</title>
</head>
<body>
    <form action="user/loging" method="post">
        用户名: <input type="text" name="username"> <br>
        <input type="submit" value="登录">
    </form>
</body>
</html>
