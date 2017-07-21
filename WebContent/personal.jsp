<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.runoob.test.ConnectDataBase"%>
    <%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
</head>
<body>
<%
//直接在数据库中查询当前用户的所有个人信息并输出
ConnectDataBase c=new ConnectDataBase();
ResultSet result =c.selectpersonal((String)request.getSession().getAttribute("username"));
%>
<div id="div" >
<div style="margin-top:0 auto;text-align: center;">
<h1>用户个人信息</h1>
</div>
</div>
<hr>
<form name="regForm" id="login" action="PersonalChange.jsp" method="post">
<table width="550" border="0" align="center">
<%
while(result.next()){
%>
<tr>
<td>用户名: </td>
<td><%=result.getString(2) %></td>
</tr>
<tr>
<td>昵称: </td>
<td><%=result.getString(10) %></td>
</tr>
<tr>
<td>电子邮箱: </td>
<td><%=result.getString(4) %></td>
</tr>
<tr>
<td>手机号码: </td>
<td><%=result.getString(5) %></td>
</tr>
<tr>
<td>出生日期: </td>
<td><%=result.getString(6) %></td>
</tr>
<tr>
<td>性别: </td>
<td><%=result.getString(7) %></td>
</tr>
<tr>
<td>收货地址: </td>
<td><%=result.getString(8) %></td>
</tr>
<tr>
<td>个人简介: </td>
<td><%=result.getString(9) %></td>
</tr>
<%
break;
}
%>
	<tr>
	<td colspan="2" align="center"><input type="submit" value="修改个人信息"/></td>
	</tr>
</table>
</form>
<form name="out" id="out" action="./Logout" method="post">
<input type="submit" value="注销"/>
</form>
<div id="div" >
<div style="margin-top:0 auto;text-align: center;">
</div>
</div>
</body>
</html>
