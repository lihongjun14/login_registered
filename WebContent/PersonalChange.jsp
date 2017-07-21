<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.runoob.test.ConnectDataBase"%>
    <%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改信息</title>
<script language="JavaScript" type="text/JavaScript" src="js/jquery-1.6.1.js"></script>
<script language="JavaScript" type="text/JavaScript" src="js/jquery.validate.min.js"></script>
<script language="JavaScript" type="text/JavaScript" src="js/jquery.metadata.js"></script>
<script language="JavaScript" type="text/JavaScript" src="js/messages_cn.js"></script>
<script>
//修改个人信息的规则，和注册差不多
$().ready(function() {
    $("#login").validate({
    	rules:{    		
    		email:{
    			required:true,
    			email:true
    		},
    		phone:{
    			required:true,
    			minlength:11
    		},
    		password:{
    			required:true,
    			minlength:6
    		},
    		repeatpassword:{
    			equalTo:"#password"
    			
    		}
    	},
    	messages:{   		
    		email:{
    			required:" * 邮箱地址不能为空！"
    		},
    		phone:{
    			required:" * 手机号码不能为空！",
    			minlength: $.format(" * 手机号长度不得小于11位！")
    		},
    		password:{
    			required:" * 用户密码不能为空！",
    			minlength: $.format(" * 密码长度不得小于6位！")
    		},
    		repeatpassword:{
    			equalTo:" * 两次密码输入不一致！"
    		}
    	}
    	
    	
    });
});

</script>
<style>
.error{
	color:red;
}
</style>
</head>
<body>
<%
//直接在数据库中查询当前用户的所有个人信息并输出
ConnectDataBase c=new ConnectDataBase();
ResultSet result =c.selectpersonal((String)request.getSession().getAttribute("username"));
%>
<div id="div" >
<div style="margin-top:0 auto;text-align: center;">
<h1>修改个人信息</h1>
</div>
</div>
<hr>
<form name="regForm" id="login" action="./ChangeInformation" method="post">
<table width="550" border="0" align="center">
<%
while(result.next()){
%>
<tr>
<td>用户名: </td>
<td><%=session.getAttribute("username") %></td>
</tr>
<tr>
<td>昵称: </td>
<td><input type="text" id="nickname" name="nickname" value=<%=result.getString(10) %> style="width:150px"/></td>
</tr>
<tr>
<td>电子邮箱: </td>
<td><input type="email" id="email" name="email" value=<%=result.getString(4) %> style="width:150px" /></td>
</tr>
<tr>
<td>手机号码: </td>
<td><input type="text" id="phone" name="phone" value=<%=result.getString(5) %> style="width:150px"/></td>
</tr>
<tr>
<td>密码: </td>
<td><input type="password" id="password" name="password" style="width:150px"/></td>
</tr>
<tr>
<td>确认密码: </td>
<td><input type="password" id="repeatpassword" name="repeatpassword" style="width:150px"/></td>
</tr>
<tr>
<td>出生日期: </td>
<td><input type="text" id="birth" name="birth" value=<%=result.getString(6) %> style="width:150px"/></td>
</tr>
<tr>
<td>收货地址: </td>
<td><input type="text" id="address" name="address" value=<%=result.getString(8) %> style="width:150px"/></td>
</tr>
<tr>
<td>性别: </td>
<td><input type="radio" id="sex" name="sex" value="男"/>男<input type="radio" id="sex" name="sex" value="女"/>女</td>
</tr>
<tr>
<td>个人简介: </td>
<td><input type="text" id="personal" name="personal" value=<%=result.getString(9) %>  style="height:50px;width:200px"/></td>
</tr>
<%
break;
}
%>
	<tr>
	<td colspan="2" align="center"><input type="submit" value="确认"/></td>
	</tr>
</table>
</form>
<a href="personal.jsp"><button>返回</button></a>
<div id="div" >
<div style="margin-top:0 auto;text-align: center;">
</div>
</div>
</body>
</html>
