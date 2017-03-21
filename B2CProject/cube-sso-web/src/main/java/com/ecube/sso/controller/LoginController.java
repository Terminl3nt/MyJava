package com.ecube.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cube.common.utils.CookieUtils;
import com.cube.common.utils.CubeResult;
import com.ecube.sso.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@Value("${TOKEN_KEY}")
	private String TOKEN_KEY;
	
	@RequestMapping("/page/login")
	public String loginPage(){
		return "login";
	}
	
	//登陆功能
	@RequestMapping(value="/user/login",method=RequestMethod.POST)
	@ResponseBody
	public CubeResult SignOn(String username , String password,HttpServletRequest request, HttpServletResponse response){
		CubeResult cubeResult = loginService.LoginSignOn(username, password);
		//判断是否登陆成功
		if (cubeResult.getStatus()==200) {
			String token  = cubeResult.getData().toString();
			//添加到cookie
			CookieUtils.setCookie(request, response,TOKEN_KEY , token);
		}
		return cubeResult;
	}
}
