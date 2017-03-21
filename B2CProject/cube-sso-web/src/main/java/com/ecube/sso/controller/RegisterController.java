package com.ecube.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cube.common.utils.CubeResult;
import com.ecube.pojo.TbUser;
import com.ecube.sso.service.RegisterService;

@Controller
public class RegisterController {

	@Autowired
	private RegisterService registerService;
	
	
	
	@RequestMapping("/page/register")
	public String showPage(){
		
		return "register";
	}
	
	@RequestMapping("/user/check/{param}/{type}")
	@ResponseBody
	public CubeResult registerPage(@PathVariable String param , @PathVariable int type){
		CubeResult cubeResult = registerService.registerPage(param, type);
		return cubeResult;
	}
	
	//进行注册操作
	@RequestMapping("/user/register")
	@ResponseBody
	public CubeResult registerForLogin(TbUser user){
		CubeResult cubeResult = registerService.registerForLogin(user);
		return cubeResult;
	}
	
}
