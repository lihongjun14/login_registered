<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<script language="JavaScript" type="text/JavaScript" src="js/jquery-1.6.1.js"></script>
<script language="JavaScript" type="text/JavaScript" src="js/jquery.validate.min.js"></script>
<script language="JavaScript" type="text/JavaScript" src="js/jquery.metadata.js"></script>
<script language="JavaScript" type="text/JavaScript" src="js/messages_cn.js"></script>
<script>
$().ready(function() {
    $("#sign").validate({
    	rules:{
    		username:{
    			required:true   			
    		},
    		password:{
    			required:true
    		}
    	},messages:{
    		username:{
    			required:"请输入用户名!"
    		},
    		password:{
    			required:"请输入密码！"
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
<div id="div" >
<div style="margin-top:0 auto;text-align: center;">
<h1>用户登录</h1>
<p>${message}
</div>
</div>
<hr>
<form name="sign" id="sign" action="sign" method="post">
<table align="center">
<tr>
<td>用户名: </td>
<td><input type="text" id="username" name="username" style="width:150px"/></td>
</tr>
<tr>
<td>密码: </td>
<td><input type="password" id="password" name="password" style="width:150px"/></td>
</tr>
<tr>
	</tr>
	<tr>
	<td colspan="2" align="center"><input type="submit" value="登录"/></td>
	</tr>
</table>
</form>
<div id="div" >
<div style="margin-top:0 auto;text-align: center;">
<a href="login"><button>注册</button></a>
</div>
</div>
</body>
</html>