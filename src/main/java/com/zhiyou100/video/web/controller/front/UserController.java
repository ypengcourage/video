package com.zhiyou100.video.web.controller.front;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
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
import com.zhiyou100.video.util.MailUtil;

@Controller
public class UserController {
	@Autowired
	UserService us;
	
	@RequestMapping(value="front/user/regist.do",method=RequestMethod.POST)
	@ResponseBody
	public UserVO userRegist(User uu) {
		int id = us.findIdByEmail(uu.getEmail());
		System.out.println(uu);
		UserVO vo = new UserVO();
		if(id == 0){
			us.addUser(uu);
			vo.setSuccess(true);
		}else{
			vo.setMessage("该邮箱已注册,可以在登录窗口点击忘记密码,重新设置登录密码");
		}
		return vo;
	}
	
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
			md.addAttribute("message","密码错误,修改未完成");
			str = "/front/user/password";
		}
		md.addAttribute("user",user);
		return str;
	}
	
	@RequestMapping(value="/front/user/forgetpwd.do",method=RequestMethod.GET)
	public String forgetpwd(){
		return "/front/user/forget_pwd";
	}
	
	@RequestMapping(value="/front/user/forgetpwd.do",method=RequestMethod.POST)
	public String forgetpwd(String captcha,String email,Model md){
		String str = "";
		String result = us.selectCaptcha(captcha,email);
		if(result.equals("success")){
			md.addAttribute("captcha",captcha);
			md.addAttribute("email",email);
			str = "/front/user/reset_pwd";
		}else{
			md.addAttribute("message","验证码错误,你可以重新发送");
			str ="/front/user/forget_pwd";
		}
		return str;
	}
	
	@RequestMapping("/front/user/sendemail.do")
	@ResponseBody
	public UserVO sendemail(String email) throws Exception{
	/*	Random ran = new Random();
		int a = ran.nextInt(90000) + 10000;
		String str = ""+a;
		User uu = us.findUserByEmail(email);
		UserVO vo = new UserVO();
		if(uu != null){
			MailUtil.send(email, "智游集团", "智游集团验证码:"+str+",不要告诉其他人哦!");
			uu.setCaptcha(str);
			us.updateUser(uu);
			vo.setSuccess(true);
		}else{
			vo.setMessage("这个邮箱没有注册哦,快去注册吧");
		}*/
		
		User uu = us.findUserByEmail(email);
		UserVO vo = new UserVO();
		if(uu != null){
			MailUtil.send(email, "智游集团", "智游集团验证码:"+uu.getCaptcha()+",不要告诉其他人哦!");
			vo.setSuccess(true);
		}else{
			vo.setMessage("这个邮箱没有注册哦,快去注册吧");
		}
		return vo;
	}
	
	@RequestMapping(value="/front/user/resetpwd.do",method=RequestMethod.POST)
	public String resetpwd(User uu){
		//System.out.println(uu);
		int id = us.findIdByEmail(uu.getEmail());
		uu.setId(id);
		us.updateUser(uu);
		return "redirect:/index.jsp";
	}
	
}
