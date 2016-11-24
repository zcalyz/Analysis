package com.zc.simple.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zc.simple.controller.SimpleController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring-mvc.xml"}) 
public class SimpleTest {
	@Resource
	private SimpleController simple;
	
	@Test
	public void testSignUp(){
		
	}
}
