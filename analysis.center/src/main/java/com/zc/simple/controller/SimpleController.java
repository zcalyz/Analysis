package com.zc.simple.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zc.simple.model.User;
import com.zc.simple.service.UserService;

@Controller
@RequestMapping("/user")
public class SimpleController {
	@Resource
	private UserService userService;
	
	@RequestMapping("/signUp")
	public String signUp(User user, Model model){
		System.out.println(user);
		userService.save(user);
		return "saveSucce";
	}
	
	@RequestMapping("/getUserInfo")
	public String getUserInfoByName(String name, Model model){
		System.out.println(name);
		User user = userService.getUserByName(name);
		
		model.addAttribute("user", user);
		return "showUser";
	}
}
