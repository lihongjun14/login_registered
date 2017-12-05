package com.li.Model;
/**
 * 存放一个用户信息的实体类
 * @author 2016
 *
 */
public class UserBean {
private String username;
private String password;
private String email;
private String phone;
public UserBean(String username,String password,String email,String phone){
	this.username=username;
	this.password=password;
	this.email=email;
	this.phone=phone;
}
public String getUsername() {
	return username;
}
public String getPassword() {
	return password;
}
public String getEmail() {
	return email;
}
public String getPhone() {
	return phone;
}

}
