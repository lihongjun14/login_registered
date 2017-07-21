package com.runoob.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * 用于处理用户修改个人信息的servlet
 */
@WebServlet("/ChangeInformation")
public class ChangeInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeInformation() {
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
		HttpSession session = request.getSession();
		String username,email,phone,password,birth,sex,address,personal,nickname;
		//获取表单中提交的修改信息
		username=(String) session.getAttribute("username");
		email=request.getParameter("email");
		phone=request.getParameter("phone");
		password=request.getParameter("password");
		birth=request.getParameter("birth");
		sex=request.getParameter("sex");
		address=request.getParameter("address");
		personal=request.getParameter("personal");
		nickname=request.getParameter("nickname");
		//传进数据库内并更新原数据				
		c.executeUpdate(username, password, email, phone, birth, sex, address, personal,nickname);
		//完成后返回到用户个人信息界面
		response.sendRedirect("./personal.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
