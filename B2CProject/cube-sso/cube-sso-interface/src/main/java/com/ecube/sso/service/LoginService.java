package com.ecube.sso.service;

import com.cube.common.utils.CubeResult;

public interface LoginService {

	CubeResult LoginSignOn(String username , String password);
}
