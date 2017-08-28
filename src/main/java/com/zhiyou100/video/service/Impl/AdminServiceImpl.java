package com.zhiyou100.video.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.mapper.AdminMapper;
import com.zhiyou100.video.model.Admin;
import com.zhiyou100.video.model.AdminExample;
import com.zhiyou100.video.service.AdminService;
@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	AdminMapper am;
	
	@Override
	public Admin login(Admin ad) {
		// TODO Auto-generated method stub
		AdminExample example = new AdminExample();
		example.createCriteria().andLoginNameEqualTo(ad.getLoginName()).andLoginPwdEqualTo(ad.getLoginPwd());
		List<Admin> list = am.selectByExample(example);
		Admin admin;
		if(list.size()==0){
			admin=null;
		}else{
			admin=list.get(0);
		}
		return admin;
	}
	
}
