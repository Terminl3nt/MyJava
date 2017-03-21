package com.ecube.sso.service;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cube.common.jedis.JedisClient;
import com.cube.common.utils.CubeResult;
import com.cube.common.utils.JsonUtils;
import com.ecube.pojo.TbUser;

@Service
public class TokenServiceImpl implements TokenService{

	@Autowired
	private JedisClient jedisClient;
	
	@Value("${SESSION_EXPIRE}")
	private int SESSION_EXPIRE;
	
	@Override
	public CubeResult tokenForSession(String token) {
		//查询用户信息
		//根据token到redis中获取用户信息
		String json = jedisClient.get("SESSION:" + token);
		//若取不到json，说明session已经过期
		if (StringUtils.isBlank(json)) {
			return CubeResult.build(400, "Session already timeout");
		}
		//获取更新的token，并且重置过期时间
		jedisClient.expire("SESSION:" + token, SESSION_EXPIRE);
		TbUser tbUser = JsonUtils.jsonToPojo(json, TbUser.class);
		
		return CubeResult.ok(tbUser);
	}

}
