<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<title>仿淘宝网的最近浏览记录功能</title> 
<meta http-equiv="content-type" content="text/html;charset=utf-8"> 
<script language="javascript"> 
//cookie相关函数 
function getCookieVal(offset){ 
var endstr = document.cookie.indexOf (";", offset); 
if (endstr == -1) endstr = document.cookie.length; 
return unescape(document.cookie.substring(offset, endstr)); 
} 
function getCookie(name){ 
var arg = name + "="; 
var alen = arg.length; 
var clen = document.cookie.length; 
var i = 0; 
while (i < clen) { 
var j = i + alen; 
if (document.cookie.substring(i, j) == arg) return getCookieVal (j); 
i = document.cookie.indexOf(" ", i) + 1; 
if (i == 0) break; 
} 
return null; 
} 
function setCookie(name,value){ 
var exp = new Date(); 
exp.setTime (exp.getTime()+3600000000); 
document.cookie = name + "=" + value + "; expires=" + exp.toGMTString(); 
} 
function glog(evt){ 
evt=evt?evt:window.event; 
var srcElem=(evt.target)?evt.target:evt.srcElement; 
try{ 
while(srcElem.parentNode&&srcElem!=srcElem.parentNode){ 
if(srcElem.tagName&&srcElem.tagName.toUpperCase()=="A"){ 
linkname=srcElem.innerHTML; 
address=srcElem.href+"|"; 
wlink=linkname+"+"+address; 
old_info=getCookie("history_info"); 
var insert=true; 
if(old_info==null){//判断cookie是否为空 
insert=true; 
} 
else{ 
var old_link=old_info.split("|"); 
for(var j=0;j<=5;j++){ 
if(old_link[j].indexOf(linkname)!=-1) 
insert=false; 
if(old_link[j]=="null") 
break; 
} 
} 
if(insert){ 
wlink+=getCookie("history_info"); 
setCookie("history_info",wlink); 
history_show().reload(); 
break; 
} 
else 
{ 


var old_link1=old_info.split("|"); 
var length=old_link1.length 
var newcookie='' 
for(var j=0;length<=6?j<=length-1:j<=5;j++){ 
if(old_link1[j].indexOf(linkname)==-1) 
{ 
if(j==length-1||j==5) 
{ 
newcookie=newcookie+old_link1[j] 
} 
else 
{ 
newcookie=newcookie+old_link1[j]+'|' 
} 
} 

} 
newcookie=wlink+newcookie 
setCookie("history_info",newcookie); 
history_show().reload(); 
break; 
} 
} 
srcElem = srcElem.parentNode; 
} 
} 
catch(e){} 
return true; 
} 
//document.onclick=glog; 
function history_show(){ 
var history_info=getCookie("history_info"); 
var content=""; 
if(history_info!=null){ 
history_arg=history_info.split("|"); 
var i; 
for(i=0;i<=5;i++){ 
if(history_arg[i]!="null"){ 
var wlink=history_arg[i].split("+"); 
content+=("<font color='#ff000'>↑</font>"+"<a href='"+wlink[1]+"' target='_blank'>"+wlink[0]+"</a><br>"); 
} 
document.getElementById("history").innerHTML=content; 
} 
} 
else{ 
document.getElementById("history").innerHTML="对不起，您没有任何浏览记录！"; 
} 
} 
</script> 
</head> 
<body> 
<div>您最近关注的内容（只显示6个最近关注的内容并且不会重复出现）：</div> 
<div id="history"> 
<script language="javascript">history_show();</script> 
</div> 
<div> 
<br><br>请选择：<br> 
<a href="http://www.baidu.com" target='_blank' onclick="glog()"><img src="C:\WINDOWS\system32\orange-install.ico" id="1"/></a> 
<a href="http://www.baidu.com" onclick="glog()"><img src="C:\WINDOWS\system32\orange-install.ico" id="2"/>PHP</a> 
<a href="http://www.baidu.com" onclick="glog()"><img src="C:\WINDOWS\system32\orange-install.ico" id="3"/>ASP.NET</a> 
<a href="http://www.baidu.com" onclick="glog()"><img src="C:\WINDOWS\system32\orange-install.ico" id="4"/>JSP</a> 
<a href="http://www.baidu.com" onclick="glog()"><img src="C:\WINDOWS\system32\orange-install.ico" id="5"/>C#</a> 
<a href="http://www.baidu.com" onclick="glog()"><img src="C:\WINDOWS\system32\orange-install.ico" id="6"/>VB</a> 
<a href="http://www.baidu.com" onclick="glog()"><img src="C:\WINDOWS\system32\orange-install.ico" id="7"/>VC</a> 
<a href="http://www.baidu.com" onclick="glog()"><img src="C:\WINDOWS\system32\orange-install.ico" id="8"/>AJAX</a> 
<a href="http://www.baidu.com" onclick="glog()"><img src="C:\WINDOWS\system32\orange-install.ico" id="9"/>DELPHI</a> 
</div> 
</body> 
</html> 
