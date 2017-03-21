package com.ecube.sso.service;

import com.cube.common.utils.CubeResult;
import com.ecube.pojo.TbUser;

public interface RegisterService {

	//注册之前的表单校验
	CubeResult registerPage(String param , int type);
	//注册操作
	CubeResult registerForLogin(TbUser user);
}
