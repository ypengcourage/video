package com.zhiyou100.video.web.controller.front;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhiyou100.video.model.User;
import com.zhiyou100.video.model.UserVO;
import com.zhiyou100.video.service.front.UserService;

@Controller
@RequestMapping("/front/user")
public class UserController {
	private static final String User = null;
	@Autowired
	UserService us;
	
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	@ResponseBody
	public UserVO userLogin(User u,HttpServletRequest req) {
		String email = u.getEmail();
		String password = u.getPassword();
		User uu = us.userLogin(email,password);
		UserVO vo = new UserVO();
		if(uu != null){
			req.getSession().setAttribute("_front_user", uu);
			vo.setSuccess(true);
		}else{
			vo.setMessage("邮箱或密码错误");
		}
		return vo;
	}
	
	@RequestMapping("/index.do")
	public String userInfo(){
		return "/front/user/avatar";
	}
	
	@RequestMapping("/logout.do")
	public String userLogout(HttpServletRequest req){
		req.getSession().invalidate();
		return "redirect:/index.jsp";
	}
	@RequestMapping("/avatar.do")
	public String avatar(MultipartFile image_file,HttpServletRequest req) throws IllegalStateException, IOException{
		String filename = UUID.randomUUID().toString().replaceAll("-","")+"."+FilenameUtils.getExtension(image_file.getOriginalFilename());
		String path = "D://upload";
		image_file.transferTo(new File(path+"//"+filename));
		if(filename.length() != 32){
			User uu = (User) req.getSession().getAttribute("_front_user");
			uu.setHeadUrl("/pic/"+filename);
			us.updateUser(uu);
			req.getSession().setAttribute("_front_user", uu);
		}
		return "/front/user/avatar";
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
}
