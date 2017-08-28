package com.zhiyou100.video.web.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiyou100.video.model.Admin;
import com.zhiyou100.video.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminService as;
	@RequestMapping("/login.action")
	public String login(Admin ad,Model md,HttpServletRequest req){
		
		String pwd = ad.getLoginPwd();
		String hex = DigestUtils.md5DigestAsHex(pwd.getBytes());
		ad.setLoginPwd(hex);
		String str = null;
		Admin admin = as.login(ad);
		if (admin != null) {
			req.getSession().setAttribute("admin", admin);
			str = "forward:/admin.jsp";
		}else{
			md.addAttribute("error","用户名或密码错误");
			str = "forward:/login.jsp";
		}
		return str;
	}
}
