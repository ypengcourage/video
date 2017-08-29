package com.zhiyou100.video.web.controller.front;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhiyou100.video.model.User;
import com.zhiyou100.video.model.UserVO;
import com.zhiyou100.video.service.front.UserService;
import com.zhiyou100.video.util.MD5Utils;

@Controller
public class UserController {
	@Autowired
	UserService us;
	
	@RequestMapping(value="/front/user/login.do",method=RequestMethod.POST)
	@ResponseBody
	public UserVO userLogin(User u,HttpSession session) {
		String email = u.getEmail();
		String password = u.getPassword();
		User uu = us.userLogin(email,password);
		UserVO vo = new UserVO();
		if(uu != null){
			session.setAttribute("_front_user", uu);
			vo.setSuccess(true);
		}else{
			vo.setMessage("邮箱或密码错误");
		}
		return vo;
	}
	
	@RequestMapping("/front/user/index.do")
	public String userInfo(Integer id,Model md){
		md.addAttribute("user",us.findUserById(id));
		return "/front/user/index";
	}
	
	@RequestMapping("/front/user/logout.do")
	public String userLogout(HttpServletRequest req){
		req.getSession().invalidate();
		return "redirect:/index.jsp";
	}
	
	
	@RequestMapping(value="/front/user/avatar.do",method=RequestMethod.GET)
	public String avatar(Integer id,Model md) {
		md.addAttribute("user",us.findUserById(id));
		return "/front/user/avatar";
	}
	@RequestMapping(value="/front/user/avatar.do",method=RequestMethod.POST)
	public String avatar(MultipartFile image_file,HttpSession session,Integer id,Model md) throws IllegalStateException, IOException{
		String filename = UUID.randomUUID().toString().replaceAll("-","")+"."+FilenameUtils.getExtension(image_file.getOriginalFilename());
		String path = "D://upload";
		image_file.transferTo(new File(path+"//"+filename));
		if(filename.length() != 32){
			User uu = (User) session.getAttribute("_front_user");
			uu.setHeadUrl("/pic/"+filename);
			us.updateUser(uu);
			session.setAttribute("_front_user", uu);
		}
		md.addAttribute("user",us.findUserById(id));
		return "redirect:/front/user/index.do?id="+id;
	}
	

	
	@RequestMapping(value="/front/user/profile.do",method=RequestMethod.GET)
	public String userProfile(Integer id,Model md) {
		md.addAttribute("user",us.findUserById(id));
		return "/front/user/profile";
	}
	
	@RequestMapping(value="/front/user/profile.do",method=RequestMethod.POST)
	public String userUpdate(User uu) {
		us.updateUser(uu);
		int a = uu.getId();
		return "redirect:/front/user/index.do?id="+a;
	}

	@RequestMapping(value="/front/user/password.do",method=RequestMethod.GET)
	public String userPassword(Integer id,Model md) {
		md.addAttribute("user",us.findUserById(id));
		return "/front/user/password";
	}
	@RequestMapping(value="/front/user/password.do",method=RequestMethod.POST)
	public String userPassword(Integer id,Model md,String oldPassword,String newPassword,HttpSession session) {
		String str= "" ;
		User user = us.findUserById(id);
		String password = user.getPassword();
		oldPassword = MD5Utils.getMD5(oldPassword);
		oldPassword = MD5Utils.getMD5(oldPassword);
		if(password.equals(oldPassword)){
			user.setPassword(newPassword);
			us.updateUser(user);
			session.invalidate();
			str = "redirect:/index.jsp";
		}else{
			md.addAttribute("cuocuocuo","密码错误");
			str = "/front/user/password";
		}
		md.addAttribute("user",user);
		return str;
	}
	
}
