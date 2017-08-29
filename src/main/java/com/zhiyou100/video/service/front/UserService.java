package com.zhiyou100.video.service.front;

import com.zhiyou100.video.model.User;

public interface UserService {

	User userLogin(String email, String password);

	void updateUser(User uu);

	User findUserById(Integer id);

}
