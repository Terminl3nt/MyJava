package com.ecube.sso.service;

import com.cube.common.utils.CubeResult;

public interface TokenService {

	CubeResult tokenForSession(String token);
}
