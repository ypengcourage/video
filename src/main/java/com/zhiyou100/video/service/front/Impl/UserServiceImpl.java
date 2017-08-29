package com.zhiyou100.video.service.front.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.mapper.UserMapper;
import com.zhiyou100.video.model.Admin;
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
		um.updateByPrimaryKeySelective(uu);
	}
}
