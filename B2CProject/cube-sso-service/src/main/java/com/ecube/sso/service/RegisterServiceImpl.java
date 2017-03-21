package com.ecube.sso.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.cube.common.utils.CubeResult;
import com.ecube.mapper.TbUserMapper;
import com.ecube.pojo.TbUser;
import com.ecube.pojo.TbUserExample;
import com.ecube.pojo.TbUserExample.Criteria;

@Service
public class RegisterServiceImpl implements RegisterService{

	@Autowired
	private TbUserMapper tbUserMapper;
	
	@Override
	public CubeResult registerPage(String param , int type) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(param) &&  type == 1 ) {
			criteria.andUsernameEqualTo(param);
		}else if (StringUtils.isNotBlank(param) && type == 2) {
			criteria.andPhoneEqualTo(param);
		}else if (StringUtils.isNotBlank(param) && type == 3) {
			criteria.andEmailEqualTo(param);
		}else {
			CubeResult.build(401, "非法的参数");
		}
		List<TbUser> list = tbUserMapper.selectByExample(example);
		if (list!=null && list.size()>0) {
			return CubeResult.ok(false);
		}
		return CubeResult.ok(true);
	}

	
	//进行注册操作
	@Override
	public CubeResult registerForLogin(TbUser user) {
		//注册进行数据校验
		if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())  || StringUtils.isBlank(user.getPhone())) {
			CubeResult.build(402, "表单数据输入有误");
		}
		CubeResult cubeResult = registerPage(user.getUsername(), 1);
		if (!(boolean) cubeResult.getData()) {
			CubeResult.build(401, "此用户已经存在");
		}
		CubeResult result = registerPage(user.getPhone(), 2);
		if (!(boolean) result.getData()) {
			CubeResult.build(401, "电话号码注册过");
		}
		//补全属性
		Date date = new Date();
		user.setCreated(date);
		user.setUpdated(date);
		String md5DigestAsHex = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(md5DigestAsHex);
		tbUserMapper.insert(user);
		return CubeResult.ok();
	}

	
	
}
