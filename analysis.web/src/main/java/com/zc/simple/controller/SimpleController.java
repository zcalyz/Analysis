package com.zc.simple.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zc.simple.model.User;
import com.zc.simple.service.OrderService;
import com.zc.simple.service.UserService;

@Controller
@RequestMapping("/user")
public class SimpleController {
	@Resource
	private UserService userService;
	@Resource
	private OrderService orderService;
	
	private static Logger logger = LoggerFactory.getLogger(SimpleController.class);
	
	@RequestMapping("/signUp")
	public String signUp(User user, Model model){
		System.out.println(user);
		userService.save(user);
		return "saveSucce";
	}
	
	@RequestMapping("/getUserInfo")
	public String getUserInfoByName(String name, Model model){
		logger.error("error: " + name);
		System.out.println(name);
		User user = userService.getUserByName(name);
		
		String orderId = orderService.getOrderId();
		System.out.println("orderId: " + orderId);
		
		model.addAttribute("user", user);
		return "showUser";
	}
	
}
