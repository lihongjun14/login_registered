package com.li.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.li.Model.ConnectDataBase;



@Controller
@SessionAttributes(value={"email","phone","birth","sex","address","personal","nickname"})
public class ManageController {
	
	
	@RequestMapping("/Manage")
	public String JumpToManage() {
		return "PersonalChange";
	}

	@RequestMapping("/Change")
	public String ChangeInformation(HttpServletRequest request,@RequestParam("email") String email, @RequestParam("phone") String phone,
			@RequestParam("password") String password, @RequestParam("birth") String birth,
			@RequestParam("sex") String sex, @RequestParam("address") String address,
			@RequestParam("personal") String personal, @RequestParam("nickname") String nickname,Model model) {
		ConnectDataBase c=new ConnectDataBase();
		HttpSession session = request.getSession();
		String username=(String) session.getAttribute("username");
		c.executeUpdate(username, password, email, phone, birth, sex, address, personal,nickname);
		//完成后返回到用户个人信息界面
				return "forward:/Information";

	}
	
	@RequestMapping("/Information")
	public String Information(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String username=(String) session.getAttribute("username");
		ConnectDataBase c = new ConnectDataBase();
		ResultSet result = c.selectpersonal(username);
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "personal";
	}
	
	
	
	
	
}
