package com.li.Controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.li.Model.ConnectDataBase;
import com.li.Model.UserBean;



@Controller
@SessionAttributes(value={"username","email","phone","birth","sex","address","personal","nickname"})
public class LandingController {
	
	@RequestMapping("/login")
	public String JumpToLogin() {
		return "login";
	}
	
	@RequestMapping("/SignIn")
	public String JumpToSign() {
		return "redirect:SignIn.jsp";
	}

	@RequestMapping("/Register")
	public String CheckReg(HttpServletResponse response ,@RequestParam("username") String username, @RequestParam("email") String email,
			@RequestParam("phone") String phone, @RequestParam("password") String password) {			
		try {
			ConnectDataBase c = new ConnectDataBase();
			UserBean u = new UserBean(username, password, email, phone);
			c.executeADD(u.getUsername(), u.getPassword(), u.getEmail(), u.getPhone());
			response.getWriter().print("注册成功！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:SignIn.jsp";
		
	}
	
	
	@RequestMapping("/sign")	
  public String CheckSign(Model model,HttpServletRequest request, HttpServletResponse response,@RequestParam("username") String username, @RequestParam("password") String password){	
		//获取并检查用户密码是否正确
		ConnectDataBase c = new ConnectDataBase();
		ResultSet result = c.select();			
		try {
			while (result.next()) {
				if (username.equals(result.getString("username")) && password.equals(result.getString("password"))) {	
					model.addAttribute("username",result.getString("username"));
					model.addAttribute("email",result.getString("email"));		
					model.addAttribute("phone",result.getString("phone"));		
					model.addAttribute("birth",result.getString("birth"));		
					model.addAttribute("sex",result.getString("sex"));	
					model.addAttribute("address",result.getString("address"));		
					model.addAttribute("personal",result.getString("personal"));
					model.addAttribute("nickname",result.getString("nickname"));
					return "personal";
				} else {					
					continue;
				}				
			}						
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}		
		//检查用户名密码错误时，应弹出错误提示窗口并跳转回原页面
		
		return "forward:SignIn.jsp";
  }
	

	/**
	 * 注销登陆
	 * @param request
	 * @param response
	 */
	@RequestMapping("/logout")
	public String logout(Model model){				
		return "forward:SignIn.jsp";
	}
	
	
	@RequestMapping("/CheckName")
	public void CheckName(@RequestParam("username") String username, HttpServletResponse response){
		//判断用户名是否存在，若是则返回错误信息
		response.setCharacterEncoding("utf-8");
		ConnectDataBase c = new ConnectDataBase();
		ResultSet result = c.select();
		boolean check=false;
		System.out.println(username);
		try {
			while(result.next()){
				if(username.equals(result.getString("username"))){
					response.getWriter().print("false");
					 check=true;
					 break;
				}else{
					continue;
				}
			}
			if(check==false){
				response.getWriter().print("true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
