package com.gznytm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gznytm.entity.User;
import com.gznytm.mapper.UserMapper;

@Component
public class UserService {
	@Autowired
	UserMapper userMapper;

	public User getUser(String userName, String password) {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		return userMapper.findUser(user);
	}

	public User getUserByUserName(String userName) {
		User user = new User();
		user.setUserName(userName);
		return userMapper.findUserByUserName(user);
	}

	public User getUserById(String userId) {
		return userMapper.findById(userId);
	}
}
