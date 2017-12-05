package com.li.Controller;

import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.li.Model.UserBean;
import com.li.dao.DAO;
import com.li.daoImpl.DAOImpl;

/**
 * 
 * @author 2016 管理用户登录注册控制器
 */

@Controller
@SessionAttributes(value = { "username", "email", "phone", "birth", "sex", "address", "personal", "nickname" })
public class LandingController {

	private static DAO dao;

	// 到注册页面的跳转
	@RequestMapping("/login")
	public String JumpToLogin() {
		return "login";
	}

	// 到登录页面的跳转
	@RequestMapping("/SignIn")
	public String JumpToSign() {
		return "redirect:SignIn.jsp";
	}

	// 接收用户的注册信息，录入到数据库，跳转到注册成功页面
	@RequestMapping("/Register")
	public String CheckReg(Model model, HttpServletResponse response, @RequestParam("username") String username,
			@RequestParam("email") String email, @RequestParam("phone") String phone,
			@RequestParam("password") String password) {
		try {
			UserBean u = new UserBean(username, password, email, phone);
			dao = new DAOImpl();
			dao.executeADD(u.getUsername(), u.getPassword(), u.getEmail(), u.getPhone());
			model.addAttribute("username", username);
		} catch (NullPointerException e) {
			System.out.println("无法正常获取注册信息！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";

	}

	/*
	 * 旧方法检验登录及登出
	 */
	// @RequestMapping("/sign")
	// public String CheckSign(Model model,HttpServletRequest request,
	// HttpServletResponse response,@RequestParam("username") String username,
	// @RequestParam("password") String password){
	// //获取并检查用户密码是否正确
	// ConnectDataBase c = new ConnectDataBase();
	// ResultSet result = c.select();
	// try {
	// while (result.next()) {
	// if (username.equals(result.getString("username")) &&
	// password.equals(result.getString("password"))) {
	// model.addAttribute("username",result.getString("username"));
	// model.addAttribute("email",result.getString("email"));
	// model.addAttribute("phone",result.getString("phone"));
	// model.addAttribute("birth",result.getString("birth"));
	// model.addAttribute("sex",result.getString("sex"));
	// model.addAttribute("address",result.getString("address"));
	// model.addAttribute("personal",result.getString("personal"));
	// model.addAttribute("nickname",result.getString("nickname"));
	// return "personal";
	// } else {
	// continue;
	// }
	// }
	// } catch (SQLException e) {
	// // TODO 自动生成的 catch 块
	// e.printStackTrace();
	// }
	// //检查用户名密码错误时，应弹出错误提示窗口并跳转回原页面
	//
	// return "forward:SignIn.jsp";
	// }
	//
	// @RequestMapping("/logout")
	// public String logout(Model model){
	// return "forward:SignIn.jsp";
	// }

	// 利用Shiro框架验证用户身份
	@RequestMapping(value = "/sign")
	public String login(RedirectAttributes redirectAttributes, Model model, HttpServletRequest request,
			HttpServletResponse response, @RequestParam("username") String username,
			@RequestParam("password") String password) {
		dao = new DAOImpl();
		ResultSet result = dao.selectpersonal(username);
		String error = null;
		String message = "用户名或密码错误!";
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.login(token);
			while (result.next()) {
				// 用户验证通过时，获取当前用户的个人信息并跳转到个人信息管理页面
				model.addAttribute("username", result.getString("username"));
				model.addAttribute("email", result.getString("email"));
				model.addAttribute("phone", result.getString("phone"));
				model.addAttribute("birth", result.getString("birth"));
				model.addAttribute("sex", result.getString("sex"));
				model.addAttribute("address", result.getString("address"));
				model.addAttribute("personal", result.getString("personal"));
				model.addAttribute("nickname", result.getString("nickname"));
			}
			// 用户验证不通过时，抛出异常信息并返回错误信息到页面
		} catch (UnknownAccountException e) {
			error = "用户名错误";
			System.out.println(error);
			model.addAttribute("message", message);
			return "forward:SignIn.jsp";
		} catch (IncorrectCredentialsException e) {
			error = "密码错误";
			System.out.println(error);
			model.addAttribute("message", message);
			return "forward:SignIn.jsp";
		} catch (NullPointerException e) {
			System.out.println("无法正确获取用户登录信息！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "personal";
	}

	// 用户登出操作
	@RequestMapping(value = "/logout")
	public String logout(RedirectAttributes redirectAttributes) {
		SecurityUtils.getSubject().logout();
		return "forward:SignIn.jsp";
	}

	// 判断用户名是否存在
	@RequestMapping("/CheckName")
	public void CheckName(@RequestParam("username") String username, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		dao = new DAOImpl();
		ResultSet result = dao.select();
		boolean check = false;
		System.out.println(username);
		try {
			while (result.next()) {
				if (username.equals(result.getString("username"))) {
					response.getWriter().print("false");
					check = true;
					break;
				} else {
					continue;
				}
			}
			if (check == false) {
				response.getWriter().print("true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
