package com.zhiyou100.video.service.front.Impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.mapper.UserMapper;
import com.zhiyou100.video.model.User;
import com.zhiyou100.video.model.UserExample;
import com.zhiyou100.video.service.front.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper um;
	@Override
	public User userLogin(String email, String password) {
		UserExample example = new UserExample();
		example.createCriteria().andEmailEqualTo(email).andPasswordEqualTo(password);
		List<User> list = um.selectByExample(example);
		User uu;
		if(list.size()==0){
			uu=null;
		}else{
			uu=list.get(0);
		}
		return uu;
	}

	@Override
	public void updateUser(User uu) {
		uu.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		um.updateByPrimaryKeySelective(uu);
	}

	@Override
	public User findUserById(Integer id) {
		// TODO Auto-generated method stub
		return um.selectByPrimaryKey(id);
	}

	@Override
	public void addUser(User uu) {
		uu.setInsertTime(new Timestamp(System.currentTimeMillis()));
		um.insertSelective(uu);
	}

	@Override
	public User findUserByEmail(String email) {
		UserExample example = new UserExample();
		example.createCriteria().andEmailEqualTo(email);
		List<User> list = um.selectByExample(example);
		User uu;
		if(list.size()==0){
			uu=null;
		}else{
			Random ran = new Random();
			int a = ran.nextInt(90000) + 10000;
			String str = ""+a;
			uu=list.get(0);
			uu.setCaptcha(str);
			um.updateByPrimaryKeySelective(uu);
		}
		return uu;
	}

	@Override
	public String selectCaptcha(String captcha, String email) {
		String str = "false";
		UserExample example = new UserExample();
		example.createCriteria().andEmailEqualTo(email).andCaptchaEqualTo(captcha);
		List<User> list = um.selectByExample(example);
		if(list.size()!=0){
			str = "success";
		}
		return str;
	}

	@Override
	public int findIdByEmail(String email) {
		UserExample example = new UserExample();
		example.createCriteria().andEmailEqualTo(email);
		List<User> list = um.selectByExample(example);
		int a = 0;
		if(list.size()!=0){
			User uu = list.get(0);
			a = uu.getId();
		}
		return a;
	}


/*	@Override
	public String selectAuth(Mail mail) {
		MailExample example = new MailExample();
		example.createCriteria().andAuthEqualTo(mail.getAuth()).andEmailEqualTo(mail.getEmail());
		List<Mail> list = mm.selectByExample(example);
		if(list.size() != 0){
			str = "success";
		}
		return str;
	}*/
}
