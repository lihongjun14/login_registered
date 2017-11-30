<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
</head>
<body>
<div id="div" >
<div style="margin-top:0 auto;text-align: center;">
<h1>用户个人信息</h1>
</div>
</div>
<hr>
<form name="Information" id="Information" action="Manage" method="post">
<table width="550" border="0" align="center">
<tr>
<td>用户名: </td>
<td>${username}</td>
</tr>
<tr>
<td>昵称: </td>
<td>${nickname}</td>
</tr>
<tr>
<td>电子邮箱: </td>
<td>${email}</td>
</tr>
<tr>
<td>手机号码: </td>
<td>${phone}</td>
</tr>
<tr>
<td>出生日期: </td>
<td>${birth}</td>
</tr>
<tr>
<td>性别: </td>
<td>${sex}</td>
</tr>
<tr>
<td>收货地址: </td>
<td>${address}</td>
</tr>
<tr>
<td>个人简介: </td>
<td>${personal}</td>
</tr>

	<tr>
	<td colspan="2" align="center"><input type="submit" value="修改个人信息"/></td>
	</tr>
</table>
</form>
<a href="logout"><button>注销</button></a>
<div id="div" >
<div style="margin-top:0 auto;text-align: center;">
</div>
</div>
</body>
</html>
