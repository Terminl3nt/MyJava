package com.ecube.sso.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cube.common.utils.CubeResult;
import com.cube.common.utils.JsonUtils;
import com.ecube.sso.service.TokenService;

@Controller
public class TokenController {

	@Autowired
	private TokenService tokenService;
	
	@RequestMapping(value = "/user/token/{token}", 
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE+"application/json;charset=utf-8")
	@ResponseBody
	public String  tokenForSession(@PathVariable String token , String callback){
		CubeResult cubeResult = tokenService.tokenForSession(token);
		//判断响应结果是否为jsonp请求
		if (StringUtils.isNotBlank(callback)) {
			//吧结果封装成js语句响应
			return callback + "(" +JsonUtils.objectToJson(cubeResult) +");";
		}
		return JsonUtils.objectToJson(cubeResult);
	}
}
