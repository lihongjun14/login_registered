package com.runoob.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;


/**
 * 用于用户登录检验的servlet
 */
@SuppressWarnings("unused")
@WebServlet("/CheckServlet")
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
//		获取提交的用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean check=false;
		ConnectDataBase c = new ConnectDataBase();
//		遍历数据库，如果找到相应的用户名和密码则跳转到主页
		ResultSet result = c.select();	
		try {
			while (result.next()) {
				if (username.equals(result.getString(2)) && password.equals(result.getString(3))) {
					System.out.println("成功");
					//登录成功后把用户名保存到session中以便查询更改个人信息操作
					request.getSession().setAttribute("username",result.getString(2));					
					response.sendRedirect("personal.jsp");
					check=true;
					break;
				} else {
					continue;
				}
			}
//			没有找到相应的用户名和密码则跳转回原页面并弹出错误信息
			if(check==false){
//				JOptionPane.showMessageDialog(null, "用户名或密码错误！");
//				response.sendRedirect("./SignIn.jsp");
				request.setCharacterEncoding("utf-8");
				PrintWriter out =response.getWriter();
				String a=URLEncoder.encode("用户名或密码不正确！", "UTF-8");
				out.print("<script language='javascript'>alert(decodeURIComponent('"+a+"'));window.location.href='./SignIn.jsp';</script>");
			}

		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
