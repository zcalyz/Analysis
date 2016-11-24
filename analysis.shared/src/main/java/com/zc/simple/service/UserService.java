package com.zc.simple.service;

import com.zc.simple.model.User;

public interface UserService {
	/**
	 * 保存用户数据
	 */
	void save(User user);
	
	User getUserById(int id);
	
	User getUserByName(String name);
}
