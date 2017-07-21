package com.runoob.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * 处理用户注册信息的servlet
 */
@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");		
		ConnectDataBase c=new ConnectDataBase();
		String username,email,phone,password;
//		获取用户提交的注册信息
		username=request.getParameter("username");
		email=request.getParameter("email");
		phone=request.getParameter("phone");
		password=request.getParameter("password");
//		把获取到的信息提交给UserBean，再由UserBean传入数据库中				
		UserBean u=new UserBean(username,password,email,phone);
		
		c.executeADD(u.getUsername(),u.getPassword(), u.getEmail(), u.getPhone());
//		处理完成后返回注册成功页面
		response.sendRedirect("./success.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
