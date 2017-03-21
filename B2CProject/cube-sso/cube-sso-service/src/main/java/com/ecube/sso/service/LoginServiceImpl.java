package com.ecube.sso.service;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.ctc.wstx.util.StringUtil;
import com.cube.common.jedis.JedisClient;
import com.cube.common.utils.CubeResult;
import com.cube.common.utils.JsonUtils;
import com.ecube.mapper.TbUserMapper;
import com.ecube.pojo.TbUser;
import com.ecube.pojo.TbUserExample;
import com.ecube.pojo.TbUserExample.Criteria;

@Service
public class LoginServiceImpl  implements LoginService{

	@Autowired
	private TbUserMapper tbUserMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${SESSION_EXPIRE}")
	private int SESSION_EXPIRE;
	
	@Override
	public CubeResult LoginSignOn(String username, String password) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbUser> list = tbUserMapper.selectByExample(example);
		//通过返回的list是否为null来判断用户名是否输入正确
		if (list==null || list.size()==0) {
			return CubeResult.build(402, "账号姓名输入错误");
		}
		TbUser tbUser = list.get(0);
		//判断输入的密码是否正确
		if (StringUtils.isBlank(tbUser.getPassword()) ||!DigestUtils.md5DigestAsHex(password.getBytes()).equals(tbUser.getPassword())) {
			return CubeResult.build(402, "密码输入错误");
		}
		//登陆成功后生成token，Token相当于原来的jsessionid，字符串，可以使用uuid
		String token = UUID.randomUUID().toString();
		//把用户信息保存到redis，key即使token，value就是TbUser对象转换成json
			//避免把密码传递到表现层
			tbUser.setPassword(null);
		jedisClient.set("SESSION:" + token, JsonUtils.objectToJson(tbUser));
		//设置持久化时间
		jedisClient.expire("SESSION:" + token, SESSION_EXPIRE);
		return CubeResult.ok(token);
	}

}
