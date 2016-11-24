package com.zc.simple.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.simple.dao.UserDAO;
import com.zc.simple.model.User;
import com.zc.simple.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Resource
	private UserDAO userDAO;


	@Override
	public void save(User user) {
		int result = userDAO.insert(user);
		System.out.println("result is " + result);
	}

	@Override
	public User getUserById(int id) {
		User user = userDAO.selectByPrimaryKey(id);
		return user;
	}

	@Override
	public User getUserByName(String name) {
		return userDAO.selectByName(name);
	}

}
