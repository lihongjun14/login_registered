package com.li.Controller;

import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.li.dao.DAO;
import com.li.daoImpl.DAOImpl;

/**
 * 个人信息页面控制器
 * @author 2016
 *
 */

@Controller
@SessionAttributes(value={"email","phone","birth","sex","address","personal","nickname"})
public class ManageController {
	
	private DAO dao;
	
	//跳转到修改个人信息页面
	@RequestMapping("/Manage")
	public String JumpToManage() {
		return "PersonalChange";
	}
	
	//将修改的个人信息更新至数据库
	@RequestMapping("/Change")
	public String ChangeInformation(HttpServletRequest request,@RequestParam("email") String email, @RequestParam("phone") String phone,
			@RequestParam("password") String password, @RequestParam("birth") String birth,
			@RequestParam("sex") String sex, @RequestParam("address") String address,
			@RequestParam("personal") String personal, @RequestParam("nickname") String nickname,Model model) {
		HttpSession session = request.getSession();
		String username=(String) session.getAttribute("username");
		dao=new DAOImpl();
		dao.executeUpdate(username, password, email, phone, birth, sex, address, personal,nickname);
		//完成后交给个人信息控制器处理后续
				return "forward:/Information";

	}
	
	//更新个人信息页面的资料
	@RequestMapping("/Information")
	public String Information(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String username=(String) session.getAttribute("username");
		dao=new DAOImpl();
		ResultSet result = dao.selectpersonal(username);
		try {
			while(result.next()) {
				model.addAttribute("email",result.getString("email"));		
				model.addAttribute("phone",result.getString("phone"));		
				model.addAttribute("birth",result.getString("birth"));		
				model.addAttribute("sex",result.getString("sex"));	
				model.addAttribute("address",result.getString("address"));		
				model.addAttribute("personal",result.getString("personal"));
				model.addAttribute("nickname",result.getString("nickname"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "personal";
	}
	
	
	
	
	
}
