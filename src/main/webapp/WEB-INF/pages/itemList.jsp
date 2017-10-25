<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base href="<%=basePath%>">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>查询商品列表</title>
    <script type="text/javascript" src="resource/jquery-3.2.1.js"></script>
</head>
<body>
<form action="item/queryItem" method="post">
    查询条件：
    <table width="100%" border=1>
        <tr>
            <td><input type="text" name="item_name" value="${keyword}"></td>
            <td><input type="submit" value="查询"/></td>
        </tr>
    </table>
</form>
<form method="post" id="form">
    商品列表：<input type="button" id="button1" value="批量删除"><input type="button" id="button2" value="批量修改">
    <table width="100%" border=1>
        <tr>
            <td>选择</td>
            <td>商品名称</td>
            <td>商品价格</td>
            <td>生产日期</td>
            <td>商品描述</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${itemList }" var="item" varStatus="status">
            <tr>
                <td><input type="checkbox" name="id" value="${item.id}"></td>
                <input type="hidden" name="itemList[${status.index}].id" value="${item.id}">
                <td><input type="text" name="itemList[${status.index}].name" value="${item.name}"></td>
                <td><input type="text" name="itemList[${status.index}].price" value="${item.price}"></td>
                <td><input type="text" name="itemList[${status.index}].createtime" value=<fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>></td>
                <td><input type="text" name="itemList[${status.index}].detail" value="${item.detail}"></td>
                <td><a href="item/itemEdit/${item.id}">修改</a></td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
<script type="text/javascript">
    $("#button1").click(function () {
            $("#form").attr("action","item/deleteBatch");
            $("#form").submit();
        }
    );
    $("#button2").click(function () {
            $("#form").attr("action","item/updateBatch");
            $("#form").submit();
        }
    );

</script>
</html>